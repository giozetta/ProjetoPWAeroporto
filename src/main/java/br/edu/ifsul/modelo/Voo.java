
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
import javax.validation.constraints.NotNull;
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
    
    @NotNull(message = "O tempo deve ser informado")
    @Column(name="tempoEstimado", nullable = false, columnDefinition="numeric(4,2)")
    private Double tempoEstimado;
    
    @NotNull(message = "O ativo deve ser informado")
    @Column(name="ativo", nullable = false)
    private Boolean ativo;
    
    @NotBlank(message = "A periodicidade deve ser informada")
    @Length(max = 200, message = "A periodicidade não pode ter mais que {max} caracteres")
    @Column(name = "periodicidade", length = 200, nullable = false)
    private String periodicidade;
    
    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VooAgendado> voosAgendados = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="escalas", joinColumns = @JoinColumn(name="id_voo", referencedColumnName="id", nullable=false),
                               inverseJoinColumns = @JoinColumn(name="id_aeroporto", referencedColumnName="id", nullable=false))
    private Set<Aeroporto> escalas = new HashSet<>();

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
    public Set<Aeroporto> getEscalas() {
        return escalas;
    }

    /**
     * @param escalas the escalas to set
     */
    public void setEscalas(Set<Aeroporto> escalas) {
        this.escalas = escalas;
    }
    
    public void setVooAgendado(VooAgendado vooAgendado) {
        vooAgendado.setVoo(this);
        this.voosAgendados.add(vooAgendado);
    }
    
    public void removerVooAgendado(int index){
        this.voosAgendados.remove(index);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
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
