package com.perks.gql.parsers;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The schema parser implementation
 */
public class SchemaParser implements Parser {

    @Override
    public StringBuilder parse(String fileLocation) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileLocation))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (Exception e) {
            System.err.println("a schema file called schema.graphql is expected in main/resources. " + e.getMessage());
            throw new RuntimeException(e);
        }

        return builder;
    }
}
