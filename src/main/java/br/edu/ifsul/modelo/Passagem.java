
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "passagem")
public class Passagem implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_passagem", sequenceName = "seq_passagem_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_passagem", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "dataCompra", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCompra;
    
    @NotBlank(message = "A bagagem deve ser informado")
    @Column(name = "nome", length = 50, nullable = false)
    private Integer bagagem;
    
    @NotNull(message = "A pessoa não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;
    
    @NotNull(message = "A classe não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "classe", referencedColumnName = "id", nullable = false)
    private Classe classe;
    
    @ManyToOne
    @JoinColumn(name = "vooAgendado_id", nullable = false)
    private VooAgendado vooAgendado;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dataCompra
     */
    public Calendar getDataCompra() {
        return dataCompra;
    }

    /**
     * @param dataCompra the dataCompra to set
     */
    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     * @return the bagagem
     */
    public Integer getBagagem() {
        return bagagem;
    }

    /**
     * @param bagagem the bagagem to set
     */
    public void setBagagem(Integer bagagem) {
        this.bagagem = bagagem;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the classe
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    /**
     * @return the vooAgendado
     */
    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }

    /**
     * @param vooAgendado the vooAgendado to set
     */
    public void setVooAgendado(VooAgendado vooAgendado) {
        this.vooAgendado = vooAgendado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Passagem other = (Passagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
