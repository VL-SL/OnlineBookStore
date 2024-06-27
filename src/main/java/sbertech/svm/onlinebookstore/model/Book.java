package sbertech.svm.onlinebookstore.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private int bookId;
    private String bookTitle;
    private String author;
    private String language;
    private String publishingHouse;
    private int publicationYear;
    private String genre;
    private String ISBN;
    private double price;
    private int pageCount;
    private String annotation;
    private int bookRating;
    private boolean isNewBook;
    private int bookCount;
}
