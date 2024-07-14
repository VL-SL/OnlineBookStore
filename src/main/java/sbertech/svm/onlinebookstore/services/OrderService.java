package sbertech.svm.onlinebookstore.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sbertech.svm.onlinebookstore.models.*;
import sbertech.svm.onlinebookstore.repositories.BookRepository;
import sbertech.svm.onlinebookstore.repositories.OrderRepository;
import sbertech.svm.onlinebookstore.repositories.CartItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final BookRepository bookRepository;

    @Transactional
    public String placeOrder(User user) {
        List<CartItem> cartItems = cartService.getCartItems();

        // Check book availability
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            if (book.getBookCount() < cartItem.getQuantity()) {
                return "Недостаточно книг \"" + book.getBookTitle() + "\" в наличии.";
            }
        }

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setBook(cartItem.getBook());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
                    return orderItem;
                }).collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        orderItems.forEach(orderItem -> orderItem.setOrder(order));
        orderRepository.save(order);

        // Reduce book stock
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            book.setBookCount(book.getBookCount() - cartItem.getQuantity());
            bookRepository.save(book);
        }

        cartService.clearCart();
        return "success";
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
