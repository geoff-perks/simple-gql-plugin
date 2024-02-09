package com.perks.gql.writers;

import com.perks.gql.scalars.EnumInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The enum source generator generates the enum source files
 */
public class EnumSourceFileWriter implements SourceFileWriter<EnumInfo> {

    @Override
    public void write(Set<EnumInfo> scalars, String relativeDirectoryPath) {

        scalars.forEach(enumInfo -> {

            if (enumInfo.getName() != null) {

                StringBuilder content = new StringBuilder();

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

                content.append("package ").append(packageBuilder).append("\n");
                content.append("/**").append("\n");
                content.append(" * The ").append(capitalize(enumInfo.getName())).append(" enum").append("\n");
                content.append(" */").append("\n");

                content.append("public enum ").append(capitalize(enumInfo.getName())).append(" {").append("\n");

                List<String> values = new ArrayList<>(enumInfo.getValues());

                for (int i = 0; i < values.size(); i++) {
                    String value = values.get(i);
                    content.append("\t").append(value);
                    if (i < values.size() - 1) {
                        content.append("\t").append(", ").append("\n");
                    }
                }
                content.append("\n").append("}");

                writeFile(relativeDirectoryPath, content, enumInfo);
            }
        });
    }
}
