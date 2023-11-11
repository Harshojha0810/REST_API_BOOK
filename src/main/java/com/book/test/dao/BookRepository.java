package com.book.test.dao;

import org.springframework.data.repository.CrudRepository;

import com.book.test.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

	public Book findById(int id);
}
