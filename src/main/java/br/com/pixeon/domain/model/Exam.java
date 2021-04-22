package br.com.pixeon.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Exam extends BaseEntity {

	private static final long serialVersionUID = 4744774966526214877L;

	@NotNull
	private String patientName;

	@NotNull
	private int patientAge;

	@Enumerated(EnumType.STRING)
	private Gender patientGender;

	@NotNull
	private String physicianName;

	@NotNull
	private double physicianCRM;

	private String ProcedureName;

	private boolean read;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(updatable = false, nullable = false)
	private HealthcareInstitution healthcareInstitution;

}
