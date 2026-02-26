package com.example.libraryManager.service;

import com.example.libraryManager.dto.BookDto;
import com.example.libraryManager.model.Book;
import com.example.libraryManager.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto saveBook(Book book){
        return bookRepository.save(book).toDto();
    }

    public BookDto updateBook(Long id, Book newBook){
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            book.setTitle(newBook.getTitle() != null ? newBook.getTitle() : book.getTitle());
            book.setAuteur(newBook.getAuteur() != null ? newBook.getAuteur() : book.getAuteur());
            book.setIsbn(newBook.getIsbn() != null ? newBook.getIsbn() : book.getIsbn());
            book.setCategory(newBook.getCategory() != null ? newBook.getCategory() : book.getCategory());
            book.setStatus(newBook.getStatus() != null ? newBook.getStatus() : book.getStatus());
            return bookRepository.save(book).toDto();
        }
        return null;
    }

    public List<BookDto> getAllBooks(){
        return bookRepository.findAll().stream().map(
                Book::toDto
        ).collect(Collectors.toList());
    }

    public BookDto getBookInformation(Long id){
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            return Objects.requireNonNull(bookRepository.findById(id).orElse(null)).toDto();
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
