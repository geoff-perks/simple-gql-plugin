package com.perks.gql.scalars;

import java.util.Set;

/**
 * The EnumInfo which represents an enum in a GQL Schema
 */
public class EnumInfo implements Scalar {

    private final String name;

    private final Set<String> values;

    /**
     * The constructor
     *
     * @param name the name of the enum
     * @param values the unique values the enum contains
     */
    public EnumInfo(String name, Set<String> values) {
        this.name = name;
        this.values = values;
    }

    /**
     * Gets the name of the enum
     *
     * @return the name of the enum
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the values of the enum as a set
     *
     * @return a set containing all the values within the enum
     */
    public Set<String> getValues() {
        return values;
    }
}
