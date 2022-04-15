/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ConnectSQL.ConnectSQL;
import Model.Book;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class BookServices {
    public List<Book> getAllBook(){
        List<Book> books = new ArrayList<Book>();
        Connection con = ConnectSQL.getConnectSQL();
     
        String sql = "Select * from Sach";
        
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next())
            {
                Book sach = new Book();
                
                sach.setiD(rs.getInt("iD"));
                sach.setTitle(rs.getString("title"));
                sach.setPrice(rs.getDouble("price"));
               
                books.add(sach);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }
    public List<Integer> getAllId(){
        List<Integer> allId = new ArrayList<Integer>();
        Connection con = ConnectSQL.getConnectSQL();
        
        String sql = "Select id from Sach";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                allId.add(rs.getInt("id"));  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allId;
    }
    public List<Book> findBookByName(String txt){
        List<Book> books = new ArrayList<Book>();
        Connection con = ConnectSQL.getConnectSQL();
        
        String sql = "Select * from Sach where title like N'%" + txt + "%'";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next())
            {
                Book sach = new Book();
                
                sach.setiD(rs.getInt("iD"));
                sach.setTitle(rs.getString("title"));
                sach.setPrice(rs.getDouble("price"));
                
                books.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findById(int id){
        Book book = new Book();
        Connection con = ConnectSQL.getConnectSQL();
        String sql = "Select * from Sach where id = " + id;
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                book.setiD(id);
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getDouble("price"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return book;
    }
    
    public void updateBook(Book book){
        Connection con = ConnectSQL.getConnectSQL();
        String sql = "Update Sach set title = ?, price = ? where id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDouble(2, book.getPrice());
            preparedStatement.setInt(3, book.getiD());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(int id) {
        Connection con = ConnectSQL.getConnectSQL();
        String sql = "Delete from Sach where id = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void addBook(Book book){
        Connection con = ConnectSQL.getConnectSQL();
        String sql = "Insert into Sach(title, price) values(?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDouble(2, book.getPrice());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
