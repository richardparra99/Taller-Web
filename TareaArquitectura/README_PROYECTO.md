# To-Do List — Vertical Slice + Capas (SOLID)

## Requisitos
- Java 21; Spring Boot **4.1.1**; Maven Wrapper del proyecto original.
- PostgreSQL en localhost:5432 y base de datos `todo_arquitectura`.
- `pom.xml` preservado **sin cambios** (misma versión y mismas dependencias).

## Ejecutar en Windows / IntelliJ
1. Descomprimir, abrir la carpeta `TareaArquitectura` en IntelliJ; esperar a que Maven sincronice.
2. En DBeaver, conectado a `postgres`, ejecutar `CREATE DATABASE todo_arquitectura;`.
3. En `src/main/resources/application.properties`, personalizar la conexión o definir variables de entorno `DB_URL`, `DB_USER`, `DB_PASSWORD`. Por defecto utiliza postgres/postgres.
4. En terminal de IntelliJ ejecutar `./mvnw.cmd clean test` y luego `./mvnw.cmd spring-boot:run` (PowerShell: `.\mvnw.cmd clean test`, `.\mvnw.cmd spring-boot:run`).
5. Abrir `http://localhost:8080/` para la interfaz gráfica.

**Nota:** Con `spring.jpa.hibernate.ddl-auto=update`, Hibernate crea la tabla automáticamente. `database/optional_schema.sql` es una referencia opcional, NO es una migración Flyway (no se agregó la dependencia). No ejecutar ambas estrategias sobre diferentes esquemas sin revisarlas.

## Endpoints
| Método | URL | Función |
|---|---|---|
| POST | `/api/todos` | Crear (`{"title":"Leer","description":"20 páginas"}`) |
| GET | `/api/todos` | Listar |
| GET | `/api/todos/{id}` | Consultar por ID |
| PUT | `/api/todos/{id}` | Actualizar título/descripcion |
| PATCH | `/api/todos/{id}/complete` | Marcar completada |
| DELETE | `/api/todos/{id}` | Eliminar |

## Arquitectura
- **Slices:** `features/todo/{create,list,get,update,complete,delete}`. Cada slice contiene `presentation` y `application`.
- **Presentation** importa `Application` y DTO de respuesta, nunca `Infrastructure`.
- **Application** importa exclusivamente `Domain` y DTO de aplicación; jamás `Infrastructure` ni controllers.
- **Domain** contiene el modelo de negocio y el contrato `TodoRepository`; no depende de Spring/JPA.
- **Infrastructure** implementa `TodoRepository` con JPA y mapea entidades de persistencia.
- **Inyección de dependencias:** Spring conecta el contrato con el adaptador, no los handlers.
- **SRP:** un handler por caso de uso; **OCP/DIP:** repositorio abstracto; **LSP:** implementaciones deben respetar contrato; **ISP:** para un ejercicio pequeño se mantiene una interfaz, divisible cuando haga falta.

## Pruebas
`mvnw.cmd test` ejecuta pruebas unitarias de dominio, de handlers con repositorio en memoria y comprobación sencilla de imports entre capas. No requiere PostgreSQL para estas pruebas. La ejecución REST y la persistencia completa requieren PostgreSQL.

## Consideraciones
- La interfaz en `static/index.html` es opcional, usa solo HTML/CSS/JS y `fetch`, sin dependencias nuevas.
- API REST local sin autenticación; **no exponer a Internet** sin seguridad.
- Para producción conviene gestionar migraciones, pruebas integradas y credenciales seguras. No se agregaron dependencias por instrucción del usuario.
