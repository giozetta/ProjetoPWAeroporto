
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "vooAgendado")
public class VooAgendado implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_vooAgendado", sequenceName = "seq_vooAgendado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_vooAgendado", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message = "A aeronave deve ser informada")
    @Length(max = 50, message = "A aeronave não pode ter mais que {max} caracteres")
    @Column(name = "aeronave", length = 50, nullable = false)
    private String aeronave;
    
    @NotBlank(message = "O total de passageiros deve ser informado")
    @Column(name = "totalPassageiros", length = 50, nullable = false)
    private Integer totalPassageiros;
    
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @OneToMany(mappedBy = "vooAgendado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens;
    
    @ManyToOne
    @JoinColumn(name = "voo_id", nullable = false)
    private Voo voo;
    
    
    public void adicionarPassagem(Passagem obj){
        obj.setVooAgendado(this);
        this.passagens.add(obj);
    }
    
    public void removerPassagem(int index){
        this.passagens.remove(index);
    }
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
     * @return the aeronave
     */
    public String getAeronave() {
        return aeronave;
    }

    /**
     * @param aeronave the aeronave to set
     */
    public void setAeronave(String aeronave) {
        this.aeronave = aeronave;
    }

    /**
     * @return the totalPassageiros
     */
    public Integer getTotalPassageiros() {
        return totalPassageiros;
    }

    /**
     * @param totalPassageiros the totalPassageiros to set
     */
    public void setTotalPassageiros(Integer totalPassageiros) {
        this.totalPassageiros = totalPassageiros;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the voo
     */
    public Voo getVoo() {
        return voo;
    }

    /**
     * @param voo the voo to set
     */
    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    /**
     * @return the passagens
     */
    public List<Passagem> getPassagens() {
        return passagens;
    }

    /**
     * @param passagens the passagens to set
     */
    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final VooAgendado other = (VooAgendado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
