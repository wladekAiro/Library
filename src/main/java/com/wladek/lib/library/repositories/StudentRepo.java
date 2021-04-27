package com.wladek.lib.library.repositories;

import com.wladek.lib.library.models.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByClientId(String clientId);
}
