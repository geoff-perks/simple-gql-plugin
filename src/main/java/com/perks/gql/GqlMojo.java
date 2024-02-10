package com.perks.gql;

import com.perks.gql.generators.EnumSourceGenerator;
import com.perks.gql.generators.TypeSourceGenerator;
import com.perks.gql.parsers.SchemaParser;
import com.perks.gql.scalars.EnumInfo;
import com.perks.gql.scalars.TypeInfo;
import com.perks.gql.writers.EnumSourceFileWriter;
import com.perks.gql.writers.QuerySourceFileWriter;
import com.perks.gql.writers.TypeSourceFileWriter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Mojo(name = "generate-gql-model")
public class GqlMojo extends AbstractMojo {

    @Parameter(property = "graphQlSchemaName", defaultValue = "src/main/resources/schema.graphql")
    private String graphQlSchemaName;

    @Parameter(property = "packageName", defaultValue = "com.perks.gql")
    private String packageName;

    @Parameter(property = "scalars")
    private Map<String, String> scalars;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        System.out.println("Generating sources from graphql schema");

        // parse the schema
        StringBuilder schema = new SchemaParser().parse(graphQlSchemaName);

        // generate the source
        Set<TypeInfo> generatedTypes = new TypeSourceGenerator().generate(schema.toString());
        Set<EnumInfo> generatedEnums = new EnumSourceGenerator().generate(schema.toString());

        System.out.println("Scalars include: ");
        scalars.forEach((key, value) -> System.out.println(key));

        // write the sources
        new QuerySourceFileWriter().writeQuery(packageName);
        new TypeSourceFileWriter().write(generatedTypes, packageName, Optional.of(scalars).orElse(Collections.emptyMap()));
        new EnumSourceFileWriter().write(generatedEnums, packageName, Optional.of(scalars).orElse(Collections.emptyMap()));

        System.out.println("Generating sources complete!");
    }

}
