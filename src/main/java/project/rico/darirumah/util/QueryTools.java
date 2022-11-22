package project.rico.darirumah.util;

import org.springframework.beans.factory.annotation.Autowired;
import project.rico.darirumah.config.AppProperties;

public class QueryTools {

    @Autowired
    AppProperties appProperties;

    public static StringBuilder buildQuery(String query, String table) {
        StringBuilder sb = new StringBuilder(query);

        return sb.append(" "+AppProperties.SCHEMA+"."+table+" ");

    }

}
