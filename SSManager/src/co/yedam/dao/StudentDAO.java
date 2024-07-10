package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.StudentVO;

// 목록(R), 등록(C), 수정(U), 삭제(D)
// 주의: DAO 메세지(System.out.println)가 없음

public class StudentDAO extends DAO {
	//필드
	
	//생성자
	
	//메소드
	// 목록반환기능
	public List<StudentVO> selectList() {
		String sql = "select * from tbl_student order by std_no";
		List<StudentVO> list = new ArrayList<>();
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				StudentVO svo = new StudentVO();
				svo.setStdNo(rs.getString("std_no"));
				svo.setStdName(rs.getString("std_name"));
				svo.setStdPhone(rs.getString("std_phone"));
				svo.setAddress(rs.getString("address"));
				svo.setBirthDate(rs.getString("birth_date"));
				svo.setCreationDate(rs.getDate("creation_date"));
				
				list.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of selectList
	
	// 등록기능
	public boolean insertStudent(StudentVO svo) {
		conn = getConn();
		String sql = "insert into tbl_student (std_no, std_name, std_phone, address, birth_date)";
		sql += " values (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svo.getStdNo());
			pstmt.setString(2, svo.getStdName());
			pstmt.setString(3, svo.getStdPhone());
			pstmt.setString(4, svo.getAddress());
			pstmt.setString(5, svo.getBirthDate());
			
			int result = pstmt.executeUpdate(); // 쿼리실행
			if (result == 1) {
				return true; // 정상처리
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 비정상처리
	} // end of insertStudent
	
	// 단건조회
	public int selectExists(String sno) {
		String sql = "select count(1) from tbl_student";
		sql += " where std_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 수정기능
	public boolean updateStudent(StudentVO svo) {
		System.out.println(svo);
		conn = getConn();
		String sql = "update tbl_student";
		sql += " set std_phone = nvl('" + svo.getStdPhone() + "', std_phone)";
		sql += ", address = nvl('" + svo.getAddress() + "', address)";
		sql += ", birth_date = nvl('" + svo.getBirthDate() + "', birth_date)";
		sql += " where std_no = '" + svo.getStdNo() + "'";
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			System.out.println("result = " + result);
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of updateStudent
	
	// 삭제기능
	public boolean removeStudent(String stdNo) {
		conn = getConn();
		String sql = "delete from tbl_student";
		sql += " where std_no = '"+stdNo+"'";
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} // end of removeStudent
}
