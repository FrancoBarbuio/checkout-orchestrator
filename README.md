# 🛒 E-Commerce Checkout Orchestrator API

![Java 21](https://img.shields.io/badge/java_21-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring_boot_3-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Microservices](https://img.shields.io/badge/Microservices-8A2BE2?style=for-the-badge)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![JWT Security](https://img.shields.io/badge/Spring_Security_JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


API RESTful desarrollada en **Java 21** y **Spring Boot 3** que actúa como un orquestador central para procesos de compra (Checkout). Implementa el patrón de diseño de Microservicios, coordinando la comunicación con APIs externas de Pagos y Logística.

Este proyecto destaca por su enfoque en la **Tolerancia a Fallos (Fault Tolerance)** y resiliencia en redes inestables, utilizando el patrón **Circuit Breaker** para evitar fallas en cascada.

## 🚀 Arquitectura y Soluciones Técnicas

1. **Patrón Orquestador:** Centralización de la lógica de negocio coordinando múltiples servicios (Pagos y Envíos) para devolver una respuesta agregada al cliente.
2. **Clientes HTTP Declarativos (`Spring Cloud OpenFeign`):** Consumo de APIs externas de forma limpia y mantenible mediante interfaces, eliminando el código *boilerplate* de las conexiones HTTP.
3. **Resiliencia Granular (`Resilience4j - Circuit Breaker`):** Implementación de cortacircuitos independientes para las APIs de Pagos y Logística. Incluye mecanismos de *Fallback* (Plan de contingencia) para garantizar respuestas elegantes (`Graceful Degradation`) cuando los servicios de terceros caen.
4. **Manejo Centralizado de Excepciones (`@RestControllerAdvice`):** Intercepción global de errores de validación (`@Valid`) para devolver respuestas JSON estandarizadas y amigables para el cliente (HTTP 400 Bad Request).

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.4.0
* **Ecosistema Cloud:** Spring Cloud (OpenFeign, Resilience4j)
* **Validación:** Jakarta Bean Validation
* **Herramientas:** Maven, Lombok, Postman

## ⚙️ Instalación y Ejecución

### Opción 1: Usando Docker (Recomendado)
El proyecto está completamente contenedorizado. Solo necesitas Docker instalado:
```bash
   docker build -t checkout-orchestrator:1.0 .
   docker run -p 8082:8082 checkout-orchestrator:1.0
```
### Opción 2: Usando Maven (Local)
```bash
   ./mvnw clean package -DskipTests
    java -jar target/checkout-orchestrator-0.0.1-SNAPSHOT.jar
```
### 🧪 Endpoints y Seguridad (JWT)
La API está protegida con Spring Security y JSON Web Tokens (JWT). Todas las peticiones al Checkout requieren un token Bearer en las cabeceras.
#### 1. Obtener Token VIP (Login)
        POST /api/auth/login
```text
    URL Params: ?username=administrador&password=admin123
    Response: <tu_token_jwt_generado>
```
#### 2. Orquestar Compra (Protegido)
        POST /api/checkout
        Header: Authorization: Bearer <tu_token_jwt_generado>

Body:
```json
{
  "producto": "Notebook Gamer",
  "cantidad": 1,
  "numeroTarjeta": "1234-5678-9012-3456",
  "montoTotal": 1500.00,
  "direccionDestino": "Av. Colón 1234, Córdoba"
}
```
#### Nota: El proyecto está configurado actualmente con URLs de prueba para simular fallos de red y disparar los mecanismos de Circuit Breaker

## 👨‍💻 Autor

**Franco Barbuio**
* Rol: Junior Backend Developer (Java / Spring Boot)
* LinkedIn: https://www.linkedin.com/in/franco-barbuio/
* Email: franco@ejemplo.com

Desarrollado con enfoque en buenas prácticas, código limpio y escalabilidad.