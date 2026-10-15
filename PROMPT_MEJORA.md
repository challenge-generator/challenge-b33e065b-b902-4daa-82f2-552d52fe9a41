# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `docker/Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `docker/docker-compose.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/deployment.yaml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/service.yaml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/ingress.yaml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/hpa.yaml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/banco/servicio/Application.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/domain/port/TransaccionRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/infrastructure/repository/JpaTransaccionRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/infrastructure/repository/JpaTransaccionRepository.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/application/service/TransaccionService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/application/service/TransaccionService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/infrastructure/controller/TransaccionController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/infrastructure/controller/TransaccionController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/servicio/application/service/TransaccionService.java` — `Transaccion.cuentaOrigen`: Se invoca `cuentaOrigen` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/servicio/application/service/TransaccionService.java` — `Transaccion.cuentaDestino`: Se invoca `cuentaDestino` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/servicio/application/service/TransaccionService.java` — `Transaccion.monto`: Se invoca `monto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/servicio/application/service/TransaccionService.java` — `Transaccion.tipo`: Se invoca `tipo` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Implementa la contenerización y orquestación de contenedores en proyectos, teniendo en cuenta las diferencias, ventajas y desventajas de cada enfoque. Utiliza al menos dos herramientas de contenerización/orquestación, como Docker, Docker Compose, Docker Swarm, K8s (Kubernetes) u OpenShift.

### Misión / candidato
Candidato con experiencia como Desarrollador Backend Senior en Java

### Reto
- Tema: Implementa Plataformas de Contenedores enfocados en Docker y Kubernetes
- Seniority: senior-l2
- Tipo: mixed
- Título: Diseño y Orquestación de Contenedores
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y Selección de Herramientas — objetivo: Identificar y seleccionar las herramientas adecuadas para contenerización y orquestación. — entregable (NO resolver): Documento que describe las herramientas seleccionadas y la justificación de la elección.
- Fase 2: Configuración y Despliegue de Contenedores — objetivo: Configurar y desplegar servicios en contenedores usando las herramientas seleccionadas. — entregable (NO resolver): Configuración de Docker y despliegue de servicios en Kubernetes.
- Fase 3: Validación y Optimización — objetivo: Validar el despliegue y optimizar la configuración para mejorar el rendimiento y la escalabilidad. — entregable (NO resolver): Reporte de validación y optimización del despliegue de servicios en contenedores.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.banco</groupId>
    <artifactId>servicio</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>servicio</name>
    <description>Microservicio reactivo para gestión de transacciones bancarias</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
            <version>42.7.3</version>
        </dependency>

        <!-- Reactor -->
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
            <version>3.6.8</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
            <version>3.6.8</version>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <scope>test</scope>
            <version>1.20.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/banco/servicio/Application.java ===
package com.banco.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configuración básica de rutas para health check y métricas.
     * Esto permite verificar el estado del servicio durante el despliegue en contenedores.
     */
    @Bean
    RouterFunction<ServerResponse> actuatorRoutes() {
        return route(RequestPredicates.GET("/actuator/health"),
            request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("{\"status\":\"UP\"}"), String.class))
            .andRoute(RequestPredicates.GET("/actuator/info"),
                request -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just("{\"app\":\"servicio-transacciones\"}"), String.class));
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: servicio-transacciones
  profiles:
    active: dev
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/banco_transacciones
    username: banco_user
    password: banco_password
    pool:
      enabled: true
      initial-size: 10
      max-size: 20
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
  webflux:
    base-path: /api/v1

server:
  port: 8080
  shutdown: graceful

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      probes:
        enabled: true
      group:
        readiness:
          include: db
        liveness:
          include: ping
  metrics:
    export:
      prometheus:
        enabled: true

logging:
  level:
    org.springframework: INFO
    com.banco: DEBUG
    io.r2dbc: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"

---

spring.config.activate.on-profile: dev
spring:
  r2dbc:
    url: r2dbc:postgresql://postgres:5432/banco_transacciones
    username: banco_user
    password: banco_password

---

spring.config.activate.on-profile: prod
spring:
  r2dbc:
    url: r2dbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
    pool:
      max-size: 50
  jpa:
    hibernate:
      ddl-auto: validate

management:
  endpoint:
    health:
      show-details: always

