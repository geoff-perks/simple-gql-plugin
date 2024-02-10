package com.perks.gql.writers;

import com.perks.gql.utils.WritableUtils;

import java.util.Map;
import java.util.Set;

/**
 * The source code generator interface
 */
public interface SourceFileWriter<T extends Writable> {

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
     * @param writable the name of the source file
     */
    default void writeFile(WritableItem writable) {
        WritableUtils.instance().writeItem(writable);
    }
}
