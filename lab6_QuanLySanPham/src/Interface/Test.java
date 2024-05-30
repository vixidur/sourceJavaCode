/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Database.DBConnection;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class Test {
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        Connection connection = db.GetConnection();
    }
}
