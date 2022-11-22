package project.rico.darirumah.config;


import java.text.SimpleDateFormat;

public class AppConstant {
    public static final String APP_NAME = "darirumah";
    public static final String BEAN_APP_CONFIG= "app-config";

    public static final String BEAN_DS_MASTERDATA_POSTGRES = "ds-masterdata-postgres";
    public static final String BEAN_DS_CONFIG_POSTGRES = "ds-configuration-postgres";
    public static final String BEAN_DS_MASTERDATA = "datasource-masterdata";

    public static final String BEAN_JDBC_MASTERDATA_POSTGRES = "jdbc-masterdata-postgres";
    public static final String BEAN_JDBC_CONFIG_POSTGRES = "jdbc-configuration-postgres";
    public static final String BEAN_JDBC_MASTERDATA = "jdbc-masterdata";

    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public enum LOGIN_VALIDATION {
        ACCEPTED,
        REJECTED
    }


}