// === ARCHIVO: src/main/java/com/banco/servicio/domain/model/Transaccion.java ===
package com.banco.servicio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaccion(
    UUID id,
    String cuentaOrigen,
    String cuentaDestino,
    BigDecimal monto,
    LocalDateTime fecha,
    String tipo,
    String estado,
    String descripcion) {

    public Transaccion {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new IllegalArgumentException("La cuenta de origen no puede ser nula o vacía");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new IllegalArgumentException("La cuenta de destino no puede ser nula o vacía");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo o vacío");
        }
        if (estado == null || estado.isBlank()) {
            estado = "PENDIENTE";
        }
    }

    public Transaccion withEstado(String nuevoEstado) {
        return new Transaccion(
            this.id,
            this.cuentaOrigen,
            this.cuentaDestino,
            this.monto,
            this.fecha,
            this.tipo,
            nuevoEstado,
            this.descripcion
        );
    }
}

// === ARCHIVO: src/main/java/com/banco/servicio/domain/port/TransaccionRepository.java ===
package com.banco.servicio.domain.port;

import com.banco.servicio.domain.model.Transaccion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface TransaccionRepository {
    Mono<Transaccion> save(Transaccion transaccion);
    Mono<Transaccion> findById(UUID id);
    Flux<Transaccion> findByCuentaOrigen(String cuentaOrigen);
    Flux<Transaccion> findByCuentaDestino(String cuentaDestino);
    Flux<Transaccion> findByTipo(String tipo);
    Flux<Transaccion> findByEstado(String estado);
    Flux<Transaccion> findAll();
    Mono<Void> deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/banco/servicio/infrastructure/repository/JpaTransaccionRepository.java ===
package com.banco.servicio.infrastructure.repository;

import com.banco.servicio.domain.model.Transaccion;
import com.banco.servicio.domain.port.TransaccionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface JpaTransaccionRepository extends JpaRepository<TransaccionEntity, UUID>, TransaccionRepository {

    @Override
    default Mono<Transaccion> save(Transaccion transaccion) {
        return Mono.fromCallable(() -> {
            TransaccionEntity entity = TransaccionEntity.fromDomain(transaccion);
            TransaccionEntity savedEntity = save(entity);
            return savedEntity.toDomain();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    default Mono<Transaccion> findById(UUID id) {
        return Mono.fromCallable(() -> findById(id).orElse(null))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(entity -> entity != null ? Mono.just(entity.toDomain()) : Mono.empty());
    }

    @Override
    default Flux<Transaccion> findByCuentaOrigen(String cuentaOrigen) {
        return Flux.fromIterable(findByCuentaOrigenInternal(cuentaOrigen))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findByCuentaDestino(String cuentaDestino) {
        return Flux.fromIterable(findByCuentaDestinoInternal(cuentaDestino))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findByTipo(String tipo) {
        return Flux.fromIterable(findByTipoInternal(tipo))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findByEstado(String estado) {
        return Flux.fromIterable(findByEstadoInternal(estado))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findAll() {
        return Flux.fromIterable(findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Mono<Void> deleteById(UUID id) {
        return Mono.fromRunnable(() -> deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    // Métodos internos para JPA
    default TransaccionEntity save(TransaccionEntity entity) {
        return save(entity);
    }

    default java.util.Optional<TransaccionEntity> findById(UUID id) {
        return findById(id);
    }

    default java.util.List<TransaccionEntity> findByCuentaOrigenInternal(String cuentaOrigen) {
        return findAll().stream()
                .filter(t -> cuentaOrigen.equals(t.getCuentaOrigen()))
                .collect(Collectors.toList());
    }

    default java.util.List<TransaccionEntity> findByCuentaDestinoInternal(String cuentaDestino) {
        return findAll().stream()
                .filter(t -> cuentaDestino.equals(t.getCuentaDestino()))
                .collect(Collectors.toList());
    }

    default java.util.List<TransaccionEntity> findByTipoInternal(String tipo) {
        return findAll().stream()
                .filter(t -> tipo.equals(t.getTipo()))
                .collect(Collectors.toList());
    }

    default java.util.List<TransaccionEntity> findByEstadoInternal(String estado) {
        return findAll().stream()
                .filter(t -> estado.equals(t.getEstado()))
                .collect(Collectors.toList());
    }
}

// === ARCHIVO: src/main/java/com/banco/servicio/application/service/TransaccionService.java ===
package com.banco.servicio.application.service;

import com.banco.servicio.domain.model.Transaccion;
import com.banco.servicio.domain.port.TransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransaccionService {

    private static final Logger log = LoggerFactory.getLogger(TransaccionService.class);
    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public Mono<Transaccion> crearTransaccion(Transaccion transaccion) {
        if (transaccion == null) {
            return Mono.error(new IllegalArgumentException("La transacción no puede ser nula"));
        }
        if (transaccion.cuentaOrigen() == null || transaccion.cuentaOrigen().isBlank()) {
            return Mono.error(new IllegalArgumentException("La cuenta de origen es obligatoria"));
        }
        if (transaccion.cuentaDestino() == null || transaccion.cuentaDestino().isBlank()) {
            return Mono.error(new IllegalArgumentException("La cuenta de destino es obligatoria"));
        }
        if (transaccion.cuentaOrigen().equals(transaccion.cuentaDestino())) {
            return Mono.error(new IllegalArgumentException("La cuenta de origen y destino no pueden ser iguales"));
        }
        if (transaccion.monto() == null || transaccion.monto().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new IllegalArgumentException("El monto debe ser mayor a cero"));
        }

        Transaccion transaccionNueva = new Transaccion(
            UUID.randomUUID(),
            transaccion.cuentaOrigen(),
            transaccion.cuentaDestino(),
            transaccion.monto(),
            transaccion.tipo() != null ? transaccion.tipo() : "TRANSFERENCIA",
            "PENDIENTE",
            LocalDateTime.now(),
            null
        );

        log.info("Creando transacción: {} -> {}, monto: {}", 
            transaccionNueva.cuentaOrigen(), 
            transaccionNueva.cuentaDestino(), 
            transaccionNueva.monto());

        return transaccionRepository.save(transaccionNueva)
            .doOnSuccess(t -> log.info("Transacción creada exitosamente con ID: {}", t.id()))
            .doOnError(e -> log.error("Error al crear transacción: {}", e.getMessage()));
    }

    public Mono<Transaccion> obtenerTransaccionPorId(UUID id) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("El ID no puede ser nulo"));
        }
        log.info("Buscando transacción por ID: {}", id);
        return transaccionRepository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Transacción no encontrada con ID: " + id)));
    }

    public Flux<Transaccion> listarTodasLasTransacciones() {
        log.info("Listando todas las transacciones");
        return transaccionRepository.findAll();
    }

    public Flux<Transaccion> listarTransaccionesPorCuentaOrigen(String cuentaOrigen) {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            return Flux.error(new IllegalArgumentException("La cuenta de origen es obligatoria"));
        }
        log.info("Listando transacciones por cuenta de origen: {}", cuentaOrigen);
        return transaccionRepository.findByCuentaOrigen(cuentaOrigen);
    }

    public Flux<Transaccion> listarTransaccionesPorCuentaDestino(String cuentaDestino) {
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            return Flux.error(new IllegalArgumentException("La cuenta de destino es obligatoria"));
        }
        log.info("Listando transacciones por cuenta de destino: {}", cuentaDestino);
        return transaccionRepository.findByCuentaDestino(cuentaDestino);
    }

    public Flux<Transaccion> listarTransaccionesPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            return Flux.error(new IllegalArgumentException("El tipo de transacción es obligatorio"));
        }
        log.info("Listando transacciones por tipo: {}", tipo);
        return transaccionRepository.findByTipo(tipo.toUpperCase());
    }

    public Flux<Transaccion> listarTransaccionesPorEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            return Flux.error(new IllegalArgumentException("El estado es obligatorio"));
        }
        log.info("Listando transacciones por estado: {}", estado);
        return transaccionRepository.findByEstado(estado.toUpperCase());
    }

    public Mono<Transaccion> actualizarEstadoTransaccion(UUID id, String nuevoEstado) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("El ID no puede ser nulo"));
        }
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            return Mono.error(new IllegalArgumentException("El nuevo estado es obligatorio"));
        }

        String estadoNormalizado = nuevoEstado.toUpperCase();
        if (!esEstadoValido(estadoNormalizado)) {
            return Mono.error(new IllegalArgumentException("Estado inválido. Estados válidos: PENDIENTE, PROCESADA, RECHAZADA, COMPLETADA"));
        }

        log.info("Actualizando estado de transacción {} a {}", id, estadoNormalizado);

        return transaccionRepository.findById(id)
            .flatMap(transaccionExistente -> {
                Transaccion transaccionActualizada = transaccionExistente.withEstado(estadoNormalizado);
                return transaccionRepository.save(transaccionActualizada);
            })
            .switchIfEmpty(Mono.error(new RuntimeException("Transacción no encontrada con ID: " + id)))
            .doOnSuccess(t -> log.info("Estado actualizado exitosamente a: {}", t.estado()))
            .doOnError(e -> log.error("Error al actualizar estado: {}", e.getMessage()));
    }

    public Mono<Void> eliminarTransaccion(UUID id) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("El ID no puede ser nulo"));
        }
        log.info("Eliminando transacción con ID: {}", id);
        return transaccionRepository.findById(id)
            .flatMap(transaccionExistente -> transaccionRepository.deleteById(id)
                .doOnSuccess(v -> log.info("Transacción eliminada exitosamente: {}", id))
                .doOnError(e -> log.error("Error al eliminar transacción: {}", e.getMessage())))
            .switchIfEmpty(Mono.error(new RuntimeException("Transacción no encontrada con ID: " + id)));
    }

    private boolean esEstadoValido(String estado) {
        return estado.equals("PENDIENTE") || 
               estado.equals("PROCESADA") || 
               estado.equals("RECHAZADA") || 
               estado.equals("COMPLETADA");
    }
}

