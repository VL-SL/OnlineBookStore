package sbertech.svm.onlinebookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import sbertech.svm.onlinebookstore.model.Book;
import sbertech.svm.onlinebookstore.service.BookService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class AddBookController {
    private final BookService bookService;

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("id", bookService.getID());
        model.addAttribute("genre", bookService.getGenre());
        model.addAttribute("languages", bookService.getLanguages());
        return "add-book";
    }

    @PostMapping("/add-book/new")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/employee/add-book";
    }
}
