/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.ArrayList;

/**
 *
 * @author Labing
 */
public class AlquilerVO {
    private int id;
    private String fecha;
    private int cedulaResponsable;

    public AlquilerVO() {
        
    }
    
    public AlquilerVO(int id, String fecha, int cedulaResponsable) {
        this.id = id;
        this.fecha = fecha;
        this.cedulaResponsable = cedulaResponsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCedulaResponsable() {
        return cedulaResponsable;
    }

    public void setCedulaResponsable(int cedulaResponsable) {
        this.cedulaResponsable = cedulaResponsable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
