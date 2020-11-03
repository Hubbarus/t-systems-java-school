package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class security configs.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired private ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ClientDTO user = clientService.findByEmail(s);
        List<GrantedAuthority> authorities = new ArrayList<>(user.getAuthorities());

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(ClientDTO user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities);
    }
}
