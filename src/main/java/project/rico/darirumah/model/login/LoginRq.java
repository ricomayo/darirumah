package project.rico.darirumah.model.login;

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
public class LoginRq {

    @NotNull(message = "userName is Mandatory")
    @JsonProperty("userName")
    private String userName;

    @NotNull(message = "password is Mandatory")
    @JsonProperty("password")
    private String password;


}
