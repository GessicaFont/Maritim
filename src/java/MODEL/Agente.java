/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import lombok.Data;

/**
 *
 * @author gessica
 */
public @Data class Agente {
    int Cod_Agente;
    int Id_Porto;
    String Nome;
    String Telefone;

    public Agente() {
    }

    public Agente(int Cod_Agente, int Id_Porto, String Nome, String Telefone) {
        this.Cod_Agente = Cod_Agente;
        this.Id_Porto = Id_Porto;
        this.Nome = Nome;
        this.Telefone = Telefone;
    }
    
}
