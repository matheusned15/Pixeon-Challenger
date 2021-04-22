package br.com.pixeon.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pixeon.domain.model.Exam;
import br.com.pixeon.domain.model.HealthcareInstitution;
import br.com.pixeon.domain.repository.ExamRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {

	private final ExamRepository examRepository;
	private final HealthCareService service;

	public List<Exam> buscaTodosExames() {
		return examRepository.findAll();
	}

	public ResponseEntity<Exam> buscaExame(Long codigo) {
		Optional<Exam> list = examRepository.findById(codigo);
		return list.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}

	public ResponseEntity<Exam> buscaExameHealth(Long examId, HealthcareInstitution idInstituicao, Exam exam) {
		Optional<Exam> list = examRepository.findById(examId);
		Exam exam2 = list.get();
		if (exam2.getHealthcareInstitution().getMoedas() == 0) {
			throw new NullPointerException("Quantidade de moedas = 0");
		}
		canRead(exam2, idInstituicao);
		examRepository.save(exam2);
		return list.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}

	public void canRead(Exam exam, HealthcareInstitution healthcareInstitution) {

		if (exam.isRead() == false) {
			atualizaExame(exam);
			service.atualizaMoedas(healthcareInstitution);
		}

	}

	public Exam criaExame(@Valid Exam exam) {
		return examRepository.save(exam);
	}

	public void atualizaExame(Exam exam) {
		exam.setRead(true);
		examRepository.save(exam);
	}

	public ResponseEntity<Exam> deletaExame(Long codigo) {
		Optional<Exam> buscaExame = examRepository.findById(codigo);
		if (buscaExame.isPresent()) {
			examRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Exam> atualizaExame(Long codigo, @Valid Exam exam) {
		Optional<Exam> buscaServico = examRepository.findById(codigo);
		if (buscaServico.isPresent()) {
			BeanUtils.copyProperties(exam, buscaServico.get(), "codigo");
			Exam salva = examRepository.save(buscaServico.get());
			return ResponseEntity.ok(salva);
		}
		return ResponseEntity.notFound().build();

	}
}
