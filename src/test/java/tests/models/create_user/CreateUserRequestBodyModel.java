package tests.models.create_user;

import lombok.Data;

@Data
public class CreateUserRequestBodyModel {
    String name, job;
}