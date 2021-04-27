package com.wladek.lib.library.repositories;

import com.wladek.lib.library.models.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {
    BookEntity findByClientId(String clientId);
    @Query( value = " select * from books b where b.author ilike ?searchTerm or b.title ilike ?searchTerm", nativeQuery = true)
    List<BookEntity> searchBooks(@Param("searchTerm") String searchTerm);
}
