CREATE TABLE `url_shorten`
(
    `id`                    CHAR(36)      NOT NULL COMMENT 'Unique identifier of record',
    `code`                  VARCHAR(22)   NOT NULL COMMENT 'Result code of the full url',
    `url`                 TEXT          NOT NULL COMMENT 'Full url from the request',
    `secret`                TEXT          NULL DEFAULT NULL COMMENT 'Secret code for update operation',
    PRIMARY KEY (`id`)
);

CREATE UNIQUE INDEX `url_shorten_uq_code` ON `url_shorten` (`code`);
