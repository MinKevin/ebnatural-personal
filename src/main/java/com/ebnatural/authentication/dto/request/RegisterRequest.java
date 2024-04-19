package com.ebnatural.authentication.dto.request;

import com.ebnatural.authentication.domain.Member;
import com.ebnatural.authentication.domain.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class RegisterRequest {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Length(max = 320, message = "이메일은 320자리를 넘을 수 없습니다.")
    private String username;
    @Pattern(regexp = "[a-zA-Z1-9!@#$%^&*()]{8,16}",
            message = "비밀번호는 영어, 숫자, 특수문자(!@#$%^&*())를 포함한 8~16자리로 입력해주세요.")
    String password;
    @NotBlank(message = "please enter password confirm")
    String passwordConfirm;
    MemberRole memberRole = MemberRole.ROLE_USER;
    @NotBlank(message = "please enter business name")
    String businessName;
    @NotBlank(message = "please enter representative")
    String representative;
    @NotBlank(message = "please enter business number")
    String businessNumber;
    @NotBlank(message = "please enter postal code")
    String postalCode;
    @NotBlank(message = "please enter address")
    String address;
    String businessRegistration;
    @NotBlank(message = "please enter manager name")
    String manager;
    @NotBlank(message = "please enter manager email")
    String managerEmail;
    @NotBlank(message = "please enter manager phone number")
    String managerPhoneNumber;
    @NotNull(message = "please agree terms of services")
    List<Boolean> termsOfServices;

    public Member toEntity(String encodedPassword) {
        return new Member(username,
                encodedPassword,
                businessName,
                representative,
                businessNumber,
                postalCode,
                address,
                businessRegistration,
                manager,
                managerEmail,
                managerPhoneNumber);
    }
}
