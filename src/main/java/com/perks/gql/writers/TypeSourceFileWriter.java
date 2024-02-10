package com.perks.gql.writers;

import com.perks.gql.scalars.FieldInfo;
import com.perks.gql.scalars.TypeInfo;
import com.perks.gql.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The type source generator generates the java class source files
 */
public class TypeSourceFileWriter implements SourceFileWriter<TypeInfo> {

    @Override
    public void write(Set<TypeInfo> typeInfoSet, String packageName, Map<String, String> scalars) {

        typeInfoSet.forEach(typeInfo -> {

            if (typeInfo.getName() != null) {

                StringBuilder contents = new StringBuilder();

                contents.append("package ").append(packageName).append(".").append(Constants.MODEL_PACKAGE_NAME.getConstant()).append(";");
                contents.append(System.lineSeparator());
                contents.append(System.lineSeparator());
                contents.append("import ").append(packageName).append(String.format(".%s;", Constants.QUERYABLE_CLASS_NAME.getConstant()));
                contents.append(System.lineSeparator());
                contents.append(System.lineSeparator());
                contents.append("/**").append(System.lineSeparator());
                contents.append(" * The ").append(capitalize(typeInfo.getName())).append(" record").append("\n");
                contents.append(" */").append(System.lineSeparator());

                contents.append("public record ").append(capitalize(typeInfo.getName())).append("(");

                List<FieldInfo> fields = new ArrayList<>(typeInfo.getFields());

                for (int i = 0; i < fields.size(); i++) {

                    FieldInfo field = fields.get(i);
                    contents.append(scalars.containsKey(field.getType()) ? scalars.get(field.getType()) : field.getType())
                            .append(" ")
                            .append(field.getName());

                    if (i < fields.size() - 1) {
                        contents.append(", ");
                    }
                }
                contents.append(String.format(") implements %s {", Constants.QUERYABLE_CLASS_NAME.getConstant()));
                contents.append(System.lineSeparator()).append("}");

                writeFile(new WritableItem(typeInfo.getName(),
                        packageName + "." + Constants.MODEL_PACKAGE_NAME.getConstant(), contents));
            }
        });
    }
}