// === ARCHIVO: src/main/java/com/banco/servicio/infrastructure/controller/TransaccionController.java ===
package com.banco.servicio.infrastructure.controller;

import com.banco.servicio.application.service.TransaccionService;
import com.banco.servicio.domain.model.Transaccion;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping
    public Mono<ResponseEntity<Transaccion>> crearTransaccion(@Valid @RequestBody Transaccion transaccion) {
        log.info("Petición POST para crear transacción");
        return transaccionService.crearTransaccion(transaccion)
            .map(transaccionCreada -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(transaccionCreada))
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación al crear transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.badRequest().build());
            })
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Error al crear transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Transaccion>> obtenerTransaccionPorId(@PathVariable UUID id) {
        log.info("Petición GET para obtener transacción con ID: {}", id);
        return transaccionService.obtenerTransaccionPorId(id)
            .map(transaccion -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(transaccion))
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Transacción no encontrada: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }

    @GetMapping
    public Flux<Transaccion> listarTodasLasTransacciones() {
        log.info("Petición GET para listar todas las transacciones");
        return transaccionService.listarTodasLasTransacciones();
    }

    @GetMapping(params = "cuentaOrigen")
    public Flux<Transaccion> listarTransaccionesPorCuentaOrigen(@RequestParam String cuentaOrigen) {
        log.info("Petición GET para listar transacciones por cuenta origen: {}", cuentaOrigen);
        return transaccionService.listarTransaccionesPorCuentaOrigen(cuentaOrigen)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @GetMapping(params = "cuentaDestino")
    public Flux<Transaccion> listarTransaccionesPorCuentaDestino(@RequestParam String cuentaDestino) {
        log.info("Petición GET para listar transacciones por cuenta destino: {}", cuentaDestino);
        return transaccionService.listarTransaccionesPorCuentaDestino(cuentaDestino)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @GetMapping(params = "tipo")
    public Flux<Transaccion> listarTransaccionesPorTipo(@RequestParam String tipo) {
        log.info("Petición GET para listar transacciones por tipo: {}", tipo);
        return transaccionService.listarTransaccionesPorTipo(tipo)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @GetMapping(params = "estado")
    public Flux<Transaccion> listarTransaccionesPorEstado(@RequestParam String estado) {
        log.info("Petición GET para listar transacciones por estado: {}", estado);
        return transaccionService.listarTransaccionesPorEstado(estado)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @PatchMapping("/{id}/estado")
    public Mono<ResponseEntity<Transaccion>> actualizarEstadoTransaccion(
            @PathVariable UUID id, 
            @RequestParam String nuevoEstado) {
        log.info("Petición PATCH para actualizar estado de transacción {} a {}", id, nuevoEstado);
        return transaccionService.actualizarEstadoTransaccion(id, nuevoEstado)
            .map(transaccion -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(transaccion))
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación al actualizar estado: {}", e.getMessage());
                return Mono.just(ResponseEntity.badRequest().build());
            })
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Error al actualizar estado de transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarTransaccion(@PathVariable UUID id) {
        log.info("Petición DELETE para eliminar transacción con ID: {}", id);
        return transaccionService.eliminarTransaccion(id)
            .then(Mono.just(ResponseEntity.noContent().build()))
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación al eliminar: {}", e.getMessage());
                return Mono.just(ResponseEntity.badRequest().build());
            })
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Error al eliminar transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }
}

