# JavaFX JDBC CRUD

[![License](https://img.shields.io/npm/l/react)](https://github.com/rodrigobarr0s/javafx-jdbc/blob/main/LICENSE)

## Sobre o Projeto

Este repositório contém uma aplicação desktop desenvolvida com **JavaFX** e **JDBC**, estruturada em camadas para realizar operações CRUD com banco de dados **MySQL**. A interface gráfica foi construída com FXML e o backend utiliza JDBC puro para persistência.

## Tecnologias Utilizadas

- **Java 17**
- **JavaFX**
- **FXML**
- **JDBC**
- **MySQL**
- **Maven**

## Modelo Conceitual

A aplicação gerencia entidades como **Departamentos** e **Vendedores**, com funcionalidades de cadastro, edição, listagem e exclusão. A estrutura segue o padrão MVC com DAO e serviços.

## Estrutura do Projeto

```plaintext
src/
├── application/         → Classe principal (Main.java)
├── db/                  → Conexão e exceções JDBC
├── gui/                 → FXMLs e controllers da interface
│   ├── util/            → Utilitários de GUI
│   └── listeners/       → Interface para eventos de mudança
├── model/
│   ├── entities/        → Classes de domínio (Seller, Department)
│   ├── dao/             → Interfaces DAO
│   ├── dao/impl/        → Implementações JDBC dos DAOs
│   ├── services/        → Camada de serviços
│   └── exceptions/      → Validações de formulário
```

## Dependências Necessárias

Para que o projeto funcione corretamente, é necessário configurar as seguintes dependências:

#### 1. **MySQL Connector/J**

O **MySQL Connector/J** é o driver JDBC que permite a comunicação entre a aplicação Java e o banco de dados MySQL.

- **Download**: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
- Após o download, adicione o `.jar` do conector ao **classpath** ou à pasta de bibliotecas do seu projeto.

#### 2. **JavaFX (via Gluon)**

A aplicação utiliza interface gráfica com JavaFX, é necessário baixar as bibliotecas JavaFX compatíveis com sua versão do Java.

- **Download**: [JavaFX SDK – Gluon](https://gluonhq.com/products/javafx/)
- Após o download, adicione os `.jar` da pasta `lib` ao classpath do projeto.

## Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven 3.8+
- IDE com suporte a JavaFX (IntelliJ, Eclipse...)

### Passos para execução

```bash
# Clonar o repositório
git clone https://github.com/seuusuario/javafx-jdbc.git

# Acessar o diretório do projeto
cd javafx-jdbc

# Executar via IDE (recomenda-se executar a classe Main.java)
```

> A aplicação é desktop, portanto não roda em navegador. A interface será exibida em uma janela JavaFX.

## Arquivo `db.properties`

Este arquivo contém as configurações de conexão JDBC para o banco de dados MySQL usado no projeto:

```properties
user=developer
password=1234567
dburl=jdbc:mysql://localhost:3307/coursejdbc
useSSL=false
allowPublicKeyRetrieval=true
```

#### Detalhes
- `user`: nome do usuário do banco de dados  
- `password`: senha do usuário  
- `dburl`: URL JDBC com host, porta e nome do banco  
- `useSSL`: define se a conexão deve usar SSL (desativado para ambiente local)  
- `allowPublicKeyRetrieval`: permite a recuperação da chave pública para autenticação com MySQL 8+

**Local recomendado**: coloque o arquivo `db.properties` na raiz do seu projeto ou na pasta `resources`, dependendo da estrutura do seu projeto Java.

## Script Docker para MySQL

Este script cria um container Docker com o MySQL configurado e pronto para uso:

```bash
sudo docker run -d \
--name mysql \
-e MYSQL_DATABASE=coursejdbc \
-e MYSQL_USER=developer \
-e MYSQL_PASSWORD='1234567' \
-e MYSQL_ROOT_PASSWORD='root' \
-p 3307:3306 \
mysql
```

#### Detalhes do Script
- Cria o banco `coursejdbc`  
- Define o usuário `developer` com senha `1234567`  
- Define a senha do root como `root`  
- Expõe a porta `3306` do container na porta `3307` da máquina host

## Script MySQL para Dataseed

Este script cria as tabelas `department` e `seller`, e insere dados iniciais para testes e desenvolvimento:

```sql
USE coursejdbc;

CREATE TABLE department (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE seller (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  BirthDate datetime NOT NULL,
  BaseSalary double NOT NULL,
  DepartmentId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartmentId) REFERENCES department (id)
);

INSERT INTO department (Name) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
```
## Capturas de Tela

### Tela de listagem de vendedores

![Listagem de vendedores](https://raw.githubusercontent.com/rodrigobarr0s/javafx-jdbc/refs/heads/main/img/lista.png)

### Formulário de inserção

![Formulário de inserção](https://raw.githubusercontent.com/rodrigobarr0s/javafx-jdbc/refs/heads/main/img/formulario-insercao.png)

### Formulário de edição

![Formulário de edição](https://raw.githubusercontent.com/rodrigobarr0s/javafx-jdbc/refs/heads/main/img/formulario-edicao.png)

### Alerta de exclusão

![Alerta de exclusão](https://raw.githubusercontent.com/rodrigobarr0s/javafx-jdbc/refs/heads/main/img/alert-exclusao.png)

## Autor

**Rodrigo Barros**  
https://www.linkedin.com/in/rodrigobarr0s

