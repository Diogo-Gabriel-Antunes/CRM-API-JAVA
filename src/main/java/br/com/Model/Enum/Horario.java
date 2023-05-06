package br.com.Model.Enum;

public enum Horario {
    H01("01:00", 1), H02("02:00", 2), H03("03:00", 3), H04("04:00", 4), H05("05:00", 5), H06("06:00", 6),
    H07("07:00", 7), H08("08:00", 8), H09("09:00", 9), H10("10:00", 10), H11("11:00", 11),H12("12:00", 12),
    H13("13:00", 13), H14("14:00", 14), H15("15:00", 15), H16("16:00", 16), H17("17:00", 17), H18("18:00", 18),
    H19("19:00", 19), H20("20:00", 20), H21("21:00", 21), H22("22:00", 22), H23("23:00", 23), H24("24:00", 24);

    private String horario;
    private Integer valor;

    Horario(String horario, Integer valor) {
        this.horario = horario;
        this.valor = valor;
    }

    public String getHorario() {
        return horario;
    }

    public Integer getValor() {
        return valor;
    }


}
