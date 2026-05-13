CREATE TABLE reclamos (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      pedido_id BIGINT NOT NULL,
      cliente_id BIGINT NOT NULL,
      descripcion VARCHAR(500) NOT NULL,
      estado VARCHAR(50) NOT NULL,
      fecha_creacion DATETIME NOT NULL
);