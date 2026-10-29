# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Optimización y Seguridad en un Sistema de Gestión de Cuentas Bancarias**.

| | |
|---|---|
| Tema | Especialista en Framework con Proficiencia en Seguridad y Legibilidad de Código |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa 3.5.6
- org.springframework.boot:spring-boot-starter-security 3.5.6
- org.springframework.boot:spring-boot-starter-validation 3.5.6
- io.jsonwebtoken:jjwt-api 0.12.5
- io.jsonwebtoken:jjwt-impl 0.12.5
- io.jsonwebtoken:jjwt-jackson 0.12.5
- org.springframework.boot:spring-boot-starter-oauth2-client 3.5.6
- org.postgresql:postgresql 42.7.3
- org.springframework.boot:spring-boot-starter-test n/a
- org.springframework.security:spring-security-test n/a
- org.junit.jupiter:junit-jupiter-api n/a
- org.mockito:mockito-core n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Refactorización para Legibilidad**: Código refactorizado que es más legible y no redundante.
- **Fase 2 — Implementación de Mecanismos de Autenticación**: Sistema con dos mecanismos de autenticación adicionales implementados.
- **Fase 3 — Evaluación de Seguridad**: Informe de evaluación de seguridad con hallazgos y propuestas de solución.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragmabank/config/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/pragmabank/infrastructure/security/JwtTokenUtil.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/pragmabank/infrastructure/security/OAuth2AuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/pragmabank/infrastructure/security/BasicAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (53)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragmabank/domain/service/AccountService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragmabank/domain/service/TransactionService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragmabank/Application.java` — `ApplicationProperties.getJwt`
      Se invoca `getJwt` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/Application.java` — `ApplicationProperties.getDatasource`
      Se invoca `getDatasource` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/CreateAccountUseCase.java` — `CreateAccountRequest.accountNumber`
      Se invoca `accountNumber` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/CreateAccountUseCase.java` — `CreateAccountRequest.customerId`
      Se invoca `customerId` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `AccountService.findById`
      Se invoca `findById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransferRequest.sourceAccountId`
      Se invoca `sourceAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransferRequest.targetAccountId`
      Se invoca `targetAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransferRequest.amount`
      Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java` — `TransactionService.save`
      Se invoca `save` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.findById`
      Se invoca `findById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.findByAccountNumber`
      Se invoca `findByAccountNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.findByCustomerId`
      Se invoca `findByCustomerId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `WithdrawRequest.customerId`
      Se invoca `customerId` sobre `WithdrawRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `WithdrawRequest.initialBalance`
      Se invoca `initialBalance` sobre `WithdrawRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.save`
      Se invoca `save` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `WithdrawRequest.amount`
      Se invoca `amount` sobre `WithdrawRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java` — `AccountService.deleteById`
      Se invoca `deleteById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findById`
      Se invoca `findById` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByTransactionNumber`
      Se invoca `findByTransactionNumber` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByAccountId`
      Se invoca `findByAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findBySourceAccountId`
      Se invoca `findBySourceAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByTargetAccountId`
      Se invoca `findByTargetAccountId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByType`
      Se invoca `findByType` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.findByStatus`
      Se invoca `findByStatus` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `CreateTransactionRequest.accountId`
      Se invoca `accountId` sobre `CreateTransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `CreateTransactionRequest.amount`
      Se invoca `amount` sobre `CreateTransactionRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java` — `TransactionService.save`
      Se invoca `save` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.crearCuenta`
      Se invoca `crearCuenta` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.buscarPorId`
      Se invoca `buscarPorId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `Account.isPresent`
      Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `Account.get`
      Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `Account.isEmpty`
      Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.buscarPorNumero`
      Se invoca `buscarPorNumero` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.depositar`
      Se invoca `depositar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.retirar`
      Se invoca `retirar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java` — `AccountService.buscarPorCliente`
      Se invoca `buscarPorCliente` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.crearTransaccion`
      Se invoca `crearTransaccion` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.ejecutarTransferencia`
      Se invoca `ejecutarTransferencia` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.completarTransaccion`
      Se invoca `completarTransaccion` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.failTransaccion`
      Se invoca `failTransaccion` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.buscarPorId`
      Se invoca `buscarPorId` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `Transaction.isPresent`
      Se invoca `isPresent` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `Transaction.get`
      Se invoca `get` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java` — `TransactionService.buscarPorNumero`
      Se invoca `buscarPorNumero` sobre `TransactionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.buscarPorId`
      Se invoca `buscarPorId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.buscarPorNumero`
      Se invoca `buscarPorNumero` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.crearCuenta`
      Se invoca `crearCuenta` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.depositar`
      Se invoca `depositar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java` — `AccountService.retirar`
      Se invoca `retirar` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java` — `JwtTokenUtil.extractUsername`
      Se invoca `extractUsername` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java` — `JwtAuthenticationFilter.shouldNotFilter`
      Se invoca `shouldNotFilter` sobre `JwtAuthenticationFilter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (24)

- `pom.xml`
- `src/main/java/com/pragmabank/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragmabank/domain/model/Account.java`
- `src/main/java/com/pragmabank/domain/model/Transaction.java`
- `src/main/java/com/pragmabank/domain/port/AccountRepository.java`
- `src/main/java/com/pragmabank/domain/port/TransactionRepository.java`
- `src/main/java/com/pragmabank/domain/service/AccountService.java`
- `src/main/java/com/pragmabank/domain/service/TransactionService.java`
- `src/main/java/com/pragmabank/infrastructure/adapter/AccountJpaRepository.java`
- `src/main/java/com/pragmabank/infrastructure/adapter/TransactionJpaRepository.java`
- `src/main/java/com/pragmabank/application/usecase/CreateAccountUseCase.java`
- `src/main/java/com/pragmabank/application/usecase/TransferUseCase.java`
- `src/main/java/com/pragmabank/infrastructure/controller/AccountController.java`
- `src/main/java/com/pragmabank/infrastructure/controller/TransactionController.java`
- `src/main/java/com/pragmabank/config/SecurityConfig.java`
- `src/main/java/com/pragmabank/infrastructure/security/JwtTokenUtil.java`
- `src/main/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilter.java`
- `src/main/java/com/pragmabank/infrastructure/security/OAuth2AuthenticationFilter.java`
- `src/main/java/com/pragmabank/infrastructure/security/BasicAuthenticationFilter.java`
- `src/test/java/com/pragmabank/domain/service/AccountServiceTest.java`
- `src/test/java/com/pragmabank/domain/service/TransactionServiceTest.java`
- `src/test/java/com/pragmabank/infrastructure/controller/AccountControllerTest.java`
- `src/test/java/com/pragmabank/infrastructure/security/JwtAuthenticationFilterTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragmabank/domain`
- `src/main/java/com/pragmabank/application`
- `src/main/java/com/pragmabank/infrastructure`
- `src/main/java/com/pragmabank/config`
- `src/main/resources`
- `src/test/java/com/pragmabank`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced L2
- Brecha que el reto ataca: Experto en el marco de trabajo (framework) mas relevante en su lenguaje de programación principal, lo que le permite escribir código más legible y reducir la redundancia. Ha implementado al menos dos mecanismos de autenticación e identifica el uso de un JWT, Experto en el marco de trabajo (framework) mas relevante en su lenguaje de programación principal, lo que le permite escribir código más legible y reducir la redundancia. Ha implementado al menos dos mecanismos de autenticación e identifica el uso de un JWT

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
