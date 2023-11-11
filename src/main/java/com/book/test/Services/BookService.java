package com.book.test.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.test.dao.BookRepository;
import com.book.test.entities.Book;

@Component
public class BookService {
	
	//Fake Service 
//	private static List<Book> list = new ArrayList<>();

	/*
	 * static {
	 * 
	 * list.add(new Book(12,"Java Complete Reference","harsh Ojha")); list.add(new
	 * Book(14,"Python","Sambhav")); list.add(new Book(16,"C++","Sanskar"));
	 * list.add(new Book(18,"Javascript","Sanskriti"));
	 * 
	 * }
	 */
	@Autowired
	private BookRepository bookRepository;
	
	// Get All Books Method
	public List<Book> getAllBooks()
	{
		List<Book> list=(List<Book>)this.bookRepository.findAll();
		
		return list;
	}
	
	//Get Single Books
	public Book getbookbyId(int id) {
		
		Book book = null;
		try {
	//	book = list.stream().filter(e -> e.getId()== id).findFirst().get();
			book =this.bookRepository.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;				
	}
	// add book
	public Book addBook(Book b) {
	//	list.add(b);
		Book result = this.bookRepository.save(b);
		return result;
	}
	
	// Delete book
	public void deleteBook(int bid) {
		
		//list=list.stream().filter(book -> book.getId()!=bid).
		//collect(Collectors.toList());
		bookRepository.deleteById(bid);
	}
	//update Book
	public void updateBook(Book book,int bookId) {
		
		/*
		 * list= list.stream().map(b ->{ if(b.getId()== bookId) {
		 * b.setTitle(book.getTitle()); b.setAuthor(book.getAuthor()); }
		 * 
		 * return b; }).collect(Collectors.toList());
		 */
		book.setId(bookId);
		bookRepository.save(book);
	}
}
