package com.shujia.util;

import java.sql.*;

public class LoginUtils {


    /**
     * 验证用户名是否存在
     *如果用户名已存在，返回true
     *
     * 通过sql注入绕过用户名验证
     * 111' or '1'='1
     * select * from user where name='111' or '1'='1'
     *
     */
    public static boolean verifyUsernmae(String username){
        String selSql = "select * from user where name=?";

        System.out.println(selSql);
        Connection connection = null;

        boolean flag = false;

        try {
            connection = DBUtils.getConnection();
            //普通sql执行器，直接发送一条sql给sql服务器
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery(selSql);

            //解决sql注入。使用预编译sql执行器
            //创建预编译sql执行器
            PreparedStatement statement = connection.prepareStatement(selSql);
            //给参数赋值，下标从1开始
            statement.setObject(1,username);

            //执行查询，返回结果
            ResultSet resultSet = statement.executeQuery();
            //如果用户名已存在，返回true
            flag = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
