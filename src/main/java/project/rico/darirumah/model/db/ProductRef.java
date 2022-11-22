package project.rico.darirumah.model.db;

import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductRef {

    private String productCode;
    private String productName;
    private String supplier;
    private String uom;
    private String type;

}
