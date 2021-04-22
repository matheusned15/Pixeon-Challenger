package br.com.pixeon.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "HEALTH")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HealthcareInstitution extends BaseEntity {

	private static final long serialVersionUID = 5469535228775424912L;

	// @CNPJ
	private String cnpj;

	@NotNull
	@Size(min = 3, max = 50)
	private String nome;

	private int moedas;

	@JsonBackReference
	@OneToMany(mappedBy = "healthcareInstitution", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Exam> exames = new ArrayList<>();

	@PrePersist
	private void adicionaMoedaAntesDeSalvar() {
		moedas = 20;
	}

}
