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
public @Data class Navio {
    int Id_Navio;
    String Nome,Status;
    double Cap_Maxima;

    public Navio() {
    }

    public Navio(int Id_Navio, String Nome, String Status, double Cap_Maxima) {
        this.Id_Navio = Id_Navio;
        this.Nome = Nome;
        this.Status = Status;
        this.Cap_Maxima = Cap_Maxima;
    }

    public Navio(String Nome, String Status, double Cap_Maxima) {        
        this.Nome = Nome;
        this.Status = Status;
        this.Cap_Maxima = Cap_Maxima;
    }
}
