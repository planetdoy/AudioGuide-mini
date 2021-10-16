package com.miniproject.audioguide.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginForm {
    private String loginId;
    private String password;

}
