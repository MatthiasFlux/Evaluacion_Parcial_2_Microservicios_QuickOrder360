# QuickOrder360 - Sistema de Microservicios

#### Descripción del Proyecto

<span style="font-size: 11px;">Sistema de microservicios distribuido diseñado para la gestión integral de un ecommerce con arquitectura escalable. Gestiona clientes, productos, inventario, pedidos, pagos y despachos aplicando patrones de diseño profesionales como CSR (Controller – Service – Repository). Implementa persistencia real de datos en base de datos relacional y comunicación REST entre servicios.</span>

---

## Requisitos Cumplidos

#### Objetivo del Proyecto

<span style="font-size: 11px;">
✅ Arquitectura distribuida basada en microservicios
✅ Gestión de clientes, productos, inventario, pedidos, pagos y despachos
✅ Patrón CSR (Controller – Service – Repository)
✅ Persistencia real de datos en base de datos relacional
✅ Comunicación REST entre servicios
</span>

#### Requisitos Técnicos Obligatorios

<span style="font-size: 11px;">
✅ Uso de Spring Boot
✅ Uso de JPA + Hibernate
✅ Persistencia real en base de datos relacional
✅ Separación por capas utilizando patrón CSR
✅ Uso de JpaRepository
✅ Operaciones CRUD completas implementadas
✅ Validaciones con Bean Validation (@NotNull, @Email, @Min, etc.)
✅ ResponseEntity en endpoints REST
✅ Manejo de excepciones mediante @ControllerAdvice
✅ Logs con SLF4J
✅ Comunicación entre microservicios mediante REST
✅ Pruebas funcionales usando Postman
✅ Repositorio GitHub público
✅ README.md con instrucciones de ejecución
✅ Documentación de endpoints
</span>

#### Flujo del Sistema Implementado

<span style="font-size: 11px;">
✅ El microservicio de Pedidos consulta al de Clientes para validar existencia del cliente
✅ Verificación de disponibilidad de productos e inventario
✅ Registro de pedido, detalle del pedido, pago y despacho correspondiente
</span>

---

## Arquitectura de Microservicios

#### Microservicios Implementados

<span style="font-size: 11px;">

| Microservicio | Puerto | Responsabilidad |
|---|---|---|
| **ms-clientes** | 8001 | Gestión de clientes y validación |
| **ms-catalogo** | 8002 | Gestión de productos e inventario |
| **ms-pedidos** | 8003 | Gestión de pedidos y detalles |
| **ms-pagos** | 8004 | Procesamiento de pagos |
| **ms-despachos** | 8005 | Gestión de despachos |
| **ms-usuarios** | 8006 | Gestión de usuarios |
| **ms-notificaciones** | 8007 | Envío de notificaciones |
| **ms-reclamos** | 8008 | Gestión de reclamos |
| **ms-inventario** | 8009 | Control de inventario |

</span>

---

## Endpoints por Microservicio

#### MS-CLIENTES (Puerto 8001)

<span style="font-size: 11px;">

**Obtener todos los clientes**
```
GET /api/clientes
```
Respuesta: Lista de clientes | Status: 200 OK

**Obtener cliente por ID**
```
GET /api/clientes/{id}
```
Parámetro: id (Long) | Status: 200 OK | 404 Not Found

**Crear nuevo cliente**
```
POST /api/clientes
```
Body:
```json
{
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "telefono": "3104567890",
  "direccion": "Calle 123 #45-67",
  "ciudad": "Bogotá"
}
```
Validaciones: Email válido, nombre no vacío | Status: 201 Created

**Actualizar cliente**
```
PUT /api/clientes/{id}
```
Status: 200 OK | 404 Not Found

**Eliminar cliente**
```
DELETE /api/clientes/{id}
```
Status: 204 No Content | 404 Not Found

**Validar existencia de cliente**
```
GET /api/clientes/validar/{id}
```
Respuesta: { "existe": true/false } | Status: 200 OK

</span>

#### MS-CATALOGO (Puerto 8002)

<span style="font-size: 11px;">

**Obtener todos los productos**
```
GET /api/productos
```
Status: 200 OK

