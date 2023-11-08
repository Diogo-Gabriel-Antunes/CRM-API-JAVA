package br.com.Invokers.IVK;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectIVKDTO {

    private String value;
    private String label;

    public SelectIVKDTO(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public SelectIVKDTO() {
    }
}

