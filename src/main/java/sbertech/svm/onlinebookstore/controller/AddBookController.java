package sbertech.svm.onlinebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import sbertech.svm.onlinebookstore.model.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import sbertech.svm.onlinebookstore.service.BookService;

@Controller
@RequestMapping("/api/v1/addbook")
public class AddBookController {
    private final BookService bookService;

    @Autowired
    public AddBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping
    public String addBook(@ModelAttribute Book book, Model model) {
        model.addAttribute("book", book);
        return "book-added-success";
    }
}
