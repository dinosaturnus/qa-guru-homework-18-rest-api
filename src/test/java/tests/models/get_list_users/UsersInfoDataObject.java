package tests.models.get_list_users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsersInfoDataObject {

    Integer id;

    String email;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    String avatar;
}
