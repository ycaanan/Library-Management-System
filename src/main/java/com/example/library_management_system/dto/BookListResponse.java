package com.example.library_management_system.dto;
import com.example.library_management_system.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookListResponse {
    private List<Book> books;
    private String message;
    private int status;

}
