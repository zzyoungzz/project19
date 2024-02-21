package filesource.project16.service;

import filesource.project16.domain.SiteUser;
import filesource.project16.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public SiteUser save(String username, String password) {

        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setPassword(passwordEncoder.encode(password));     //보안password

        userRepository.save(siteUser);
//        this.userRepository.save(user);
        return siteUser;
    }
}
