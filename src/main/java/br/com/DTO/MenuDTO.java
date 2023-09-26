package br.com.DTO;

import lombok.Data;

@Data
public class MenuDTO {


    private String link;
    private String subTitle;

    public MenuDTO(String link, String subTitle) {
        this.link = link;
        this.subTitle = subTitle;
    }
}
