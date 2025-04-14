# ⚙️ Backend - Diagrama Unifilar CEPEL

Este é o backend da aplicação web de Diagrama Unifilar desenvolvida para o CEPEL. A API é responsável por receber arquivos XML com dados de componentes elétricos e retornar uma estrutura JSON contendo os nós e conexões do diagrama.

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Maven
- Jackson (para parseamento XML e JSON)
- CORS habilitado para comunicação com o frontend

## 📦 Como executar localmente

### 1. Pré-requisitos

- Java 17 ou superior
- Maven instalado
- IDE como IntelliJ ou VS Code com suporte a Spring

### 2. Clone o repositório

```bash
git clone https://github.com/Wesnnen/Cepel_Diagrama_Unifilar_Backend.git
cd diagrama-backend
```

### 3. Execute o projeto

```bash
./mvnw spring-boot:run
```
O backend estará disponível em: [http://localhost:8083](http://localhost:8083)

### 4. Teste via Postman

- Método: POST
- URL: http://localhost:8083/api/diagram
- Body: Form-data contendo um campo file com o arquivo .xml

## 📁 Estrutura da API
- POST /api/diagram
  Recebe um arquivo XML e retorna os dados dos componentes e conexões.

```bash
{
"components": [
        {
            "id": "bus_num-12",
            "title": "bus 12",
            "type": "bus",
            "baseKv": 500.0,
            "x": 2250.0,
            "y": 1270.0,
            "hidden": false,
            "sourcePosition": "right",
            "targetPosition": "left"
        }],
  "connections": [
  ]
}
```

## ✨ Funcionalidades
 ✅ Leitura de arquivos XML com múltiplos formatos
 ✅ Conversão para JSON compatível com React Flow
 ✅ Suporte a diferentes tipos de componentes (bus, machine, load etc.)
 ✅ Validação e tratamento de falhas na estrutura do XML

## 🔧 Pontos Bônus Implementados
💡 Mensagens de log detalhadas em tempo de execução
📦 Tratamento para múltiplos formatos de XML (com fallback se estrutura principal falhar)
🔄 Integração com CORS para comunicação com frontend React

## 📃 Licença
MIT © Wesnnen Silva
