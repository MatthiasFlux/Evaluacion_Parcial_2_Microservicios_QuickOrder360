# QuickOrder360 - Sistema de Microservicios

## 📋 Descripción del Proyecto

Sistema de microservicios distribuido diseñado para la gestión integral de un ecommerce, permitiendo administrar clientes, productos, inventario, pedidos, pagos y despachos. Implementa arquitectura basada en patrones modernos y comunicación REST entre servicios independientes.

---

## ✅ Requisitos Cumplidos

### Objetivo del Proyecto
- ✅ Arquitectura distribuida basada en microservicios
- ✅ Gestión de clientes, productos, inventario, pedidos, pagos y despachos
- ✅ Patrón CSR (Controller – Service – Repository)
- ✅ Persistencia real de datos en base de datos relacional
- ✅ Comunicación REST entre servicios

### Requisitos Técnicos
- ✅ Uso de Spring Boot
- ✅ Uso de JPA + Hibernate
- ✅ Persistencia real en base de datos relacional
- ✅ Separación por capas utilizando patrón CSR
- ✅ Uso de JpaRepository
- ✅ Operaciones CRUD completas implementadas
- ✅ Validaciones con Bean Validation (@NotNull, @Email, etc.)
- ✅ ResponseEntity en endpoints REST
- ✅ Manejo de excepciones mediante ControllerAdvice
- ✅ Logs con SLF4J
- ✅ Comunicación entre microservicios mediante REST
- ✅ Pruebas funcionales en Postman
- ✅ Repositorio GitHub grupal
- ✅ README.md con instrucciones de ejecución
- ✅ Documentación de endpoints

### Flujo del Sistema
- ✅ El microservicio de Pedidos consulta al de Clientes para validar existencia
- ✅ Verificación de disponibilidad de productos e inventario
- ✅ Registro de pedido, detalle del pedido, pago y despacho

---

## 🏗️ Arquitectura de Microservicios

### Microservicios Implementados

| Microservicio | Puerto | Responsabilidad |
|---|---|---|
| **ms-clientes** | 8001 | Gestión de clientes y validación |
| **ms-catalogo** | 8002 | Gestión de productos e inventario |
| **ms-pedidos** | 8003 | Gestión de pedidos y detalles |
| **ms-pagos** | 8004 | Procesamiento de pagos |
| **ms-despachos** | 8005 | Gestión de despachos |

---

## 🔌 Endpoints por Microservicio

### 1. MS-CLIENTES (Puerto 8001)

#### Obtener todos los clientes
```
GET /api/clientes
```
- **Respuesta:** Lista de clientes
- **Status:** 200 OK

#### Obtener cliente por ID
```
GET /api/clientes/{id}
```
- **Parámetro:** `id` (Long)
- **Respuesta:** Datos del cliente
- **Status:** 200 OK | 404 Not Found

#### Crear nuevo cliente
```
POST /api/clientes
```
- **Body:**
```json
{
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "telefono": "3104567890",
  "direccion": "Calle 123 #45-67",
  "ciudad": "Bogotá"
}
```
- **Validaciones:** Email válido, nombre no vacío
- **Status:** 201 Created

#### Actualizar cliente
```
PUT /api/clientes/{id}
```
- **Parámetro:** `id` (Long)
- **Body:** Datos a actualizar
- **Status:** 200 OK | 404 Not Found

#### Eliminar cliente
```
DELETE /api/clientes/{id}
```
- **Parámetro:** `id` (Long)
- **Status:** 204 No Content | 404 Not Found

#### Validar existencia de cliente
```
GET /api/clientes/validar/{id}
```
- **Parámetro:** `id` (Long)
- **Respuesta:** `{ "existe": true/false }`
- **Status:** 200 OK

---

### 2. MS-CATALOGO (Puerto 8002)

#### Obtener todos los productos
```
GET /api/productos
```
- **Respuesta:** Lista de productos
- **Status:** 200 OK

#### Obtener producto por ID
```
GET /api/productos/{id}
```
- **Parámetro:** `id` (Long)
- **Status:** 200 OK | 404 Not Found

#### Crear producto
```
POST /api/productos
```
- **Body:**
```json
{
  "nombre": "Laptop Dell",
  "descripcion": "Laptop de 15 pulgadas",
  "precio": 1299.99,
  "categoria": "Electrónica",
  "stock": 50
}
```
- **Validaciones:** Precio > 0, stock >= 0
- **Status:** 201 Created

#### Actualizar producto
```
PUT /api/productos/{id}
```
- **Parámetro:** `id` (Long)
- **Status:** 200 OK | 404 Not Found

