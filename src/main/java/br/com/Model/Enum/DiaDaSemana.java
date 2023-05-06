package br.com.Model.Enum;

import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public enum DiaDaSemana {
    DOMINGO("Domingo",DayOfWeek.SUNDAY),
    SEGUNDA("Segunda",DayOfWeek.MONDAY),
    TERCA("Terça",DayOfWeek.TUESDAY),
    QUARTA("Quarta",DayOfWeek.WEDNESDAY),
    QUINTA("Quinta",DayOfWeek.THURSDAY),
    SEXTA("Sexta",DayOfWeek.FRIDAY),
    SABADO("Sabado",DayOfWeek.SATURDAY);

    private String dia;
    private DayOfWeek dayOfWeek;

    DiaDaSemana(String dia, DayOfWeek dayOfWeek) {
        this.dia = dia;
        this.dayOfWeek = dayOfWeek;
    }
}
