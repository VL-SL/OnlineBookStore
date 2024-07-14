package sbertech.svm.onlinebookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sbertech.svm.onlinebookstore.models.Book;
import sbertech.svm.onlinebookstore.models.CartItem;
import sbertech.svm.onlinebookstore.services.BookService;
import sbertech.svm.onlinebookstore.services.CartService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BookListController {

    private final BookService bookService;
    private final CartService cartService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "home";
    }

    @GetMapping("/book/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        Optional<CartItem> cartItem = cartService.getCartItems().stream()
                .filter(item -> item.getBook().getBookId().equals(id))
                .findFirst();

        if (cartItem.isPresent()) {
            model.addAttribute("isInCart", true);
            model.addAttribute("cartItemQuantity", cartItem.get().getQuantity());
        } else {
            model.addAttribute("isInCart", false);
        }

        return "book-info";
    }
}
