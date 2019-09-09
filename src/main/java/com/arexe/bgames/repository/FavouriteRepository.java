package com.arexe.bgames.repository;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.entity.Favourite;
import com.arexe.bgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    List<Favourite> findAllByUser(User user);
    List<Favourite> findAllByBoardGame(BoardGame boardGame);
}
