package br.com.Security.DTO;

import br.com.Model.Enum.DiaDaSemana;
import lombok.Data;

import java.util.List;

@Data
public class CargaHorariaDTO {
    public DiaDaSemana diaDaSemana;
    public String horarioEntrada;
    public String horarioSaida;
}
