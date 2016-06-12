/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.Date;
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
    
}
