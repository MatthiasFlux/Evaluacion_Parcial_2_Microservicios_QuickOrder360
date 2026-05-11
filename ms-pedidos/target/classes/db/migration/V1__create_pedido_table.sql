CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    fecha_creacion DATETIME NOT NULL,
    estado VARCHAR(50) NOT NULL,
    total DECIMAL(10,2) NOT NULL

);
