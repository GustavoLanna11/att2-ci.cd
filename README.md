# Disciplinas API - Projeto CI/CD

API REST desenvolvida com Spring Boot para gerenciamento de disciplinas do curso DSM (Desenvolvimento de Software Multiplataforma), incluindo integração com CI/CD através do GitHub Actions.

## 📋 Características

- ✅ Spring Boot 3.2.0 com Java 17
- ✅ Banco de dados simulado com HashMap
- ✅ API REST com rotas GET e POST
- ✅ Testes unitários implementados
- ✅ Swagger/OpenAPI para documentação
- ✅ CI/CD configurado com GitHub Actions
- ✅ Geração automática de artefato JAR

## 🚀 Endpoints da API

### Base URL
```
http://localhost:8080/api/disciplinas
```

### Rotas Disponíveis

#### 1. Listar todas as disciplinas
```http
GET /api/disciplinas
```

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Desenvolvimento de Aplicações Modernas",
    "cargaHoraria": 80,
    "professor": "Prof. João Silva",
    "descricao": "Disciplina focada em desenvolvimento de aplicações usando tecnologias modernas",
    "dataCriacao": "2024-01-01T10:00:00"
  },
  {
    "id": 2,
    "nome": "Banco de Dados Avançado",
    "cargaHoraria": 60,
    "professor": "Prof. Maria Santos",
    "descricao": "Estudo de técnicas avançadas de modelagem e otimização de bancos de dados",
    "dataCriacao": "2024-01-01T10:00:00"
  }
]
```

#### 2. Buscar disciplina por ID
```http
GET /api/disciplinas/{id}
```

**Exemplo:**
```http
GET /api/disciplinas/1
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de Aplicações Modernas",
  "cargaHoraria": 80,
  "professor": "Prof. João Silva",
  "descricao": "Disciplina focada em desenvolvimento de aplicações usando tecnologias modernas",
  "dataCriacao": "2024-01-01T10:00:00"
}
```

#### 3. Criar nova disciplina
```http
POST /api/disciplinas
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Programação Web",
  "cargaHoraria": 60,
  "professor": "Prof. Carlos",
  "descricao": "Desenvolvimento web com HTML, CSS e JavaScript"
}
```

**Resposta:**
```json
{
  "id": 3,
  "nome": "Programação Web",
  "cargaHoraria": 60,
  "professor": "Prof. Carlos",
  "descricao": "Desenvolvimento web com HTML, CSS e JavaScript",
  "dataCriacao": "2024-01-01T10:30:00"
}
```

## 📚 Documentação Swagger

Acesse a interface interativa do Swagger UI para testar os endpoints:

```
http://localhost:8080/swagger-ui.html
```

Documentação OpenAPI (JSON):
```
http://localhost:8080/api-docs
```

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Web**
- **Spring Boot Validation**
- **springdoc-openapi-starter-webmvc-ui** (Swagger)
- **JUnit 5** (Testes)
- **MockMvc** (Testes de API)
- **Maven**
- **GitHub Actions**

## 📦 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+ instalado
- Git

### Passos

1. **Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd att2-ci.cd
```

2. **Compile o projeto:**
```bash
mvn clean install
```

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

4. **A aplicação estará disponível em:**
```
http://localhost:8080
```

## 🧪 Executar Testes

Execute todos os testes unitários:

```bash
mvn test
```

Execute testes com relatório detalhado:

```bash
mvn test -X
```

## 🔄 CI/CD com GitHub Actions

O projeto possui um workflow de CI/CD configurado que:

1. **Executa em eventos:**
   - Push nas branches `main` e `develop`
   - Pull requests para `main` e `develop`

2. **Etapas do pipeline:**
   - ✅ Checkout do código
   - ✅ Configuração do JDK 17
   - ✅ Execução dos testes unitários
   - ✅ Compilação do projeto com Maven
   - ✅ Geração do artefato JAR
   - ✅ Upload do JAR como artefato

3. **Artefato gerado:**
   - Arquivo: `disciplinas-api-1.0.0.jar`
   - Localização: `target/disciplinas-api-1.0.0.jar`
   - Disponível por 30 dias no GitHub Actions

### Visualizar o pipeline

Acesse a aba **Actions** no GitHub para ver o histórico de execuções e baixar os artefatos gerados.

## 📁 Estrutura do Projeto

```
att2-ci.cd/
├── .github/
│   └── workflows/
│       └── ci-cd.yml          # Pipeline CI/CD
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/disciplinas/
│   │   │       ├── DisciplinasApplication.java
│   │   │       ├── config/
│   │   │       │   └── OpenApiConfig.java
│   │   │       ├── controller/
│   │   │       │   └── DisciplinaController.java
│   │   │       ├── model/
│   │   │       │   └── Disciplina.java
│   │   │       └── repository/
│   │   │           └── DisciplinaRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/disciplinas/
│               ├── DisciplinasApplicationTests.java
│               ├── controller/
│               │   └── DisciplinaControllerTest.java
│               └── repository/
│                   └── DisciplinaRepositoryTest.java
├── .gitignore
├── pom.xml                     # Configuração Maven
└── README.md                   # Este arquivo
```

## 🎯 Testes Implementados

### DisciplinaControllerTest
- ✅ Listar todas as disciplinas
- ✅ Buscar disciplina por ID existente
- ✅ Retornar 404 para ID inexistente
- ✅ Criar nova disciplina
- ✅ Validar dados inválidos

### DisciplinaRepositoryTest
- ✅ Inicializar com duas disciplinas padrão
- ✅ Buscar disciplina por ID
- ✅ Retornar vazio para ID inexistente
- ✅ Salvar nova disciplina com ID gerado
- ✅ Listar todas as disciplinas

## 👤 Autor

Projeto desenvolvido para a disciplina de CI/CD - DSM

## 📄 Licença

Este projeto está sob a licença especificada no arquivo LICENSE.
