package br.com.pixeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pixeon.domain.model.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
