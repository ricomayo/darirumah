package project.rico.darirumah.model.solace;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SolaceMessageRq {
    @SerializedName("eventid")
    private String eventId;

    @SerializedName("idorder")
    private String idOrder;

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("idproduct")
    private String idProduct;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("status")
    private String status;

}
