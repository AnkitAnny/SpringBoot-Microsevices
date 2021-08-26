package com.ekart.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int uid;

    @Size(min = 3, max = 50)
    @NotBlank(message = "Name is mandatory")
    //@Pattern(regexp = "^[a-zA-Z]", message = "Name contains invalid character")
    private String name;

    @Email(message = "Please enter a valid e-mail address")
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true,length = 30)
    private String email;

    @NotBlank(message = "Password is mandatory")
    //@Pattern(regexp = "^[a-zA-Z0-9]", message = "Password should contain at least an uppercase and a lowercase character, a number and a special character")
    private String passwd;

    @Transient
    private String confirmPassword;

    @Column(name="account_type")
    private int type;
}

