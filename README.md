# Diseño y Orquestación de Contenedores

El sistema de un banco necesita implementar la contenerización y orquestación de servicios para mejorar la escalabilidad y la gestión de microservicios. Debes considerar las diferencias, ventajas y desventajas de Docker y Kubernetes. El sistema debe soportar al menos dos herramientas de contenerización/orquestación, como Docker, Docker Compose, Docker Swarm, K8s (Kubernetes) u OpenShift. Los servicios deben ser desplegados de manera que garanticen alta disponibilidad y escalabilidad.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Implementa Plataformas de Contenedores enfocados en Docker y Kubernetes |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y Selección de Herramientas

**Objetivo:** Identificar y seleccionar las herramientas adecuadas para contenerización y orquestación.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Investiga y compara las diferencias, ventajas y desventajas de Docker y Kubernetes.
- Selecciona al menos dos herramientas de contenerización/orquestación y justifica tu elección.

**Entregable:** Documento que describe las herramientas seleccionadas y la justificación de la elección.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la complejidad de implementación, la escalabilidad y la gestión de servicios.

</details>

### Fase 2: Configuración y Despliegue de Contenedores

**Objetivo:** Configurar y desplegar servicios en contenedores usando las herramientas seleccionadas.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Configura Docker para contenerizar servicios backend.
- Despliega los servicios en Kubernetes para asegurar alta disponibilidad y escalabilidad.

**Entregable:** Configuración de Docker y despliegue de servicios en Kubernetes.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza Dockerfiles y Kubernetes manifests para configurar y desplegar servicios.

</details>

### Fase 3: Validación y Optimización

**Objetivo:** Validar el despliegue y optimizar la configuración para mejorar el rendimiento y la escalabilidad.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Realiza pruebas de carga y rendimiento para validar el despliegue.
- Identifica y soluciona cuellos de botella y optimiza la configuración de los servicios.

**Entregable:** Reporte de validación y optimización del despliegue de servicios en contenedores.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de prueba de carga y monitoreo para identificar problemas y optimizar la configuración.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son Docker y Kubernetes y cómo se relacionan en la contenerización y orquestación de servicios?
- **paraQueSirve**: ¿Para qué sirven Docker y Kubernetes en el contexto de un proyecto de backend?
- **comoSeUsa**: ¿Cómo se configura y despliega un servicio en Docker y Kubernetes?
- **erroresComunes**: ¿Cuáles son los errores comunes al contenerizar y orquestar servicios y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones arquitectónicas y de configuración implica la elección de Docker y Kubernetes en un proyecto?

## Criterios de Evaluacion

- Selección justificada de herramientas de contenerización y orquestación.
- Configuración y despliegue correcto de servicios en Docker y Kubernetes.
- Validación y optimización efectiva del despliegue de servicios.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
