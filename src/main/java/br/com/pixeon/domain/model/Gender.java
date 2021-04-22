package br.com.pixeon.domain.model;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("Masculino"),
    FEMALE("Feminino"),
    OTHERS("Outros");

    private final String name;

    private Gender(String name){
        this.name = name;
    }

}
