package br.com.pixeon.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -203354989948674167L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
