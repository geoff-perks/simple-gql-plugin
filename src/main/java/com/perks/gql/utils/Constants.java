package com.perks.gql.utils;

/**
 * The constants
 */
public enum Constants {

    /**
     * The name of the Query marker interface
     */
    QUERYABLE_CLASS_NAME("Queryable"),

    /**
     * The package's last child where to generate the class model items
     */
    MODEL_PACKAGE_NAME("model"),

    /**
     * The package's last child where to generate the enum model items
     */
    QUERY_PACKAGE_NAME("queries");

    private String constant;

    /**
     * The constructor
     *
     * @param constant the constant
     */
    Constants(String constant) {
        this.constant = constant;
    }

    /**
     * Gets the constant
     *
     * @return the constant as a String
     */
    public String getConstant() {
        return constant;
    }
}
