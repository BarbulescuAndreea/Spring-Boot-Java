package com.example.libraryStore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class SpringBootTest {
        private static final String API_ROOT
                = "http://localhost:8081/api/books";

        private BookClass createRandomBook() {
            BookClass book = new BookClass();
            book.setTitle(randomAlphabetic(10));
            book.setAuthor(randomAlphabetic(15));
            return book;
        }

        private String createBookAsUri(BookClass book) {
            Response response = RestAssured.given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(book)
                    .post(API_ROOT);
            return API_ROOT + "/" + response.jsonPath().get("id");
        }

    @Test
    public void whenGetAllBooks_thenOK() {
        Response response = RestAssured.get(API_ROOT);

        assertEquals("Ok", HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetNotExistBookById_thenNotFound() {
        Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));

        assertEquals("Not Found", HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void whenCreateNewBook_thenCreated() {
        BookClass book = createRandomBook();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);


        assertEquals("Created", HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenDeleteCreatedBook_thenOk() {
        BookClass book = createRandomBook();
        String location = createBookAsUri(book);
        Response response = RestAssured.delete(location);

        assertEquals("Deleted", HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals("Not found the book", HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
}
