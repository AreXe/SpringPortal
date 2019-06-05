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

import javax.ws.rs.POST;
import java.time.LocalDate;

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
        String loggedUser = UserUtils.getLoggedUser();
        User user = userService.findUserByEmail(loggedUser);

        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setStartDate(LocalDate.now());

        booksService.updateBookStatus(book.getId(), BookStatus.UNAVAILABLE.toString());

        borrowService.saveBorrow(borrow);
        return "redirect:books";
    }
}
