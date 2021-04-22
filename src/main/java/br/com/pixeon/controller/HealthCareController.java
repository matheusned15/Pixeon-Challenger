package br.com.pixeon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pixeon.domain.model.HealthcareInstitution;
import br.com.pixeon.domain.service.HealthCareService;
import lombok.RequiredArgsConstructor;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/healthcare-institutions")
public class HealthCareController {

	private final HealthCareService healthCareService;

	@GetMapping
	public List<HealthcareInstitution> buscaTodos() {
		return healthCareService.buscaTodosServicos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<HealthcareInstitution> buscaServico(@PathVariable Long id) {
		return healthCareService.buscaServico(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<HealthcareInstitution> criaServico(@RequestBody @Valid HealthcareInstitution health) {
		return healthCareService.criaServico(health);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HealthcareInstitution> atualizaServico(@PathVariable Long id,
			@RequestBody @Valid HealthcareInstitution health) {
		return healthCareService.atualizaServico(id, health);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<HealthcareInstitution> deletaServico(@PathVariable Long id) {
		return healthCareService.deletaServico(id);
	}
}
