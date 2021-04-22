package br.com.pixeon.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.pixeon.domain.model.HealthcareInstitution;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pixeon.domain.model.Exam;
import br.com.pixeon.domain.service.ExamService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exams")
public class ExamController {

	private final ExamService examService;

	@GetMapping
	public List<Exam> buscaExame() {
		return examService.buscaTodosExames();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Exam> buscaExame(@PathVariable Long id) {
		return examService.buscaExame(id);
	}

	@GetMapping("/{idInstituicao}/{examId}")
	public ResponseEntity<Exam> buscaExameHealth(@PathVariable HealthcareInstitution idInstituicao, Exam exam,
			Long examId) {
		return examService.buscaExameHealth(examId, idInstituicao, exam);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Exam criaExame(@RequestBody @Valid Exam exam) {

		return examService.criaExame(exam);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Exam> atualizaExame(@PathVariable Long id, @RequestBody @Valid Exam exam) {
		return examService.atualizaExame(id, exam);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Exam> deletaExame(@PathVariable Long id) {
		return examService.deletaExame(id);
	}
}
