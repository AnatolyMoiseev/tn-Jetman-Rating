package ru.tn.tnJetmanRating.service.impl.secure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.persistance.repository.UserRepository;
import ru.tn.tnJetmanRating.utils.AuthUserUtils;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.trace("Try find  user with name: {}", s);
        try {
            return userRepository.findUserByUserNameIgnoreCase(s)
                    .map(AuthUserUtils::mapToUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + s));
        } catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User not found: " + s);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
