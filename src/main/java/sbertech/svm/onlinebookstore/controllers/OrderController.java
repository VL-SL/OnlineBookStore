package sbertech.svm.onlinebookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sbertech.svm.onlinebookstore.models.User;
import sbertech.svm.onlinebookstore.services.OrderService;
import sbertech.svm.onlinebookstore.services.UserService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("orders", orderService.getOrdersByUser(currentUser));
        return "orders";
    }

    @PostMapping("/order/checkout")
    public String placeOrder() {
        User user = userService.getCurrentUser();
        orderService.placeOrder(user);
        return "redirect:/orders";
    }
}
