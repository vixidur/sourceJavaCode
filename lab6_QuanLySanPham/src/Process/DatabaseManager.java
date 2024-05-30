/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.DBConnection;
import java.awt.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JComboBox;

/**
 *
 * @author Admin
 */
public class DatabaseManager {

    public static boolean SearchSanPhamWithName(JTable tbl, String tensp) {
        DBConnection dbConn = new DBConnection();
        String query = "SELECT SANPHAM.MASP, SANPHAM.TENSP, SANPHAM.DONGIA, LOAISP.TENLOAI "
                + "FROM SANPHAM INNER JOIN LOAISP ON SANPHAM.MALOAI = LOAISP.MALOAI "
                + "WHERE SANPHAM.TENSP LIKE '%" + tensp + "%'";
        try (Connection conn = dbConn.GetConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            model.setRowCount(0); // Xóa các dòng hiện tại trong bảng

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MASP"),
                    rs.getString("TENSP"),
                    rs.getLong("DONGIA"),
                    rs.getString("TENLOAI")
                });
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean SearchSanPhamWithGia(JTable tbl, long giatu, long giaden) {
        DBConnection dbConn = new DBConnection();
        String query = "SELECT SANPHAM.MASP, SANPHAM.TENSP, SANPHAM.DONGIA, LOAISP.TENLOAI "
                + "FROM SANPHAM INNER JOIN LOAISP ON SANPHAM.MALOAI = LOAISP.MALOAI "
                + "WHERE SANPHAM.DONGIA BETWEEN " + giatu + " AND " + giaden;
        try (Connection conn = dbConn.GetConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            model.setRowCount(0); // Xóa các dòng hiện tại trong bảng

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MASP"),
                    rs.getString("TENSP"),
                    rs.getLong("DONGIA"),
                    rs.getString("TENLOAI")
                });
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean ThemSanPham(String maloai, String tenloai) {
        DBConnection dbConn = new DBConnection();
        String qr = "INSERT INTO LOAISP Values('" + maloai + "', N'" + tenloai + "')";
        return dbConn.UpdateData(qr);
    }

    public static boolean SuaSanPham(String maloai, String tenloai) {
        DBConnection dbConn = new DBConnection();
        String qr = "UPDATE LOAISP SET TENLOAI = N'" + tenloai + "' WHERE MALOAI = '" + maloai + "'";
        return dbConn.UpdateData(qr);
    }

    public static boolean XoaSanPham(String maloai) {
        String qr = "DELETE FROM LOAISP WHERE MALOAI = '" + maloai + "'";
        DBConnection dbConn = new DBConnection();
        return dbConn.UpdateData(qr);
    }

    public static boolean DeleteProduct(String masp) {
        String qr = "DELETE FROM SANPHAM WHERE MASP = '" + masp + "'";
        DBConnection dbConn = new DBConnection();
        return dbConn.UpdateData(qr);
    }

    // giả sử chọn loại sp bất kỳ thì khi chọn loại sản phẩm đó thì sẽ lấy theo mã
    public static boolean EditProduct(String masp, String tensp, String gia, String loaisp) {
        DBConnection dbConn = new DBConnection();
        String qrCon = "SELECT MALOAI FROM LOAISP WHERE TENLOAI = N'" + loaisp + "'";
        String qr = "UPDATE SANPHAM SET TENSP = N'" + tensp + "', DONGIA = '" + gia + "', MALOAI = (" + qrCon + ")  WHERE MASP = '" + masp + "'";
        return dbConn.UpdateData(qr);
    }

    public static boolean AddProduct(String masp, String tensp, String gia, String loaisp) {
        DBConnection dbConn = new DBConnection();
        String qrCon = "SELECT MALOAI FROM LOAISP WHERE TENLOAI = N'" + loaisp + "'"; // Lấy MALOAI từ LOAISP dựa trên TENLOAI
        String qr = "INSERT INTO SANPHAM VALUES('" + masp + "', N'" + tensp + "', '" + gia + "', (" + qrCon + "))"; // Sử dụng subquery để lấy giá trị của MALOAI
        return dbConn.UpdateData(qr);
    }

    public static boolean ProductToTable(JTable table) {
        // Xóa dữ liệu hiện tại của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng

        // Tạo truy vấn SQL để lấy sản phẩm theo loại
        String qr = "SELECT * FROM SANPHAM ORDER BY MALOAI ASC";

        // Thực hiện truy vấn và cập nhật dữ liệu vào bảng
        DBConnection dbConn = new DBConnection();
        ResultSet rs = dbConn.GetData(qr);
        if (rs != null) {
            try {
                // Duyệt qua các hàng của ResultSet và thêm vào bảng
                while (rs.next()) {
                    Object[] row = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)
                    };
                    model.addRow(row); // Thêm hàng mới vào bảng
                }
                return true; // Trả về true nếu thành công
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public static boolean SanPhamToTable(JTable jTable) {
        try {
            DefaultTableModel dfTableModel = (DefaultTableModel) jTable.getModel();
            dfTableModel.setRowCount(0);
            DBConnection db = new DBConnection();
            ResultSet rs = db.GetData("SELECT * FROM LOAISP");
            String[] row = new String[2];
            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                dfTableModel.addRow(row);
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean SanPhamToTable_ByLoaiSP(JTable table, String tenloai) {
        // Xóa dữ liệu hiện tại của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng

        // Tạo truy vấn SQL để lấy sản phẩm theo loại
        String qr = "SELECT * FROM SANPHAM WHERE MALOAI = (SELECT MALOAI FROM LOAISP WHERE TENLOAI = N'" + tenloai + "') ORDER BY MASP DESC";

        // Thực hiện truy vấn và cập nhật dữ liệu vào bảng
        DBConnection dbConn = new DBConnection();
        ResultSet rs = dbConn.GetData(qr);
        if (rs != null) {
            try {
                // Duyệt qua các hàng của ResultSet và thêm vào bảng
                while (rs.next()) {
                    Object[] row = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)
                    };
                    model.addRow(row); // Thêm hàng mới vào bảng
                }
                return true; // Trả về true nếu thành công
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public static boolean LoaiSanPhamToCombox(JComboBox cbLoaiSP) {
        String qr = "SELECT TENLOAI FROM LOAISP";
        DBConnection dbConn = new DBConnection();
        ResultSet rs = dbConn.GetData(qr);
        if (rs != null) {
            try {
                // Xóa các mục hiện tại trong ComboBox
                cbLoaiSP.removeAllItems();

                // Thêm dữ liệu từ ResultSet vào ComboBox
                while (rs.next()) {
                    cbLoaiSP.addItem(rs.getString("TENLOAI"));
                }
                return true; // Trả về true nếu thành công
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public static ArrayList<String> LayDataLoaiSP() {
        String query = "SELECT TENLOAI FROM LOAISP";
        ArrayList<String> productTypes = new ArrayList<>();
        DBConnection db = new DBConnection();
        try (Connection conn = db.GetConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                String type = rs.getString("TENLOAI");
                productTypes.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productTypes;
    }

    public static int Count(String tableName, String columnName, String id) {
        String qr = "";
        if (id.length() == 0 || columnName.length() == 0) {
            qr = "SELECT COUNT(*) FROM " + tableName;
        } else {
            qr = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = '" + id + "'";
        }
        DBConnection dbConn = new DBConnection();
        ResultSet rs = dbConn.GetData(qr);
        try {
            if (rs.next()) {
                int count = Integer.parseInt(rs.getString(1));
                return count;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return -1;
    }

}
