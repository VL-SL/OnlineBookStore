package sbertech.svm.onlinebookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import sbertech.svm.onlinebookstore.services.BookService;
import sbertech.svm.onlinebookstore.services.CartService;
import sbertech.svm.onlinebookstore.services.OrderService;
import sbertech.svm.onlinebookstore.services.UserService;
import sbertech.svm.onlinebookstore.models.User;
import sbertech.svm.onlinebookstore.models.Book;

@SpringBootTest
class OnlineBookStoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private BookService bookService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	// Тест для проверки что метод getAllBooks не возвращает null
	@Test
	void testGetAllBooks() {
		Assertions.assertNotNull(bookService.getAllBooks());
	}

	// Тест для проверки добавления новой книги
	@Test
	void testAddBook() {
		Book book = new Book();
		bookService.addBook(book);
		Assertions.assertNotNull(bookService.getBookById(book.getBookId()));
	}
}
