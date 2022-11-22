package project.rico.darirumah.util;

import org.springframework.beans.factory.annotation.Autowired;
import project.rico.darirumah.config.AppProperties;

/**
 * @author IDCB.RIEFKA RICO [idcb.riefkar@xl.co.id]
 * Created At 22/11/2022 14:12
 */

public class QueryTools {

    @Autowired
    AppProperties appProperties;

    public static StringBuilder buildQuery(String query, String table) {
        StringBuilder sb = new StringBuilder(query);

        return sb.append("FROM "+AppProperties.SCHEMA+"."+table+" ");

    }

}
