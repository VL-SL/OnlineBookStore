package sbertech.svm.onlinebookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbertech.svm.onlinebookstore.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
