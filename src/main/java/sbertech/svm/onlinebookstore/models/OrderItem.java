package sbertech.svm.onlinebookstore.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ORDER_ITEMS")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int quantity;
    private double price;
}
