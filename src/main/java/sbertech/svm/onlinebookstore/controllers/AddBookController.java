package sbertech.svm.onlinebookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import sbertech.svm.onlinebookstore.models.Book;
import sbertech.svm.onlinebookstore.services.BookService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class AddBookController {
    private final BookService bookService;

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("genre", bookService.getGenre());
        model.addAttribute("languages", bookService.getLanguages());
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book/new")
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "redirect:/employee/add-book";
    }
}
