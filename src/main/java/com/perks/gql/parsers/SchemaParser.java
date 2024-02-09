package com.perks.gql.parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.util.Objects.requireNonNull;

/**
 * The schema parser implementation
 */
public class SchemaParser implements Parser {

    @Override
    public StringBuilder parse(String fileLocation) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileLocation))))) {
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
