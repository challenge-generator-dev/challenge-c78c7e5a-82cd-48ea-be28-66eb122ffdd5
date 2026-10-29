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

- `src/main/java/com/pragmabank/config/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/pragmabank/infrastructure/security/JwtTokenUtil.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/pragmabank/infrastructure/security/OAuth2AuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/pragmabank/infrastructure/security/BasicAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragmabank/domain/service/AccountService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragmabank/domain/service/TransactionService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragmabank/Application.java` — `ApplicationProperties.getJwt`: Se invoca `getJwt` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/Application.java` — `ApplicationProperties.getDatasource`: Se invoca `getDatasource` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/CreateAccountUseCase.java` — `CreateAccountRequest.accountNumber`: Se invoca `accountNumber` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/CreateAccountUseCase.java` — `CreateAccountRequest.customerId`: Se invoca `customerId` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `AccountService.findById`: Se invoca `findById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransferRequest.sourceAccountId`: Se invoca `sourceAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransferRequest.targetAccountId`: Se invoca `targetAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransferRequest.amount`: Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransactionService.save`: Se invoca `save` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.findById`: Se invoca `findById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.findByAccountNumber`: Se invoca `findByAccountNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.findByCustomerId`: Se invoca `findByCustomerId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `WithdrawRequest.customerId`: Se invoca `customerId` sobre `WithdrawRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `WithdrawRequest.initialBalance`: Se invoca `initialBalance` sobre `WithdrawRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.save`: Se invoca `save` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `WithdrawRequest.amount`: Se invoca `amount` sobre `WithdrawRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.deleteById`: Se invoca `deleteById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findById`: Se invoca `findById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByTransactionNumber`: Se invoca `findByTransactionNumber` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByAccountId`: Se invoca `findByAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findBySourceAccountId`: Se invoca `findBySourceAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByTargetAccountId`: Se invoca `findByTargetAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByType`: Se invoca `findByType` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByStatus`: Se invoca `findByStatus` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `CreateTransactionRequest.accountId`: Se invoca `accountId` sobre `CreateTransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `CreateTransactionRequest.amount`: Se invoca `amount` sobre `CreateTransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.save`: Se invoca `save` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.crearCuenta`: Se invoca `crearCuenta` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.buscarPorId`: Se invoca `buscarPorId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `Account.isPresent`: Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `Account.get`: Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `Account.isEmpty`: Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.buscarPorNumero`: Se invoca `buscarPorNumero` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.depositar`: Se invoca `depositar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.retirar`: Se invoca `retirar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.buscarPorCliente`: Se invoca `buscarPorCliente` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.crearTransaccion`: Se invoca `crearTransaccion` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.ejecutarTransferencia`: Se invoca `ejecutarTransferencia` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.completarTransaccion`: Se invoca `completarTransaccion` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.failTransaccion`: Se invoca `failTransaccion` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.buscarPorId`: Se invoca `buscarPorId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `Transaction.isPresent`: Se invoca `isPresent` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `Transaction.get`: Se invoca `get` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.buscarPorNumero`: Se invoca `buscarPorNumero` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.buscarPorId`: Se invoca `buscarPorId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.buscarPorNumero`: Se invoca `buscarPorNumero` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.crearCuenta`: Se invoca `crearCuenta` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.depositar`: Se invoca `depositar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.retirar`: Se invoca `retirar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java` — `JwtTokenUtil.extractUsername`: Se invoca `extractUsername` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java` — `JwtAuthenticationFilter.shouldNotFilter`: Se invoca `shouldNotFilter` sobre `JwtAuthenticationFilter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

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
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced L2

### Brecha de conocimiento
Experto en el marco de trabajo (framework) mas relevante en su lenguaje de programación principal, lo que le permite escribir código más legible y reducir la redundancia. Ha implementado al menos dos mecanismos de autenticación e identifica el uso de un JWT, Experto en el marco de trabajo (framework) mas relevante en su lenguaje de programación principal, lo que le permite escribir código más legible y reducir la redundancia. Ha implementado al menos dos mecanismos de autenticación e identifica el uso de un JWT

### Reto
- Tema: Especialista en Framework con Proficiencia en Seguridad y Legibilidad de Código
- Seniority: advanced-l2
- Tipo: practical
- Título: Optimización y Seguridad en un Sistema de Gestión de Cuentas Bancarias
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Refactorización para Legibilidad — objetivo: Mejorar la legibilidad del código existente. — entregable (NO resolver): Código refactorizado que es más legible y no redundante.
- Fase 2: Implementación de Mecanismos de Autenticación — objetivo: Implementar dos mecanismos de autenticación adicionales. — entregable (NO resolver): Sistema con dos mecanismos de autenticación adicionales implementados.
- Fase 3: Evaluación de Seguridad — objetivo: Evaluar la seguridad del sistema después de las implementaciones. — entregable (NO resolver): Informe de evaluación de seguridad con hallazgos y propuestas de solución.

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
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragmabank</groupId>
    <artifactId>pragmabank</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>pragmabank</name>
    <description>Sistema de gestión de cuentas bancarias con seguridad avanzada</description>

    <properties>
        <java.version>21</java.version>
        <jjwt.version>0.12.5</jjwt.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-client</artifactId>
        </dependency>

        <!-- JWT Dependencies -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/pragmabank/Application.java ===
package com.pragmabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    private final ApplicationProperties applicationProperties;

    public Application(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private void validateProperties() {
        if (applicationProperties.getJwt().getSecret() == null || applicationProperties.getJwt().getSecret().isBlank()) {
            throw new IllegalStateException("La clave secreta JWT no puede estar vacía");
        }
        if (applicationProperties.getJwt().getExpiration() <= 0) {
            throw new IllegalStateException("El tiempo de expiración JWT debe ser positivo");
        }
        if (applicationProperties.getDatasource().getUrl() == null || applicationProperties.getDatasource().getUrl().isBlank()) {
            throw new IllegalStateException("La URL del datasource no puede estar vacía");
        }
    }

    public record ApplicationProperties(
            JwtProperties jwt,
            DatasourceProperties datasource
    ) {
        public record JwtProperties(String secret, long expiration) {}
        public record DatasourceProperties(String url, String username, String password) {}
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: pragmabank
  datasource:
    url: jdbc:postgresql://localhost:5432/pragmabank
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope: email,profile
          github:
            client-id: ${GITHUB_CLIENT_ID}
            client-secret: ${GITHUB_CLIENT_SECRET}
            scope: user:email

application:
  jwt:
    secret: ${JWT_SECRET:defaultSecretKeyForDevelopmentOnlyChangeInProduction}
    expiration: 86400000 # 24 horas en milisegundos
  cors:
    allowed-origins: http://localhost:3000,http://localhost:8080
    allowed-methods: GET,POST,PUT,DELETE,OPTIONS
    allowed-headers: Authorization,Content-Type
    max-age: 3600

// === ARCHIVO: src/main/java/com/pragmabank/domain/model/Account.java ===
package com.pragmabank.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private final UUID id;
    private final String accountNumber;
    private final UUID customerId;
    private BigDecimal balance;
    private final AccountStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String lastTransactionId;

    public enum AccountStatus {
        ACTIVE, FROZEN, CLOSED
    }

    public Account(@NotNull UUID id,
                  @NotNull @Size(min = 10, max = 20) String accountNumber,
                  @NotNull UUID customerId,
                  @PositiveOrZero BigDecimal initialBalance,
                  @NotNull AccountStatus status) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.lastTransactionId = null;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getLastTransactionId() {
        return lastTransactionId;
    }

    public void deposit(@PositiveOrZero BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(@PositiveOrZero BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLastTransactionId(String transactionId) {
        this.lastTransactionId = transactionId;
        this.updatedAt = LocalDateTime.now();
    }

    public void freezeAccount() {
        if (this.status == AccountStatus.CLOSED) {
            throw new IllegalStateException("Cannot freeze a closed account");
        }
        this.status = AccountStatus.FROZEN;
        this.updatedAt = LocalDateTime.now();
    }

    public void closeAccount() {
        if (this.status == AccountStatus.CLOSED) {
            throw new IllegalStateException("Account is already closed");
        }
        if (this.balance.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("Cannot close account with positive balance");
        }
        this.status = AccountStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/domain/model/Transaction.java ===
package com.pragmabank.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final String transactionNumber;
    private final UUID sourceAccountId;
    private final UUID targetAccountId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDateTime timestamp;
    private final TransactionStatus status;
    private String failureReason;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED
    }

    public Transaction(@NotNull UUID id,
                      @NotNull String transactionNumber,
                      @NotNull UUID sourceAccountId,
                      UUID targetAccountId,
                      @NotNull @Positive BigDecimal amount,
                      @NotNull TransactionType type) {
        this.id = id;
        this.transactionNumber = transactionNumber;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.failureReason = null;
    }

    public UUID getId() {
        return id;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public UUID getTargetAccountId() {
        return targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void complete() {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException("Transaction is not pending");
        }
        this.status = TransactionStatus.COMPLETED;
    }

    public void fail(String reason) {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException("Transaction is not pending");
        }
        this.status = TransactionStatus.FAILED;
        this.failureReason = reason;
    }

    public boolean isTransfer() {
        return this.type == TransactionType.TRANSFER && this.targetAccountId != null;
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/domain/port/AccountRepository.java ===
package com.pragmabank.domain.port;

import com.pragmabank.domain.model.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByCustomerId(UUID customerId);

    void deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/pragmabank/domain/port/TransactionRepository.java ===
package com.pragmabank.domain.port;

import com.pragmabank.domain.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define el contrato para la persistencia de transacciones.
 * Esta interfaz pertenece a la capa de dominio y es implementada por la capa de infraestructura.
 */
public interface TransactionRepository {
    
    /**
     * Persiste una nueva transacción en el repositorio.
     * @param transaction la transacción a guardar
     * @return la transacción persistida con su ID asignado
     */
    Transaction save(Transaction transaction);
    
    /**
     * Busca una transacción por su identificador único.
     * @param id el identificador de la transacción
     * @return un Optional que contiene la transacción si existe
     */
    Optional<Transaction> findById(UUID id);
    
    /**
     * Busca una transacción por su número único de transacción.
     * @param transactionNumber el número de transacción
     * @return un Optional que contiene la transacción si existe
     */
    Optional<Transaction> findByTransactionNumber(String transactionNumber);
    
    /**
     * Recupera todas las transacciones asociadas a una cuenta de origen.
     * @param accountId el identificador de la cuenta de origen
     * @return lista de transacciones realizadas desde esa cuenta
     */
    List<Transaction> findBySourceAccountId(UUID accountId);
    
    /**
     * Recupera todas las transacciones asociadas a una cuenta de destino.
     * @param accountId el identificador de la cuenta de destino
     * @return lista de transacciones recibidas en esa cuenta
     */
    List<Transaction> findByTargetAccountId(UUID accountId);
    
    /**
     * Recupera el historial de transacciones de una cuenta (origen o destino).
     * @param accountId el identificador de la cuenta
     * @return lista combinada de transacciones de origen y destino
     */
    List<Transaction> findByAccountId(UUID accountId);
    
    /**
     * Busca transacciones dentro de un rango de fechas.
     * @param startDate fecha de inicio del rango
     * @param endDate fecha de fin del rango
     * @return lista de transacciones en el rango especificado
     */
    List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Calcula el total de transacciones salientes de una cuenta en un período.
     * @param accountId identificador de la cuenta
     * @param startDate inicio del período
     * @param endDate fin del período
     * @return suma total de los montos de transacciones salientes
     */
    BigDecimal sumAmountsBySourceAccountIdInDateRange(UUID accountId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Calcula el total de transacciones entrantes a una cuenta en un período.
     * @param accountId identificador de la cuenta
     * @param startDate inicio del período
     * @param endDate fin del período
     * @return suma total de los montos de transacciones entrantes
     */
    BigDecimal sumAmountsByTargetAccountIdInDateRange(UUID accountId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Elimina una transacción por su identificador.
     * @param id el identificador de la transacción a eliminar
     */
    void deleteById(UUID id);
    
    /**
     * Verifica si existe una transacción con el número dado.
     * @param transactionNumber el número de transacción a verificar
     * @return true si existe una transacción con ese número
     */
    boolean existsByTransactionNumber(String transactionNumber);
}

// === ARCHIVO: src/main/java/com/pragmabank/domain/service/AccountService.java ===
package com.pragmabank.domain.service;

import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.model.Account.InsufficientFundsException;
import com.pragmabank.domain.port.AccountRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio de dominio que encapsula la lógica de negocio para la gestión de cuentas bancarias.
 * Este servicio es parte del núcleo del dominio y no conoce detalles de infraestructura.
 */
public class AccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(100.00);
    private static final BigDecimal MAX_WITHDRAWAL_LIMIT = BigDecimal.valueOf(10000.00);
    
    private final AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    /**
     * Crea una nueva cuenta bancaria con el saldo inicial especificado.
     * @param id identificador único de la cuenta
     * @param accountNumber número de cuenta único
     * @param customerId identificador del cliente propietario
     * @param initialBalance saldo inicial de la cuenta
     * @return la cuenta creada
     * @throws IllegalArgumentException si el saldo inicial es menor al mínimo requerido
     */
    public Account createAccount(UUID id, String accountNumber, UUID customerId, BigDecimal initialBalance) {
        logger.info("Creando cuenta {} para cliente {}", accountNumber, customerId);
        
        if (initialBalance.compareTo(MINIMUM_BALANCE) < 0) {
            logger.warn("Intento de crear cuenta {} con saldo insuficiente: {}", accountNumber, initialBalance);
            throw new IllegalArgumentException(
                "El saldo inicial debe ser al menos " + MINIMUM_BALANCE + " para activar la cuenta"
            );
        }
        
        Account account = new Account(
            id,
            accountNumber,
            customerId,
            initialBalance,
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            LocalDateTime.now(),
            null
        );
        
        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta {} creada exitosamente con ID {}", accountNumber, savedAccount.getId());
        return savedAccount;
    }
    
    /**
     * Procesa un depósito en una cuenta existente.
     * @param accountId identificador de la cuenta
     * @param amount monto a depositar
     * @return la cuenta con el saldo actualizado
     * @throws IllegalArgumentException si el monto es cero o negativo
     */
    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Procesando depósito de {} en cuenta {}", amount, accountId);
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getStatus() != AccountStatus.ACTIVE) {
            logger.warn("Intento de depósito en cuenta {} con estado {}", accountId, account.getStatus());
            throw new IllegalStateException("No se puede depositar en una cuenta que no está activa");
        }
        
        account.deposit(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * Procesa un retiro de una cuenta existente verificando fondos suficientes.
     * @param accountId identificador de la cuenta
     * @param amount monto a retirar
     * @return la cuenta con el saldo actualizado
     * @throws IllegalArgumentException si el monto es cero o negativo
     * @throws InsufficientFundsException si no hay fondos suficientes
     */
    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Procesando retiro de {} en cuenta {}", amount, accountId);
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }
        
        if (amount.compareTo(MAX_WITHDRAWAL_LIMIT) > 0) {
            logger.warn("Intento de retiro que excede el límite: {} > {}", amount, MAX_WITHDRAWAL_LIMIT);
            throw new IllegalArgumentException(
                "El monto del retiro no puede exceder " + MAX_WITHDRAWAL_LIMIT + " por transacción"
            );
        }
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getStatus() != AccountStatus.ACTIVE) {
            logger.warn("Intento de retiro en cuenta {} con estado {}", accountId, account.getStatus());
            throw new IllegalStateException("No se puede retirar de una cuenta que no está activa");
        }
        
        BigDecimal projectedBalance = account.getBalance().subtract(amount);
        if (projectedBalance.compareTo(MINIMUM_BALANCE) < 0) {
            logger.warn("Fondos insuficientes en cuenta {}: saldo={}, retiro={}, mínimo={}",
                accountId, account.getBalance(), amount, MINIMUM_BALANCE);
            throw new InsufficientFundsException(
                "Saldo insuficiente. Saldo actual: " + account.getBalance() + 
                ", monto solicitado: " + amount + 
                ", saldo mínimo requerido: " + MINIMUM_BALANCE
            );
        }
        
        account.withdraw(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * Obtiene una cuenta por su identificador.
     * @param id identificador de la cuenta
     * @return un Optional con la cuenta si existe
     */
    public Optional<Account> getAccountById(UUID id) {
        logger.debug("Buscando cuenta por ID: {}", id);
        return accountRepository.findById(id);
    }
    
    /**
     * Obtiene una cuenta por su número de cuenta.
     * @param accountNumber número de cuenta
     * @return un Optional con la cuenta si existe
     */
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    /**
     * Obtiene todas las cuentas de un cliente.
     * @param customerId identificador del cliente
     * @return lista de cuentas del cliente
     */
    public List<Account> getAccountsByCustomerId(UUID customerId) {
        logger.debug("Buscando cuentas para cliente: {}", customerId);
        return accountRepository.findByCustomerId(customerId)
            .map(List::of)
            .orElse(List.of());
    }
    
    /**
     * Congela una cuenta, impidiendo operaciones de depósito y retiro.
     * @param accountId identificador de la cuenta
     * @return la cuenta congelada
     */
    public Account freezeAccount(UUID accountId) {
        logger.info("Congelando cuenta: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getStatus() == AccountStatus.CLOSED) {
            throw new IllegalStateException("No se puede congelar una cuenta cerrada");
        }
        
        account.freezeAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta {} congelada exitosamente", accountId);
        return updatedAccount;
    }
    
    /**
     * Cierra una cuenta permanentemente.
     * @param accountId identificador de la cuenta
     */
    public void closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException(
                "No se puede cerrar una cuenta con saldo positivo. Saldo actual: " + account.getBalance()
            );
        }
        
        account.closeAccount();
        accountRepository.save(account);
        logger.info("Cuenta {} cerrada exitosamente", accountId);
    }
    
    /**
     * Verifica si una cuenta tiene saldo suficiente para una operación.
     * @param accountId identificador de la cuenta
     * @param amount monto a verificar
     * @return true si tiene fondos suficientes
     */
    public boolean hasSufficientFunds(UUID accountId, BigDecimal amount) {
        return accountRepository.findById(accountId)
            .map(account -> account.getBalance().subtract(amount).compareTo(MINIMUM_BALANCE) >= 0)
            .orElse(false);
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/domain/service/TransactionService.java ===
package com.pragmabank.domain.service;

import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.port.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio de dominio que encapsula la lógica de negocio para la gestión de transacciones bancarias.
 * Coordina la creación, validación y seguimiento de transacciones entre cuentas.
 */
public class TransactionService {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private static final BigDecimal MIN_TRANSACTION_AMOUNT = BigDecimal.valueOf(0.01);
    private static final BigDecimal MAX_TRANSACTION_AMOUNT = BigDecimal.valueOf(50000.00);
    
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }
    
    /**
     * Crea una nueva transacción de depósito en una cuenta.
     * @param id identificador único de la transacción
     * @param transactionNumber número único de transacción
     * @param targetAccountId cuenta destino del depósito
     * @param amount monto a depositar
     * @return la transacción creada y completada
     */
    public Transaction createDeposit(UUID id, String transactionNumber, UUID targetAccountId, BigDecimal amount) {
        logger.info("Creando depósito de {} en cuenta {}", amount, targetAccountId);
        
        validateTransactionAmount(amount);
        validateAccountExists(targetAccountId, "cuenta destino");
        
        Transaction transaction = new Transaction(
            id,
            transactionNumber,
            null,
            targetAccountId,
            amount,
            TransactionType.DEPOSIT,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
        
        try {
            accountService.deposit(targetAccountId, amount);
            transaction.complete();
            logger.info("Depósito completado: {}", transactionNumber);
        } catch (Exception e) {
            logger.error("Error al procesar depósito: {}", e.getMessage());
            transaction.fail("Error al procesar depósito: " + e.getMessage());
        }
        
        return transactionRepository.save(transaction);
    }
    
    /**
     * Crea una nueva transacción de retiro de una cuenta.
     * @param id identificador único de la transacción
     * @param transactionNumber número único de transacción
     * @param sourceAccountId cuenta origen del retiro
     * @param amount monto a retirar
     * @return la transacción creada
     */
    public Transaction createWithdrawal(UUID id, String transactionNumber, UUID sourceAccountId, BigDecimal amount) {
        logger.info("Creando retiro de {} desde cuenta {}", amount, sourceAccountId);
        
        validateTransactionAmount(amount);
        validateAccountExists(sourceAccountId, "cuenta origen");
        
        Transaction transaction = new Transaction(
            id,
            transactionNumber,
            sourceAccountId,
            null,
            amount,
            TransactionType.WITHDRAWAL,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
        
        try {
            accountService.withdraw(sourceAccountId, amount);
            transaction.complete();
            logger.info("Retiro completado: {}", transactionNumber);
        } catch (Exception e) {
            logger.error("Error al procesar retiro: {}", e.getMessage());
            transaction.fail("Error al procesar retiro: " + e.getMessage());
        }
        
        return transactionRepository.save(transaction);
    }
    
    /**
     * Crea una transferencia entre dos cuentas.
     * @param id identificador único de la transacción
     * @param transactionNumber número único de transacción
     * @param sourceAccountId cuenta origen
     * @param targetAccountId cuenta destino
     * @param amount monto a transferir
     * @return la transacción creada
     */
    public Transaction createTransfer(UUID id, String transactionNumber, UUID sourceAccountId, 
                                       UUID targetAccountId, BigDecimal amount) {
        logger.info("Creando transferencia de {} desde {} hacia {}", amount, sourceAccountId, targetAccountId);
        
        validateTransactionAmount(amount);
        validateAccountExists(sourceAccountId, "cuenta origen");
        validateAccountExists(targetAccountId, "cuenta destino");
        
        if (sourceAccountId.equals(targetAccountId)) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }
        
        Transaction transaction = new Transaction(
            id,
            transactionNumber,
            sourceAccountId,
            targetAccountId,
            amount,
            TransactionType.TRANSFER,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
        
        try {
            accountService.withdraw(sourceAccountId, amount);
            accountService.deposit(targetAccountId, amount);
            transaction.complete();
            logger.info("Transferencia completada: {}", transactionNumber);
        } catch (Exception e) {
            logger.error("Error al procesar transferencia: {}", e.getMessage());
            transaction.fail("Error al procesar transferencia: " + e.getMessage());
        }
        
        return transactionRepository.save(transaction);
    }
    
    /**
     * Obtiene una transacción por su identificador.
     * @param id identificador de la transacción
     * @return un Optional con la transacción si existe
     */
    public Optional<Transaction> getTransactionById(UUID id) {
        logger.debug("Buscando transacción por ID: {}", id);
        return transactionRepository.findById(id);
    }
    
    /**
     * Obtiene una transacción por su número.
     * @param transactionNumber número de transacción
     * @return un Optional con la transacción si existe
     */
    public Optional<Transaction> getTransactionByNumber(String transactionNumber) {
        logger.debug("Buscando transacción por número: {}", transactionNumber);
        return transactionRepository.findByTransactionNumber(transactionNumber);
    }
    
    /**
     * Obtiene el historial de transacciones de una cuenta.
     * @param accountId identificador de la cuenta
     * @return lista de transacciones de la cuenta
     */
    public List<Transaction> getAccountTransactionHistory(UUID accountId) {
        logger.debug("Obteniendo historial de cuenta: {}", accountId);
        return transactionRepository.findByAccountId(accountId);
    }
    
    /**
     * Obtiene las transacciones enviadas por una cuenta.
     * @param accountId identificador de la cuenta
     * @return lista de transacciones enviadas
     */
    public List<Transaction> getSentTransactions(UUID accountId) {
        logger.debug("Obteniendo transacciones enviadas desde: {}", accountId);
        return transactionRepository.findBySourceAccountId(accountId);
    }
    
    /**
     * Obtiene las transacciones recibidas por una cuenta.
     * @param accountId identificador de la cuenta
     * @return lista de transacciones recibidas
     */
    public List<Transaction> getReceivedTransactions(UUID accountId) {
        logger.debug("Obteniendo transacciones recibidas en: {}", accountId);
        return transactionRepository.findByTargetAccountId(accountId);
    }
    
    /**
     * Obtiene transacciones dentro de un rango de fechas.
     * @param startDate fecha de inicio
     * @param endDate fecha de fin
     * @return lista de transacciones en el rango
     */
    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Buscando transacciones entre {} y {}", startDate, endDate);
        return transactionRepository.findByTimestampBetween(startDate, endDate);
    }
    
    /**
     * Calcula el flujo neto de una cuenta en un período.
     * @param accountId identificador de la cuenta
     * @param startDate inicio del período
     * @param endDate fin del período
     * @return flujo neto (entradas - salidas)
     */
    public BigDecimal calculateNetFlow(UUID accountId, LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Calculando flujo neto para cuenta {} entre {} y {}", accountId, startDate, endDate);
        
        BigDecimal incoming = transactionRepository.sumAmountsByTargetAccountIdInDateRange(
            accountId, startDate, endDate);
        BigDecimal outgoing = transactionRepository.sumAmountsBySourceAccountIdInDateRange(
            accountId, startDate, endDate);
        
        return incoming.subtract(outgoing);
    }
    
    /**
     * Valida que el monto de la transacción esté dentro de los límites permitidos.
     * @param amount monto a validar
     * @throws IllegalArgumentException si el monto es inválido
     */
    private void validateTransactionAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("El monto de la transacción no puede ser nulo");
        }
        
        if (amount.compareTo(MIN_TRANSACTION_AMOUNT) < 0) {
            throw new IllegalArgumentException(
                "El monto mínimo de transacción es " + MIN_TRANSACTION_AMOUNT
            );
        }
        
        if (amount.compareTo(MAX_TRANSACTION_AMOUNT) > 0) {
            throw new IllegalArgumentException(
                "El monto máximo de transacción es " + MAX_TRANSACTION_AMOUNT
            );
        }
    }
    
    /**
     * Valida que una cuenta exista.
     * @param accountId identificador de la cuenta
     * @param accountType descripción del tipo de cuenta para el mensaje de error
     * @throws IllegalArgumentException si la cuenta no existe
     */
    private void validateAccountExists(UUID accountId, String accountType) {
        if (!accountService.getAccountById(accountId).isPresent()) {
            throw new IllegalArgumentException(
                accountType + " no encontrada: " + accountId
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/adapter/AccountJpaRepository.java ===
package com.pragmabank.infrastructure.adapter;

import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.port.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountJpaRepository implements AccountRepository {

    private final JpaRepository<Account, UUID> jpaRepository;

    public AccountJpaRepository(JpaRepository<Account, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Account save(Account account) {
        return jpaRepository.save(account);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return jpaRepository.findAll().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    @Override
    public Optional<Account> findByCustomerId(UUID customerId) {
        return jpaRepository.findAll().stream()
                .filter(account -> account.getCustomerId().equals(customerId))
                .findFirst();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    public List<Account> findAll() {
        return jpaRepository.findAll();
    }

    public boolean existsByAccountNumber(String accountNumber) {
        return findByAccountNumber(accountNumber).isPresent();
    }

    public boolean existsByCustomerId(UUID customerId) {
        return findByCustomerId(customerId).isPresent();
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/adapter/TransactionJpaRepository.java ===
package com.pragmabank.infrastructure.adapter;

import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.port.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionJpaRepository implements TransactionRepository {

    private final JpaRepository<Transaction, UUID> jpaRepository;

    public TransactionJpaRepository(JpaRepository<Transaction, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return jpaRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Transaction> findBySourceAccountId(UUID sourceAccountId) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getSourceAccountId() != null 
                        && t.getSourceAccountId().equals(sourceAccountId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByTargetAccountId(UUID targetAccountId) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getTargetAccountId() != null 
                        && t.getTargetAccountId().equals(targetAccountId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    public List<Transaction> findAll() {
        return jpaRepository.findAll();
    }

    public List<Transaction> findByAccountIdInvolved(UUID accountId) {
        List<Transaction> fromSource = findBySourceAccountId(accountId);
        List<Transaction> fromTarget = findByTargetAccountId(accountId);
        return fromSource.stream()
                .filter(t -> !fromTarget.contains(t))
                .collect(Collectors.toList());
    }

    public List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getTimestamp().isAfter(start) && t.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/application/usecase/CreateAccountUseCase.java ===
package com.pragmabank.application.usecase;


import com.pragmabank.domain.model.AccountStatus;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.port.AccountRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account execute(CreateAccountRequest request) {
        validateRequest(request);
        validateBusinessRules(request);
        
        Account account = new Account(
                UUID.randomUUID(),
                request.accountNumber(),
                request.customerId(),
                BigDecimal.ZERO,
                Account.AccountStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        
        return accountRepository.save(account);
    }

    private void validateRequest(CreateAccountRequest request) {
        if (request.accountNumber() == null || request.accountNumber().isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (request.customerId() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
    }

    private void validateBusinessRules(CreateAccountRequest request) {
        if (accountRepository.findByAccountNumber(request.accountNumber()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una cuenta con el número: " + request.accountNumber());
        }
    }

    public record CreateAccountRequest(
            @NotBlank(message = "El número de cuenta es requerido")
            String accountNumber,
            @NotNull(message = "El ID del cliente es requerido")
            UUID customerId,
            @Positive(message = "El balance inicial debe ser positivo")
            BigDecimal initialBalance
    ) {}
}

// === ARCHIVO: src/main/java/com/pragmabank/application/usecase/TransferUseCase.java ===
package com.pragmabank.application.usecase;

import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.service.AccountService;
import com.pragmabank.domain.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransferUseCase {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransferUseCase(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public TransferResponse execute(@Valid TransferRequest request) {
        Account sourceAccount = accountService.findById(request.sourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta origen no encontrada"));

        Account targetAccount = accountService.findById(request.targetAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta destino no encontrada"));

        if (sourceAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("La cuenta origen no está activa");
        }

        if (targetAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("La cuenta destino no está activa");
        }

        if (sourceAccount.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientFundsException("Fondos insuficientes en la cuenta origen");
        }

        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                generateTransactionNumber(),
                sourceAccount.getId(),
                targetAccount.getId(),
                request.amount(),
                TransactionType.TRANSFER,
                LocalDateTime.now(),
                TransactionStatus.PENDING
        );

        try {
            accountService.withdraw(sourceAccount.getId(), request.amount());
            accountService.deposit(targetAccount.getId(), request.amount());
            transaction.complete();
            transaction = transactionService.save(transaction);
        } catch (Exception e) {
            transaction.fail("Error durante la transferencia: " + e.getMessage());
            transaction = transactionService.save(transaction);
            throw new TransferFailedException("La transferencia falló: " + e.getMessage());
        }

        return new TransferResponse(
                transaction.getId(),
                transaction.getTransactionNumber(),
                sourceAccount.getId(),
                targetAccount.getId(),
                request.amount(),
                TransactionStatus.COMPLETED.name()
        );
    }

    private String generateTransactionNumber() {
        return "TXN-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public record TransferRequest(
            @NotNull(message = "El ID de la cuenta origen es obligatorio")
            UUID sourceAccountId,

            @NotNull(message = "El ID de la cuenta destino es obligatorio")
            UUID targetAccountId,

            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record TransferResponse(
            UUID transactionId,
            String transactionNumber,
            UUID sourceAccountId,
            UUID targetAccountId,
            BigDecimal amount,
            String status
    ) {}

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }

    public static class AccountInactiveException extends RuntimeException {
        public AccountInactiveException(String message) {
            super(message);
        }
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    public static class TransferFailedException extends RuntimeException {
        public TransferFailedException(String message) {
            super(message);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/controller/AccountController.java ===
package com.pragmabank.infrastructure.controller;


import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable UUID id) {
        return accountService.findById(id)
                .map(account -> ResponseEntity.ok(AccountResponse.fromDomain(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountByNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber)
                .map(account -> ResponseEntity.ok(AccountResponse.fromDomain(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByCustomer(@PathVariable UUID customerId) {
        List<Account> accounts = accountService.findByCustomerId(customerId);
        List<AccountResponse> responses = accounts.stream()
                .map(AccountResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = new Account(
                UUID.randomUUID(),
                generateAccountNumber(),
                request.customerId(),
                request.initialBalance() != null ? request.initialBalance() : BigDecimal.ZERO,
                AccountStatus.ACTIVE,
                java.time.LocalDateTime.now(),
                null,
                null
        );
        Account created = accountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountResponse.fromDomain(created));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable UUID id,
            @Valid @RequestBody DepositRequest request) {
        return accountService.findById(id)
                .map(account -> {
                    account.deposit(request.amount());
                    Account updated = accountService.save(account);
                    return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable UUID id,
            @Valid @RequestBody WithdrawRequest request) {
        return accountService.findById(id)
                .map(account -> {
                    try {
                        account.withdraw(request.amount());
                        Account updated = accountService.save(account);
                        return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                    } catch (Account.InsufficientFundsException e) {
                        throw new RuntimeException("Fondos insuficientes", e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/freeze")
    public ResponseEntity<AccountResponse> freezeAccount(@PathVariable UUID id) {
        return accountService.findById(id)
                .map(account -> {
                    account.freezeAccount();
                    Account updated = accountService.save(account);
                    return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<AccountResponse> closeAccount(@PathVariable UUID id) {
        return accountService.findById(id)
                .map(account -> {
                    account.closeAccount();
                    Account updated = accountService.save(account);
                    return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        if (accountService.findById(id).isPresent()) {
            accountService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis() + "-" + 
               UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public record CreateAccountRequest(
            @NotNull(message = "El ID del cliente es obligatorio")
            UUID customerId,

            BigDecimal initialBalance
    ) {}

    public record DepositRequest(
            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record WithdrawRequest(
            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record AccountResponse(
            UUID id,
            String accountNumber,
            UUID customerId,
            BigDecimal balance,
            String status,
            String createdAt,
            String updatedAt,
            String lastTransactionId
    ) {
        public static AccountResponse fromDomain(Account account) {
            return new AccountResponse(
                    account.getId(),
                    account.getAccountNumber(),
                    account.getCustomerId(),
                    account.getBalance(),
                    account.getStatus().name(),
                    account.getCreatedAt() != null ? account.getCreatedAt().toString() : null,
                    account.getUpdatedAt() != null ? account.getUpdatedAt().toString() : null,
                    account.getLastTransactionId()
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java ===
package com.pragmabank.infrastructure.controller;





import com.pragmabank.application.usecase.TransferFailedException;
import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.application.usecase.AccountInactiveException;
import com.pragmabank.application.usecase.AccountNotFoundException;
import com.pragmabank.application.usecase.TransferRequest;
import com.pragmabank.application.usecase.TransferResponse;
import com.pragmabank.application.usecase.TransferUseCase;
import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransferUseCase transferUseCase;

    public TransactionController(TransactionService transactionService, TransferUseCase transferUseCase) {
        this.transactionService = transactionService;
        this.transferUseCase = transferUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable UUID id) {
        return transactionService.findById(id)
                .map(transaction -> ResponseEntity.ok(TransactionResponse.fromDomain(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{transactionNumber}")
    public ResponseEntity<TransactionResponse> getTransactionByNumber(@PathVariable String transactionNumber) {
        return transactionService.findByTransactionNumber(transactionNumber)
                .map(transaction -> ResponseEntity.ok(TransactionResponse.fromDomain(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccount(
            @PathVariable UUID accountId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {
        List<Transaction> transactions = transactionService.findByAccountId(accountId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/source/{sourceAccountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsBySourceAccount(@PathVariable UUID sourceAccountId) {
        List<Transaction> transactions = transactionService.findBySourceAccountId(sourceAccountId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/target/{targetAccountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByTargetAccount(@PathVariable UUID targetAccountId) {
        List<Transaction> transactions = transactionService.findByTargetAccountId(targetAccountId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByType(@PathVariable TransactionType type) {
        List<Transaction> transactions = transactionService.findByType(type);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByStatus(@PathVariable TransactionStatus status) {
        List<Transaction> transactions = transactionService.findByStatus(status);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest request) {
        try {
            TransferResponse response = transferUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (TransferUseCase.AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (TransferUseCase.AccountInactiveException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (TransferUseCase.InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (TransferUseCase.TransferFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> createDeposit(@Valid @RequestBody CreateTransactionRequest request) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                generateTransactionNumber(),
                null,
                request.accountId(),
                request.amount(),
                TransactionType.DEPOSIT,
                java.time.LocalDateTime.now(),
                TransactionStatus.COMPLETED
        );
        Transaction created = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.fromDomain(created));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionResponse> createWithdrawal(@Valid @RequestBody CreateTransactionRequest request) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                generateTransactionNumber(),
                request.accountId(),
                null,
                request.amount(),
                TransactionType.WITHDRAWAL,
                java.time.LocalDateTime.now(),
                TransactionStatus.COMPLETED
        );
        Transaction created = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.fromDomain(created));
    }

    private String generateTransactionNumber() {
        return "TXN-" + System.currentTimeMillis() + "-" + 
               UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public record CreateTransactionRequest(
            @NotNull(message = "El ID de la cuenta es obligatorio")
            UUID accountId,

            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record TransactionResponse(
            UUID id,
            String transactionNumber,
            UUID sourceAccountId,
            UUID targetAccountId,
            BigDecimal amount,
            String type,
            String timestamp,
            String status,
            String failureReason
    ) {
        public static TransactionResponse fromDomain(Transaction transaction) {
            return new TransactionResponse(
                    transaction.getId(),
                    transaction.getTransactionNumber(),
                    transaction.getSourceAccountId(),
                    transaction.getTargetAccountId(),
                    transaction.getAmount(),
                    transaction.getType().name(),
                    transaction.getTimestamp() != null ? transaction.getTimestamp().toString() : null,
                    transaction.getStatus().name(),
                    transaction.getFailureReason()
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/config/SecurityConfig.java ===
package com.pragmabank.config;

import com.pragmabank.infrastructure.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/accounts/**").authenticated()
                .requestMatchers("/api/transactions/**").authenticated()
                .anyRequest().permitAll())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/security/JwtTokenUtil.java ===
package com.pragmabank.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public String generateToken(UUID userId, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(userId.toString())
                .claim("username", username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public UUID getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return UUID.fromString(claims.getSubject());
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("username", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilter.java ===
package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);

        if (StringUtils.hasText(token) && jwtTokenUtil.validateToken(token)) {
            UUID userId = jwtTokenUtil.getUserIdFromToken(token);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/security/OAuth2AuthenticationFilter.java ===
package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

/**
 * Filtro de autenticación OAuth2 para proteger endpoints.
 * Este filtro valida tokens OAuth2 y establece la autenticación en el contexto de seguridad.
 */
public class OAuth2AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private AuthenticationConverter authenticationConverter;

    public OAuth2AuthenticationFilter(RequestMatcher matcher,
                                       ClientRegistrationRepository clientRegistrationRepository) {
        super(matcher);
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                 HttpServletResponse response)
            throws OAuth2AuthenticationException {
        if (authenticationConverter == null) {
            throw new OAuth2AuthenticationException(
                new OAuthError("invalid_request", "Authentication converter not configured", null));
        }
        
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    public void setAuthenticationConverter(AuthenticationConverter converter) {
        this.authenticationConverter = converter;
    }
}

// === ARCHIVO: src/main/java/com/pragmabank/infrastructure/security/BasicAuthenticationFilter.java ===
package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Filtro de autenticación básica para proteger endpoints.
 * Este filtro valida credenciales en formato Basic Auth y establece la autenticación.
 */
public class BasicAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;

    public BasicAuthenticationFilter(RequestMatcher matcher, AuthenticationManager authenticationManager) {
        super(matcher, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                 HttpServletResponse response)
            throws AuthenticationException {
        String header = request.getHeader("Authorization");
        
        if (header == null || !header.startsWith("Basic ")) {
            return null;
        }
        
        try {
            String base64Credentials = header.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                                           StandardCharsets.UTF_8);
            
            String[] parts = credentials.split(":", 2);
            if (parts.length != 2) {
                return null;
            }
            
            String username = parts[0];
            String password = parts[1];
            
            UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
            
            return getAuthenticationManager().authenticate(authRequest);
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        if (successHandler != null) {
            successHandler.onAuthenticationSuccess(request, response, authResult);
        }
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        if (failureHandler != null) {
            failureHandler.onAuthenticationFailure(request, response, failed);
        }
    }

    public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }
}

// === ARCHIVO: src/test/java/com/pragmabank/domain/service/AccountServiceTest.java ===
package com.pragmabank.domain.service;


import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.port.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AccountService")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account cuentaPrueba;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        cuentaPrueba = new Account(
            UUID.randomUUID(),
            "ACC-2024-001",
            customerId,
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
    }

    @Test
    @DisplayName("Crear cuenta - debe persistir y retornar cuenta con ID asignado")
    void crearCuenta_debePersistirYRetornarCuenta() {
        when(accountRepository.save(any(Account.class))).thenReturn(cuentaPrueba);

        Account resultado = accountService.crearCuenta(customerId, "ACC-2024-001", new BigDecimal("1000.00"));

        assertNotNull(resultado);
        assertEquals("ACC-2024-001", resultado.getAccountNumber());
        assertEquals(customerId, resultado.getCustomerId());
        assertEquals(AccountStatus.ACTIVE, resultado.getStatus());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("Buscar cuenta por ID - debe retornar cuenta cuando existe")
    void buscarPorId_debeRetornarCuentaCuandoExiste() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));

        Optional<Account> resultado = accountService.buscarPorId(cuentaPrueba.getId());

        assertTrue(resultado.isPresent());
        assertEquals(cuentaPrueba.getId(), resultado.get().getId());
    }

    @Test
    @DisplayName("Buscar cuenta por ID - debe retornar vacío cuando no existe")
    void buscarPorId_debeRetornarVacioCuandoNoExiste() {
        UUID idInexistente = UUID.randomUUID();
        when(accountRepository.findById(idInexistente)).thenReturn(Optional.empty());

        Optional<Account> resultado = accountService.buscarPorId(idInexistente);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Buscar cuenta por número - debe retornar cuenta cuando existe")
    void buscarPorNumero_debeRetornarCuentaCuandoExiste() {
        when(accountRepository.findByAccountNumber("ACC-2024-001")).thenReturn(Optional.of(cuentaPrueba));

        Optional<Account> resultado = accountService.buscarPorNumero("ACC-2024-001");

        assertTrue(resultado.isPresent());
        assertEquals("ACC-2024-001", resultado.get().getAccountNumber());
    }

    @Test
    @DisplayName("Depositar - debe incrementar balance cuando cuenta está activa")
    void depositar_debeIncrementarBalanceCuandoCuentaActiva() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.depositar(cuentaPrueba.getId(), new BigDecimal("500.00"));

        assertEquals(new BigDecimal("1500.00"), resultado.getBalance());
    }

    @Test
    @DisplayName("Depositar - debe lanzar excepción cuando cantidad es negativa")
    void depositar_debeLanzarExcepcionCuandoCantidadNegativa() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));

        assertThrows(IllegalArgumentException.class, () -> 
            accountService.depositar(cuentaPrueba.getId(), new BigDecimal("-100.00"))
        );
    }

    @Test
    @DisplayName("Retirar - debe decrementar balance cuando hay fondos suficientes")
    void retirar_debeDecrementarBalanceCuandoHayFondosSuficientes() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.retirar(cuentaPrueba.getId(), new BigDecimal("300.00"));

        assertEquals(new BigDecimal("700.00"), resultado.getBalance());
    }

    @Test
    @DisplayName("Retirar - debe lanzar excepción cuando fondos insuficientes")
    void retirar_debeLanzarExcepcionCuandoFondosInsuficientes() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));

        assertThrows(Account.InsufficientFundsException.class, () -> 
            accountService.retirar(cuentaPrueba.getId(), new BigDecimal("5000.00"))
        );
    }

    @Test
    @DisplayName("Freezar cuenta - debe cambiar estado a FROZEN")
    void freezeAccount_debeCambiarEstadoAFrozen() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.freezeAccount(cuentaPrueba.getId());

        assertEquals(AccountStatus.FROZEN, resultado.getStatus());
    }

    @Test
    @DisplayName("Cerrar cuenta - debe cambiar estado a CLOSED")
    void closeAccount_debeCambiarEstadoAClosed() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.closeAccount(cuentaPrueba.getId());

        assertEquals(AccountStatus.CLOSED, resultado.getStatus());
    }

    @Test
    @DisplayName("Buscar cuentas por cliente - debe retornar lista de cuentas del cliente")
    void buscarPorCliente_debeRetornarListaDeCuentas() {
        when(accountRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cuentaPrueba));

        Optional<Account> resultado = accountService.buscarPorCliente(customerId);

        assertTrue(resultado.isPresent());
        assertEquals(customerId, resultado.get().getCustomerId());
    }
}

// === ARCHIVO: src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java ===
package com.pragmabank.domain.service;


import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.port.AccountRepository;
import com.pragmabank.domain.port.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para TransactionService")
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account cuentaOrigen;
    private Account cuentaDestino;
    private Transaction transaccionPrueba;

    @BeforeEach
    void setUp() {
        UUID customerId = UUID.randomUUID();
        cuentaOrigen = new Account(
            UUID.randomUUID(),
            "ACC-ORIGEN-001",
            customerId,
            new BigDecimal("5000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
        cuentaDestino = new Account(
            UUID.randomUUID(),
            "ACC-DESTINO-001",
            UUID.randomUUID(),
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
        transaccionPrueba = new Transaction(
            UUID.randomUUID(),
            "TXN-2024-001",
            cuentaOrigen.getId(),
            cuentaDestino.getId(),
            new BigDecimal("500.00"),
            TransactionType.TRANSFER,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
    }

    @Test
    @DisplayName("Crear transacción - debe persistir y retornar transacción con estado PENDING")
    void crearTransaccion_debePersistirYRetornarTransaccion() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaccionPrueba);

        Transaction resultado = transactionService.crearTransaccion(
            cuentaOrigen.getId(),
            cuentaDestino.getId(),
            new BigDecimal("500.00"),
            TransactionType.TRANSFER
        );

        assertNotNull(resultado);
        assertEquals(TransactionStatus.PENDING, resultado.getStatus());
        assertEquals(TransactionType.TRANSFER, resultado.getType());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Ejecutar transferencia - debe completar transacción y actualizar saldos")
    void ejecutarTransferencia_debeCompletarTransaccionYActualizarSaldos() {
        when(accountRepository.findById(cuentaOrigen.getId())).thenReturn(Optional.of(cuentaOrigen));
        when(accountRepository.findById(cuentaDestino.getId())).thenReturn(Optional.of(cuentaDestino));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction resultado = transactionService.ejecutarTransferencia(
            cuentaOrigen.getId(),
            cuentaDestino.getId(),
            new BigDecimal("500.00")
        );

        assertEquals(TransactionStatus.COMPLETED, resultado.getStatus());
        assertEquals(new BigDecimal("4500.00"), cuentaOrigen.getBalance());
        assertEquals(new BigDecimal("1500.00"), cuentaDestino.getBalance());
    }

    @Test
    @DisplayName("Ejecutar transferencia - debe fallar cuando cuenta origen no existe")
    void ejecutarTransferencia_debeFallarCuandoCuentaOrigenNoExiste() {
        UUID idInexistente = UUID.randomUUID();
        when(accountRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> 
            transactionService.ejecutarTransferencia(idInexistente, cuentaDestino.getId(), new BigDecimal("500.00"))
        );
    }

    @Test
    @DisplayName("Ejecutar transferencia - debe fallar cuando fondos insuficientes")
    void ejecutarTransferencia_debeFallarCuandoFondosInsuficientes() {
        when(accountRepository.findById(cuentaOrigen.getId())).thenReturn(Optional.of(cuentaOrigen));
        when(accountRepository.findById(cuentaDestino.getId())).thenReturn(Optional.of(cuentaDestino));

        assertThrows(Account.InsufficientFundsException.class, () -> 
            transactionService.ejecutarTransferencia(
                cuentaOrigen.getId(),
                cuentaDestino.getId(),
                new BigDecimal("10000.00")
            )
        );
    }

    @Test
    @DisplayName("Completar transacción - debe cambiar estado a COMPLETED")
    void completarTransaccion_debeCambiarEstadoACompleted() {
        when(transactionRepository.findById(transaccionPrueba.getId())).thenReturn(Optional.of(transaccionPrueba));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction resultado = transactionService.completarTransaccion(transaccionPrueba.getId());

        assertEquals(TransactionStatus.COMPLETED, resultado.getStatus());
    }

    @Test
    @DisplayName("Fallar transacción - debe cambiar estado a FAILED y guardar razón")
    void failTransaccion_debeCambiarEstadoAFailedYGuardarRazon() {
        String razon = "Fondos insuficientes";
        when(transactionRepository.findById(transaccionPrueba.getId())).thenReturn(Optional.of(transaccionPrueba));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction resultado = transactionService.failTransaccion(transaccionPrueba.getId(), razon);

        assertEquals(TransactionStatus.FAILED, resultado.getStatus());
        assertEquals(razon, resultado.getFailureReason());
    }

    @Test
    @DisplayName("Buscar transacción por ID - debe retornar transacción cuando existe")
    void buscarPorId_debeRetornarTransaccionCuandoExiste() {
        when(transactionRepository.findById(transaccionPrueba.getId())).thenReturn(Optional.of(transaccionPrueba));

        Optional<Transaction> resultado = transactionService.buscarPorId(transaccionPrueba.getId());

        assertTrue(resultado.isPresent());
        assertEquals(transaccionPrueba.getId(), resultado.get().getId());
    }

    @Test
    @DisplayName("Buscar transacción por número - debe retornar transacción cuando existe")
    void buscarPorNumero_debeRetornarTransaccionCuandoExiste() {
        when(transactionRepository.findByTransactionNumber("TXN-2024-001")).thenReturn(Optional.of(transaccionPrueba));

        Optional<Transaction> resultado = transactionService.buscarPorNumero("TXN-2024-001");

        assertTrue(resultado.isPresent());
        assertEquals("TXN-2024-001", resultado.get().getTransactionNumber());
    }

    @Test
    @DisplayName("Es transferencia - debe retornar true cuando tipo es TRANSFER")
    void isTransfer_debeRetornarTrueCuandoTipoEsTransfer() {
        assertTrue(transaccionPrueba.isTransfer());
    }

    @Test
    @DisplayName("Es transferencia - debe retornar false cuando tipo no es TRANSFER")
    void isTransfer_debeRetornarFalseCuandoTipoNoEsTransfer() {
        Transaction deposito = new Transaction(
            UUID.randomUUID(),
            "TXN-2024-002",
            null,
            cuentaDestino.getId(),
            new BigDecimal("100.00"),
            TransactionType.DEPOSIT,
            LocalDateTime.now(),
            TransactionStatus.COMPLETED,
            null
        );

        assertFalse(deposito.isTransfer());
    }
}

// === ARCHIVO: src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java ===
package com.pragmabank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@DisplayName("Pruebas de integración para AccountController")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private Account cuentaPrueba;
    private UUID cuentaId;

    @BeforeEach
    void setUp() {
        cuentaId = UUID.randomUUID();
        cuentaPrueba = new Account(
            cuentaId,
            "ACC-2024-001",
            UUID.randomUUID(),
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
    }

    @Test
    @DisplayName("GET /api/cuentas/{id} - debe retornar cuenta cuando existe")
    void getAccount_debeRetornarCuentaCuandoExiste() throws Exception {
        when(accountService.buscarPorId(cuentaId)).thenReturn(Optional.of(cuentaPrueba));

        mockMvc.perform(get("/api/cuentas/{id}", cuentaId.toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cuentaId.toString()))
            .andExpect(jsonPath("$.accountNumber").value("ACC-2024-001"))
            .andExpect(jsonPath("$.balance").value(1000.00))
            .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    @DisplayName("GET /api/cuentas/{id} - debe retornar 404 cuando no existe")
    void getAccount_debeRetornar404CuandoNoExiste() throws Exception {
        UUID idInexistente = UUID.randomUUID();
        when(accountService.buscarPorId(idInexistente)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cuentas/{id}", idInexistente.toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/cuentas/numero/{accountNumber} - debe retornar cuenta por número")
    void getAccountByNumber_debeRetornarCuentaPorNumero() throws Exception {
        when(accountService.buscarPorNumero("ACC-2024-001")).thenReturn(Optional.of(cuentaPrueba));

        mockMvc.perform(get("/api/cuentas/numero/{accountNumber}", "ACC-2024-001"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accountNumber").value("ACC-2024-001"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas - debe crear cuenta exitosamente")
    void createAccount_debeCrearCuentaExitosamente() throws Exception {
        when(accountService.crearCuenta(any(), eq("ACC-2024-001"), any(BigDecimal.class)))
            .thenReturn(cuentaPrueba);

        String requestBody = "{\"customerId\":\"" + cuentaPrueba.getCustomerId() + "\",\"accountNumber\":\"ACC-2024-001\",\"initialBalance\":1000.00}";

        mockMvc.perform(post("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.accountNumber").value("ACC-2024-001"))
            .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas - debe retornar 400 cuando datos inválidos")
    void createAccount_debeRetornar400CuandoDatosInvalidos() throws Exception {
        String requestBody = "{\"accountNumber\":\"\",\"initialBalance\":-100}";

        mockMvc.perform(post("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas/{id}/depositar - debe realizar depósito exitosamente")
    void deposit_debeRealizarDepositoExitosamente() throws Exception {
        when(accountService.depositar(eq(cuentaId), any(BigDecimal.class))).thenReturn(cuentaPrueba);

        String requestBody = "{\"amount\":500.00}";

        mockMvc.perform(post("/api/cuentas/{id}/depositar", cuentaId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cuentaId.toString()));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas/{id}/retirar - debe realizar retiro exitosamente")
    void withdraw_debeRealizarRetiroExitosamente() throws Exception {
        when(accountService.retirar(eq(cuentaId), any(BigDecimal.class))).thenReturn(cuentaPrueba);

        String requestBody = "{\"amount\":200.00}";

        mockMvc.perform(post("/api/cuentas/{id}/retirar", cuentaId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cuentaId.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/cuentas/{id}/freezar - debe freezar cuenta con rol ADMIN")
    void freeze_debeFreezarCuentaConRolAdmin() throws Exception {
        Account cuentaFrozen = new Account(
            cuentaId,
            "ACC-2024-001",
            cuentaPrueba.getCustomerId(),
            new BigDecimal("1000.00"),
            AccountStatus.FROZEN,
            LocalDateTime.now(),
            null,
            null
        );
        when(accountService.freezeAccount(cuentaId)).thenReturn(cuentaFrozen);

        mockMvc.perform(post("/api/cuentas/{id}/freezar", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("FROZEN"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas/{id}/freezar - debe retornar 403 con rol USER")
    void freeze_debeRetornar403ConRolUser() throws Exception {
        mockMvc.perform(post("/api/cuentas/{id}/freezar", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("DELETE /api/cuentas/{id} - debe eliminar cuenta con rol ADMIN")
    void delete_debeEliminarCuentaConRolAdmin() throws Exception {
        mockMvc.perform(delete("/api/cuentas/{id}", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("DELETE /api/cuentas/{id} - debe retornar 403 con rol USER")
    void delete_debeRetornar403ConRolUser() throws Exception {
        mockMvc.perform(delete("/api/cuentas/{id}", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isForbidden());
    }
}

// === ARCHIVO: src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java ===
package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Authentication authentication;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenUtil, authenticationManager);
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal_withValidToken_shouldAuthenticateUser() throws Exception {
        // Arrange
        String validToken = "Bearer valid.jwt.token";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn(validToken);
        when(jwtTokenUtil.extractUsername(validToken.substring(7))).thenReturn(username);
        when(jwtTokenUtil.validateToken(validToken.substring(7), username)).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void doFilterInternal_withNoAuthHeader_shouldNotAuthenticate() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void doFilterInternal_withInvalidToken_shouldNotAuthenticate() throws Exception {
        // Arrange
        String invalidToken = "Bearer invalid.token";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn(invalidToken);
        when(jwtTokenUtil.extractUsername(invalidToken.substring(7))).thenReturn(username);
        when(jwtTokenUtil.validateToken(invalidToken.substring(7), username)).thenReturn(false);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void shouldNotFilter_internalEndpoints_shouldSkipFilter() {
        // Arrange
        when(request.getRequestURI()).thenReturn("/actuator/health");
        
        // Act
        boolean result = jwtAuthenticationFilter.shouldNotFilter(request);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void doFilterInternal_withExpiredToken_shouldNotAuthenticate() throws Exception {
        // Arrange
        String expiredToken = "Bearer expired.jwt.token";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn(expiredToken);
        when(jwtTokenUtil.extractUsername(expiredToken.substring(7))).thenReturn(username);
        when(jwtTokenUtil.validateToken(expiredToken.substring(7), username)).thenReturn(false);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }
}
```
