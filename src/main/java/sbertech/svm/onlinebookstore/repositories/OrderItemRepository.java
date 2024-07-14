package sbertech.svm.onlinebookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbertech.svm.onlinebookstore.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
