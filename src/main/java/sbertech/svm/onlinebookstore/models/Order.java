package sbertech.svm.onlinebookstore.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private LocalDateTime orderDate;

    private double totalPrice;
    private String status;

    @PrePersist
    private void init() {
        orderDate = LocalDateTime.now();
        status = "Создан";
    }
}

