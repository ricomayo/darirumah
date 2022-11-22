package project.rico.darirumah.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    String iduser;
    String username;
    @JsonIgnore
    String password;
    String name;
    String address;
    String handphone;
    String access;

}
