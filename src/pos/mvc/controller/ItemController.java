/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pos.mvc.db.DBConnection;
import pos.mvc.model.ItemModel;

/**
 *
 * @author Nethmi
 */
public class ItemController {

    public ArrayList<ItemModel> getAllItems() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rst = preparedStatement.executeQuery();
        ArrayList<ItemModel> itemModels = new ArrayList<>();
        while (rst.next()) {
            ItemModel item = new ItemModel(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
            itemModels.add(item);

        }
        return itemModels;
    }

    public String saveItem(ItemModel item) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, item.getItemCode());
        preparedStatement.setString(2, item.getDescription());
        preparedStatement.setString(3, item.getPackSize());
        preparedStatement.setDouble(4, item.getUnitPrice());
        preparedStatement.setInt(5, item.getQoh());
            if (preparedStatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }   
    }
    
    public ItemModel searchItem(String itemCode) throws SQLException{
         Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item WHERE itemCode=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, itemCode);

        ResultSet rst = preparedStatement.executeQuery();
        while (rst.next()) {
            ItemModel item = new ItemModel(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
            return item;
        }
        return null;
    }
    
       public String updateItem(ItemModel itemModel) throws SQLException{
     Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE Item set description=?,packSize=?,unitPrice=?,qoh=? where itemCode=?";   
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(5, itemModel.getItemCode());
        preparedStatement.setString(1, itemModel.getDescription());
        preparedStatement.setString(2, itemModel.getPackSize());
        preparedStatement.setDouble(3, itemModel.getUnitPrice());
        preparedStatement.setInt(4, itemModel.getQoh());
        if (preparedStatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }
        public String deleteItem(String itemCode)throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "Delete from Item where itemCode=?";
        PreparedStatement statement=connection.prepareCall(query);
        statement.setString(1, itemCode);
               if (statement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
