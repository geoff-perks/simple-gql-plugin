package com.perks.gql.generators;

import com.perks.gql.scalars.FieldInfo;
import com.perks.gql.scalars.TypeInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The type source generator implementation
 */
public class TypeSourceGenerator implements SourceGenerator<Set<TypeInfo>> {

    private final List<String> EXCLUSIONS = Arrays.asList("Query", "Mutation");

    private final Map<String, String> scalarTypes =
            Map.of("Int", "Integer", "UUID", "String");

    @Override
    public Set<TypeInfo> generate(String schema) {

        Set<TypeInfo> typeInfos = new HashSet<>();
        String[] typeDefinitions = schema.split("type ");

        // Start from index 1 to skip the initial empty string
        for (int i = 1; i < typeDefinitions.length; i++) {
            String typeDefinition = typeDefinitions[i];

            String typeName = null;

            // Extract type name
            Pattern pattern = Pattern.compile("(\\w+)\\s*\\{");
            Matcher matcher = pattern.matcher(typeDefinition);

            if (matcher.find()) {
                typeName = matcher.group(1);
                if (EXCLUSIONS.contains(typeName)) {
                    continue;
                }
            }

            // Extract fields
            Set<FieldInfo> fields = new HashSet<>();
            pattern = Pattern.compile("(\\w+):\\s*(\\w+)(!)?");
            matcher = pattern.matcher(typeDefinition);

            while (matcher.find()) {
                String fieldName = matcher.group(1);
                String fieldType = matcher.group(2);
                if (!containsField(fields, fieldName)) {
                    FieldInfo fieldInfo = new FieldInfo(fieldName, scalarTypes.getOrDefault(capitalize(fieldType), capitalize(fieldType)));
                    fields.add(fieldInfo);
                } else {
                    System.err.println("Duplicate field found: " + fieldName);
                }
            }

            TypeInfo typeInfo = new TypeInfo(typeName, fields);
            typeInfos.add(typeInfo);
        }
        return typeInfos;
    }

    private boolean containsField(Set<FieldInfo> fields, String fieldName) {
        for (FieldInfo field : fields) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
