package com.solution.shorturl.service.dto;

import javax.annotation.Generated;

public class UrlShortenDTO {

    private final String code;

    private final String url;

    private final String secret;

    @Generated("SparkTools")
    private UrlShortenDTO(Builder builder) {
        this.code = builder.code;
        this.url = builder.url;
        this.secret = builder.secret;
    }

    public String getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }

    public String getSecret() {
        return secret;
    }

    /**
     * Creates builder to build {@link UrlShortenDTO}.
     *
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link UrlShortenDTO}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String code;
        private String url;
        private String secret;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public UrlShortenDTO build() {
            return new UrlShortenDTO(this);
        }
    }

}