// === ARCHIVO: docker/Dockerfile ===
# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]

// === ARCHIVO: docker/docker-compose.yml ===
version: '3.8'

services:
  app:
    build:
      context: ..
      dockerfile: docker/Dockerfile
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/banco
      - SPRING_DATASOURCE_USERNAME=banco_user
      - SPRING_DATASOURCE_PASSWORD=banco_pass
    depends_on:
      db:
        condition: service_healthy
    networks:
      - banco-net

  db:
    image: postgres:16-alpine
    environment:
      - POSTGRES_DB=banco
      - POSTGRES_USER=banco_user
      - POSTGRES_PASSWORD=banco_pass
    volumes:
      - postgres-data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U banco_user -d banco"]
      interval: 10s
      timeout: 5s
      retries: 5
    networks:
      - banco-net

networks:
  banco-net:
    driver: bridge

volumes:
  postgres-data:

// === ARCHIVO: docker/.dockerignore ===
# Git
.git
.gitignore

# Maven build artifacts
target/
.mvn/
mvnw
mvnw.cmd
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# IDE files
.idea/
*.iws
*.iml
*.ipr
.vscode/
.settings/
.classpath
.project
*.swp
*.swo
*~

# OS files
.DS_Store
Thumbs.db

# Documentation and configs (not needed in image)
docs/
k8s/
docker/
README.md
AGENTS.md
.env
.env.*

