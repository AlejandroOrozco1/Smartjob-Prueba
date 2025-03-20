CREATE TABLE IF NOT EXISTS usuarios (
                                       usuario_id    VARCHAR(36) PRIMARY KEY,
                                       nombre       VARCHAR(255) NOT NULL,
                                       email      VARCHAR(255) NOT NULL UNIQUE,
                                       password   VARCHAR(255) NOT NULL,
                                       token      VARCHAR(255),
                                       created    TIMESTAMP,
                                       modified   TIMESTAMP,
                                       last_login TIMESTAMP,
                                       isactive   BOOLEAN
);

CREATE TABLE IF NOT EXISTS telefono (
                                     telefono_id   VARCHAR(36) PRIMARY KEY,
                                     numero     VARCHAR(255),
                                     codigoCiudad   VARCHAR(10),
                                     codigoPais VARCHAR(10),
                                     usuario_id    VARCHAR(36) NOT NULL,
                                     CONSTRAINT fk_telefono_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id) ON DELETE CASCADE
);
