/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hdd_tranvanchien;
import java.sql.*;
/**
 *
 * @author Peggy
 */
public class Ketnoi {
    public static Connection KetnoiSQL() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlhoadon", "root", "");
        return c; 
    }
}