# Logs
*.log
logs/

# Test files
src/test/
*.test
*.tests

# Build scripts
*.sh
build.sh
deploy.sh

# Temporary files
*.tmp
*.bak
*.orig

// === ARCHIVO: docker/healthcheck.sh ===
#!/bin/bash
# Healthcheck script para el contenedor del servicio bancario
# Verifica la disponibilidad del servicio y sus dependencias

set -e

# Configuración
SERVICE_HOST="${SERVICE_HOST:-localhost}"
SERVICE_PORT="${SERVICE_PORT:-8080}"
ACTUATOR_HEALTH_URL="http://${SERVICE_HOST}:${SERVICE_PORT}/actuator/health"
MAX_RETRIES="${HEALTHCHECK_RETRIES:-30}"
RETRY_INTERVAL="${HEALTHCHECK_INTERVAL:-2}"

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

log_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

log_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Función para verificar la conectividad a PostgreSQL
check_postgres() {
    local pg_host="${POSTGRES_HOST:-localhost}"
    local pg_port="${POSTGRES_PORT:-5432}"
    local pg_user="${POSTGRES_USER:-postgres}"
    local pg_db="${POSTGRES_DB:-banco}"
    
    if command -v pg_isready &> /dev/null; then
        if pg_isready -h "$pg_host" -p "$pg_port" -U "$pg_user" -d "$pg_db" &> /dev/null; then
            log_info "PostgreSQL está disponible en ${pg_host}:${pg_port}"
            return 0
        else
            log_error "PostgreSQL no está disponible en ${pg_host}:${pg_port}"
            return 1
        fi
    else
        # Alternativa usando nc (netcat) si pg_isready no está disponible
        if command -v nc &> /dev/null; then
            if nc -z "$pg_host" "$pg_port" 2>/dev/null; then
                log_info "Puerto PostgreSQL ${pg_host}:${pg_port} accesible"
                return 0
            else
                log_error "Puerto PostgreSQL ${pg_host}:${pg_port} no accesible"
                return 1
            fi
        else
            log_warn "No se puede verificar PostgreSQL (pg_isready y nc no disponibles)"
            return 0
        fi
    fi
}

