package sbertech.svm.onlinebookstore.service;

import org.springframework.stereotype.Service;
import sbertech.svm.onlinebookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>(List.of(
            Book.builder()
                    .bookId(1)
                    .bookTitle("Мертвые души")
                    .author("Н. В. Гоголь")
                    .language("Русский")
                    .publishingHouse("АСТ")
                    .publicationYear(2023)
                    .genre("Роман-поэма")
                    .ISBN("978-5-17-112382-6")
                    .price(490)
                    .pageCount(352)
                    .annotation("Это история Чичикова, странствующего чиновника среднего звена," +
                            " отчаянно пытающегося нажить своё состояние. Он стремится сделать" +
                            " это не обычным путём, а скупая крестьян, умерших после последней" +
                            " переписи населения, и поэтому живых только на бумаге. Затем он" +
                            " может заложить эти «мёртвые души» и разбогатеть.")
                    .bookRating(9)
                    .isNewBook(false)
                    .bookCount(3)
                    .build()
    ));

    public List<Book> getAllBooks() {
        return books;
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }
}
