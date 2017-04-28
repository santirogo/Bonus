/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import vo.AlquilerVO;

/**
 *
 * @author Labing
 */
public class AlquilerDAO {
    public List<AlquilerVO> findAll() {
        List<AlquilerVO> alquileres = null;
        String query = "SELECT * FROM Alquiler";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String fecha = null;
            int cedulaResponsable = 0;

            while (rs.next()) {
                if (alquileres == null) {
                    alquileres = new ArrayList<AlquilerVO>();
                }

                id = rs.getInt("Id");
                fecha = rs.getString("Fecha");
                cedulaResponsable = rs.getInt("Cedula");

                AlquilerVO registro = new AlquilerVO(id,fecha,cedulaResponsable);
                alquileres.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de AlquilerVOs");
            e.printStackTrace();
        }

        return alquileres;
    }
    
    public boolean insert(AlquilerVO t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Alquiler (Id,Fecha,Cedula) values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId());
            preparedStmt.setString(2, t.getFecha());
            preparedStmt.setInt(3, t.getCedulaResponsable());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<AlquilerDAO> informacion(){
        ArrayList<AlquilerDAO> informacion = null;
        
        String query = "SELECT * FROM Personas JOIN Alquiler JOIN Habitaciones";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String fecha = null;
            int cedulaResponsable = 0;

            while (rs.next()) {
                if (informacion == null) {
                    informacion = new ArrayList<>();
                }

                id = rs.getInt("Id");
                fecha = rs.getString("Fecha");
                cedulaResponsable = rs.getInt("Cedula");

                AlquilerVO registro = new AlquilerVO(id,fecha,cedulaResponsable);
                //informacion.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de AlquilerVOs");
            e.printStackTrace();
        }
        
        return informacion;
    }
}
