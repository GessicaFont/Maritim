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
public @Data class Porto {
    int Id_Porto;
    String Nome,Local;

    public Porto(String Nome, String Local) {
        this.Nome = Nome;
        this.Local = Local;
    }

    public Porto() {
    }
    
    
}
