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
public class LoginRq {

    @NotNull(message = "groupId is Mandatory")
    @JsonProperty("groupId")
    private String userName;

    @NotNull(message = "alias is Mandatory")
    @JsonProperty("alias")
    private String password;


}
