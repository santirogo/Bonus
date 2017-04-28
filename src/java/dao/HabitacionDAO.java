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
import vo.HabitacionVO;

/**
 *
 * @author Labing
 */
public class HabitacionDAO {
    public List<HabitacionVO> findAll() {
        List<HabitacionVO> habitaciones = null;
        String query = "SELECT * FROM Habitaciones";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int numero = 0;
            int piso = 0;
            int cantidad = 0;
            double precio = 0;

            while (rs.next()) {
                if (habitaciones == null) {
                    habitaciones = new ArrayList<HabitacionVO>();
                }

                numero = rs.getInt("Numero");
                piso = rs.getInt("Piso");
                cantidad = rs.getInt("Cantidad");
                precio = rs.getDouble("Precio");

                HabitacionVO registro = new HabitacionVO(numero, piso, cantidad, precio);
                habitaciones.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de HabitacionVOs");
            e.printStackTrace();
        }

        return habitaciones;
    }

    /**
     * Funcion que permite realizar la insercion de un nuevo registro en la
     * tabla HabitacionVO
     *
     * @param HabitacionVO recibe un objeto de tipo HabitacionVO
     * @return boolean retorna true si la operacion de insercion es exitosa.
     */
    public boolean insert(HabitacionVO t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Habitaciones (Numero,Piso,Capacidad,Precio) values (?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getNumero());
            preparedStmt.setInt(2, t.getPiso());
            preparedStmt.setInt(3, t.getCapacidad());
            preparedStmt.setDouble(4, t.getPrecio());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
