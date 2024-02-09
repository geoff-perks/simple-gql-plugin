package com.perks.gql;

import com.perks.gql.generators.EnumSourceGenerator;
import com.perks.gql.generators.TypeSourceGenerator;
import com.perks.gql.parsers.Parser;
import com.perks.gql.parsers.SchemaParser;
import com.perks.gql.scalars.EnumInfo;
import com.perks.gql.scalars.TypeInfo;
import com.perks.gql.writers.EnumSourceFileWriter;
import com.perks.gql.writers.SourceFileWriter;
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
        Parser parser = new SchemaParser();
        StringBuilder schema = parser.parse(graphQlSchemaName);

        // generate the source
        Set<TypeInfo> generatedTypes = new TypeSourceGenerator().generate(schema.toString());
        Set<EnumInfo> generatedEnums = new EnumSourceGenerator().generate(schema.toString());

        String relativeDirectoryWithPackageName =
                "src/main/java/" + packageName.replaceAll("\\.", "/") + "/";

        System.out.println("Scalars include: ");
        scalars.forEach((key, value) -> System.out.println(key));

        // write the record sources to file
        SourceFileWriter<TypeInfo> typeSourceFileWriter = new TypeSourceFileWriter();
        typeSourceFileWriter.write(generatedTypes, relativeDirectoryWithPackageName, Optional.of(scalars).orElse(Collections.emptyMap()));

        // write the enum sources to file
        SourceFileWriter<EnumInfo> enumSourceFileWriter = new EnumSourceFileWriter();
        enumSourceFileWriter.write(generatedEnums, relativeDirectoryWithPackageName, Optional.of(scalars).orElse(Collections.emptyMap()));

        System.out.println("Generating sources complete!");
    }

}
