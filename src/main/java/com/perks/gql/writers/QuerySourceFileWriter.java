package com.perks.gql.writers;

import com.perks.gql.utils.WritableUtils;

/**
 * The queryable source file writer
 */
public class QuerySourceFileWriter {

    private static final String QUERYABLE_NAME = "Queryable";

    /**
     * Writes the queries if required
     *
     * @param packageName the package name
     */
    public void writeQuery(String packageName) {

        StringBuilder queryableBuilder = new StringBuilder();
        queryableBuilder.append("package ").append(packageName).append(";\n\n");
        queryableBuilder.append("import java.io.Serializable;\n");
        queryableBuilder.append("public interface Queryable extends Serializable {\n}");

        WritableUtils.instance()
                .writeItem(new WritableItem(QUERYABLE_NAME, packageName ,queryableBuilder));
    }
}
