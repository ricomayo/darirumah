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
public class UserRef {

    private String iduser;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private String address;
    private String handphone;
    private String access;

}
