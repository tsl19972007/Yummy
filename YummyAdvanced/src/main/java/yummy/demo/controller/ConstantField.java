package yummy.demo.controller;

public final class ConstantField {
    private ConstantField() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SESSION_CUSTOMER_ID = "cstId";
    public static final String SESSION_RESTAURANT_ID = "rstId";
    public static final String SESSION_MANAGER_ID = "mngId";
    public static final String SESSION_USERNAME = "userName";
}
