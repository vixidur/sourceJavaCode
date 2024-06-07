/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsvcntt_tranvanchienn;
import java.sql.*;
/**
 *
 * @author Peggy
 */
public class ketnoi {
    public static Connection KetnoiSQL() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/svuneti", "root", "");
        return c;
        // ALT + SHIFT + F
    }
}
