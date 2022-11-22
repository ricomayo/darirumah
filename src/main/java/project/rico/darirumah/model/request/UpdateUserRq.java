package project.rico.darirumah.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UpdateUserRq {
    @NotNull(message = "password is Mandatory")
    @JsonProperty("oldPassword")
    private String oldPassword;

    @JsonProperty("newPassword")
    private String newPassword;


    @JsonProperty("name")
    private String name;


    @JsonProperty("address")
    private String address;


    @JsonProperty("handphone")
    private String handphone;
}
