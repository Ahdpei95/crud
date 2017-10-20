package com.Crud.CrudController.controller;

import com.Crud.CrudController.model.Book;
import com.Crud.CrudController.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BooksController {
    private BookService bookService;
    private int maxRows = 10;
    @Autowired(required = true)
    public BooksController(@Qualifier(value = "BookService") BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/index")
    public String startPage() {
        return "redirect:/books/0";
    }

    @RequestMapping(value = "/books/{page}")
    public String listBooks(@ModelAttribute("search_title") String title,
                            Model model, @PathVariable ("page") int page) {
        model.addAttribute("listBooks", bookService.listBooks(page, maxRows));
        model.addAttribute("book", new Book());
        model.addAttribute("page", page);
        model.addAttribute("count", bookService.getAllBooks().size() % maxRows == 0 ?
                bookService.getAllBooks().size() / maxRows - 1 :
                bookService.getAllBooks().size() / maxRows);
        model.addAttribute("isEdit", Boolean.FALSE);
        model.addAttribute("searchTitle", title);
        return "books";
    }

    @RequestMapping(value = "/books/{page}/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("search_title") String title,
                          @ModelAttribute("book") Book book, @PathVariable("page") int page, Model model) {
        System.out.println("add " + title);
        if(book.getId() == 0){
            this.bookService.addBook(book);
            model.addAttribute("isEdit", Boolean.FALSE);
        }else {
            this.bookService.updateBook(book);
            model.addAttribute("isEdit", Boolean.TRUE);
        }
        return chooseToRedirect(title, page);
    }

    @RequestMapping("{page}/remove/{id}")
    public String removeBook(@ModelAttribute("search_title") String title,
                             @PathVariable("id") int id, Model model, @PathVariable("page") int page) {
        bookService.removeBook(id);
        model.addAttribute("listBooks", bookService.getAllBooks());
        model.addAttribute("isEdit", Boolean.FALSE);
        model.addAttribute("searchTitle", title);
        System.out.println("Remove" + title);
        return chooseToRedirect(title, page);
    }

    @RequestMapping("{page}/edit/{id}")
    public String editBook(@ModelAttribute("search_title") String title,
                           @PathVariable("id") int id, Model model, @PathVariable("page") int page) {

        if (title.isEmpty()) {
            model.addAttribute("count",  bookService.getAllBooks().size() % maxRows == 0 ?
                    bookService.getAllBooks().size() / maxRows - 1 :
                    bookService.getAllBooks().size() / maxRows);
            model.addAttribute("book", bookService.getBookById(id));
            model.addAttribute("page", page);
            model.addAttribute("listBooks", bookService.listBooks(page, maxRows));
            model.addAttribute("isEdit", Boolean.TRUE);
            model.addAttribute("searchTitle", title);
        } else {
            model.addAttribute("count",  bookService.searchAllTitles(title).size() % maxRows == 0 ?
                    bookService.searchAllTitles(title).size() / maxRows - 1 :
                    bookService.searchAllTitles(title).size() / maxRows);
            model.addAttribute("book", bookService.getBookById(id));
            model.addAttribute("page", page);
            model.addAttribute("listBooks", bookService.searchTitles(title, page, maxRows));
            model.addAttribute("isEdit", Boolean.TRUE);
            model.addAttribute("searchTitle", title);
        }
        System.out.println("add " + title);
            return "books";
    }

    @RequestMapping(value = "{page}/readAlready/{id}")
    public String setReadAlready(@ModelAttribute("search_title") String title,
                                 @PathVariable("id") int id, @PathVariable("page") int page, Model model) {
        Book book = bookService.getBookById(id);
        book.setReadAlready(true);
        bookService.updateBook(book);
        model.addAttribute("isEdit", Boolean.FALSE);
        model.addAttribute("searchTitle", title);
        System.out.println("ReadAlready" + title);
        return chooseToRedirect(title, page);
    }


    @RequestMapping(value = "/books/find/{page}")
    public String search(@ModelAttribute("search_title") String title, @PathVariable("page") int page, Model model) {
        try{
        if(!title.isEmpty()){
            model.addAttribute("count", bookService.searchAllTitles(title).size() % maxRows == 0 ?
                    bookService.searchAllTitles(title).size() / maxRows - 1 :
                    bookService.searchAllTitles(title).size() / maxRows);
            model.addAttribute("book", new Book());
            model.addAttribute("page", page);
            model.addAttribute("isEdit", Boolean.FALSE);
            model.addAttribute("listBooks", bookService.searchTitles(title, page, maxRows));
            model.addAttribute("searchTitle", title);
            System.out.println(title);
        } else {
            return "redirect:/books/" + page;
        }}
        catch(Exception e){
            e.printStackTrace();
        }
        return "books";
    }

    private String chooseToRedirect(String title, int page) {
        if (!title.isEmpty())
            return "redirect:/books/find/" + page;
        else
            return "redirect:/books/" + page;
    }
}
