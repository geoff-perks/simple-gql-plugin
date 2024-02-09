# **Simple-GQL-plugin**

The motivation for this project is to generate the model (in java) without being bound to an opinionated framework. It allows
simple model generation in the form of java records and enums with custom GQL scalars.

This allows any java project to decide on its own serialization features, and which networking clients to use without being
bogged down by framework code.

_Kotlin also isn't required._

### Requirements:

* Java 17+
* Maven 3.9.0+

### To use the plugin:

In the **build** element of your pom.xml add the following:

```
<plugin>
<groupId>com.perks.gql</groupId>
<artifactId>simple-gql-plugin</artifactId>
<version>1.0-SNAPSHOT</version>
<executions>
  <execution>
    <phase>generate-sources</phase>
    <goals>
      <goal>generate-gql-model</goal>
    </goals>
  </execution>
</executions>
<configuration>
  <graphQlSchemaName>src/main/resources/schema.graphql</graphQlSchemaName>
  <packageName>com.virgin.atlantic.vaa.gql.model</packageName>
  <scalars>
    <Decimal>java.math.BigDecimal</Decimal>
    <DateTime>java.time.LocalDateTime</DateTime>
    <LocalDateTime>java.time.LocalDateTime</LocalDateTime>
  </scalars>
</configuration>
</plugin>
```

#### Configuration


* graphQlSchemaName - the graphQlSchemaName is the relative path to the schema including the file which must be a .graphql file
* packageName - the packageName is the package name to where you wish to generate the model sources
* scalars - the scalars is a map which consist of the GQL scalar as the key, and the replacement class for the scalar, e.g. where the GQL scalar is Int, this can be replaced with Integer