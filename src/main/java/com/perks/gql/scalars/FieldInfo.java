package com.perks.gql.scalars;

import java.util.Objects;

/**
 * The FieldInfo used to represent fields associated to a type in a GQL schema
 */
public class FieldInfo {

    private final String name;

    private final String type;

    /**
     * The constructor
     *
     * @param name the field name
     * @param type the field data type
     */
    public FieldInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name of the field
     *
     * @return the name of the field
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the return type of the field
     *
     * @return the return type of the field
     */
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldInfo fieldInfo = (FieldInfo) o;
        return Objects.equals(name, fieldInfo.name) && Objects.equals(type, fieldInfo.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}