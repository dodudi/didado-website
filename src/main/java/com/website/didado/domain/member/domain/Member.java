package com.website.didado.domain.member.domain;

import com.website.didado.domain.member.dto.MemberParameter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    protected Member() {

    }

    public Member(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateMember(MemberParameter memberParameter) {
        if (StringUtils.hasText(memberParameter.username()))
            this.username = memberParameter.username();

        if (StringUtils.hasText(memberParameter.fullEmail()))
            this.email = memberParameter.fullEmail();

        if (StringUtils.hasText(memberParameter.password()))
            this.password = memberParameter.password();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
