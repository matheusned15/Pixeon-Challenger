package br.com.pixeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pixeon.domain.model.HealthcareInstitution;

@Repository
public interface HealthCareRepository extends JpaRepository<HealthcareInstitution, Long> {
}
