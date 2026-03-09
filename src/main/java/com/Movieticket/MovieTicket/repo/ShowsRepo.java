package com.Movieticket.MovieTicket.repo;

import com.Movieticket.MovieTicket.model.Show;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowsRepo extends JpaRepository<Show,String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Show s WHERE s.id= :id")
    Optional<Show> findByIdWithLock(@Param("id")String id);
}
