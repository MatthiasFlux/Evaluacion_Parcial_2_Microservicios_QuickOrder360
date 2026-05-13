CREATE TABLE pedido_detalles (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     pedido_id BIGINT NOT NULL,
     producto_id BIGINT NOT NULL,
     cantidad INT NOT NULL,
     precio_unitario DECIMAL(19, 2) NOT NULL
);
