package sbertech.svm.onlinebookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbertech.svm.onlinebookstore.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
