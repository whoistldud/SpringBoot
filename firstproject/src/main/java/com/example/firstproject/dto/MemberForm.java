package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    // Member 생성자 추가
//    public MemberForm(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }

    // toString() 메소드 추가
//    @Override
//    public String toString() {
//        return "MemberForm{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

    public Member toEntity() {
        return new Member(id, email, password);
    }
}
