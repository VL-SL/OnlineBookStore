package sbertech.svm.onlinebookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sbertech.svm.onlinebookstore.enums.Role;
import sbertech.svm.onlinebookstore.models.Book;
import sbertech.svm.onlinebookstore.models.User;
import sbertech.svm.onlinebookstore.services.BookService;
import sbertech.svm.onlinebookstore.services.UserService;

@SpringBootApplication
public class OnlineBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(BookService bookService) {
		return args -> {
			Book book = new Book(
					null,
					"Мертвые души",
					"Н. В. Гоголь",
					"Русский",
					"АСТ",
					2023,
					"Роман-поэма",
					"978-5-17-112382-6",
					489.99,
					352,
					"Это история Чичикова, странствующего чиновника среднего звена, отчаянно пытающегося" +
							" нажить своё состояние.",
					9,
					false,
					10,
					"1"
			);
			bookService.addBook(book);

			Book book2 = new Book(
					null,
					"Сияние",
					"Стивен Кинг",
					"Английский",
					"АСТ",
					2022,
					"Ужасы",
					"978-5-17-130945-2",
					464.99,
					320,
					"Мальчик Дэйвид обладает сильной " +
							"экстрасенсорикой и видит в отеле множество ужасных призраков.",
					8,
					false,
					12,
					"4"
			);
			bookService.addBook(book2);

			Book book3 = new Book(
					null,
					"451 градус по Фаренгейту",
					"Рэй Брэдбери",
					"Английский",
					"АСТ",
					2021,
					"Научная фантастика",
					"978-5-17-129729-5",
					224.99,
					152,
					"В мире, где книги запрещены и сжигаются, Гай Монтаг - один из немногих," +
							" кто отказывается подчиняться власти и сохраняет в себе страсть к чтению.",
					7,
					false,
					1,
					"8"
			);
			bookService.addBook(book3);
		};
	}

	@Bean
	public CommandLineRunner initUsers(UserService userService) {
		return args -> {
			User adminUser = new User();
			adminUser.setEmail("admin@mail.ru");
			adminUser.setPassword("admin");
			adminUser.getRoles().add(Role.ROLE_ADMIN);
			userService.createUser(adminUser);

			User regularUser = new User();
			regularUser.setEmail("user@mail.ru");
			regularUser.setPassword("user");
			regularUser.getRoles().add(Role.ROLE_USER);
			userService.createUser(regularUser);
		};
	}
}
