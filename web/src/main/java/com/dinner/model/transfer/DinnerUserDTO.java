package com.dinner.model.transfer;

import lombok.Data;

/**
 * Created by Tomek on 05-Feb-17.
 */
@Data
public class DinnerUserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatPassword;
}