**Obtener producto por ID**
```
GET /api/productos/{id}
```
Status: 200 OK | 404 Not Found

**Crear producto**
```
POST /api/productos
```
Body:
```json
{
  "nombre": "Laptop Dell",
  "descripcion": "Laptop de 15 pulgadas",
  "precio": 1299.99,
  "categoria": "Electrónica",
  "stock": 50
}
```
Validaciones: Precio > 0, stock >= 0 | Status: 201 Created

**Actualizar producto**
```
PUT /api/productos/{id}
```
Status: 200 OK | 404 Not Found

**Eliminar producto**
```
DELETE /api/productos/{id}
```
Status: 204 No Content | 404 Not Found

**Obtener disponibilidad de producto**
```
GET /api/productos/{id}/disponibilidad
```
Respuesta: { "disponible": true/false, "stock": 50 } | Status: 200 OK

**Actualizar inventario**
```
PATCH /api/productos/{id}/inventario
```
Body:
```json
{
  "cantidad": -5
}
```
Status: 200 OK

</span>

#### MS-PEDIDOS (Puerto 8003)

<span style="font-size: 11px;">

**Obtener todos los pedidos**
```
GET /api/pedidos
```
Status: 200 OK

**Obtener pedido por ID**
```
GET /api/pedidos/{id}
```
Status: 200 OK | 404 Not Found

**Crear pedido**
```
POST /api/pedidos
```
Body:
```json
{
  "clienteId": 1,
  "fecha": "2026-05-11",
  "total": 2599.98,
  "estado": "PENDIENTE"
}
```
Validaciones: Cliente debe existir, Total > 0 | Status: 201 Created

**Obtener detalles del pedido**
```
GET /api/pedidos/{pedidoId}/detalles
```
Status: 200 OK

**Agregar detalle a pedido**
```
POST /api/pedidos/{pedidoId}/detalles
```
Body:
```json
{
  "productoId": 1,
  "cantidad": 2,
  "precioUnitario": 1299.99
}
```
Validaciones: Producto existe, stock suficiente | Status: 201 Created

**Actualizar estado de pedido**
```
PATCH /api/pedidos/{id}/estado
```
Body:
```json
{
  "estado": "CONFIRMADO"
}
```
Estados válidos: PENDIENTE, CONFIRMADO, EN_PREPARACION, ENVIADO, ENTREGADO, CANCELADO | Status: 200 OK

**Obtener pedidos por cliente**
```
GET /api/pedidos/cliente/{clienteId}
```
Status: 200 OK

</span>

#### MS-PAGOS (Puerto 8004)

<span style="font-size: 11px;">

**Obtener todos los pagos**
```
GET /api/pagos
```
Status: 200 OK

**Obtener pago por ID**
```
GET /api/pagos/{id}
```
Status: 200 OK | 404 Not Found

**Crear pago**
```
POST /api/pagos
```
Body:
```json
{
  "pedidoId": 1,
  "monto": 2599.98,
  "metodoPago": "TARJETA_CREDITO",
  "referencia": "TXN123456"
}
```
Validaciones: Pedido existe, Monto >= total del pedido | Status: 201 Created

**Procesar pago**
```
POST /api/pagos/{id}/procesar
```
Validaciones: Estado = PENDIENTE | Status: 200 OK | 400 Bad Request

**Verificar estado de pago**
```
GET /api/pagos/{id}/estado
```
Respuesta: { "estado": "APROBADO", "pedidoId": 1 } | Status: 200 OK

**Obtener pagos por pedido**
```
GET /api/pagos/pedido/{pedidoId}
```
Status: 200 OK

</span>

#### MS-DESPACHOS (Puerto 8005)

<span style="font-size: 11px;">

**Obtener todos los despachos**
```
GET /api/despachos
```
Status: 200 OK

**Obtener despacho por ID**
```
GET /api/despachos/{id}
```
Status: 200 OK | 404 Not Found

**Crear despacho**
```
POST /api/despachos
```
Body:
```json
{
  "pedidoId": 1,
  "numeroSeguimiento": "TRK123456789",
  "transportista": "Envios Express",
  "direccionEntrega": "Calle 123 #45-67"
}
```
Validaciones: Pedido existe, Pago APROBADO, Pedido CONFIRMADO | Status: 201 Created

