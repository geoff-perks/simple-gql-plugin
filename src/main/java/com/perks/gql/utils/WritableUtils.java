package com.perks.gql.utils;

import com.perks.gql.writers.WritableItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The writable utils
 */
public class WritableUtils {

    private static final WritableUtils INSTANCE = new WritableUtils();

    /**
     * hidden constructor singleton
     */
    private WritableUtils() {
    }

    /**
     * Gets the instance
     *
     * @return the instance
     */
    public static WritableUtils instance() {
        return INSTANCE;
    }

    /**
     * Writes the writable item
     *
     * @param writableItem the writable item
     */
    public void writeItem(WritableItem writableItem) {

        String filePath = writableItem.getPackageName().replaceAll("\\.", "/");
        String fileName = capitalize(writableItem.getName()) + ".java";
        String source = "src/main/java/" + filePath + "/" + fileName;

        File file = new File(source);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Error creating directory");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(writableItem.getBuilder().toString());
            System.out.println("StringBuilder content successfully written to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing StringBuilder content to file: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
