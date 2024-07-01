package sbertech.svm.onlinebookstore.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import sbertech.svm.onlinebookstore.model.Book;
import sbertech.svm.onlinebookstore.model.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addBookToCart(Book book) {
        Optional<CartItem> existingCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getBook().getBookId().equals(book.getBookId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItems.add(new CartItem(book, 1));
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getBook().getPrice() * cartItem.getQuantity())
                .sum();
    }

    public Optional<CartItem> getCartItemByBookId(Long bookId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getBook().getBookId().equals(bookId))
                .findFirst();
    }
}