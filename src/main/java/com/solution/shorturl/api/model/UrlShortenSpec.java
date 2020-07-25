package com.solution.shorturl.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UrlShortenSpec
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-25T12:32:37.249+08:00[Asia/Shanghai]")
public class UrlShortenSpec {
    @JsonProperty("url")
    private String url = null;

    @JsonProperty("secret")
    private String secret = null;

    public UrlShortenSpec url(String url) {
        this.url = url;
        return this;
    }

    /**
     * Full url from the request
     *
     * @return url
     **/
    @ApiModelProperty(required = true, value = "Full url from the request")
    @NotNull

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UrlShortenSpec secret(String secret) {
        this.secret = secret;
        return this;
    }

    /**
     * Secret for delete operation, generated if empty
     *
     * @return secret
     **/
    @ApiModelProperty(value = "Secret for delete operation, generated if empty")

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlShortenSpec urlShortenSpec = (UrlShortenSpec) o;
        return Objects.equals(this.url, urlShortenSpec.url) && Objects.equals(this.secret, urlShortenSpec.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, secret);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UrlShortenSpec {\n");

        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    secret: ").append(toIndentedString(secret)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
