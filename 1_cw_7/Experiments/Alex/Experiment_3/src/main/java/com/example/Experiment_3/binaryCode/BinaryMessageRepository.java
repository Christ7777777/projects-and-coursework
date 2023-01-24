package com.example.Experiment_3.binaryCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinaryMessageRepository extends JpaRepository<BinaryMessage, String> {
}
