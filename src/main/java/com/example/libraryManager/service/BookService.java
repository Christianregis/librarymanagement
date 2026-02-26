package com.example.libraryManager.service;

import com.example.libraryManager.model.Book;
import com.example.libraryManager.repository.BookRepository;

import java.util.List;


public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book newBook){
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            book.setTitle(newBook.getTitle() != null ? newBook.getTitle() : book.getTitle());
            book.setAuteur(newBook.getAuteur() != null ? newBook.getAuteur() : book.getAuteur());
            book.setIsbn(newBook.getIsbn() != null ? newBook.getIsbn() : book.getIsbn());
            book.setCategory(newBook.getCategory() != null ? newBook.getCategory() : book.getCategory());

            return bookRepository.save(book);
        }
        return null;
    }

    public Boolean deleteBooK(Long id){
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            bookRepository.delete(book);
            return true;
        }
        return false;
    }

    // Recherche de livres ici
}
