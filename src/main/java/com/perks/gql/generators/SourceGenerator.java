package com.perks.gql.generators;

import com.perks.gql.writers.Writable;

import java.util.Set;

/**
 * The source generator interface
 *
 */
public interface SourceGenerator<T extends Set<? extends Writable>> {

    /**
     * Generates the source from the schema
     *
     * @return the source
     */
    T generate(String schema);
}
