CREATE TABLE IF NOT EXISTS users (
                                       user_id    VARCHAR(36) PRIMARY KEY,
                                       name       VARCHAR(255) NOT NULL,
                                       email      VARCHAR(255) NOT NULL UNIQUE,
                                       password   VARCHAR(255) NOT NULL,
                                       token      VARCHAR(255),
                                       created    TIMESTAMP,
                                       modified   TIMESTAMP,
                                       last_login TIMESTAMP,
                                       isactive   BOOLEAN
);

CREATE TABLE IF NOT EXISTS phone (
                                     phone_id   VARCHAR(36) PRIMARY KEY,
                                     number     VARCHAR(255),
                                     citycode   VARCHAR(10),
                                     countrycode VARCHAR(10),
                                     user_id    VARCHAR(36) NOT NULL,
                                     CONSTRAINT fk_phone_user FOREIGN KEY (user_id) REFERENCES usuario(user_id) ON DELETE CASCADE
);
