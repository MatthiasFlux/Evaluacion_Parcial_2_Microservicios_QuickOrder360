CREATE TABLE notificaciones (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      cliente_id BIGINT NOT NULL,
      mensaje TEXT NOT NULL,
      tipo VARCHAR(50) NOT NULL,
      fecha_envio DATETIME NOT NULL
);