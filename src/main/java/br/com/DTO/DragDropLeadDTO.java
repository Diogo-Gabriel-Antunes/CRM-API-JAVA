package br.com.DTO;

import br.com.Model.Lead;
import lombok.Data;

import java.util.List;

@Data
public class DragDropLeadDTO {

    private String etapaUuid;
    private String etapaName;
    private List<ItensDragDrop> leads;

    @Data
    public static class ItensDragDrop{
        private int posicao;
        private Relevancia relevancia;
        private Lead lead;

        public enum Relevancia{
            BAIXA,MEDIA,ALTA;
        }
    }
}
