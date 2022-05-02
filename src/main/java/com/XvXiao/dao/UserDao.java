package com.XvXiao.dao;

import com.XvXiao.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao{

    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        //insert ..into usertable --write code yourself
        return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        //delete ...where id=?
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        //update ......where id=?

        //ToDo 5.1 - write update sql where id =?
        String sql="update usertable set username=?,password=?,email=?,gender=?,birthDate=? where id=?";
        //Todo 5.2 - create prepared statement
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setString(4,user.getGender());
        preparedStatement.setString(5,user.getBirthDate());
        preparedStatement.setInt(6,user.getId());
        //ToDo 5.3 - executeUpdate()
        preparedStatement.executeUpdate();
        //ToDo 5.4 - return int
        return 1;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        //select --- where id=?---write jdbc code yourself
        return null;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        //use for login
        //select --- where username=? and password=?--- i will show you now
        String sql="Select * from usertable where username=? and password=? ";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        User user=null;

            if (resultSet.next()){
                //get from resultSet and set into user model
                user=new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getString("gender"));
                user.setBirthDate(resultSet.getString("birthdate"));
            }


        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        //select --- where username=?--- write jdbc code yourself

        return null;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        //select ---where pass=?--- write jdbc code yourself
        return null;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        //select ---where email=?--- write jdbc code yourself
        return null;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        //select ---where gender=?--- write jdbc code yourself
        return null;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        //select ---where birthdate=?--- write jdbc code yourself
        return null;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        //select * from usertable--- --- write jdbc code yourself
        return null;
    }
}
