package com.example.libraryManager.controller;

import com.example.libraryManager.dto.BookDto;
import com.example.libraryManager.dto.CategoryDto;
import com.example.libraryManager.dto.EmpruntDto;
import com.example.libraryManager.dto.UserDto;
import com.example.libraryManager.model.Book;
import com.example.libraryManager.model.Category;
import com.example.libraryManager.model.Emprunt;
import com.example.libraryManager.model.User;
import com.example.libraryManager.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final EmpruntService empruntService;
    private final ReturnService returnService;

    public AdminController(UserService userService, CategoryService categoryService, BookService bookService, EmpruntService empruntService, ReturnService returnService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.empruntService = empruntService;
        this.returnService = returnService;
    }

    // Gestion des utilisateurs

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser().stream().map(User::toDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<List<String>> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.OK).body(List.of("success","Utlisateur supprimee !"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserInformation(@PathVariable Long id){
        if (userService.findUserById(id) != null){
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id).toDto());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/count")
    public ResponseEntity<Long> getUsersCount(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersCount());
    }

    // Gestion des categories

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories().stream().map(
                Category::toDto
        ).collect(Collectors.toList()));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(category).toDto());
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        Category newCategory = new Category();
        newCategory.setName(categoryDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.updateCategory(id, newCategory).toDto());
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<List<String>> deleteCategory(@PathVariable Long id){
        if (categoryService.deleteCategory(id)){
            return ResponseEntity.status(HttpStatus.OK).body(List.of("success","Categorie supprimee !"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categories/count")
    public ResponseEntity<Long> getCategoriesCount(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoriesCount());
    }

    // Gestion des Livres

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks().stream().map(Book::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto){
        Category category = categoryService.getCategoryById(bookDto.getCategoryId()).orElse(null);
        if (category != null){
            Book book = new Book();
            book.setTitle(bookDto.getTitle());
            book.setAuteur(bookDto.getAuteur());
            book.setIsbn(bookDto.getIsbn());
            book.setStatus(bookDto.getStatus());

            book.setCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(book));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookInformation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookInformation(id).toDto());
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        Category category = categoryService.getCategoryById(bookDto.getCategoryId()).orElse(null);
        if (category != null){
            Book book = new Book();
            book.setTitle(bookDto.getTitle());
            book.setAuteur(bookDto.getAuteur());
            book.setIsbn(bookDto.getIsbn());
            book.setStatus(bookDto.getStatus());

            book.setCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(id, book).toDto());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/books/count")
    public ResponseEntity<Long> getBooksCount(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksCount());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<List<String>> deleteBook(@PathVariable Long id){
        if(bookService.deleteBooK(id)){
            return ResponseEntity.status(HttpStatus.OK).body(List.of("success","Livre supprimee !"));
        }
        return ResponseEntity.notFound().build();
    }

    // Gestion des emprunts
    @GetMapping("/emprunts/count")
    public ResponseEntity<Long> getEmpruntCount(){
        return ResponseEntity.status(HttpStatus.OK).body(empruntService.getEmpruntCount());
    }

    @GetMapping("/emprunts/")
    public ResponseEntity<List<EmpruntDto>> getAllEmprunts(){
        return ResponseEntity.status(HttpStatus.OK).body(empruntService.getAllEmprunts().stream().map(Emprunt::toDto).collect(Collectors.toList()));
    }

    // Gestion des retours
    @GetMapping("/returns/count")
    public ResponseEntity<Long> getReturnsCount(){
        return ResponseEntity.status(HttpStatus.OK).body(returnService.getReturnsCount());
    }

    @GetMapping("/returns/")
    public ResponseEntity<List<EmpruntDto>> getAllReturns(){
        return ResponseEntity.status(HttpStatus.OK).body(returnService.getAllReturns().stream().map(Emprunt::toDto).collect(Collectors.toList()));
    }

}
