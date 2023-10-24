package ch07;

import ch06.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://127.0.0.1/jwbook?serverTimezone=Asia/Seoul";

    public Connection open() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("연결하는중...");
            conn = DriverManager.getConnection(JDBC_URL, "root", "1111");

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            System.out.println("연결완료...");
        }
        return conn;
    }

//    public void close(){
//        try {
//            pstmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public NewsDAO() {
    }

    public List<News> getAll() throws SQLException{
        Connection conn = open();
        List<News> newsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("select aid, title, PARSEDATETIME(date, 'yyyy-mm-dd hh:mm:ss') as cdate from news");
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            News news = new News();
            news.setAid(rs.getInt("aid"));
            news.setTitle(rs.getString("title"));
            news.setDate(rs.getString("cdate"));
            newsList.add(news);
        }
        pstmt.close();
        rs.close();
        conn.close();
        return newsList;
    }

    public News getNews(int aid) throws SQLException{
        Connection conn = open();
        News news = new News();
        PreparedStatement pstmt = null;

        pstmt = conn.prepareStatement("select aid, title, img, PARSEDATETIME(date, 'yyyy-mm-dd hh:mm:ss') as cdate, content " +
                "from product where id = ?");
        pstmt.setInt(1,aid);
        ResultSet rs = pstmt.executeQuery();

        rs.next();

        news.setAid(rs.getInt("aid"));
        news.setTitle(rs.getString("title"));
        news.setImg(rs.getString("img"));
        news.setDate(rs.getString("cdate"));
        news.setContent(rs.getString("content"));

        pstmt.close();
        rs.close();
        conn.close();
        return news;
    }

//    public void update(String id, int price){
//        try {
//            pstmt = conn.prepareStatement("update product set price=? where id = ?");
//            pstmt.setInt(1,price);
//            pstmt.setString(2,id);
//            int res = pstmt.executeUpdate();
//            if (res == 1) {
//                System.out.println("수정 완료");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void addNews(News news) throws SQLException{
        Connection conn = open();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement("insert into news(title, img, date, content) " +
                "values(?,?,CURRENT_TIMESTAMP(),?);");
        pstmt.setString(1,news.getTitle());
        pstmt.setString(2,news.getImg());
        pstmt.setString(3,news.getContent());

        int res = pstmt.executeUpdate();
        if (res == 1) {
            System.out.println("등록 완료");
        }
        pstmt.close();
        conn.close();
    }

    public void delNews(int aid) throws SQLException{
        Connection conn = open();
        PreparedStatement pstmt = conn.prepareStatement("delete from product where id = ?");
        pstmt.setInt(1,aid);

        int res = pstmt.executeUpdate();

        if (res == 0) {
            throw new SQLException("News 삭제 오류");
        }
        pstmt.close();
        conn.close();
    }
}
