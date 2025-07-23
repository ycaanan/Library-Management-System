

package com.example.library_management_system.repository;

import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//extends ettiğimden CRUD şlemlerini otomatik yapar
public interface BookRepository extends JpaRepository<Book, Long> { }
