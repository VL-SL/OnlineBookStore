package sbertech.svm.onlinebookstore.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbertech.svm.onlinebookstore.enums.Role;
import sbertech.svm.onlinebookstore.models.User;
import sbertech.svm.onlinebookstore.repositories.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with  email: {}", email);
        userRepository.save(user);
        return true;
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email);
    }
}