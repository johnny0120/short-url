package com.solution.shorturl.api.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UrlShortenResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-25T12:32:37.249+08:00[Asia/Shanghai]")
public class UrlShortenResult extends UrlShortenSpec {
    @JsonProperty("code")
    private String code = null;

    public UrlShortenResult code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Result code of the full url
     *
     * @return code
     **/
    @ApiModelProperty(required = true, value = "Result code of the full url")
    @NotNull

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlShortenResult urlShortenResult = (UrlShortenResult) o;
        return Objects.equals(this.code, urlShortenResult.code) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UrlShortenResult {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
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
