package com.arexe.portal.controllers;

import com.arexe.portal.entity.Book;
import com.arexe.portal.entity.BookStatus;
import com.arexe.portal.entity.Borrow;
import com.arexe.portal.entity.User;
import com.arexe.portal.service.BooksService;
import com.arexe.portal.service.BorrowService;
import com.arexe.portal.service.UserService;
import com.arexe.portal.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BorrowController {

    @Autowired
    private UserService userService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    BooksService booksService;

    @POST
    @RequestMapping(value = "addnewborrow")
    public String addBorrow(Model model, Book book) {
        Borrow borrow = new Borrow();

        borrow.setUser(getLoggedUser());
        borrow.setBook(book);
        borrow.setStartDate(LocalDate.now());

        booksService.updateBookStatus(book.getId(), BookStatus.UNAVAILABLE.toString());

        borrowService.saveBorrow(borrow);
        return "redirect:books";
    }

    @GET
    @RequestMapping(value = "borrows")
    public String getBorrowList(Model model, Borrow borrow) {
        List<Borrow> borrowList = borrowService.getBorrowsByUser(getLoggedUser()).stream().filter(b -> b.getEndDate() == null).collect(Collectors.toList());
        model.addAttribute("borrowList", borrowList);
        return "borrows";
    }

    @PUT
    @RequestMapping(value = "returnbook")
    public String returnBook(Model model, Borrow borrow) {
        Borrow borrowById = borrowService.getBorrowById(borrow.getId());
        borrowById.setEndDate(LocalDate.now());
        booksService.updateBookStatus(borrowById.getBook().getId(), BookStatus.AVAILABLE.toString());
        borrowService.saveBorrow(borrowById);
        return "redirect:borrows";
    }

    private User getLoggedUser() {
        String loggedUser = UserUtils.getLoggedUser();
        return userService.findUserByEmail(loggedUser);
    }
}
