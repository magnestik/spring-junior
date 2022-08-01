package ru.iteco.teachbase.springjunior.account.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.model.entity.UserAuthEntity;
import ru.iteco.teachbase.springjunior.account.repository.UserAuthRepository;

import java.util.List;

@Component
public class DbUserDetailService implements UserDetailsService {
    private final UserAuthRepository userAuthRepository;

    public DbUserDetailService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthEntity userAuthEntity = userAuthRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(userAuthEntity.getUsername(),
            userAuthEntity.getPassword(),
            List.of(new SimpleGrantedAuthority("user")));
    }
}