**Actualizar estado despacho**
```
PATCH /api/despachos/{id}/estado
```
Body:
```json
{
  "estado": "EN_TRANSITO"
}
```
Estados válidos: PREPARACION, EN_TRANSITO, ENTREGADO, DEVUELTO | Status: 200 OK

**Rastrear despacho**
```
GET /api/despachos/rastrear/{numeroSeguimiento}
```
Status: 200 OK

**Obtener despachos por pedido**
```
GET /api/despachos/pedido/{pedidoId}
```
Status: 200 OK

</span>

---

## Instrucciones de Instalación y Ejecución

#### Requisitos Previos

<span style="font-size: 11px;">
- Java: JDK 11 o superior
- Maven: 3.6 o superior
- Base de Datos: MySQL 5.7+ o PostgreSQL 10+
- Git: Última versión
</span>

#### Paso 1: Clonar el Repositorio

<span style="font-size: 11px;">
```bash
git clone https://github.com/MatthiasFlux/Evaluacion_Parcial_2_Microservicios_QuickOrder360.git
cd Evaluacion_Parcial_2_Microservicios_QuickOrder360
```
</span>

#### Paso 2: Configurar Base de Datos

<span style="font-size: 11px;">
```sql
CREATE DATABASE db_clientes;
CREATE DATABASE db_catalogo;
CREATE DATABASE db_pedidos;
CREATE DATABASE db_pagos;
CREATE DATABASE db_despachos;
```
</span>

#### Paso 3: Compilar y Ejecutar cada Microservicio

<span style="font-size: 11px;">
```bash
# MS-Clientes
cd ms-clientes
mvn clean install
mvn spring-boot:run

# En otra terminal - MS-Catalogo
cd ms-catalogo
mvn clean install
mvn spring-boot:run

# En otra terminal - MS-Pedidos
cd ms-pedidos
mvn clean install
mvn spring-boot:run

# En otra terminal - MS-Pagos
cd ms-pagos
mvn clean install
mvn spring-boot:run

# En otra terminal - MS-Despachos
cd ms-despachos
mvn clean install
mvn spring-boot:run
```
</span>

#### Paso 4: Verificar que los Servicios Estén Funcionando

<span style="font-size: 11px;">
```bash
curl http://localhost:8001/api/clientes
curl http://localhost:8002/api/productos
curl http://localhost:8003/api/pedidos
curl http://localhost:8004/api/pagos
curl http://localhost:8005/api/despachos
```
</span>

---

## Reglas de Negocio Implementadas

<span style="font-size: 11px;">

✅ **No se puede crear un pedido si el cliente no existe**
Validación en ms-pedidos que consulta a ms-clientes

✅ **No se puede registrar un pedido sin stock disponible**
Validación al agregar detalle de pedido, consulta a ms-catalogo

✅ **Un despacho solo puede generarse si el pago fue aprobado**
Validación en ms-despachos que verifica estado de pago en ms-pagos

✅ **El inventario se descuenta automáticamente al confirmar un pedido**
Al agregar detalle, se decrementa stock en ms-catalogo

✅ **El pago no puede ser inferior al total del pedido**
Validación en ms-pagos

</span>

---

## Tecnologías Utilizadas

<span style="font-size: 11px;">

| Tecnología | Versión | Propósito |
|---|---|---|
| Spring Boot | 2.7.x | Framework base |
| Spring Data JPA | 2.7.x | Acceso a datos |
| Hibernate | 5.6.x | ORM |
| MySQL | 5.7+ | Base de datos relacional |
| Maven | 3.6+ | Gestión de dependencias |
| SLF4J | Latest | Logging |
| Validation API | 2.0 | Bean Validation |
| RestTemplate | Spring | Comunicación entre servicios |

</span>

---

## Contacto

<span style="font-size: 11px;">

**Desarrollador:** MatthiasFlux
**Repositorio:** https://github.com/MatthiasFlux/Evaluacion_Parcial_2_Microservicios_QuickOrder360

</span>

---

**Última actualización:** 13 de Mayo de 2026