#### Eliminar producto
```
DELETE /api/productos/{id}
```
- **Status:** 204 No Content | 404 Not Found

#### Obtener disponibilidad de producto
```
GET /api/productos/{id}/disponibilidad
```
- **Parámetro:** `id` (Long)
- **Respuesta:** `{ "disponible": true/false, "stock": 50 }`
- **Status:** 200 OK

#### Actualizar inventario
```
PATCH /api/productos/{id}/inventario
```
- **Body:**
```json
{
  "cantidad": -5
}
```
- **Status:** 200 OK

---

### 3. MS-PEDIDOS (Puerto 8003)

#### Obtener todos los pedidos
```
GET /api/pedidos
```
- **Status:** 200 OK

#### Obtener pedido por ID
```
GET /api/pedidos/{id}
```
- **Parámetro:** `id` (Long)
- **Status:** 200 OK | 404 Not Found

#### Crear pedido
```
POST /api/pedidos
```
- **Body:**
```json
{
  "clienteId": 1,
  "fecha": "2026-05-11",
  "total": 2599.98,
  "estado": "PENDIENTE"
}
```
- **Validaciones:**
  - Cliente debe existir (consulta a ms-clientes)
  - Total debe ser > 0
- **Status:** 201 Created

#### Obtener detalles del pedido
```
GET /api/pedidos/{pedidoId}/detalles
```
- **Parámetro:** `pedidoId` (Long)
- **Respuesta:** Lista de detalles del pedido
- **Status:** 200 OK

#### Agregar detalle a pedido
```
POST /api/pedidos/{pedidoId}/detalles
```
- **Body:**
```json
{
  "productoId": 1,
  "cantidad": 2,
  "precioUnitario": 1299.99
}
```
- **Validaciones:**
  - Producto debe existir
  - Stock debe ser suficiente (consulta a ms-catalogo)
  - Se descuenta automáticamente del inventario
- **Status:** 201 Created

#### Actualizar estado de pedido
```
PATCH /api/pedidos/{id}/estado
```
- **Body:**
```json
{
  "estado": "CONFIRMADO"
}
```
- **Estados válidos:** PENDIENTE, CONFIRMADO, EN_PREPARACION, ENVIADO, ENTREGADO, CANCELADO
- **Status:** 200 OK

#### Obtener pedidos por cliente
```
GET /api/pedidos/cliente/{clienteId}
```
- **Status:** 200 OK

---

### 4. MS-PAGOS (Puerto 8004)

#### Obtener todos los pagos
```
GET /api/pagos
```
- **Status:** 200 OK

#### Obtener pago por ID
```
GET /api/pagos/{id}
```
- **Status:** 200 OK | 404 Not Found

#### Crear pago
```
POST /api/pagos
```
- **Body:**
```json
{
  "pedidoId": 1,
  "monto": 2599.98,
  "metodoPago": "TARJETA_CREDITO",
  "referencia": "TXN123456"
}
```
- **Validaciones:**
  - Pedido debe existir (consulta a ms-pedidos)
  - Monto debe ser >= total del pedido
  - Método de pago válido
- **Status:** 201 Created

#### Procesar pago
```
POST /api/pagos/{id}/procesar
```
- **Validaciones:** Estado = PENDIENTE
- **Status:** 200 OK | 400 Bad Request

#### Verificar estado de pago
```
GET /api/pagos/{id}/estado
```
- **Respuesta:** `{ "estado": "APROBADO", "pedidoId": 1 }`
- **Status:** 200 OK

#### Obtener pagos por pedido
```
GET /api/pagos/pedido/{pedidoId}
```
- **Status:** 200 OK

---

### 5. MS-DESPACHOS (Puerto 8005)

#### Obtener todos los despachos
```
GET /api/despachos
```
- **Status:** 200 OK

#### Obtener despacho por ID
```
GET /api/despachos/{id}
```
- **Status:** 200 OK | 404 Not Found

#### Crear despacho
```
POST /api/despachos
```
- **Body:**
```json
{
  "pedidoId": 1,
  "numeroSeguimiento": "TRK123456789",
  "transportista": "Envios Express",
  "direccionEntrega": "Calle 123 #45-67"
}
```
- **Validaciones:**
  - Pedido debe existir
  - Pago asociado debe estar APROBADO
  - Pedido debe estar CONFIRMADO
- **Status:** 201 Created

#### Actualizar estado despacho
```
PATCH /api/despachos/{id}/estado
```
- **Body:**
```json
{
  "estado": "EN_TRANSITO"
}
```
- **Estados válidos:** PREPARACION, EN_TRANSITO, ENTREGADO, DEVUELTO
- **Status:** 200 OK

