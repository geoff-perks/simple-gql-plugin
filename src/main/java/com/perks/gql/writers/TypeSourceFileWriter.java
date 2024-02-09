package com.perks.gql.writers;

import com.perks.gql.scalars.FieldInfo;
import com.perks.gql.scalars.TypeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The type source generator generates the java class source files
 */
public class TypeSourceFileWriter implements SourceFileWriter<TypeInfo> {

    @Override
    public void write(Set<TypeInfo> scalars, String relativeDirectoryPath) {

        scalars.forEach(typeInfo -> {
            if (typeInfo.getName() != null) {

                StringBuilder contents = new StringBuilder();

                List<String> relativePackage = Stream.of(relativeDirectoryPath.split("/")).collect(Collectors.toList());
                List<String> absolutePackage = relativePackage.subList(3, relativePackage.size());
                StringBuilder packageBuilder = new StringBuilder();

                for (int i = 0; i <= absolutePackage.size() - 1; i++) {
                    if (i != absolutePackage.size() - 1) {
                        packageBuilder.append(String.format("%s.", absolutePackage.get(i)));
                    } else {
                        packageBuilder.append(String.format("%s", absolutePackage.get(i)));
                    }
                }

                packageBuilder.append(";").append("\n");

                contents.append("package ").append(packageBuilder).append("\n");
                contents.append("/**").append("\n");
                contents.append(" * The ").append(capitalize(typeInfo.getName())).append(" record").append("\n");
                contents.append(" */").append("\n");

                contents.append("public record ").append(capitalize(typeInfo.getName())).append("(");

                List<FieldInfo> fields = new ArrayList<>(typeInfo.getFields());

                for (int i = 0; i < fields.size(); i++) {
                    FieldInfo field = fields.get(i);
                    contents.append(field.getType()).append(" ").append(field.getName());
                    if (i < fields.size() - 1) {
                        contents.append(", ");
                    }
                }
                contents.append(") {");
                contents.append("\n").append("}");

                writeFile(relativeDirectoryPath, contents, typeInfo);
            }
        });
    }
}
