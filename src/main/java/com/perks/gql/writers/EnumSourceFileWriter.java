package com.perks.gql.writers;

import com.perks.gql.scalars.EnumInfo;
import com.perks.gql.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The enum source generator generates the enum source files
 */
public class EnumSourceFileWriter implements SourceFileWriter<EnumInfo> {

    @Override
    public void write(Set<EnumInfo> enumInfoSet, String packageName, Map<String, String> scalars) {

        enumInfoSet.forEach(enumInfo -> {

            if (enumInfo.getName() != null) {

                StringBuilder contents = new StringBuilder();

                contents.append("package ").append(packageName).append(".").append(Constants.MODEL_PACKAGE_NAME.getConstant()).append(";");
                contents.append(System.lineSeparator());
                contents.append(System.lineSeparator());
                contents.append("/**").append(System.lineSeparator());
                contents.append(" * The ").append(capitalize(enumInfo.getName())).append(" enum").append(System.lineSeparator());
                contents.append(" */").append(System.lineSeparator());

                contents.append("public enum ").append(capitalize(enumInfo.getName())).append(" {").append(System.lineSeparator());

                List<String> values = new ArrayList<>(enumInfo.getValues());

                for (int i = 0; i < values.size(); i++) {
                    String value = values.get(i);
                    contents.append("\t").append(value);
                    if (i < values.size() - 1) {
                        contents.append(",").append(System.lineSeparator());
                    }
                }
                contents.append(System.lineSeparator()).append("}");

                writeFile(new WritableItem(enumInfo.getName(),
                        packageName + "." + Constants.MODEL_PACKAGE_NAME.getConstant(), contents));
            }
        });
    }
}
