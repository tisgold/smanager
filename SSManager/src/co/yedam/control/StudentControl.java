package co.yedam.control;

import java.util.List;
import java.util.Scanner;

import co.yedam.dao.StudentDAO;
import co.yedam.vo.StudentVO;

//사용자 입력을 가이드, 처리된 결과 출력

public class StudentControl {
	//필드
	Scanner scn = new Scanner(System.in);
	StudentDAO sdao = new StudentDAO();
	
	//생성자
	
	//메소드
	public void run() {
		boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("1.학생목록 2.등록 3.수정 4.삭제 5.종료");
			System.out.print("선택> ");
			int menu = Integer.parseInt(scn.nextLine());
			
			switch(menu) {
			case 1:
				studentList();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				modifyStudent();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다!");
				isTrue = false;
			}
		}
	} // end of run()
	
	// 목록 출력 기능
	void studentList() {
		List<StudentVO> students = sdao.selectList();
		System.out.println("학생번호     학생이름    연락처");
		System.out.println("------------------------------------");
		for(StudentVO svo : students) {
			System.out.println(svo.briefShow());
		}
	} // end of studentList	
	
	// 등록 기능
	void addStudent() {
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
		
		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		std.setStdName(sname);
		std.setStdPhone(phone);
		std.setAddress(addr);
		std.setBirthDate(birth);
		
		// 입력기능 호출
		if(sdao.insertStudent(std)) {
			System.out.println("저장완료!");
		}
		else {
			System.out.println("처리중 예외발생!");
		}
	} // end of addStudent
	
	// 수정 기능
	void modifyStudent() {
		StudentVO svo = new StudentVO();
		String sno = null;
		String phone = null;
		String addr = null;
		String birth = null;
		int count = 0;
		
		while(true) {
			if(count > 3) {
				break;
			}
			else {
				System.out.print(">학생번호 입력: ");
				sno = scn.nextLine();
				if(sdao.selectExists(sno) == 1) { //학생번호 존재
					System.out.print(">연락처 입력: ");
					phone = scn.nextLine();
					System.out.print(">주소 입력: ");
					addr = scn.nextLine();
					System.out.print(">생일 입력: ");
					birth = scn.nextLine();
				}
				count++;
				System.out.println("찾는 학생번호가 없습니다 학생번호를 다시 입력하세요");
			}
			svo.setStdNo(sno);
			svo.setStdPhone(phone);
			svo.setAddress(addr);
			svo.setBirthDate(birth);
			break;
		}
		
		// 수정기능 호출
		if(sdao.updateStudent(svo)) {
			System.out.println("수정완료!");
		}
		else {
			System.out.println("처리중 예외발생!");
		}
		
	} // end of modStudent

	// 삭제 기능
	void deleteStudent() {
		System.out.print(">학생번호 입력: ");
		String sno = scn.nextLine();
		
		if(sdao.removeStudent(sno)) {
			System.out.println("삭제완료!");
		}
		else {
			System.out.println("처리중 예외발생!");
		}
	} // end of deleteStudent

}
