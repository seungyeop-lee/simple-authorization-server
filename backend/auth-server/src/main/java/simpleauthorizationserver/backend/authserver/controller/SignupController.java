package simpleauthorizationserver.backend.authserver.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import simpleauthorizationserver.backend.authserver.app.MemberService;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupParam param) {
        memberService.createMember(param.getEmail(), param.getPassword());
        return ResponseEntity.ok().build();
    }

    @Data
    public static class SignupParam {
        private String email;
        private String password;
    }
}
