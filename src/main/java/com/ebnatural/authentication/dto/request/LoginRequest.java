package com.ebnatural.authentication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class LoginRequest {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Length(max = 320, message = "이메일은 320자리를 넘을 수 없습니다.")
    private String username;

    @Pattern(regexp = "[a-zA-Z1-9!@#$%^&*()]{8,16}",
            message = "비밀번호는 영어, 숫자, 특수문자를 포함한 8~16자리로 입력해주세요")
    private String password;
}
