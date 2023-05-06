package br.com.Model.AA;

import br.com.Security.DTO.UsuarioLogado;
import br.com.Security.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class EntidadeGenerica extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "usuario_uuid")
    @JsonIgnore
    @JsonbTransient
    private Usuario usuario;
    @Column(name = "dataatualização")
    private LocalDateTime dataAtualizacao;
    @Column(name = "dataintegração")
    private LocalDateTime dataIntegracao;

    @PrePersist
    public void prePersist(){
        this.setDataIntegracao(LocalDateTime.now());
        setPropriedades();
    }

    @PreUpdate
    public void preUpdate(){
        setPropriedades();
    }

    private void setPropriedades() {
        this.usuario = UsuarioLogado.getUsuario();
        this.setDataAtualizacao(LocalDateTime.now());
    }
}
