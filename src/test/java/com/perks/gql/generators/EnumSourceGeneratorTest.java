package com.perks.gql.generators;

import com.perks.gql.scalars.EnumInfo;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * The enum source generator tests
 */
class EnumSourceGeneratorTest {


    @Test
    public void shouldCreateEnum() {

        // arrange
        String schema = """
                enum TestStatus {
                  SUCCESS
                  FAILED
                }""";

        // act
        Set<EnumInfo> infoSet = new EnumSourceGenerator().generate(schema);

        // assert
        assertFalse(infoSet.isEmpty());
        assertEquals("TestStatus", infoSet.stream().map(EnumInfo::getName).findFirst().orElseThrow());
        assertEquals(1, infoSet.size());
        assertEquals(1, infoSet.stream().map(EnumInfo::getValues).toList().size());
    }

    @Test
    public void shouldCreateEnumWithoutDuplicateValues() {

        // arrange
        String schema = """
                enum TestStatus {
                  SUCCESS
                  FAILED
                  SUCCESS
                }""";

        // act
        Set<EnumInfo> infoSet = new EnumSourceGenerator().generate(schema);

        // assert
        assertFalse(infoSet.isEmpty());
        assertEquals("TestStatus", infoSet.stream().map(EnumInfo::getName).findFirst().orElseThrow());
        assertEquals(1, infoSet.size());
        assertEquals(1, infoSet.stream().map(EnumInfo::getValues).toList().size());
    }

    @Test
    public void shouldCreateEnumExclusively() {

        // arrange
        String schema = """
                enum TestStatus {
                  SUCCESS
                  FAILED
                  SUCCESS
                }
                
                type SomeRandomType {
                    property1: String
                    property2: Int
                }""";

        // act
        Set<EnumInfo> infoSet = new EnumSourceGenerator().generate(schema);

        // assert
        assertFalse(infoSet.isEmpty());
        assertEquals("TestStatus", infoSet.stream().map(EnumInfo::getName).findFirst().orElseThrow());
        assertEquals(1, infoSet.size());
        assertEquals(1, infoSet.stream().map(EnumInfo::getValues).toList().size());
    }

}