# Función para verificar el endpoint de health de Spring Boot
check_actuator_health() {
    local attempt=1
    local max_attempts="${MAX_RETRIES}"
    
    while [ $attempt -le $max_attempts ]; do
        log_info "Verificando health endpoint (intento ${attempt}/${max_attempts}): ${ACTUATOR_HEALTH_URL}"
        
        # Verificar si curl o wget está disponible
        if command -v curl &> /dev/null; then
            HTTP_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" "${ACTUATOR_HEALTH_URL}" 2>/dev/null || echo "000")
        elif command -v wget &> /dev/null; then
            HTTP_RESPONSE=$(wget -q -O /dev/null "${ACTUATOR_HEALTH_URL}" 2>/dev/null && echo "200" || echo "000")
        else
            log_error "Ni curl ni wget están disponibles para verificar el health endpoint"
            return 1
        fi
        
        case "$HTTP_RESPONSE" in
            "200")
                log_info "Health check exitoso: HTTP ${HTTP_RESPONSE}"
                
                # Verificar el estado detallado si es posible
                if command -v curl &> /dev/null; then
                    HEALTH_DETAILS=$(curl -s "${ACTUATOR_HEALTH_URL}" 2>/dev/null || echo "{}")
                    DB_STATUS=$(echo "$HEALTH_DETAILS" | grep -o '"db":{[^}]*}' || echo "")
                    if [ -n "$DB_STATUS" ]; then
                        if echo "$DB_STATUS" | grep -q '"status":"UP"'; then
                            log_info "Componente DB reportado como UP"
                        else
                            log_warn "Componente DB puede tener problemas"
                        fi
                    fi
                fi
                return 0
                ;;
            "000")
                log_warn "Servicio no responde (intento ${attempt}/${max_attempts})"
                ;;
            *)
                log_error "Respuesta inesperada del servicio: HTTP ${HTTP_RESPONSE}"
                ;;
        esac
        
        # Si no es el último intento, esperar antes de reintentar
        if [ $attempt -lt $max_attempts ]; then
            log_info "Esperando ${RETRY_INTERVAL} segundos antes del siguiente intento..."
            sleep "${RETRY_INTERVAL}"
        fi
        
        attempt=$((attempt + 1))
    done
    
    log_error "Health check falló después de ${max_attempts} intentos"
    return 1
}

# Función principal de verificación
main() {
    log_info "=== Iniciando verificación de salud del contenedor ==="
    log_info "Host del servicio: ${SERVICE_HOST}:${SERVICE_PORT}"
    
    # Verificar que el servicio esté escuchando en el puerto
    if command -v nc &> /dev/null; then
        if ! nc -z "${SERVICE_HOST}" "${SERVICE_PORT}" 2>/dev/null; then
            log_error "El servicio no está escuchando en ${SERVICE_HOST}:${SERVICE_PORT}"
            exit 1
        fi
        log_info "Puerto del servicio ${SERVICE_HOST}:${SERVICE_PORT} accesible"
    fi
    
    # Verificar PostgreSQL
    if ! check_postgres; then
        log_error "Verificación de PostgreSQL falló"
        exit 1
    fi
    
    # Verificar el health endpoint de Spring Boot Actuator
    if ! check_actuator_health; then
        log_error "Verificación del health endpoint falló"
        exit 1
    fi
    
    log_info "=== Todas las verificaciones de salud pasaron exitosamente ==="
    exit 0
}

# Ejecutar la función principal
main "$@"

// === ARCHIVO: k8s/deployment.yaml ===
apiVersion: apps/v1
kind: Deployment
metadata:
  name: servicio-transacciones
  labels:
    app: servicio-transacciones
    version: v1
spec:
  replicas: 3
  selector:
    matchLabels:
      app: servicio-transacciones
  template:
    metadata:
      labels:
        app: servicio-transacciones
        version: v1
    spec:
      containers:
      - name: servicio
        image: servicio:latest
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8080
          name: http
          protocol: TCP
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            configMapKeyRef:
              name: servicio-config
              key: database.url
        - name: SPRING_DATASOURCE_USERNAME
          valueFrom:
            secretKeyRef:
              name: servicio-secret
              key: database.username
        - name: SPRING_DATASOURCE_PASSWORD
          valueFrom:
            secretKeyRef:
              name: servicio-secret
              key: database.password
        resources:
          requests:
            memory: "512Mi"
            cpu: "250m"
          limits:
            memory: "1Gi"
            cpu: "1000m"
        livenessProbe:
          httpGet:
            path: /actuator/health/liveness
            port: 8080
          initialDelaySeconds: 60
          periodSeconds: 10
          timeoutSeconds: 5
          failureThreshold: 3
        readinessProbe:
          httpGet:
            path: /actuator/health/readiness
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 5
          timeoutSeconds: 3
          failureThreshold: 2
      affinity:
        podAntiAffinity:
          preferredDuringSchedulingIgnoredDuringExecution:
          - weight: 100
            podAffinityTerm:
              labelSelector:
                matchExpressions:
                - key: app
                  operator: In
                  values:
                  - servicio-transacciones
              topologyKey: kubernetes.io/hostname
      serviceAccountName: servicio-sa
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: servicio-config
data:
  database.url: "jdbc:postgresql://postgres-svc:5432/transacciones"
