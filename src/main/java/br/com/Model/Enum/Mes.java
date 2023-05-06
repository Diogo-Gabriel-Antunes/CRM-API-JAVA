package br.com.Model.Enum;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Month;

@Getter
public enum Mes {
    JANEIRO(Month.JANUARY,1,true), FEVEREIRO(Month.FEBRUARY,2,false),
    MARCO(Month.MARCH,3,true), ABRIL(Month.APRIL,4,false), MAIO(Month.MAY,5,true),
    JUNHO(Month.JUNE,6,false), JULHO(Month.JUNE,7,true), AGOSTO(Month.AUGUST,8,true),
    SETEMBRO(Month.SEPTEMBER,9,false), OUTUBRO(Month.OCTOBER,10,true), NOVEMBRO(Month.NOVEMBER,11,false),
    DEZEMBRO(Month.DECEMBER,12,true);

    private Month month;
    private Integer numero;
    private Boolean tem31;

    Mes(Month month, Integer numero,Boolean tem31) {
        this.month = month;
        this.numero = numero;
        this.tem31 = tem31;
    }
}

