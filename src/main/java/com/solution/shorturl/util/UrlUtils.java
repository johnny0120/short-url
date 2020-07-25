package com.solution.shorturl.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.solution.shorturl.api.model.UrlShortenResult;
import com.solution.shorturl.repository.entity.UrlShortenEntity;
import com.solution.shorturl.service.dto.UrlShortenDTO;

public abstract class UrlUtils {

    protected static final List<String> SUPPORT_PROTOCOLS = Arrays.asList("http", "https");

    private UrlUtils() {
    }

    public static boolean isBlank(String text) {
        return StringUtils.isEmpty(text) || StringUtils.isEmpty(text.trim());
    }

    public static boolean isValidUrl(String text) {
        try {
            URL url = new URL(text);
            String protocal = url.getProtocol();
            if (!SUPPORT_PROTOCOLS.contains(protocal)) {
                return false;
            }
            url.toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public static String generateCodeFromUrl(String url) {
        byte[] digest = DigestUtils.md5Digest(url.getBytes(StandardCharsets.UTF_8));
        String md5Base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        return md5Base64.substring(0, 6);
    }

    public static String generateSecret() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.array());
    }

    public static UrlShortenDTO toUrlShortenDTO(UrlShortenEntity entity, boolean showSecret) {
        return UrlShortenDTO.builder()
                .withCode(entity.getCode())
                .withUrl(entity.getUrl())
                .withSecret(showSecret ? entity.getSecret() : null)
                .build();
    }

    public static UrlShortenResult toUrlShortenResult(UrlShortenDTO dto) {
        UrlShortenResult response = new UrlShortenResult();
        response.setCode(dto.getCode());
        response.setSecret(dto.getSecret());
        response.setUrl(dto.getUrl());
        return response;
    }

}
