package com.arexe.portal.service;

import com.arexe.portal.entity.Borrow;

public interface BorrowService {

    Borrow getBorrowById(int id);
    void saveBorrow(Borrow borrow);
}