---
apiVersion: v1
kind: Secret
metadata:
  name: servicio-secret
type: Opaque
stringData:
  database.username: "transacciones_user"
  database.password: "changeme"
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: servicio-sa

// === ARCHIVO: k8s/service.yaml ===
apiVersion: v1
kind: Service
metadata:
  name: servicio-svc
  labels:
    app: servicio-transacciones
spec:
  type: ClusterIP
  selector:
    app: servicio-transacciones
  ports:
  - name: http
    port: 80
    targetPort: 8080
    protocol: TCP
  sessionAffinity: None
  publishNotReadyAddresses: false
---
apiVersion: v1
kind: Service
metadata:
  name: servicio-svc-nodeport
  labels:
    app: servicio-transacciones
spec:
  type: NodePort
  selector:
    app: servicio-transacciones
  ports:
  - name: http
    port: 8080
    nodePort: 30080
    targetPort: 8080
    protocol: TCP

// === ARCHIVO: k8s/ingress.yaml ===
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: servicio-ingress
  labels:
    app: servicio-transacciones
  annotations:
    nginx.ingress.kubernetes.io/ssl-redirect: "true"
    nginx.ingress.kubernetes.io/proxy-read-timeout: "300"
    nginx.ingress.kubernetes.io/proxy-send-timeout: "300"
    nginx.ingress.kubernetes.io/proxy-body-size: "10m"
    cert-manager.io/cluster-issuer: "letsencrypt-prod"
spec:
  ingressClassName: nginx
  tls:
  - hosts:
    - transacciones.banco.com
    secretName: servicio-tls
  rules:
  - host: transacciones.banco.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: servicio-svc
            port:
              number: 80

// === ARCHIVO: k8s/hpa.yaml ===
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: servicio-hpa
  namespace: default
  labels:
    app: servicio
    tier: backend
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: servicio-deployment
  minReplicas: 2
  maxReplicas: 10
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          type: Utilization
          averageUtilization: 70
    - type: Resource
      resource:
        name: memory
        target:
          type: Utilization
          averageUtilization: 80
  behavior:
    scaleDown:
      stabilizationWindowSeconds: 300
      policies:
        - type: Percent
          value: 10
          periodSeconds: 60
    scaleUp:
      stabilizationWindowSeconds: 0
      policies:
        - type: Percent
          value: 100
          periodSeconds: 15
        - type: Pods
          value: 4
          periodSeconds: 15
      selectPolicy: Max

// === ARCHIVO: README.md ===
# Servicio de Gestión de Transacciones Bancarias

Microservicio reactivo desarrollado con Spring Boot 3.5 y Java 21 para la gestión de transacciones bancarias.

## Características del Proyecto

- **Arquitectura Reactiva**: Implementado con Spring WebFlux y Project Reactor
- **Persistencia**: JPA con PostgreSQL
- **Contenerización**: Docker y Docker Compose
- **Orquestación**: Kubernetes con HPA para auto-escalado
- **Observabilidad**: Spring Boot Actuator

## Estructura del Proyecto

```
servicio/
├── src/main/java/com/banco/servicio/
│   ├── Application.java                 # Punto de entrada
│   ├── domain/                          # Capa de dominio
│   │   ├── model/                       # Entidades y DTOs
│   │   └── port/                        # Interfaces de repositorio
│   ├── application/                     # Capa de aplicación
│   │   └── service/                     # Servicios de negocio
│   └── infrastructure/                  # Capa de infraestructura
│       ├── repository/                  # Implementaciones de repositorio
│       └── controller/                  # Controladores REST
├── src/main/resources/
│   └── application.yml                  # Configuración
├── docker/
│   ├── Dockerfile
│   ├── docker-compose.yml
│   ├── .dockerignore
│   └── healthcheck.sh
├── k8s/
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── ingress.yaml
│   └── hpa.yaml
└── pom.xml
```

