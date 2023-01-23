package com.example.libraryStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/books") // map web request on certain classes or methods
// for every request on that path
public class ControllerForBooks {
    // In Spring Boot, the controller class is responsible for processing incoming REST API requests,
    // preparing a model, and returning the view to be rendered as a response
    // RestController vs Controller
    // the first one has the default @ResponseBody included in @RequestMapping which sends http
    // response with the data in JSON format.
    // Controller needs to specialy specify the @ResponseBody on every handler method

    private BooksRepo mybooksrepo;

    @GetMapping //  maps HTTP GET requests onto specific handler methods - only for GET operation!
    // @GetMapping acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    // used to connects the methods!
    public Iterable findAll() { // for a not specific get request, show all the books
        return mybooksrepo.findAll();
    }

    @GetMapping("/{id}") // http get request for book based on id
    public BookClass findOne(@PathVariable Long id) throws NoBookException {
        return mybooksrepo.findById(id)
                .orElseThrow(NoBookException::new);
    }

    @GetMapping("/title/{bookTitle}") // find the book by title
    public List findByTitle(@PathVariable String bookTitle) {
        return mybooksrepo.bookByTitle(bookTitle);
    }

    @PostMapping // @RequestMapping(method = RequestMethod. POST)
    // POST is used to send data to a server to create/update a resource.
    @ResponseStatus(HttpStatus.CREATED) //statusul raspunsului - CREATE
    public BookClass create(@RequestBody BookClass book) {
        return mybooksrepo.save(book);
    }

    @DeleteMapping("/{id}") // maps HTTP DELETE requests onto specific handler methods
    public void delete(@PathVariable Long id) throws NoBookException {
        mybooksrepo.findById(id).orElseThrow(NoBookException::new); // first check if the book exists
        mybooksrepo.deleteById(id); // delete it
    }
}