#### Rastrear despacho
```
GET /api/despachos/rastrear/{numeroSeguimiento}
```
- **Status:** 200 OK

#### Obtener despachos por pedido
```
GET /api/despachos/pedido/{pedidoId}
```
- **Status:** 200 OK

---

## 🚀 Instrucciones de Instalación y Ejecución

### Requisitos Previos
- **Java:** JDK 11 o superior
- **Maven:** 3.6 o superior
- **Base de Datos:** MySQL 5.7+ o PostgreSQL 10+
- **Git:** Última versión

### Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/MatthiasFlux/Evaluacion_Parcial_2_Microservicios_QuickOrder360.git
cd Evaluacion_Parcial_2_Microservicios_QuickOrder360
```

### Paso 2: Configurar Base de Datos
Crear bases de datos para cada microservicio:
```sql
CREATE DATABASE db_clientes;
CREATE DATABASE db_catalogo;
CREATE DATABASE db_pedidos;
CREATE DATABASE db_pagos;
CREATE DATABASE db_despachos;
```

### Paso 3: Configurar propiedades de cada microservicio

Editar `application.properties` en cada microservicio:

#### MS-CLIENTES (src/main/resources/application.properties)
```properties
server.port=8001
spring.application.name=ms-clientes
spring.datasource.url=jdbc:mysql://localhost:3306/db_clientes
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
logging.level.root=INFO
logging.level.com.quickorder360=DEBUG
```

#### MS-CATALOGO (src/main/resources/application.properties)
```properties
server.port=8002
spring.application.name=ms-catalogo
spring.datasource.url=jdbc:mysql://localhost:3306/db_catalogo
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
logging.level.root=INFO
logging.level.com.quickorder360=DEBUG
```

#### MS-PEDIDOS (src/main/resources/application.properties)
```properties
server.port=8003
spring.application.name=ms-pedidos
spring.datasource.url=jdbc:mysql://localhost:3306/db_pedidos
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
ms.clientes.url=http://localhost:8001/api/clientes
ms.catalogo.url=http://localhost:8002/api/productos
ms.pagos.url=http://localhost:8004/api/pagos
logging.level.root=INFO
logging.level.com.quickorder360=DEBUG
```

#### MS-PAGOS (src/main/resources/application.properties)
```properties
server.port=8004
spring.application.name=ms-pagos
spring.datasource.url=jdbc:mysql://localhost:3306/db_pagos
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
ms.pedidos.url=http://localhost:8003/api/pedidos
logging.level.root=INFO
logging.level.com.quickorder360=DEBUG
```

#### MS-DESPACHOS (src/main/resources/application.properties)
```properties
server.port=8005
spring.application.name=ms-despachos
spring.datasource.url=jdbc:mysql://localhost:3306/db_despachos
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
ms.pedidos.url=http://localhost:8003/api/pedidos
ms.pagos.url=http://localhost:8004/api/pagos
logging.level.root=INFO
logging.level.com.quickorder360=DEBUG
```

### Paso 4: Compilar cada microservicio
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

### Paso 5: Verificar que los servicios estén funcionando
```bash
# Terminal adicional
curl http://localhost:8001/api/clientes        # MS-Clientes
curl http://localhost:8002/api/productos       # MS-Catalogo
curl http://localhost:8003/api/pedidos         # MS-Pedidos
curl http://localhost:8004/api/pagos           # MS-Pagos
curl http://localhost:8005/api/despachos       # MS-Despachos
```

---

## 🧪 Pruebas con Postman

### Importar Colección
1. Abrir Postman
2. Ir a `File → Import`
3. Seleccionar archivo `QuickOrder360.postman_collection.json`

### Flujo de Prueba Recomendado

#### 1. Crear Cliente
```
POST http://localhost:8001/api/clientes
```

#### 2. Crear Producto
```
POST http://localhost:8002/api/productos
```

#### 3. Crear Pedido
```
POST http://localhost:8003/api/pedidos
```

#### 4. Agregar Detalle al Pedido
```
POST http://localhost:8003/api/pedidos/{pedidoId}/detalles
```

#### 5. Crear Pago
```
POST http://localhost:8004/api/pagos
```

#### 6. Procesar Pago
```
POST http://localhost:8004/api/pagos/{pagoId}/procesar
```

#### 7. Crear Despacho
```
POST http://localhost:8005/api/despachos
```

---

## 🏛️ Modelo Relacional

```
┌─────────────┐
│   CLIENTES  │
├─────────────┤
│ id (PK)     │
│ nombre      │
│ email       │
│ telefono    │
│ direccion   │
│ ciudad      │
└─────────────┘
         │
         │ 1:N
         │
    ┌────┴─────────────────┐
    │                      │
