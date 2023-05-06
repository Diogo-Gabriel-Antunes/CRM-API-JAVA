package br.com.Repository;

import br.com.DTO.ClienteModalDTO;
import br.com.Model.Cliente;
import br.com.Model.Contato;
import br.com.Model.Empresa;
import br.com.Model.Endereco;
import br.com.Security.DTO.UsuarioLogado;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<Cliente, String> {

    private EntityManager em = this.getEntityManager();

    public Cliente saveCliente(Cliente cliente ) {
        Contato contato = cliente.getContato();
        Empresa empresa = cliente.getEmpresa();
        Endereco endereco = cliente.getEndereco();

        if (contato != null) {
                contato.persist();
            cliente.setContato(contato);
        }
        if (empresa != null) {
            empresa.persist();
            cliente.setEmpresa(empresa);
        }
        if (endereco != null) {
            endereco.persist();
            cliente.setEndereco(endereco);
        }

        cliente.persist();
        return cliente;
    }

    public Cliente findByUuid(String uuid) {
        Cliente cliente = this.findById(uuid);
        return cliente;
    }

    public List<Cliente> findAllByUsuario() {
        return em.createQuery("SELECT c FROM Cliente c LEFT JOIN c.usuario u WHERE u.uuid = :usuarioUuid",Cliente.class)
                .setParameter("usuarioUuid", UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }

    public List<ClienteModalDTO> findClienteModal() {
        return em.createQuery("SELECT new br.com.DTO.ClienteModalDTO(c.uuid,c.nome,c.sobrenome,c.cargo,c.setor," +
                        "c.minFaturamento,c.maxFaturamento,co.telefone,co.telefone2,co.email) FROM Cliente c " +
                "LEFT JOIN c.usuario u " +
                "LEFT JOIN c.contato co " +
                "WHERE u.uuid = :usuarioUuid",ClienteModalDTO.class)
                .setParameter("usuarioUuid",UsuarioLogado.getUsuario().getUuid())
                .getResultList();
    }
}
