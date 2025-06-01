# Favorites API - Clean Architecture com Spring Boot e MongoDB

Este projeto implementa uma API de gerenciamento de produtos favoritos utilizando a arquitetura limpa (Clean Architecture), MongoDB como banco de dados e Docker para orquestração do ambiente.

---

## 🌐 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- MongoDB 6
- Maven 3.8
- Docker e Docker Compose
- Swagger/OpenAPI

---

## 🚀 Como executar com Docker

### 1. Clonar o projeto:
```bash
git clone https://github.com/JhonMarques/favorites_clean_arch.git
cd favorites_clean_arch
```

### 2. Rodar os containers:
```bash
docker-compose up --build
```

Esse comando:
- Faz o build da imagem da API Java.
- Sobe o MongoDB.
- Inicializa a API em `http://localhost:8080`.

### 3. Testar no navegador:
Abra o Swagger:
```
http://localhost:8080/swagger-ui/index.html
```


---

## 🔎 Estrutura do Projeto

### `adapters`
- **inbound**: Controllers e mappers de entrada da API.
- **outbound**: Conexões com banco de dados (mappers e projections).

### `application`
- **usecases**: Casos de uso da aplicação (ex: CreateFavoritesProduct).

### `domain`
- **entities**: Entidades de negócio.
- **repositories**: Interfaces que definem regras de acesso aos dados.
- **validators**: Validações de regras de negócio.

### `infrastructure`
- **mongo.document**: Modelos de documentos do Mongo.
- **persistence**: Implementação concreta dos repositórios.

### `config`
- **exceptions**: Tratamento global de exceções.

---

## ⚖️ Configuração dinâmica do MongoDB
No arquivo `application.yml`, a URI do MongoDB é configurada de forma que:
- Se estiver rodando **fora do Docker**, usa `localhost:27017`.
- Se estiver rodando **no Docker**, usa `mongo:27017`, via variável de ambiente.

```yaml
spring:
  data:
    mongodb:
      uri: ${SPRING_DATA_MONGODB_URI:mongodb://localhost:27017/favorites-db}
```

---

## ✅ Testes
- Os testes são executados com `mvn test`.
- Implementações BDD com Cucumber e cobertura de exceções.

---

## 🎓 Autor
**Jhonatas Katayama Marques**  
[LinkedIn](https://www.linkedin.com/in/jhonatas-katayama)

---

## ✨ Contribuição
Sinta-se à vontade para abrir issues e pull requests. O feedback é bem-vindo!
