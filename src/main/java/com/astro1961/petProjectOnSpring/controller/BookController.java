package com.astro1961.petProjectOnSpring.controller;

import com.astro1961.petProjectOnSpring.model.Book;
import com.astro1961.petProjectOnSpring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by astro1961 on 25.01.2017.
 */
@Controller
public class BookController {
    BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @RequestMapping(value = "books",method = RequestMethod.GET)
    public String listBooks(Model model){
            model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());
        return "books";
    }
    @RequestMapping(value = "/books/add",method= RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
        if(book.getId()==0){
            this.bookService.addBook(book);
        }else{this.bookService.updateBook(book);}
        return "redirect:/books";
    }
    @RequestMapping(value = "remove/{id}")
    public String removeBook(@PathVariable("id") int id){
        this.bookService.removeBook(id);
        return"redirect:books/";
    }
    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id,Model model){
        model.addAttribute("book",this.bookService.getBookById(id));
        model.addAttribute("listBooks",this.bookService.listBooks());
        return "books";
    }
    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model ){
                model.addAttribute("book", this.bookService.getBookById(id));
        return "bookdata";
    }
}
