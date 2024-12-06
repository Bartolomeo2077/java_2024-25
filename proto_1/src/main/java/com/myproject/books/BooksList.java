package com.myproject.books;

import com.myproject.dao.BookDAO;
import com.myproject.entities.Books;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class BooksList {

    @EJB
    private BookDAO bookDAO;
    private Books newBook;

    public BooksList() {
        newBook = new Books();
        newBook.setPublished(new Date());
    }

    public List<Books> getBooks() {
        return bookDAO.getFullList();
    }

    public Books getNewBook() {
        return newBook;
    }

    public void setNewBook(Books newBook) {
        this.newBook = newBook;
    }

    public void addBook() {
        bookDAO.create(newBook);
        newBook = new Books();
        newBook.setPublished(new Date());
    }

    public void deleteBook(Books book) {
        bookDAO.remove(book);
    }
}
