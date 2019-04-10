package com.arexe.portal.repository;

import com.arexe.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<User, Integer> {
}
