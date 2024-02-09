package com.perks.gql.generators;

import com.perks.gql.scalars.Scalar;

import java.util.Set;

/**
 * The source generator interface
 *
 */
public interface SourceGenerator<T extends Set<? extends Scalar>> {

    /**
     * Generates the source from the schema
     *
     * @return the source
     */
    T generate(String schema);
}
