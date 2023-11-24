package com.website.didado.domain.member.repository;

import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findById() {
        Member save = memberRepository.save(new Member(1L, "username", "idejrud@gmail.com", "password"));

        Member member = memberRepository.findById(1L)
                .orElseThrow(NullPointerException::new);

        Assertions.assertThat(save).isEqualTo(member);
    }

    @Test
    public void findAll() {
        memberRepository.save(new Member(1L, "username1", "idejrud@gmail.com1", "password1"));
        memberRepository.save(new Member(2L, "username2", "idejrud@gmail.com2", "password2"));
        memberRepository.save(new Member(3L, "username3", "idejrud@gmail.com3", "password3"));
        memberRepository.save(new Member(4L, "username4", "idejrud@gmail.com4", "password4"));

        List<Member> members = memberRepository.findAll();

        Assertions.assertThat(members.size()).isEqualTo(4);
    }

    @Test
    public void deleteById() {
        memberRepository.save(new Member(1L, "username1", "idejrud@gmail.com1", "password1"));
        memberRepository.save(new Member(2L, "username2", "idejrud@gmail.com2", "password2"));
        memberRepository.save(new Member(3L, "username3", "idejrud@gmail.com3", "password3"));
        memberRepository.save(new Member(4L, "username4", "idejrud@gmail.com4", "password4"));

        memberRepository.deleteById(3L);

        Member member = memberRepository.findById(3L)
                .orElseGet(() -> null);

        Assertions.assertThat(member).isEqualTo(null);
    }

}
