package com.perks.gql.writers;

public class WritableItem implements Writable {

    private final String name;

    private final String packageName;

    private final StringBuilder builder;


    /**
     * The constructor
     *
     * @param name the file name
     * @param packageName the source package
     * @param builder the file contents
     */
    public WritableItem(String name, String packageName, StringBuilder builder) {
        this.name = name;
        this.packageName = packageName;
        this.builder = builder;
    }

    /**
     * Gets the package name
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the builder consisting of the content
     *
     * @return the builder
     */
    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public String getName() {
        return name;
    }
}
