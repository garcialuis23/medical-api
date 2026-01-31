# 🏥 Medical API - Secure Management System

> **Sistema de Gestión de Pacientes Seguro y Auditoría para Entornos Sanitarios.**  
> *Prueba Técnica Backend - NaturalSoft Solutions*

Este proyecto es una API RESTful desarrollada con **Java 21** y **Spring Boot 3**, diseñada específicamente para cumplir con los altos estándares de seguridad y privacidad requeridos en el sector médico (GDPR/Compliance).

---

## 🚀 Características Principales (Key Features)

* **🔒 Seguridad Robusta (OWASP):** Implementación de **Spring Security** con autenticación **Stateless JWT** (JSON Web Tokens). Las contraseñas se almacenan hashadas con **BCrypt**.
* **🛡️ Privacidad de Datos (Data Masking):** Los endpoints de consulta devuelven datos sensibles (como el SSN) ofuscados automáticamente (`****-5678`) para proteger la privacidad del paciente.
* **👁️ Auditoría (Audit Trail):** Sistema de registro automático que guarda en base de datos **quién, cuándo y qué** acción se realizó, vital para trazabilidad en sanidad.
* **🐳 100% Dockerized:** Infraestructura como código. El proyecto incluye un **Multi-Stage Dockerfile** optimizado y orquestación con `docker-compose`.
* **📄 Documentación Viva:** Integración con **Swagger/OpenAPI** para pruebas interactivas y documentación automática.
* **✅ Calidad de Código:** Arquitectura limpia (Controller-Service-Repository), uso de DTOs para aislamiento del dominio y validación estricta de entradas (`@Valid`).

---

## 🛠️ Tech Stack

* **Lenguaje:** Java 21 (LTS)
* **Framework:** Spring Boot 3.5.x
* **Base de Datos:** PostgreSQL 15 (Containerized)
* **Seguridad:** Spring Security 6 + JJWT
* **DevOps:** Docker & Docker Compose
* **Docs:** SpringDoc OpenAPI (Swagger UI)
* **Herramientas:** Maven, Lombok

---

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura en capas diseñada para la escalabilidad y mantenibilidad:

```text
MEDICAL-API
│
├── src/main/java/com/naturalsoft/medical_api
│   ├── audit/             # Componentes específicos de auditoría
│   ├── config/            # Configuración de Seguridad (JWT), Swagger y CORS
│   ├── controller/        # Capa REST (Endpoints públicos y protegidos)
│   ├── dto/               # Objetos de Transferencia de Datos (Request/Response)
│   ├── exception/         # Manejador Global de Errores (ControllerAdvice)
│   ├── model/             # Entidades JPA (Tablas: Users, Patients, AuditLogs)
│   ├── repository/        # Interfaces de acceso a datos (Spring Data JPA)
│   ├── service/           # Lógica de negocio, validaciones y enmascaramiento
│   ├── util/              # Utilidades (Generación y validación de JWT)
│   └── MedicalApiApplication.java
│
├── Dockerfile             # Construcción Multi-Stage de la imagen Java
├── docker-compose.yml     # Orquestación de servicios (App + DB)
└── pom.xml                # Gestión de dependencias Maven
```

---

## 🚀 Despliegue Rápido (Quick Start)

> **No es necesario tener Java ni Maven instalados. Solo necesitas Docker.**

### 1️⃣ Clonar y Desplegar

Ejecuta el siguiente comando en la raíz del proyecto para levantar la Base de Datos y la API:

```bash
docker-compose up --build
```

El sistema realizará:

* ✅ Build limpio del proyecto
* ✅ Ejecución de tests
* ✅ Generación de la imagen Docker
* ✅ Levantamiento de los contenedores

### 2️⃣ Acceder a la Documentación

Una vez veas el mensaje:

```
Started MedicalApiApplication
```

Abre tu navegador en:

👉 **http://localhost:8080/swagger-ui.html**

---

## 🔐 Credenciales de Prueba

El sistema arranca con un usuario administrador pre-cargado para facilitar las pruebas.

| Rol   | Username | Password  | Permisos                    |
|-------|----------|-----------|----------------------------|
| Admin | admin    | admin123  | Crear, Leer, Auditoría     |

### 🧪 Pasos para probar en Swagger

1. Ve al endpoint `/auth/login`
2. Introduce las credenciales indicadas arriba y ejecuta
3. Copia el **Token** generado
4. Pulsa el botón **Authorize** 🔓 (arriba a la derecha)
5. Pega el token y confirma

✅ Ya puedes probar los endpoints protegidos como:
* `/api/patients`

---

## ⚙️ Notas de Desarrollo

* 🖥️ **Puerto API:** 8080
* 🗄️ **Puerto Base de Datos:** 5455  
  *(Expuesto externamente para conexión vía cliente SQL si es necesario)*
* 🐘 **Base de Datos:** PostgreSQL persistente mediante Volúmenes de Docker

---

*Desarrollado con ❤️ y mucho café para NaturalSoft Solutions* ☕