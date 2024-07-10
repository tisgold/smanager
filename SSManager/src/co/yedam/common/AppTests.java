package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import co.yedam.vo.StudentVO;

public class AppTests {
	public static Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";
		String pass = "jsp";
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음");
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	public static void main(String[] args) {
		// 1) Oracle JDBC Driver 자바 라이브러리
		// 2) Connection 객체
		Scanner scn = new Scanner(System.in);
		System.out.print(">학생번호 입력: ");
		String sno = scn.nextLine();
		System.out.print(">학생이름 입력: ");
		String sname = scn.nextLine();
		System.out.print(">연락처 입력: ");
		String phone = scn.nextLine();
		System.out.print(">주소 입력: ");
		String addr = scn.nextLine();
		System.out.print(">생일 입력: ");
		String birth = scn.nextLine();
		
		//addStudent(sno, sname, phone); //입력
		
		//modStudent(sno, sname, phone); //수정
		
		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		std.setStdName(sname);
		std.setStdPhone(phone);
		std.setAddress(addr);
		std.setBirthDate(birth);
				
		modStu(std);
		
		//removeStudent(sno);
		
		search(); //목록조회
		
		scn.close();
	}
	
	//삭제기능
	public static void removeStudent(String stdNo) {
		Connection conn = getConn();
		String sql = "delete from tbl_student";
		sql += " where std_no = '"+stdNo+"'";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("처리된 건수는: " + r + "건");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modStu(StudentVO svo) {
		Connection conn = getConn();
		String sql = "update tbl_student";
		sql += " set std_name = nvl('" + svo.getStdName() + "', std_name)";
		sql += ", std_phone = nvl('" + svo.getStdPhone() + "', std_phone)";
		sql += ", address = nvl('" + svo.getAddress() + "', address)";
		sql += ", birth_date = nvl('" + svo.getBirthDate() + "', birth_date)";
		sql += " where std_no = '" + svo.getStdNo() + "'";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("처리된 건수는: " + r + "건");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//수정기능
	public static void modStudent(String stdNo, String name, String phone) {
		Connection conn = getConn();
		String sql = "update tbl_student";
		sql += " set std_name = '"+name+"', std_phone = '"+phone+"',";
		sql += " where std_no = '"+stdNo+"'";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("처리된 건수는: " + r + "건");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//입력기능
	public static void addStudent(String stdNo, String name, String phone) {
		Connection conn = getConn();
		String sql = "insert into tbl_student (std_no, std_name, std_phone)";
		sql += "values('"+stdNo+"', '"+name+"', '"+phone+"')";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete => 처리된 건수 반환
			System.out.println(sql);
			System.out.println("처리된 건수는: " + r + "건");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//조회기능
	public static void search() {
		//조회기능
		Connection conn = getConn();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_student order by std_no");
			// [객체1, 객체2, 객체3]
			while(rs.next()) {
				System.out.println(rs.getString("std_no") + ", " + rs.getString("std_name") + ", " + rs.getString("std_phone"));
			}
			System.out.println("End of data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//조회기능 끝
	}
}
