
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "voo")
public class Voo implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_voo", sequenceName = "seq_voo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_voo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;
    
    @NotBlank(message = "O tempo deve ser informado")
    @Column(name="tempoEstimado", nullable = false, precision = 2)
    private Double tempoEstimado;
    
    @Column(name="ativo", nullable = false)
    private Boolean ativo;
    
    @NotBlank(message = "A periodicidade deve ser informada")
    @Length(max = 200, message = "A periodicidade não pode ter mais que {max} caracteres")
    @Column(name = "periodicidade", length = 200, nullable = false)
    private String periodicidade;
    
    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VooAgendado> voosAgendados;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_escalas", joinColumns = {@JoinColumn(name = "voo_id")},
                                       inverseJoinColumns = {@JoinColumn(name = "aeroporto_id")})
    private List<Aeroporto> escalas;

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the tempoEstimado
     */
    public Double getTempoEstimado() {
        return tempoEstimado;
    }

    /**
     * @param tempoEstimado the tempoEstimado to set
     */
    public void setTempoEstimado(Double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the periodicidade
     */
    public String getPeriodicidade() {
        return periodicidade;
    }

    /**
     * @param periodicidade the periodicidade to set
     */
    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    /**
     * @return the voosAgendados
     */
    public List<VooAgendado> getVoosAgendados() {
        return voosAgendados;
    }

    /**
     * @param voosAgendados the voosAgendados to set
     */
    public void setVoosAgendados(List<VooAgendado> voosAgendados) {
        this.voosAgendados = voosAgendados;
    }

    /**
     * @return the escalas
     */
    public List<Aeroporto> getEscalas() {
        return escalas;
    }

    /**
     * @param escalas the escalas to set
     */
    public void setEscalas(List<Aeroporto> escalas) {
        this.escalas = escalas;
    }
    
    public void adicionarVooAgendado(VooAgendado obj){
        obj.setVoo(this);
        this.voosAgendados.add(obj);
    }
    
    public void removerVooAgendado(int index){
        this.voosAgendados.remove(index);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Voo other = (Voo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
