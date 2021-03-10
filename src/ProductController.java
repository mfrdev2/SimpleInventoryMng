import DBC.DataBaseHelper;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductController {
    public static boolean savePname(String pname) {
        Connection con = null;
        CallableStatement csmt = null;
        boolean t = true;
        try {
            con = DataBaseHelper.getConnection();
            csmt = con.prepareCall("{CALL saveProduct(?)}");
            csmt.setString(1, pname);
            t = csmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert con != null;
                con.close();
                assert csmt != null;
                csmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
  //      System.out.println("SavePName = "+t);
        return t;
    }

    public static void loadCombo(JComboBox comboBox){
        Connection con = null;
        CallableStatement csmt = null;
        ResultSet rs = null;
        try {
            con = DataBaseHelper.getConnection();
            csmt = con.prepareCall("{CALL listProduct()}");
            csmt.execute();
            rs = csmt.getResultSet();
            List pList = new ArrayList<>();
            while (rs.next()){
                pList.add(rs.getString(1));
            }
            comboBox.setModel(new DefaultComboBoxModel(pList.toArray()));
            comboBox.insertItemAt("Select One",0);
            comboBox.setSelectedIndex(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert con != null;
                con.close();
                assert csmt != null;
                csmt.close();
                assert rs != null;
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static boolean savePurchase(String pname,String price,String date,String qty) {
        Connection con = null;
        CallableStatement csmt = null;
        boolean t = true;
        try {
            con = DataBaseHelper.getConnection();
            csmt = con.prepareCall("{CALL savePurchase(getProductId(?),?,?,?)}");
            csmt.setString(1, pname);
            csmt.setString(2, price);
            csmt.setString(3, date);
            csmt.setString(4, qty);
            t = csmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert con != null;
                con.close();
                assert csmt != null;
                csmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
   //     System.out.println("SavePurchase = "+t);

        return t;
    }

    public static boolean saveSale(String pname,String price,String date,String qty) {
        Connection con = null;
        CallableStatement csmt = null;
        boolean t = true;
        try {
            con = DataBaseHelper.getConnection();
            csmt = con.prepareCall("{CALL saveSale(getProductId(?),?,?,?)}");
            csmt.setString(1, pname);
            csmt.setString(2, price);
            csmt.setString(3, date);
            csmt.setString(4, qty);
            t = csmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert con != null;
                con.close();
                assert csmt != null;
                csmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
   //     System.out.println("SaveSale = "+t);

        return t;
    }

    public static String loadQty(JComboBox comboBox){
        Connection con = null;
        CallableStatement csmt = null;
        String qty = null;
        try {
            con = DataBaseHelper.getConnection();
            csmt = con.prepareCall("{?= CALL getProductQty(?)}");
            csmt.registerOutParameter(1, Types.INTEGER);
            csmt.setString(2,comboBox.getSelectedItem().toString());
            csmt.execute();
            int output = csmt.getInt(1);
            qty = String.valueOf(output);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert con != null;
                con.close();
                assert csmt != null;
                csmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return qty;
    }

    public static String loadAvg(JComboBox comboBox){
        Connection con = null;
        CallableStatement csmt = null;
        ResultSet rs = null;
        String avg = null;
        try {
            con = DataBaseHelper.getConnection();
            csmt = con.prepareCall("{CALL avg_price(getProductId(?))}");
            csmt.setString(1,comboBox.getSelectedItem().toString());
            csmt.execute();
            rs = csmt.getResultSet();
            while (rs.next()){
                BigDecimal d = new BigDecimal(rs.getString(1));
                avg = d.toPlainString();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert con != null;
                con.close();
                assert csmt != null;
                csmt.close();
                assert rs != null;
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return avg;
    }
}
