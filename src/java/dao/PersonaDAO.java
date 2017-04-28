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
import vo.PersonaVO;

/**
 *
 * @author Labing
 */
public class PersonaDAO {
    public List<PersonaVO> findAll() {
        List<PersonaVO> personitas = null;
        String query = "SELECT * FROM Personas";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int cedula = 0;
            String nombre = null;
            String apellido = null;
            int telefono = 0;
            int numHab = 0;

            while (rs.next()) {
                if (personitas == null) {
                    personitas = new ArrayList<PersonaVO>();
                }

                cedula = rs.getInt("Cedula");
                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
                telefono = rs.getInt("Telefono");
                numHab = rs.getInt("NumeroHabitacion");

                PersonaVO registro = new PersonaVO(cedula, nombre, apellido, telefono, numHab);
                personitas.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de PersonaVOs");
            e.printStackTrace();
        }

        return personitas;
    }

    /**
     * Funcion que permite realizar la insercion de un nuevo registro en la
     * tabla PersonaVO
     *
     * @param PersonaVO recibe un objeto de tipo PersonaVO
     * @return boolean retorna true si la operacion de insercion es exitosa.
     */
    public boolean insert(PersonaVO t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Personas (Cedula,Nombre,Apellido,Telefono,NumeroHabitacion) values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getCedula());
            preparedStmt.setString(2, t.getNombre());
            preparedStmt.setString(2, t.getApellido());
            preparedStmt.setInt(3, t.getTelefono());
            preparedStmt.setInt(3, t.getNumHab());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
