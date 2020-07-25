package com.solution.shorturl.service;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.solution.shorturl.exception.ApplicationException;
import com.solution.shorturl.exception.ErrorModel;
import com.solution.shorturl.repository.UrlShortenRepository;
import com.solution.shorturl.repository.entity.UrlShortenEntity;
import com.solution.shorturl.service.dto.UrlShortenDTO;
import com.solution.shorturl.util.UrlUtils;

@Service
@Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = ApplicationException.class)
public class UrlShortenServiceImpl implements UrlShortenService {

    private final Logger log = LoggerFactory.getLogger(UrlShortenServiceImpl.class);

    @Autowired
    private UrlShortenRepository urlShortenRepository;

    @Override
    public UrlShortenDTO shortenUrl(String url, String secret) throws ApplicationException {
        // step-0 check and parse input
        log.debug("shortenUrl step-0 check and parse input...");
        if (!UrlUtils.isValidUrl(url)) {
            log.error("shortenUrl step-0 check and parse input: {}", ErrorModel.URL_INVALID);
            throw new ApplicationException(ErrorModel.URL_INVALID);
        }
        // step-1 check existence of record
        log.debug("shortenUrl step-1 check existence of record...");
        String code = UrlUtils.generateCodeFromUrl(url);
        Optional<UrlShortenEntity> queryResult = urlShortenRepository.findByCode(code);
        if (queryResult.isPresent()) {
            UrlShortenEntity dbEntity = queryResult.get();
            if (Objects.equals(url, dbEntity.getUrl())) {
                log.debug("shortenUrl step-1 check existence of record: found and return");
                return UrlUtils.toUrlShortenDTO(dbEntity, Objects.equals(secret, dbEntity.getSecret()));
            } else {
                log.error("shortenUrl step-1 check existence of record: {}", ErrorModel.CODE_TAKEN);
                throw new ApplicationException(ErrorModel.CODE_TAKEN);
            }
        }
        // step-2 create new record
        log.debug("shortenUrl step-2 create new record...");
        UrlShortenEntity newEntity = new UrlShortenEntity();
        newEntity.setCode(code);
        newEntity.setUrl(url);
        newEntity.setSecret(StringUtils.isEmpty(secret) ? UrlUtils.generateSecret() : secret);
        newEntity = urlShortenRepository.save(newEntity);
        log.debug("shortenUrl step-2 create new record: done");
        return UrlUtils.toUrlShortenDTO(newEntity, true);
    }

    @Override
    public String getUrl(String code) throws ApplicationException {
        // step-0 check and parse input
        log.debug("getUrl step-0 check and parse input...");
        if (UrlUtils.isBlank(code)) {
            log.error("getUrl step-0 check and parse input: {}", ErrorModel.CODE_INVALID);
            throw new ApplicationException(ErrorModel.CODE_INVALID);
        }
        // step-1 check existence of record
        log.debug("getUrl step-1 check existence of record...");
        Optional<UrlShortenEntity> queryResult = urlShortenRepository.findByCode(code);
        if (!queryResult.isPresent()) {
            log.error("getUrl step-1 check existence of record: {}", ErrorModel.CODE_NOT_FOUND);
            throw new ApplicationException(ErrorModel.CODE_NOT_FOUND);
        }
        // step-2 get url and return
        log.debug("getUrl step-2 get url and return...");
        String url = queryResult.get().getUrl();
        log.debug("getUrl step-2 get url and return: done");
        return url;
    }

    @Override
    public void deleteUrl(String code, String secret) throws ApplicationException {
        // step-0 check and parse input
        log.debug("deleteUrl step-0 check and parse input...");
        if (UrlUtils.isBlank(code)) {
            log.error("deleteUrl step-0 check and parse input: {}", ErrorModel.CODE_INVALID);
            throw new ApplicationException(ErrorModel.CODE_INVALID);
        }
        // step-1 check existence of record
        log.debug("deleteUrl step-1 check existence of record...");
        Optional<UrlShortenEntity> queryResult = urlShortenRepository.findByCode(code);
        if (queryResult.isPresent()) {
            UrlShortenEntity dbEntity = queryResult.get();
            if (Objects.equals(secret, dbEntity.getSecret())) {
                urlShortenRepository.delete(dbEntity);
                log.debug("deleteUrl step-1 check existence of record: found and delete");
            } else {
                log.error("deleteUrl step-1 check existence of record: {}", ErrorModel.SECRET_WRONG);
                throw new ApplicationException(ErrorModel.SECRET_WRONG);
            }
        }
    }

}
