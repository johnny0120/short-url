package com.solution.shorturl.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.solution.shorturl.repository.entity.UrlShortenEntity;

@Repository
@Transactional(value = Transactional.TxType.SUPPORTS)
public interface UrlShortenRepository extends JpaRepository<UrlShortenEntity, UUID> {

    Optional<UrlShortenEntity> findByCode(@NonNull String code);

}
