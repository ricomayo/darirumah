package project.rico.darirumah.model.db;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StockRef {

    String idStock;
    String idProduct;
    String productCode;
    String qty;
    String uom;
}
