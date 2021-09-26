/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author VIP
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    @Id
    @NotBlank(message = "O nome de usuário deve ser informado")
    @Length(max = 30, message = "O nome de usuário não pode ter mais que {max} caracteres")
    @Column(name = "nome_usuario", length = 30, nullable = false)      
    private String nome_usuario;
    
    @NotBlank(message = "A senha deve ser informada")
    @Length(max = 30, message = "A senha não pode ter mais que {max} caracteres")
    @Column(name = "senha", length = 30, nullable = false)
    private String senha;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permissoes",
            joinColumns = 
                    @JoinColumn(name = "nome_usuario", referencedColumnName = "nome_usuario", nullable = false),
            inverseJoinColumns = 
                    @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false))
    private Set<Permissao> permissoes = new HashSet<>();

    /**
     * @return the nome_usuario
     */
    public String getNome_usuario() {
        return nome_usuario;
    }

    /**
     * @param nome_usuario the nome_usuario to set
     */
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the permissoes
     */
    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    /**
     * @param permissoes the permissoes to set
     */
    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.nome_usuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome_usuario, other.nome_usuario)) {
            return false;
        }
        return true;
    }


}
