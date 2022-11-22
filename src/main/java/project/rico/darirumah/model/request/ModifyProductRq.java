package project.rico.darirumah.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ModifyProductRq {

    @NotNull(message = "productcode is Mandatory")
    @JsonProperty("productcode")
    private String productCode;

    @JsonProperty("productname")
    private String productName;

    @JsonProperty("uom")
    private String uom;

    @JsonProperty("type")
    private String type;

}
