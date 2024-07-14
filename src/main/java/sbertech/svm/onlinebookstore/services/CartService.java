package sbertech.svm.onlinebookstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sbertech.svm.onlinebookstore.models.Book;
import sbertech.svm.onlinebookstore.models.CartItem;
import sbertech.svm.onlinebookstore.models.User;
import sbertech.svm.onlinebookstore.repositories.CartItemRepository;
import sbertech.svm.onlinebookstore.repositories.UserRepository;

import java.text.DecimalFormat;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email);
    }

    public void addBookToCart(Book book) {
        User user = getCurrentUser();
        Optional<CartItem> existingCartItem = cartItemRepository.findByBookAndUser(book, user);

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setBook(book);
            newCartItem.setUser(user);
            newCartItem.setQuantity(1);
            cartItemRepository.save(newCartItem);
        }
    }

    public List<CartItem> getCartItems() {
        User user = getCurrentUser();
        return cartItemRepository.findByUser(user);
    }

    public void clearCart() {
        User user = getCurrentUser();
        cartItemRepository.deleteByUser(user);
    }

    public String getTotalPrice() {
        User user = getCurrentUser();
        double total = cartItemRepository.findByUser(user).stream()
                .mapToDouble(cartItem -> cartItem.getBook().getPrice() * cartItem.getQuantity())
                .sum();

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(total);
    }

    public Optional<CartItem> getCartItemByBookId(Long bookId) {
        User user = getCurrentUser();
        return cartItemRepository.findByBookIdAndUserId(bookId, user.getId());
    }
}