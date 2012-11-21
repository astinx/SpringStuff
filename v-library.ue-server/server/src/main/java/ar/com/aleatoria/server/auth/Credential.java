
package ar.com.aleatoria.server.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Credential {
    private final String username;

    private final String md5Password;

    private final List<GrantedAuthority> authorizations;

    public Credential(String username, String encodePassword, List<GrantedAuthority> authorizations) {
        this.username = username;
        this.md5Password = encodePassword;
        this.authorizations = authorizations;
    }

}
