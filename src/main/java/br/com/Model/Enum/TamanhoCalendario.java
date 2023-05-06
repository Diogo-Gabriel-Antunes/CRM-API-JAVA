package br.com.Model.Enum;

import lombok.Getter;

@Getter
public enum TamanhoCalendario {
    UMASEMANA("Uma Semana"),DUASSEMANAS("Duas Semanas"),
    TRESSEMANAS("Tres semanas"),QUATROSEMANAS("Quatro Semanas");

    private String quantidade;

    TamanhoCalendario(String quantidade) {
        this.quantidade = quantidade;
    }
}
