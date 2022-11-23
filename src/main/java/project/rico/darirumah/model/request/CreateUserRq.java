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
public class CreateUserRq {
    @NotNull(message = "userName is Mandatory")
    @JsonProperty("username")
    private String userName;

    @NotNull(message = "password is Mandatory")
    @JsonProperty("password")
    private String password;

    private String name;
    private String address;
    private String handphone;

}
