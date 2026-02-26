package com.example.libraryManager.controller;

import com.example.libraryManager.dto.BookDto;
import com.example.libraryManager.dto.CategoryDto;
import com.example.libraryManager.dto.UserDto;
import com.example.libraryManager.model.Book;
import com.example.libraryManager.model.Category;
import com.example.libraryManager.service.BookService;
import com.example.libraryManager.service.CategoryService;
import com.example.libraryManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public AdminController(UserService userService, CategoryService categoryService, BookService bookService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    // Gestion des utilisateurs

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
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
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
        }
        return ResponseEntity.notFound().build();
    }

    // Gestion des categories

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(category));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        Category newCategory = new Category();
        newCategory.setName(categoryDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.updateCategory(id, newCategory));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<List<String>> deleteCategory(@PathVariable Long id){
        if (categoryService.deleteCategory(id)){
            return ResponseEntity.status(HttpStatus.OK).body(List.of("success","Categorie supprimee !"));
        }
        return ResponseEntity.notFound().build();
    }

    // Gestion des Livres

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
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
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookInformation(id));
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
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(id, book));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<List<String>> deleteBook(@PathVariable Long id){
        if(bookService.deleteBooK(id)){
            return ResponseEntity.status(HttpStatus.OK).body(List.of("success","Livre supprimee !"));
        }
        return ResponseEntity.notFound().build();
    }
}
