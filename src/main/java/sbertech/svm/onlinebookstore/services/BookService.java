package sbertech.svm.onlinebookstore.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sbertech.svm.onlinebookstore.models.Book;
import sbertech.svm.onlinebookstore.repositories.BookRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private static final List<String> LANGUAGES = List.of("Русский", "Английский", "Французский", "Итальянский", "Немецкий", "Испанский", "Китайский", "Японский");
    private static final List<String> GENRES = List.of("Роман", "Поэма", "Детектив", "Художественная литература", "Фантастика", "Фэнтези", "Ужасы", "Детская литература", "Нон-фикшн", "Биография");

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        if (book.getImage() == null || book.getImage().isEmpty()) {
            book.setImage("not");
        }

        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    public List<String> getLanguages() {
        return LANGUAGES;
    }

    public List<String> getGenre() {
        return GENRES;
    }
}
