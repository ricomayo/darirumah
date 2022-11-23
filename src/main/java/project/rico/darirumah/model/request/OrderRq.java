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
public class OrderRq {

    @NotNull(message = "iduser is Mandatory")
    @JsonProperty("iduser")
    private int idUser;

    @NotNull(message = "productcode is Mandatory")
    @JsonProperty("productcode")
    private String productCode;

    @NotNull(message = "qty is Mandatory")
    @JsonProperty("qty")
    private int qty;

    @JsonProperty("destinationname")
    private String destinationName;

    @JsonProperty("destinationaddress")
    private String destinationAddress;
}
