package br.com.pixeon.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pixeon.domain.model.HealthcareInstitution;
import br.com.pixeon.domain.repository.HealthCareRepository;

@Service
@RequiredArgsConstructor
public class HealthCareService {

	private final HealthCareRepository healthCareRepository;

	public List<HealthcareInstitution> buscaTodosServicos() {
		return healthCareRepository.findAll();

	}

	public ResponseEntity<HealthcareInstitution> buscaServico(Long codigo) {

		Optional<HealthcareInstitution> list = healthCareRepository.findById(codigo);
		return list.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

	}

	public ResponseEntity<HealthcareInstitution> criaServico(@Valid HealthcareInstitution health) {
		setHealth(health);
		return ResponseEntity.status(HttpStatus.CREATED).body(healthCareRepository.save(health));
	}

	public void atualizaMoedas(HealthcareInstitution healthcareInstitution) {
		Integer saldo = healthcareInstitution.getMoedas() - 1;
		healthcareInstitution.setMoedas(saldo);
		healthCareRepository.save(healthcareInstitution);
	}

	public ResponseEntity<HealthcareInstitution> atualizaServico(Long codigo, @Valid HealthcareInstitution health) {
		Optional<HealthcareInstitution> buscaServico = healthCareRepository.findById(codigo);
		if (buscaServico.isPresent()) {
			BeanUtils.copyProperties(health, buscaServico.get(), "codigo");
			HealthcareInstitution salva = healthCareRepository.save(buscaServico.get());
			return ResponseEntity.ok(salva);

		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<HealthcareInstitution> deletaServico(Long codigo) {
		Optional<HealthcareInstitution> buscaService = healthCareRepository.findById(codigo);
		if (buscaService.isPresent()) {
			healthCareRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	private void setHealth(HealthcareInstitution health) {
		health.getExames().forEach(exam -> exam.setHealthcareInstitution(health));
	}

}
