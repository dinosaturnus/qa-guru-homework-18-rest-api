package tests.models.get_list_users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetListUsersResponseModel {

    Integer page;

    @JsonProperty("per_page")
    Integer perPage;

    Integer total;

    @JsonProperty("total_pages")
    Integer totalPages;

    List<UsersInfoDataObject> data;

    SupportDataObject support;
}