┌───▼───────────┐   ┌──────▼──────────┐
│    PEDIDOS    │   │  RECLAMOS (*)   │
├───────────────┤   ├─────────────────┤
│ id (PK)       │   │ id (PK)         │
│ clienteId(FK) │   │ pedidoId (FK)   │
│ fecha         │   │ descripcion     │
│ total         │   │ estado          │
│ estado        │   │ fecha_creacion  │
└───┬───────────┘   └─────────────────┘
    │
    │ 1:N
    │
    ▼
┌─────────────────────────┐
│  DETALLE_PEDIDOS        │
├─────────────────────────┤
│ id (PK)                 │
│ pedidoId (FK)           │
│ productoId (FK)         │
│ cantidad                │
│ precioUnitario          │
└────┬────────────────────┘
     │
     │ N:1
     │
     ▼
┌──────────────────┐
│    PRODUCTOS     │
├──────────────────┤
│ id (PK)          │
│ nombre           │
│ descripcion      │
│ precio           │
│ categoria        │
│ stock            │
└──────────────────┘

┌─────────────┐
│    PAGOS    │
├─────────────┤
│ id (PK)     │
│ pedidoId(FK)│
│ monto       │
│ metodoPago  │
│ estado      │
│ fecha       │
└─────────────┘

┌─────────────────┐
│   DESPACHOS     │
├─────────────────┤
│ id (PK)         │
│ pedidoId (FK)   │
│ numeroSeguimiento
│ transportista   │
│ estado          │
│ fechaEnvio      │
│ direccionEntrega
└─────────────────┘

(*) Reclamos: Tabla futura para gestión de reclamos asociados a pedidos
```

---

## 📋 Reglas de Negocio Implementadas

✅ **No se puede crear un pedido si el cliente no existe**
- Validación en ms-pedidos que consulta a ms-clientes

✅ **No se puede registrar un pedido sin stock disponible**
- Validación al agregar detalle de pedido, consulta a ms-catalogo

✅ **Un despacho solo puede generarse si el pago fue aprobado**
- Validación en ms-despachos que verifica estado de pago en ms-pagos

✅ **El inventario se descuenta automáticamente al confirmar un pedido**
- Al agregar detalle, se decrementa stock en ms-catalogo

✅ **El pago no puede ser inferior al total del pedido**
- Validación en ms-pagos

---

## 🛠️ Tecnologías Utilizadas

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
| Postman | Latest | Pruebas funcionales |

---

## 📦 Estructura de Carpetas

```
Evaluacion_Parcial_2_Microservicios_QuickOrder360/
├── ms-clientes/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/quickorder360/msclientes/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── entity/
│   │   │   │   ├── dto/
│   │   │   │   └── exception/
│   │   │   └── resources/application.properties
│   │   └── test/
│   └── pom.xml
├── ms-catalogo/
│   └── (Estructura similar)
├── ms-pedidos/
│   └── (Estructura similar)
├── ms-pagos/
│   └── (Estructura similar)
├── ms-despachos/
│   └── (Estructura similar)
├── README.md
└── QuickOrder360.postman_collection.json
```

---

## 🔐 Manejo de Excepciones

Todos los microservicios implementan manejo centralizado de excepciones mediante `@ControllerAdvice`:

```java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("Recurso no encontrado", e.getMessage()));
}

@ExceptionHandler(BusinessRuleException.class)
public ResponseEntity<?> handleBusinessRule(BusinessRuleException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse("Violación de regla de negocio", e.getMessage()));
}
```

---

## 📊 Logging

Se implementa logging con SLF4J en todos los niveles:
- **DEBUG:** Operaciones CRUD y validaciones
- **INFO:** Operaciones importantes y cambios de estado
- **WARN:** Situaciones anómalas pero controladas
- **ERROR:** Errores no controlados

Ejemplo:
```java
logger.debug("Validando existencia de cliente: {}", clienteId);
logger.info("Pedido creado exitosamente: {}", pedidoId);
logger.error("Error al procesar pago", exception);
```

---

## 📞 Contacto y Soporte

- **Desarrollador:** MatthiasFlux
- **Email:** matthias@quickorder360.com
- **Repositorio:** https://github.com/MatthiasFlux/Evaluacion_Parcial_2_Microservicios_QuickOrder360
- **Issues:** Reportar problemas en la sección de Issues del repositorio

---

## 📄 Licencia

Este proyecto es de uso educativo. Derechos reservados © 2026.

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:
1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

**Última actualización:** 11 de Mayo de 2026
