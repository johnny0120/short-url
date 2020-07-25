package com.solution.shorturl.api;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.solution.shorturl.api.model.UrlShortenResult;
import com.solution.shorturl.api.model.UrlShortenSpec;
import com.solution.shorturl.exception.ApplicationException;
import com.solution.shorturl.exception.GlobalException;
import com.solution.shorturl.service.UrlShortenService;
import com.solution.shorturl.service.dto.UrlShortenDTO;
import com.solution.shorturl.util.UrlUtils;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-25T12:32:37.249+08:00[Asia/Shanghai]")
@Controller
public class ShortenUrlApiController implements ShortenUrlApi {

    @Autowired
    private UrlShortenService urlShortenService;

    @Override
    public ResponseEntity<UrlShortenResult> shortenUrl(@Valid UrlShortenSpec body) {
        try {
            UrlShortenDTO dto = urlShortenService.shortenUrl(body.getUrl(), body.getSecret());
            UrlShortenResult response = UrlUtils.toUrlShortenResult(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ApplicationException e) {
            throw new GlobalException(e);
        }
    }

    @Override
    public ResponseEntity<Void> getUrl(String code) {
        try {
            String url = urlShortenService.getUrl(code);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(URI.create(url));
            return new ResponseEntity<>(responseHeaders, HttpStatus.FOUND);
        } catch (ApplicationException e) {
            throw new GlobalException(e);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUrl(String code, @NotNull @Valid String secret) {
        try {
            urlShortenService.deleteUrl(code, secret);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ApplicationException e) {
            throw new GlobalException(e);
        }
    }

}
