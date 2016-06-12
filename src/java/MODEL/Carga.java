/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import lombok.Data;
import org.joda.time.DateTime;

/**
 *
 * @author gessica
 */
public @Data class Carga {
    int Id_Carga,Id_Navio,tipo;
    String Origem,Destino;
    double Peso,Temp_Max;
    DateTime Data_Max,Data_Validade;
    boolean Embarcada;

    public Carga() {
    }

    
    public Carga(int Id_Navio, int tipo, String Origem, String Destino, double Peso, double Temp_Max, DateTime Data_Max, DateTime Data_Validade, boolean Embarcada) {
        this.Id_Navio = Id_Navio;
        this.tipo = tipo;
        this.Origem = Origem;
        this.Destino = Destino;
        this.Peso = Peso;
        this.Temp_Max = Temp_Max;
        this.Data_Max = Data_Max;
        this.Data_Validade = Data_Validade;
        this.Embarcada = Embarcada;
    }
    
    
}
