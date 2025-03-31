# Pedra, Papel e Tesoura - Projeto Java Web

Este é um projeto Java Web que implementa o clássico jogo "Pedra, Papel e Tesoura" utilizando JSF (JavaServer Faces) e JPA (Java Persistence API). O objetivo principal é demonstrar o uso de tecnologias Java EE em uma aplicação simples, porém funcional.

## 🛠 Tecnologias Utilizadas

- Java (versão compatível com Java EE 7 ou superior)
- JSF (JavaServer Faces)
- JPA (Java Persistence API)
- Servidor de aplicação (recomendado: Apache Tomcat 8.5+ ou Payara)
- Eclipse IDE (estrutura compatível com Web Tools Platform - WTP)

## 📁 Estrutura do Projeto

```
PedraPapelTesoura/
│── src/main/
│   ├── java/
│   │   ├── bean/         # Managed Beans do JSF
│   │   ├── dao/          # Data Access Objects (DAO)
│   │   ├── entidades/    # Classes de modelo JPA
│   │   ├── util/         # Utilitários (ex: JPAUtil)
│   ├── resources/META-INF/
│   │   ├── persistence.xml   # Configuração do JPA
│   ├── webapp/
│   │   ├── WEB-INF/
│   │   │   ├── faces-config.xml  # Configuração do JSF
│   │   │   ├── web.xml           # Configuração do servlet
│   │   ├── index.xhtml            # Página inicial do jogo
│   │   ├── listagem.xhtml         # Página de listagem das jogadas
│── lib/  # Bibliotecas necessárias para rodar o projeto
│── .classpath
│── .project
```


## 🚀 Instruções de Instalação e Execução

1. **Importar no Eclipse:**
   - Selecione `File > Import > Existing Projects into Workspace`.
   - Escolha o diretório do projeto.
   - Certifique-se de que está utilizando o *Eclipse IDE for Enterprise Java Developers*.

2. **Configurar o Servidor:**
   - Adicione um servidor compatível (ex: Tomcat ou Payara) no Eclipse.
   - Adicione o projeto ao servidor.

3. **Bibliotecas Requeridas:**
   - Adicione manualmente as seguintes bibliotecas ao classpath do projeto (via `Java Build Path > Libraries`):
     - `javax.faces.jar` (JSF)
     - `javax.persistence.jar` (JPA)
     - Driver JDBC do banco utilizado (ex: `mysql-connector-java.jar`)
     - Provedor JPA (ex: `hibernate-core.jar`)

4. **Executar:**
   - Inicie o servidor de aplicação.
   - Acesse a aplicação via navegador, geralmente em:
     ```
     http://localhost:8080/PedraPapelTesoura
     ```

## 🧪 Configuração do Banco de Dados

O projeto utiliza JPA e possui um arquivo de configuração em:

Você deve configurar a conexão com o banco, especificando:

- Driver JDBC
- URL de conexão
- Usuário e senha
- Provedor JPA (ex: Hibernate)

Exemplo:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/pedra_papel_tesoura"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="senha"/>
<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
