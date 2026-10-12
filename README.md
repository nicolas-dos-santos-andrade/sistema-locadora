# Sistema Locadora

Sistema web de gerenciamento de locadora de filmes, em desenvolvimento incremental.
Esta primeira etapa contém somente a base do backend: não há endpoints de negócio,
banco de dados, Docker Compose, frontend ou CI/CD.

## Tecnologias e decisões

- Java 25 e Spring Boot 3.5.16, uma versão estável da linha 3.5 compatível com Java 25.
- Maven 3.9.11 via Maven Wrapper: dispensa a instalação global do Maven e padroniza sua versão.
- Spring Web para a futura API HTTP; Spring Data JPA para futura persistência;
  Validation para futura validação das entradas.
- Spring Boot Starter Test, com JUnit 5 e AssertJ, para testes automatizados.
- Spotless com Google Java Format para formatação Java (dois espaços), e Checkstyle
  para verificar nomes, imports e ausência de tabulações.

O parent do Spring Boot gerencia as versões das dependências e dos plugins Maven
que ele conhece. Os plugins de qualidade têm versões explicitamente fixadas.
Maven não utiliza um lockfile nativo; o `pom.xml` e os arquivos do Wrapper devem
ser versionados para registrar a configuração do build.

A arquitetura será um monólito modular: uma aplicação executável, com módulos
organizados por negócio. Filmes será o primeiro módulo; Catálogo, Pessoas,
Locações e Financeiro ainda não foram implementados.

## Estrutura atual

```text
backend/
├── pom.xml
├── checkstyle.xml
├── mvnw
├── mvnw.cmd
├── .mvn/wrapper/maven-wrapper.properties
└── src/
    ├── main/
    │   ├── java/br/com/locadora/LocadoraApplication.java
    │   └── resources/application.yaml
    └── test/
        └── java/br/com/locadora/LocadoraApplicationTest.java
```

Na raiz ficam este README, o `AGENTS.md` e o `.gitignore`.
Os diretórios `.git/`, `.agents/`, `.codex/` e `.aws/` são metadados do workspace.
Pacotes de negócio e diretórios de assets serão introduzidos quando houver código
ou arquivos para eles.

## Pré-requisitos e instalação

Instale um JDK 25 apropriado ao seu sistema operacional. Configure `JAVA_HOME`
para o diretório desse JDK, caso necessário, e confirme com `java -version`.
Não é necessário instalar Maven globalmente. No primeiro uso, o Wrapper baixa
Maven e as dependências; portanto, é necessário acesso à internet. Em Linux/macOS,
o download do Wrapper usa `curl` ou `wget`.

Todos os comandos abaixo devem ser executados dentro de `backend/`:

```bash
cd backend
./mvnw --version
```

O segundo comando confirma as versões de Maven e Java utilizadas. No Windows,
substitua `./mvnw` por `mvnw.cmd` (ou `./mvnw.cmd` no PowerShell).

## Executar localmente

```bash
./mvnw spring-boot:run
```

Compila e inicia a aplicação com servidor HTTP na porta 8080. Use `Ctrl+C` para
encerrar. Como não há controllers nesta etapa, acessar `/` retorna HTTP 404;
isso não significa que o servidor falhou.

O starter JPA normalmente tenta configurar uma conexão com banco. Por enquanto,
`application.yaml` exclui explicitamente a autoconfiguração de DataSource,
Hibernate JPA e repositórios JPA. Isso permite executar e testar a base sem
PostgreSQL ou banco em memória. Essas exclusões deverão ser removidas quando a
persistência for implementada.

## Testes e qualidade

```bash
./mvnw test
./mvnw spotless:check checkstyle:check
./mvnw verify
```

- `test`: executa os testes JUnit 5; o ciclo também verifica estilo na fase `validate`.
- `spotless:check checkstyle:check`: verifica somente formatação e regras de código.
- `verify`: verifica estilo, compila, executa testes e gera o JAR executável.

Para aplicar a formatação automaticamente:

```bash
./mvnw spotless:apply
```

Esse comando modifica a formatação dos arquivos Java. Os testes ficam em
`src/test/java`, acompanhando os pacotes da aplicação, com nomes terminados em
`Test`. O teste inicial sobe o contexto Spring e o servidor em porta aleatória,
verificando a presença da aplicação e a ausência de DataSource.

## Gerar e executar o artefato

```bash
./mvnw package
java -jar target/locadora-0.0.1-SNAPSHOT.jar
```

`package` verifica estilo, compila, executa testes e empacota a aplicação.
O JAR inclui suas dependências e pode ser executado com Java 25, sem Maven.
Ainda não há configuração de infraestrutura para publicação em produção.

## Configuração e segurança

Esta etapa não exige variáveis de ambiente ou credenciais. Arquivos `.env`,
configurações locais das IDEs e artefatos em `target/` são ignorados pelo Git.
Não versione senhas, chaves privadas ou arquivos de ambiente preenchidos.
