package com.zcl.edu;

import java.sql.*;

/**
 * Created by Administrator on 2018-05-10.
 */
public class TestJDBC2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis?characterEncoding=utf-8", "root", "root");
            //connection = C3p0Utils.getConnection();
            String sql = "select * from user where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"张三");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id")+"--->"+resultSet.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != resultSet){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != preparedStatement){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
