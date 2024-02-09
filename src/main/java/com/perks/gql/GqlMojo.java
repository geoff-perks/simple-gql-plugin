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

import java.util.Set;

public class GqlMojo extends AbstractMojo {

    private static final String GRAPHQL_SCHEMA = "schema.graphql";

    private String relativeDirectoryPath = "src/main/java/com/perks/gql/model/";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        System.out.println("Generating sources from graphql schema");

        // parse the schema
        Parser parser = new SchemaParser();
        StringBuilder schema = parser.parse(GRAPHQL_SCHEMA);

        // generate the source
        Set<TypeInfo> generatedTypes = new TypeSourceGenerator().generate(schema.toString());
        Set<EnumInfo> generatedEnums = new EnumSourceGenerator().generate(schema.toString());

        // write the record sources to file
        SourceFileWriter<TypeInfo> typeSourceFileWriter = new TypeSourceFileWriter();
        typeSourceFileWriter.write(generatedTypes, relativeDirectoryPath);

        // write the enum sources to file
        SourceFileWriter<EnumInfo> enumSourceFileWriter = new EnumSourceFileWriter();
        enumSourceFileWriter.write(generatedEnums, relativeDirectoryPath);

        System.out.println("Generating sources complete!");
    }

}
