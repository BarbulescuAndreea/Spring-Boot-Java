package com.example.libraryStore;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CrudRepository -  It provides several methods out of the box for interacting with a database
// operations for permanent storage such as : create, read, update, delete
// so i can make operations over Books
public interface BooksRepo extends CrudRepository<BookClass, Long> {
    List<BookClass> bookByTitle(String title);
}
