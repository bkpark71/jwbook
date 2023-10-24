package ch06;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
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

    public StudentService() {
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select * from student");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setUniv(rs.getString("univ"));
                s.setBirth(rs.getString("birth"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student findById(int id){
        Student s = new Student();
        try {
            pstmt = conn.prepareStatement("select * from student where id = ?");
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setUniv(rs.getString("univ"));
            //s.setBirth(rs.getString("birth"));
            s.setEmail(rs.getString("email"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    public void update(int id, String email){
        try {
            pstmt = conn.prepareStatement("update student set email=? where id = ?");
            pstmt.setString(1,email);
            pstmt.setInt(2,id);
            int res = pstmt.executeUpdate();
            if (res == 1) {
                System.out.println("수정 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insert(Student student){
        try {
            pstmt = conn.prepareStatement("insert into student(name, univ, birth, email) values(?,?,?,?);");
            pstmt.setString(1,student.getName());
            pstmt.setString(2,student.getUniv());
            pstmt.setString(3,student.getBirth());
            pstmt.setString(4,student.getEmail());
            int res = pstmt.executeUpdate();
            if (res == 1) {
                System.out.println("등록 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try {
            System.out.println("service.delete 실행중");
            pstmt = conn.prepareStatement("delete from student where id = ?");
            pstmt.setInt(1,id);
            int res = pstmt.executeUpdate();
            if (res == 1) {
                System.out.println("삭제 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
