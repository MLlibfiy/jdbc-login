package com.shujia.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUtils {


    /**
     * 验证用户名是否存在
     *如果用户名已存在，返回true
     */
    public static boolean verifyUsernmae(String username){
        String selSql = "select * from user where name='"+username+"'";
        Connection connection = null;

        boolean flag = false;

        try {
            connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selSql);
            //如果用户名已存在，返回true
            flag = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
