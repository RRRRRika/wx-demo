package com.rika.wxspringbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String uid;
    private String name;
    private String role = "USER";

    public User(String uid)  {
        this.uid = uid;
    }
}
