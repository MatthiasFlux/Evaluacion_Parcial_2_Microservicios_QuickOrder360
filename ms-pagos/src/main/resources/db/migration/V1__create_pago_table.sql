CREATE TABLE pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    transaccion_id VARCHAR(255) NOT NULL UNIQUE,
    fecha_pago DATETIME NOT NULL
);