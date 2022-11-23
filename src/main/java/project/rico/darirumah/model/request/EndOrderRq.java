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
public class EndOrderRq {

    @NotNull(message = "idOrder is Mandatory")
    @JsonProperty("idorder")
    private int idorder;

    @NotNull(message = "idUser is Mandatory")
    @JsonProperty("iduser")
    private int idUser;


    @NotNull(message = "idProduct is Mandatory")
    @JsonProperty("idproduct")
    private int idproduct;
}
