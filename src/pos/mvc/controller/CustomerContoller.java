package pos.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pos.mvc.model.CustomerModel;
import pos.mvc.db.DBConnection;

public class CustomerContoller {

    public String saveCustomer(CustomerModel customer) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, customer.getCustId());
        preparedStatement.setString(2, customer.getTitle());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setString(4, customer.getDob());
        preparedStatement.setDouble(5, customer.getSalary());
        preparedStatement.setString(6, customer.getAddress());
        preparedStatement.setString(7, customer.getCity());
        preparedStatement.setString(8, customer.getProvince());
        preparedStatement.setString(9, customer.getZip());

        if (preparedStatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    public ArrayList<CustomerModel> getAllCustomers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rst = preparedStatement.executeQuery();

        ArrayList<CustomerModel> customerModels = new ArrayList<>();
        while (rst.next()) {
            CustomerModel cm = new CustomerModel(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(6),
                rst.getString(7),
                rst.getString(8),
                rst.getString(9),
                rst.getDouble(5)
            );
            customerModels.add(cm);
        }

        return customerModels;
    }

    public CustomerModel getCustomer(String custId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM customer WHERE ID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, custId);

        ResultSet rst = preparedStatement.executeQuery();
        while (rst.next()) {
            CustomerModel cm = new CustomerModel(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(6),
                rst.getString(7),
                rst.getString(8),
                rst.getString(9),
                rst.getDouble(5)
            );
            return cm;
        } 
            return null; // Return null if no customer with the given custId is found
        
    }
    public String updateCustomer(CustomerModel customerModel) throws SQLException{
     Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE customer set title=?,name=?,dob=?,salary=?,address=?,city=?,province=?,postalcode=? where ID=?";   
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(9, customerModel.getCustId());
        preparedStatement.setString(1, customerModel.getTitle());
        preparedStatement.setString(2, customerModel.getName());
        preparedStatement.setString(3, customerModel.getDob());
        preparedStatement.setDouble(4, customerModel.getSalary());
        preparedStatement.setString(5, customerModel.getAddress());
        preparedStatement.setString(6, customerModel.getCity());
        preparedStatement.setString(7, customerModel.getProvince());
        preparedStatement.setString(8, customerModel.getZip());
        if (preparedStatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    public String deleteCustomer(String custId)throws SQLException{
      Connection connection = DBConnection.getInstance().getConnection();
        String query = "Delete from customer where ID=?";
        PreparedStatement statement=connection.prepareCall(query);
        statement.setString(1, custId);
               if (statement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
