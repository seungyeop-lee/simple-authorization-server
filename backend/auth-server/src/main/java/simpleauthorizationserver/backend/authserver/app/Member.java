package simpleauthorizationserver.backend.authserver.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String email;

    private String password;

    public static Member forSignupOf(String email, String password) {
        Member member = new Member();
        member.uuid = UUID.randomUUID().toString();
        member.email = email;
        member.password = password;
        return member;
    }
}
