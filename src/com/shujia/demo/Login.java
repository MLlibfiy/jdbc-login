package com.shujia.demo;

import com.shujia.util.DBUtils;
import com.shujia.util.LoginUtils;
import com.shujia.util.MD5Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 登录
 * 1、判断用户名是否存在，
 * 2、判断密码是否正确
 */
public class Login {

    public static void main(String[] args) {


        //判断用户名是否存在，
        System.out.println("-------------用户登录----------------");
        Scanner scanner = new Scanner(System.in);
        String username = null;
        boolean flag = true;
        while (flag) {
            System.out.println("请输入用户名：");
            username = scanner.next();
            //验证用户名是否存在
            flag = !LoginUtils.verifyUsernmae(username);
            if (flag) {
                System.out.println("用户名不存在-----");
            }
        }
        //2、判断密码是否正确
        boolean loginFlag = true;
        while (loginFlag){
            System.out.println("请输入密码：");
            //登录的时候也需要对密码加密
            String password = MD5Util.getMD5(scanner.next());
            String sql = "select * from user where name='" + username + "' and password='" + password + "'";
            //获取连接
            Connection connection = null;
            Statement statement = null;
            try {
                connection = DBUtils.getConnection();
                //获取sql执行器
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //判断是否查询到数据
                loginFlag = ! resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtils.close(connection,statement);
            }

            if (loginFlag){
                System.out.println("密码不正确------");
            }else {
                System.out.println("登录成功----------");
            }
        }


        while (true){
            //修改密码
            //退出登录
            System.out.println("请选择，1：修改密码，2：退出登录");
            int i = scanner.nextInt();

        }

    }

}
