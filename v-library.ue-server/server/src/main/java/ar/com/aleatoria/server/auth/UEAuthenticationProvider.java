
package ar.com.aleatoria.server.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ar.com.aleatoria.server.db.UserDAO;

/**
 * Note: Theres two ways to do the authtentication, throught a subclass of
 * AuthenticationProvider or as a subclass of UserDetailsService
 */
public class UEAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserDAO userDAO;
    
    private static final Logger logger = 
	        LoggerFactory.getLogger(UEAuthenticationProvider.class);
    
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
        AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
        logger.info("Lets checkout if the user "+authentication.getName()+" exist and " +
        		"his password is equals to "+authentication.getCredentials());
        if ((userDAO.getUserByUsername(authentication.getName()).getPassword())
                .equals(authentication.getCredentials()))
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials(), AUTHORITIES);
        else
        	throw new UsernameNotFoundException("El nombre de usuario solicitado no existe");

    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);

    }

}
