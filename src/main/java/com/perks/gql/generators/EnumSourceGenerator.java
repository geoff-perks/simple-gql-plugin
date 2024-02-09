package com.perks.gql.generators;

import com.perks.gql.scalars.EnumInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The enum implementation of the source generator
 */
public class EnumSourceGenerator implements SourceGenerator<Set<EnumInfo>> {

    @Override
    public Set<EnumInfo> generate(String schema) {

        Set<EnumInfo> enumInfoSet = new HashSet<>();

        Pattern enumPattern = Pattern.compile("enum\\s+(\\w+)\\s*\\{([^}]*)}", Pattern.DOTALL);
        Matcher enumMatcher = enumPattern.matcher(schema);

        while (enumMatcher.find()) {
            String enumName = enumMatcher.group(1);
            String enumValues = enumMatcher.group(2).trim();

            Set<String> values = new HashSet<>();
            Pattern valuePattern = Pattern.compile("(\\w+)");
            Matcher valueMatcher = valuePattern.matcher(enumValues);

            while (valueMatcher.find()) {
                values.add(valueMatcher.group(1));
            }

            enumInfoSet.add(new EnumInfo(enumName, values));
        }

        return enumInfoSet;
    }
}
