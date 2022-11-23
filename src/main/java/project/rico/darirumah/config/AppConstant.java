package project.rico.darirumah.config;


import java.text.SimpleDateFormat;

public class AppConstant {
    public static final String APP_NAME = "darirumah";
    public static final String BEAN_APP_CONFIG = "app-config";

    public static final String BEAN_DS_MASTERDATA_POSTGRES = "ds-masterdata-postgres";
    public static final String BEAN_JDBC_MASTERDATA_POSTGRES = "jdbc-masterdata-postgres";

    public static final String REDIS_TEMPLATE_NAME = "redis-template";
    public static final String REDIS_CLIENT_NAME = "redis-client";
    public static final String REDIS_STRING_TEMPLATE_NAME = "redis-string-template";
    public static final String REDIS_CONFIG_NAME = "redis-config";

    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public enum LOGIN_VALIDATION {
        ACCEPTED,
        REJECTED
    }

    public enum UPDATE_USER {
        PASSWORD,
        DATA
    }

    public enum UPDATE_PASSWORD {
        SUCCESS,
        NOT_FOUND,
        NOT_MATCH
    }

    public enum MODIFY_PRODUCT {
        SUCCESS,
        PRODUCT_EXIST
    }

    public enum ORDER_PRODUCT {
        SUCCESS,
        PRODUCT_EXIST
    }

}
