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
public @Data class Rota {
    int Id_Rota,Id_Porto_Origem,Id_Porto_Destino;

    public Rota() {
    }

    public Rota(int Id_Porto_Origem, int Id_Porto_Destino) {
        this.Id_Porto_Origem = Id_Porto_Origem;
        this.Id_Porto_Destino = Id_Porto_Destino;
    }
    
    
}


