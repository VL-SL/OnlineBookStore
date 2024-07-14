package sbertech.svm.onlinebookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sbertech.svm.onlinebookstore.models.Book;
import sbertech.svm.onlinebookstore.models.CartItem;
import sbertech.svm.onlinebookstore.models.User;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByBookAndUser(Book book, User user);

    @Query("SELECT c FROM CartItem c WHERE c.book.bookId = :bookId AND c.user.id = :userId")
    Optional<CartItem> findByBookIdAndUserId(Long bookId, Long userId);

    List<CartItem> findByUser(User user);

    @Transactional
    void deleteByUser(User user);
}
