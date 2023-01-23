package com.example.libraryStore;

import jakarta.persistence.*;

@Entity
// entity - o tabela dintr o baza de date relationala, unde fiec intrare este o linie
public class BookClass {
    // The @Id annotation is inherited from javax.persistence.Id， indicating the member
    // field below is the PRIMARY KEY of current entity
    // GenerationType. IDENTITY − In identity , database is responsible to auto generate the primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    // nullable = false, meaning if i try to insert a null title into the database, it will result into an error
    // unique = true, unique in @Column is used only if you let your JPA provider create the database for you -
    // it will create the unique constraint on the specified column. Otherwise, it will have no effect
    private String bookTitle;

    @Column(nullable = false)
    // the name canot be null
    private String bookAuthor;

    public void setTitle(String randomAlphabetic) {
        bookTitle = randomAlphabetic;
    }

    public void setAuthor(String randomAlphabetic) {
        bookAuthor = randomAlphabetic;
    }
}
