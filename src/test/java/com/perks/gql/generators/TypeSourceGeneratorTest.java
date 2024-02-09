package com.perks.gql.generators;

import com.perks.gql.scalars.TypeInfo;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TypeSourceGeneratorTest {

    @Test
    public void shouldCreateRecord() {

        // arrange
        String schema = """
                type TestStatus {
                  property1: String
                  property2: Int
                }""";

        // act
        Set<TypeInfo> infoSet = new TypeSourceGenerator().generate(schema);

        // assert
        assertFalse(infoSet.isEmpty());
        assertEquals("TestStatus", infoSet.stream().map(TypeInfo::getName).findFirst().orElseThrow());
        assertEquals(1, infoSet.size());
        assertEquals(1, infoSet.stream().map(TypeInfo::getFields).toList().size());
    }

    @Test
    public void shouldCreateRecordWithoutDuplicateFields() {

        // arrange
        String schema = """
                type TestStatus {
                  property1: String
                  property2: Int
                  property1: Int
                }""";

        // act
        Set<TypeInfo> infoSet = new TypeSourceGenerator().generate(schema);

        // assert
        assertFalse(infoSet.isEmpty());
        assertEquals("TestStatus", infoSet.stream().map(TypeInfo::getName).findFirst().orElseThrow());
        assertEquals(1, infoSet.size());
        assertEquals(1, infoSet.stream().map(TypeInfo::getFields).toList().size());
    }

    @Test
    public void shouldCreateRecordExclusively() {

        // arrange
        String schema = """
                 type TestStatus {
                  property1: String
                  property2: Int
                }
                
                enum TestStatus {
                  SUCCESS
                  FAILED
                }""";

        // act
        Set<TypeInfo> infoSet = new TypeSourceGenerator().generate(schema);

        // assert
        assertFalse(infoSet.isEmpty());
        assertEquals("TestStatus", infoSet.stream().map(TypeInfo::getName).findFirst().orElseThrow());
        assertEquals(1, infoSet.size());
        assertEquals(1, infoSet.stream().map(TypeInfo::getFields).toList().size());
    }
}