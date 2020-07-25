package com.solution.shorturl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.shorturl.api.model.UrlShortenResult;
import com.solution.shorturl.api.model.UrlShortenSpec;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static Stream<Arguments> generateUrlParameters() {
        Random random = new Random();
        return IntStream.range(0, 1).mapToObj(i -> {
            boolean isHttps = random.nextBoolean();
            boolean hasSecret = random.nextBoolean();
            String domain = random.ints(1 + random.nextInt(5))
                    .mapToObj(s -> generateAlphanumericString(random, 2, 10))
                    .collect(Collectors.joining("."));
            String path = random.ints(1 + random.nextInt(5))
                    .mapToObj(s -> generateAlphanumericString(random, 10, 20))
                    .collect(Collectors.joining("/"));
            return Arguments.of((isHttps ? "http://" : "https://") + domain + "/" + path,
                    hasSecret ? generateAlphanumericString(random, 10, 32) : null);
        });
    }

    public static String generateAlphanumericString(Random random, int minLength, int maxLength) {
        return random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(random.nextInt(1 + maxLength - minLength) + minLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @ParameterizedTest(name = "#{index} - Test with Argument[url={0}, secret={1}]")
    @MethodSource("generateUrlParameters")
    void testShortenUrl_createReadDelete(String url, String secret) throws Exception {
        // create and success
        UrlShortenSpec requestBody = new UrlShortenSpec().url(url).secret(secret);
        assertThat(requestBody.toString()).isNotNull();

        MvcResult mvcResult = mockMvc
                .perform(post("/").contentType(MediaType.APPLICATION_JSON).content(serialize(requestBody)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        UrlShortenResult response = deserialize(mvcResult.getResponse().getContentAsString(), UrlShortenResult.class);
        assertThat(response.toString()).isNotNull();
        assertThat(response.getUrl()).isEqualTo(url);
        assertThat(response.getCode().length()).isEqualTo(6);
        assertThat(response.getSecret()).isNotBlank();
        assertThat(response.getSecret().equals(secret)).isNotEqualTo(StringUtils.isEmpty(secret));

        // read and found
        mockMvc.perform(get("/{code}", response.getCode())).andExpect(status().isFound()).andDo(print()).andReturn();

        // delete and success
        mockMvc.perform(delete("/{code}", response.getCode()).param("secret", response.getSecret()))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn();

        // read and not found
        mockMvc.perform(get("/{code}", response.getCode())).andExpect(status().isNotFound()).andDo(print()).andReturn();
    }

    @ParameterizedTest(name = "#{index} - Test with Argument[url={0}, secret={1}]")
    @MethodSource("generateUrlParameters")
    void testShortenUrl_invalidInput(String url, String secret) throws Exception {
        // create and fail
        UrlShortenSpec spec1 = new UrlShortenSpec().url(null).secret(secret);
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(serialize(spec1)))
                .andExpect(status().isBadRequest());
        UrlShortenSpec spec2 = new UrlShortenSpec().url("ftp://" + secret).secret(secret);
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(serialize(spec2)))
                .andExpect(status().isBadRequest());
        assertThat(spec1.equals(spec2)).isFalse();

        UrlShortenResult response = deserialize(mockMvc
                .perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(serialize(new UrlShortenSpec().url(url).secret(secret))))
                .andReturn()
                .getResponse()
                .getContentAsString(), UrlShortenResult.class);
        UrlShortenResult response2 = deserialize(mockMvc
                .perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(serialize(new UrlShortenSpec().url(url).secret(secret))))
                .andReturn()
                .getResponse()
                .getContentAsString(), UrlShortenResult.class);
        assertThat(response.equals(response2)).isNotEqualTo(StringUtils.isEmpty(secret));
        assertThat(response2.code(null).toString()).isNotEqualTo(new UrlShortenResult().toString());

        // read and fail
        mockMvc.perform(get("/{code}", " ")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/{code}", response.getCode().substring(1))).andExpect(status().isNotFound());

        // delete and success
        mockMvc.perform(delete("/{code}", " ").param("secret", response.getSecret()))
                .andExpect(status().isBadRequest());
        mockMvc.perform(delete("/{code}", response.getCode().substring(1)).param("secret", ""))
                .andExpect(status().isNoContent());
        mockMvc.perform(delete("/{code}", response.getCode()).param("secret", "")).andExpect(status().isUnauthorized());
    }

    private String serialize(Object value) throws Exception {
        return objectMapper.writeValueAsString(value);
    }

    private <T> T deserialize(String value, Class<T> type) throws Exception {
        return objectMapper.readValue(value, type);
    }

}
