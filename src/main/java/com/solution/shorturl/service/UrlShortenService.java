package com.solution.shorturl.service;

import com.solution.shorturl.exception.ApplicationException;
import com.solution.shorturl.service.dto.UrlShortenDTO;

public interface UrlShortenService {

    UrlShortenDTO shortenUrl(String url, String secret) throws ApplicationException;

    String getUrl(String code) throws ApplicationException;

    void deleteUrl(String code, String secret) throws ApplicationException;

}
