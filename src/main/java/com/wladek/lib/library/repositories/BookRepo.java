package com.wladek.lib.library.repositories;

import com.wladek.lib.library.models.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {
    BookEntity findByClientId(String clientId);
}
