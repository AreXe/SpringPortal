package com.arexe.portal.service;

import com.arexe.portal.entity.Borrow;
import com.arexe.portal.entity.User;

import java.util.List;

public interface BorrowService {

    Borrow getBorrowById(int id);
    List<Borrow> getBorrowsByUser(User user);
    void saveBorrow(Borrow borrow);
}
