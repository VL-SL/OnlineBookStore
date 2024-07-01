package sbertech.svm.onlinebookstore.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import sbertech.svm.onlinebookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class BookService {
    private List<Book> books = new ArrayList<>();
    private long ID = 0;
    private List<String> languages = List.of("Русский", "Английский", "Французский", "Итальянский", "Немецкий",
            "Испанский", "Китайский", "Японский");
    private List<String> genre = List.of("Роман", "Поэма", "Детектив", "Художественная литература",
            "Фантастика", "Фэнтези", "Ужасы", "Детская литература", "Нон-фикшн", "Биография");


    {
        books.add(new Book(++ID,"Мертвые души", "Н. В. Гоголь", "Русский",
                "АСТ", 2023, "Роман-поэма", "978-5-17-112382-6",
                490, 352, "Это история Чичикова, странствующего чиновника среднего звена," +
                " отчаянно пытающегося нажить своё состояние.", 9, false, 10, "1"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        book.setBookId(++ID);
        if (book.getImage() == null || book.getImage().isEmpty()) {
            book.setImage("not");
        }
        books.add(book);
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getBookId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

}
