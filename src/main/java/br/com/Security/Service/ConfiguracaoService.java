package br.com.Security.Service;

import br.com.Invokers.IVK.SelectIVKDTO;
import br.com.Security.DTO.ConfiguracaoDTO;
import br.com.Security.DTO.ResponseHorasTrabalhadas;
import br.com.Security.DTO.UsuarioLogado;
import br.com.Security.Model.CargaHoraria;
import br.com.Security.Model.Configuracao;
import br.com.Model.Enum.DiaDaSemana;
import br.com.Model.Enum.Horario;
import br.com.Security.Model.Usuario;
import br.com.Security.Repository.ConfiguracaoRepository;
import br.com.Service.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.Util.PrimitiveUtil.BooleanUtils;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConfiguracaoService extends Service {

    @Inject
    private ConfiguracaoRepository configuracaoRepository;


    public Response create(String json) {
        ConfiguracaoDTO dto = gson.fromJson(json, ConfiguracaoDTO.class);

        Configuracao configuracao = UsuarioLogado.getUsuario().getConfiguracao();
        if (configuracao != null) {
            configuracao.delete();
        }
        if (dto.horarioPadrao != null && dto.horarioPadrao) {
            Configuracao configuracaoPadrao = montaConfigPadrao(dto);
            em.persist(configuracaoPadrao);
            Usuario usuarioMerged = em.merge(UsuarioLogado.getUsuario());
            usuarioMerged.setConfiguracao(configuracaoPadrao);
            em.persist(usuarioMerged);
            em.flush();
            return Response.ok(configuracaoPadrao).build();
        } else {
            Configuracao configuracaoPersonalizada = montaConfigPersonalizada(dto);
            em.persist(configuracaoPersonalizada);
            Usuario usuarioMerged = em.merge(UsuarioLogado.getUsuario());
            usuarioMerged.setConfiguracao(configuracaoPersonalizada);
            em.persist(usuarioMerged);
            em.flush();
            return Response.ok(configuracaoPersonalizada).build();
        }

    }

    public Configuracao montaConfigPersonalizada(ConfiguracaoDTO dto) {
        Configuracao configuracao = new Configuracao();
        em.persist(configuracao);
        configuracao.setCargaHoraria(new ArrayList<>());
        configuracao.setHorarioPadrao(false);
        for (DiaDaSemana diaDaSemana : dto.diasDaSemana) {
            CargaHoraria cargaHoraria = new CargaHoraria();
            for (Horario value : Horario.values()) {
                if (value.getHorario().equals(dto.horaEntrada)) {
                    cargaHoraria.setHorarioEntrada(value);
                }
                if (value.getHorario().equals(dto.horaSaida)) {
                    cargaHoraria.setHorarioSaida(value);
                }
            }
            cargaHoraria.setDiaDaSemana(diaDaSemana);
            cargaHoraria.setConfiguracao(configuracao);
            em.persist(cargaHoraria);
            configuracao.getCargaHoraria().add(cargaHoraria);
        }
        return configuracao;
    }

    public Configuracao montaConfigPadrao(ConfiguracaoDTO dto) {
        Configuracao configuracao = new Configuracao();
        em.persist(configuracao);
        configuracao.setCargaHoraria(new ArrayList<>());
        configuracao.setHorarioPadrao(true);
        for (DiaDaSemana diaDaSemana : dto.diasDaSemana) {
            CargaHoraria cargaHoraria = new CargaHoraria();
            cargaHoraria.setHorarioEntrada(Horario.H08);
            cargaHoraria.setHorarioSaida(Horario.H18);
            cargaHoraria.setDiaDaSemana(diaDaSemana);
            cargaHoraria.setConfiguracao(configuracao);
            em.persist(cargaHoraria);
            configuracao.getCargaHoraria().add(cargaHoraria);

        }
        return configuracao;
    }

    public Response getJornadaDeTrabalho() {
        Configuracao configuracao = configuracaoRepository.getConfiguracao();
        List<ResponseHorasTrabalhadas> response = new ArrayList<>();

        if (configuracao.getHorarioPadrao() != null && configuracao.getHorarioPadrao()) {
            for (int i = 0; i < 10; i++) {
                ResponseHorasTrabalhadas responseHorasTrabalhadas = new ResponseHorasTrabalhadas();
                responseHorasTrabalhadas.setJornadaDeTrabalho(1);
                response.add(responseHorasTrabalhadas);
            }
        } else {
            CargaHoraria cargaHoraria = configuracao.getCargaHoraria().get(0);
            Horario horarioEntrada = cargaHoraria.getHorarioEntrada();
            Horario horarioSaida = cargaHoraria.getHorarioSaida();
            Integer horasTrabalhas = horarioSaida.getValor() - horarioEntrada.getValor();
            for (int i = 0; i < horasTrabalhas; i++) {
                ResponseHorasTrabalhadas responseHorasTrabalhadas = new ResponseHorasTrabalhadas();
                responseHorasTrabalhadas.setJornadaDeTrabalho(1);
                response.add(responseHorasTrabalhadas);
            }
        }
        return Response.ok(response).build();
    }

    public Response getJornadaDeTrabalhoSelect() {
        Configuracao configuracao = configuracaoRepository.getConfiguracao();
        List<SelectIVKDTO> response = null;

        if (configuracao != null) {
            if (BooleanUtils.isTrue(configuracao.getHorarioPadrao())) {
                response = getHorarioPadrao();
            }else{
                CargaHoraria cargaHoraria = configuracao.getCargaHoraria().get(0);
                Horario horarioEntrada = cargaHoraria.getHorarioEntrada();
                Horario horarioSaida = cargaHoraria.getHorarioSaida();
                for (int i = horarioEntrada.getValor(); i < horarioSaida.getValor(); i++) {
                    for (Horario horario : Horario.values()) {
                        if(horario.getValor() == i){
                            SelectIVKDTO selectIVKDTO = new SelectIVKDTO(horario.getHorario(),horario.name());
                            response.add(selectIVKDTO);
                        }
                    }

                }
            }
        } else {
            response = getHorarioPadrao();
        }

        return Response.ok(response).build();
    }

    private List<SelectIVKDTO> getHorarioPadrao() {
        SelectIVKDTO selectIVKDTO = new SelectIVKDTO("H08", "8 Horas");
        SelectIVKDTO selectIVKDTO1 = new SelectIVKDTO("H09", "9 Horas");
        SelectIVKDTO selectIVKDTO2 = new SelectIVKDTO("H10", "10 Horas");
        SelectIVKDTO selectIVKDTO3 = new SelectIVKDTO("H11", "11 Horas");
        SelectIVKDTO selectIVKDTO4 = new SelectIVKDTO("H12", "12 Horas");
        SelectIVKDTO selectIVKDTO5 = new SelectIVKDTO("H13", "13 Horas");
        SelectIVKDTO selectIVKDTO6 = new SelectIVKDTO("H14", "14 Horas");
        SelectIVKDTO selectIVKDTO7 = new SelectIVKDTO("H15", "15 Horas");
        SelectIVKDTO selectIVKDTO8 = new SelectIVKDTO("H16", "16 Horas");
        SelectIVKDTO selectIVKDTO9 = new SelectIVKDTO("H17", "17 Horas");
        SelectIVKDTO selectIVKDTO10 = new SelectIVKDTO("H18", "18 Horas");
        return new ArrayList<>() {{
            add(selectIVKDTO);
            add(selectIVKDTO1);
            add(selectIVKDTO2);
            add(selectIVKDTO3);
            add(selectIVKDTO4);
            add(selectIVKDTO5);
            add(selectIVKDTO6);
            add(selectIVKDTO7);
            add(selectIVKDTO8);
            add(selectIVKDTO9);
            add(selectIVKDTO10);
        }};
    }
}
