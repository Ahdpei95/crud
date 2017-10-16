package com.Crud.CrudController.controller;

import com.Crud.CrudController.model.Book;
import com.Crud.CrudController.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Controller
public class BooksController {
    private BookService bookService;

    @Autowired(required = true)
    public BooksController(@Qualifier(value = "BookService") BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(value = "/index")
    public String listBooks(Map<String, Object> map) {
        map.put("book", bookService.listBooks().get(0));
        map.put("listBooks", bookService.listBooks());
//        model.addAttribute("book", new Book());
//        model.addAttribute("listBooks", bookService.listBooks());
       return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if(book.getId() == 0){
            this.bookService.addBook(book);
        }else {
            this.bookService.updateBook(book);
        }

        return "redirect:/index";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id, Model model) {
       bookService.removeBook(id);
        model.addAttribute("listBooks", bookService.listBooks());
       return "redirect:/index";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
       model.addAttribute("book", bookService.getBookById(id));
       model.addAttribute("listBooks", bookService.listBooks());
       return "books";
    }
}
