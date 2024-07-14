package sbertech.svm.onlinebookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sbertech.svm.onlinebookstore.services.BookService;
import sbertech.svm.onlinebookstore.services.CartService;
import sbertech.svm.onlinebookstore.services.OrderService;
import sbertech.svm.onlinebookstore.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final BookService bookService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addBookToCart(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
        cartService.addBookToCart(bookService.getBookById(id));
        return "redirect:" + (referer != null ? referer : "/");
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @PostMapping("/remove-item/{id}")
    public String removeItemFromCart(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
        cartService.removeBookFromCart(id);
        return "redirect:" + (referer != null ? referer : "/cart");
    }

    @PostMapping("/checkout")
    public String placeOrder(Model model) {
        String result = orderService.placeOrder(userService.getCurrentUser());
        if (!result.equals("success")) {
            model.addAttribute("errorMessage", result);
            return viewCart(model);
        }
        return "redirect:/orders";
    }

    @PostMapping("/update-quantity/{id}")
    public String updateQuantity(@PathVariable Long id, @RequestParam String action, Model model) {
        try {
            if (action.equals("increase")) {
                cartService.increaseQuantity(id);
            } else if (action.equals("decrease")) {
                cartService.decreaseQuantity(id);
            }
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return viewCart(model);
        }
        return "redirect:/cart";
    }
}