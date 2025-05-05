package com.malik.docuflow.repository;

import com.malik.docuflow.model.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDataRepository extends JpaRepository<DocumentData, Long> {
}
