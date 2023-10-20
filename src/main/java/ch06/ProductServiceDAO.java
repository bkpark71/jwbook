package ch06;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceDAO {
    Connection conn = null;
    PreparedStatement pstmt;
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://127.0.0.1/jwbook?serverTimezone=Asia/Seoul";

    public void open() {

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("연결하는중...");
            conn = DriverManager.getConnection(JDBC_URL, "root", "1111");

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            System.out.println("연결완료...");
        }
    }

    public void close(){
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductServiceDAO() {

    }

    public List<Product> findAll() {
        System.out.println("데이터베이스 연결하기 전");
        //open();
        List<Product> products = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select * from product");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setMaker(rs.getString("maker"));
                p.setPrice(rs.getInt("price"));
                p.setDate(rs.getString("date"));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            close();
//        }
        return products;
    }

    public Product findById(String id){
        //open();
        Product p = new Product();
        try {
            pstmt = conn.prepareStatement("select * from product where id = ?");
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            p.setId(rs.getString("id"));
            p.setName(rs.getString("name"));
            p.setMaker(rs.getString("maker"));
            p.setPrice(rs.getInt("price"));
            p.setDate(rs.getString("date"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            close();
//        }
        return p;
    }

//    public void update(String id, int price){
//        Product p = findById(id);
//        p.setPrice(price);
//        products.put(id, p);
//    }
//
    public void insert(Product product){
        try {
            pstmt = conn.prepareStatement("insert into product values(?,?,?,?,?);");
            pstmt.setString(1,product.getId());
            pstmt.setString(2,product.getName());
            pstmt.setString(3,product.getMaker());
            pstmt.setInt(4,product.getPrice());
            pstmt.setString(5,product.getDate());
            int res = pstmt.executeUpdate();
            if (res == 1) {
                System.out.println("등록 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
