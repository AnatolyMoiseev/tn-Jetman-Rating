package ru.tn.tnJetmanRating.service.impl.secure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tn.tnJetmanRating.persistance.repository.UserRepository;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final TempUserRepository tempUserRepository;
    private final UserRoleRepository roleRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, TempUserRepository tempUserRepository, UserRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tempUserRepository = tempUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.trace("Try find  user with name: {}", s);
        try {
            return userRepository.findUserByScreenNameIgnoreCase(s)
                    .map(AuthUserUtils::mapToUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + s));
        } catch (UsernameNotFoundException e){
            return  tempUserRepository.findTempUserByScreenNameIgnoreCase(s)
                    .map(AuthUserUtils::mapToTempUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + s));
        }
    }

    public TempUser save(TempUser user) {
        return tempUserRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
