# Optimización y Seguridad en un Sistema de Gestión de Cuentas Bancarias

El sistema de gestión de cuentas bancarias de PragmaBank necesita una refactorización para mejorar la legibilidad del código y la seguridad de las transacciones. El sistema actualmente maneja cuentas de clientes, permite transacciones entre cuentas y registra cada transacción. Los clientes deben autenticarse mediante JWT para realizar transacciones. El objetivo es implementar dos mecanismos de autenticación adicionales y asegurar que el código sea legible y no redundante.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Especialista en Framework con Proficiencia en Seguridad y Legibilidad de Código |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

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

### Fase 1: Refactorización para Legibilidad

**Objetivo:** Mejorar la legibilidad del código existente.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identifica áreas del código que pueden ser refactorizadas para mejorar la legibilidad.
- Refactoriza el código para que sea más legible y menos redundante.

**Entregable:** Código refactorizado que es más legible y no redundante.

<details>
<summary>Pistas de conocimiento</summary>

- Patrones de diseño comunes en Java.
- Prácticas de codificación limpia.

</details>

### Fase 2: Implementación de Mecanismos de Autenticación

**Objetivo:** Implementar dos mecanismos de autenticación adicionales.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Identifica dos mecanismos de autenticación que pueden ser implementados además del JWT.
- Implementa estos mecanismos de autenticación en el sistema.

**Entregable:** Sistema con dos mecanismos de autenticación adicionales implementados.

<details>
<summary>Pistas de conocimiento</summary>

- Mecanismos de autenticación comunes.
- Integración de mecanismos de autenticación en Java.

</details>

### Fase 3: Evaluación de Seguridad

**Objetivo:** Evaluar la seguridad del sistema después de las implementaciones.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Realiza una evaluación de seguridad del sistema para asegurar que las implementaciones no han introducido vulnerabilidades.
- Documenta cualquier hallazgo y propone soluciones.

**Entregable:** Informe de evaluación de seguridad con hallazgos y propuestas de solución.

<details>
<summary>Pistas de conocimiento</summary>

- Prácticas comunes de evaluación de seguridad.
- Herramientas de evaluación de seguridad para Java.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la legibilidad del código y por qué es importante en un sistema de gestión de cuentas bancarias?
- **paraQueSirve**: ¿Para qué sirve implementar múltiples mecanismos de autenticación en un sistema?
- **comoSeUsa**: ¿Cómo se usa un JWT en el proceso de autenticación de un sistema?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar mecanismos de autenticación y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un mecanismo de autenticación sobre otro en términos de seguridad y usabilidad?

## Criterios de Evaluacion

- Legibilidad del código refactorizado.
- Implementación de dos mecanismos de autenticación adicionales.
- Evaluación de seguridad del sistema y propuestas de solución.

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
