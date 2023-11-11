package com.book.test.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.test.Services.BookService;
import com.book.test.entities.Book;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {   /*  Handler  */
		
        List<Book> list =bookService.getAllBooks();
        if(list.size()<=0) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));         
	}
	
	// Get Single Books
	@GetMapping("/books/{id}")
	public ResponseEntity <Book> getBook(@PathVariable("id") int id) {
		
		Book book = bookService.getbookbyId(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
		
	}
	
	// Add a book
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		Book b;
		try {
			b=this.bookService.addBook(book);
			System.out.println(book);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	//Delete book handler
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Object> deleteBook(@PathVariable("bookId") int bookId) {
		try {
		System.out.println(bookId);
		this.bookService.deleteBook(bookId);	
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
	// Update book Handler
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId) {
		try {
		this.bookService.updateBook(book,bookId);
		return ResponseEntity.ok().body(book);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
		
	
	
}
