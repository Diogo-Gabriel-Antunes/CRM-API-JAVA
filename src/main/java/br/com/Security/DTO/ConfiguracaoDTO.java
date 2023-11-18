package br.com.Security.DTO;

import br.com.Model.Enum.DiaDaSemana;
import br.com.Security.Model.CargaHoraria;
import lombok.Data;

import java.util.List;

@Data
public class ConfiguracaoDTO {

    private List<CargaHorariaDTO> cargaHoraria;
    public Boolean horarioPadrao;
}
