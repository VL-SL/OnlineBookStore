package sbertech.svm.onlinebookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbertech.svm.onlinebookstore.models.Order;
import sbertech.svm.onlinebookstore.models.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
