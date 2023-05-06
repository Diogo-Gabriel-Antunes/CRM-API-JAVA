package br.com.Security.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    private String email;
    private String senha;
    @ManyToOne
    private Configuracao configuracao;
    @ManyToMany
    @JoinTable(name = "permissoes_usuario",joinColumns = @JoinColumn(name = "usuario_uuid"),inverseJoinColumns = @JoinColumn(name = "permissoes_uuid"))
    private List<Permissoes> permissoes;

    private LocalDateTime dataIntegração;
    private LocalDateTime dataAtualização;
}
