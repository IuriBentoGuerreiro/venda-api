# 📊 Desafio Venda - Sistema de Gestão de Vendas

Este projeto é um exemplo de aplicação **Spring Boot 4** para gerenciamento de vendas, com **CRUD completo**, geração de **resumos por vendedor** e **testes unitários** utilizando **H2 em memória**. Ideal para estudo, demonstração ou como base para projetos maiores.  

---

## 🛠 Tecnologias

- **Backend:** Java 17, Spring Boot 4, Spring Data JPA, Spring MVC, Spring Validation  
- **Banco de Dados:** H2 (em memória, para testes e demonstração)  
- **Testes:** JUnit 5, Mockito  
- **Documentação de API:** SpringDoc OpenAPI (Swagger UI)  
- **Gerenciamento de Dependências:** Maven  

---

## 🚀 Funcionalidades

- **CRUD de Vendas**
  - Criar, listar, atualizar e deletar vendas  
  - Cada venda possui: `valor`, `dataVenda`, `vendedorId`, `vendedorNome`  

- **Resumo por Vendedor**
  - Endpoint que retorna:
    - Nome do vendedor
    - Total de vendas
    - Valor total vendido
    - Média diária de vendas (calculada pelo intervalo de datas)

- **Testes Unitários**
  - Cobertura do método de resumo por vendedor
  - Validação de cálculos de totais e média diária
  - Utiliza H2 em memória para testes isolados

---

## 📦 Configuração do Projeto

1. **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/desafio-venda.git
cd desafio-venda

Rodar a aplicação:
mvn spring-boot:run

A aplicação estará disponível em: http://localhost:8080

Swagger / OpenAPI
Documentação da API: http://localhost:8080/swagger-ui/index.html

Executar Testes Unitários
mvn clean test
Testes usam H2 em memória, então não é necessário banco externo

🔢 Exemplos de JSON para Testes via Swagger
Criar Venda
{
  "valor": 150,
  "dataVenda": "2026-03-27T10:30:00",
  "vendedorId": 1,
  "vendedorNome": "João"
}
{
  "valor": 200,
  "dataVenda": "2026-03-27T15:00:00",
  "vendedorId": 2,
  "vendedorNome": "Maria"
}
Resumo por Vendedor (Exemplo de Retorno)
[
  {
    "nome": "João",
    "totalVendas": 3,
    "totalValor": 350,
    "mediaDiaria": 116.67
  },
  {
    "nome": "Maria",
    "totalVendas": 2,
    "totalValor": 350,
    "mediaDiaria": 116.67
  }
]