## Requisitos Previos

- Java 21
- Maven 3.9+
- Docker 24+
- Docker Compose 2.24+
- kubectl (para Kubernetes)
- PostgreSQL 16+ (si se ejecuta sin Docker)

## Construcción del Proyecto

### Compilación Local

```bash
mvn clean compile
```

### Ejecución de Tests

```bash
mvn test
```

### Construcción del JAR

```bash
mvn clean package -DskipTests
```

## Ejecución

### Modo Desarrollo (con Docker Compose)

```bash
cd docker
docker-compose up --build
```

El servicio estará disponible en: `http://localhost:8080`

### Configuración de la Base de Datos

El archivo `docker-compose.yml` configura:
- PostgreSQL en el puerto 5432
- Credenciales configuradas en variables de entorno

### Verificación de Salud

```bash
curl http://localhost:8080/actuator/health
```

## Despliegue en Kubernetes

### Prerrequisitos

- Clúster de Kubernetes configurado
- kubectl configurado con el contexto correcto
- Ingress controller instalado (nginx-ingress, traefik, etc.)

### Aplicar Manifiestos

```bash
# Aplicar todos los manifiestos
kubectl apply -f k8s/

# O aplicar individualmente
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/ingress.yaml
kubectl apply -f k8s/hpa.yaml
```

### Verificar Estado

```bash
# Ver pods
kubectl get pods -l app=servicio

# Ver servicios
kubectl get svc -l app=servicio

# Ver HPA
kubectl get hpa servicio-hpa

# Ver eventos del deployment
kubectl describe deployment servicio-deployment
```

### Escalado Manual

```bash
kubectl scale deployment servicio-deployment --replicas=5
```

### Configuración de HPA

El HPA está configurado para:
- Mínimo de 2 réplicas
- Máximo de 10 réplicas
- Escalar cuando CPU > 70%
- Escalar cuando memoria > 80%
- Política de scale-up: máximo 100% o 4 pods por período
- Política de scale-down: 10% cada 60 segundos

## Variables de Entorno

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| SPRING_DATASOURCE_URL | URL de conexión JDBC | jdbc:postgresql://postgres:5432/banco |
| SPRING_DATASOURCE_USERNAME | Usuario de BD | postgres |
| SPRING_DATASOURCE_PASSWORD | Contraseña de BD | postgres |
| SPRING_JPA_HIBERNATE_DDL_AUTO | Modo de Hibernate | update |
| SERVER_PORT | Puerto del servidor | 8080 |

## Endpoints de la API

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/v1/transacciones | Listar todas las transacciones |
| GET | /api/v1/transacciones/{id} | Obtener transacción por ID |
| POST | /api/v1/transacciones | Crear nueva transacción |
| DELETE | /api/v1/transacciones/{id} | Eliminar transacción |
| GET | /api/v1/transacciones/cuenta/{cuentaOrigen} | Buscar por cuenta origen |
| GET | /api/v1/transacciones/cuenta-destino/{cuentaDestino} | Buscar por cuenta destino |
| GET | /api/v1/transacciones/tipo/{tipo} | Buscar por tipo |
| GET | /api/v1/transacciones/estado/{estado} | Buscar por estado |

## Health Checks

- **Liveness**: `/actuator/health/liveness`
- **Readiness**: `/actuator/health/readiness`
- **General**: `/actuator/health`

## Construcción de Imagen Docker

```bash
# Construir imagen
docker build -t banco/servicio:latest .

# Etiquetar para registry
docker tag banco/servicio:latest myregistry.azurecr.io/banco/servicio:v1.0.0

# Push a registry
docker push myregistry.azurecr.io/banco/servicio:v1.0.0
```

## Consideraciones de Producción

1. **Seguridad**: Configurar TLS/SSL en el Ingress
2. **Secrets**: Usar Kubernetes Secrets para credenciales de BD
3. **Recursos**: Ajustar límites de CPU/memoria según carga esperada
4. **Monitoreo**: Integrar con Prometheus y Grafana
5. **Logs**: Configurar agregación de logs (ELK, Loki, etc.)
6. **Backups**: Establecer política de backups para PostgreSQL

## Licencia

Proprietario - Banco
```
