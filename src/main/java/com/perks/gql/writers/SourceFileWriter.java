package com.perks.gql.writers;

import com.perks.gql.scalars.Scalar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The source code generator interface
 */
public interface SourceFileWriter<T extends Scalar> {

    /**
     * Generates the source code
     *
     * @param types the types to use
     * @param relativeDirectoryPath the relative directory path
     */
    void write(Set<T> types, String relativeDirectoryPath, Map<String, String> scalars);

    /**
     * Used for writing the source file
     *
     * @param relativeDirectoryPath the relative directory path
     * @param contents the contents of the source file
     * @param scalar the name of the source file
     */
    default void writeFile(String relativeDirectoryPath, StringBuilder contents, Scalar scalar) {

        String sourceFile = capitalize(scalar.getName() + ".java");
        String filePath = relativeDirectoryPath + sourceFile;

        File directory = new File(relativeDirectoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("error creating directory");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(contents.toString());
            System.out.println("StringBuilder content successfully written to file: " + sourceFile);
        } catch (IOException e) {
            System.err.println("Error writing StringBuilder content to file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
