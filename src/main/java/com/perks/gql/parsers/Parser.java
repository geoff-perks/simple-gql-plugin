package com.perks.gql.parsers;

/**
 * The parser interface
 */
public interface Parser {

    /**
     * Parses the schema and returns within an object
     *
     * @param fileLocation the schema location
     *
     * @return an object containing the schema
     */
    StringBuilder parse(String fileLocation);

}
