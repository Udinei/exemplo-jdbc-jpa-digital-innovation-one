# exemplo-jdbc-jpa-digital-innovation-one

Projeto java sobre a utilização básica de conexão a Banco de dados utilizando a API JDBC (Java Database Connectivity) e consultas a BD com JPA ( Java Persistence API ) . 

### Requisitos
- Java 15
- BD MySql
- Gradle 6.8 
  
   Dependências:
    - mysql-connector-java , version: '8.0.23'
    - javax.persistence-api', version: '2.2'
    - hibernate-core', version: '5.4.30.Final'
    - org.eclipse.persistence', version: '3.0.0'
    - hibernate-jpamodelgen', version: '5.4.30.Final'
    
Nota: Para mais detalhes ver o arquivo build.gradle do projeto

### Estrutura do projeto
   - main
     - java
       - conexao
       - consultas
       - dao
       - model
     - resources
       - META-INF
     - sql     

### Configurações
- Banco de dados MySql
- Fabrica de conexão (ConnectionFactory)
- Arquivo persistence.xml
- Arquivo conection.properties
- Gerenciamento do ciclo de vida de entidades com a interface EntityManager
- Visão dos principais métodos do EntityManager
  - find
  - persist
  - remove
  - Consultas com JDBC
    - DriverManager
    - Connection
    - getConnection()
    - PrepareStatement
    - ResultSet
    - executeQuery()
    - executeUpdate()
 - JDBC e drivers de conexão
 - Linguagem de consultas OO (JPQL, JPA Criteria API)
 - Mapeamento JPA (ORM)
 - Uso das Anotations JPA
 - Implementações JPA (Hibernate e EclipseLink)

### Outras implementações
 - DAO
 - model
 - CRUD

### Projeto original 
* [jpa-basico por Daniel Karam](https://github.com/danielkv7/jpa-basico)
* [jdbc-basico por Daniel Karam](https://github.com/danielkv7/jdbc-basico)

### Links de consultas e documentação
Outras refências:

* [Oracle Java JDBC API](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
* [Using Java Persistence API Within a Visual Web Application](https://www.oracle.com/java/technologies/persistence-jsp.html)
* [Querying JPA Entities with JPQL and Native SQL](https://www.oracle.com/technical-resources/articles/vasiliev-jpql.html)