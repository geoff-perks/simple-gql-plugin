package com.perks.gql.scalars;

import com.perks.gql.writers.Writable;

import java.util.Objects;
import java.util.Set;

/**
 * The TypeInfo which represents a type within a GQL Schema
 */
public class TypeInfo implements Writable {

    private final String name;

    private final Set<FieldInfo> fields;

    /**
     * The constructor
     *
     * @param name the name of the Type
     * @param fields the associated fields
     */
    public TypeInfo(String name, Set<FieldInfo> fields) {
        this.name = name;
        this.fields = fields;
    }

    /**
     * Gets the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the fields
     *
     * @return the fields
     */
    public Set<FieldInfo> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeInfo typeInfo = (TypeInfo) o;
        return Objects.equals(name, typeInfo.name) && Objects.equals(fields, typeInfo.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fields);
    }
}
