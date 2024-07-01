package sbertech.svm.onlinebookstore.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long bookId;
    private String bookTitle;
    private String author;
    private String language;
    private String publishingHouse;
    private int publicationYear;
    @Nullable
    private String genre;
    private String ISBN;
    @Positive
    private double price;
    @Min(1)
    private int pageCount;
    @Size(max = 100)
    @Nullable
    private String annotation;
    @Min(0)
    @Max(10)
    @Nullable
    private double bookRating;
    private boolean isNewBook;
    @Min(0)
    private int bookCount;
    private String image;

    public String getAvailabilityMessage() {
        if (bookCount < 1) {
            return "Товар закончился";
        } else if (bookCount >= 1 && bookCount < 5) {
            return "Осталось мало";
        } else {
            return "На складе";
        }
    }
}
