package br.edu.ifsul.util.relatorios;


import java.util.List;
import java.util.ArrayList;
import br.edu.ifsul.modelo.Pessoa;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VIP
 */
public class FabricaObjetos {
    
    public static List<Pessoa> carregaPessoas(){
        List<Pessoa> lista = new ArrayList<>();
        Pessoa p1 = new Pessoa();
        
        p1.setCpf("02163953050");
        p1.setEmail("giovani@gmail.com");
        p1.setNome("Giovani Pinzetta");
        p1.setTelefone("34443939");
        p1.setId(1);
        
        lista.add(p1);
        return lista;
    }
    
}
