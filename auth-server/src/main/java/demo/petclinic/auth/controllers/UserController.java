package demo.petclinic.auth.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
public class UserController {
    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> me(Principal principal) {
        var auth = (Authentication) principal;
        Map<String, Object> authorization = new HashMap<>();
        authorization.put("username", auth.getName());
        authorization.put("authorities",
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()));
        return authorization;
    }
}
