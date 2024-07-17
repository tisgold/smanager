package co.yedam.vo;

import java.util.Date;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

//@Getter
//@Setter
//@ToString
@Data

public class StudentVO {
	//필드
	private String stdNo; //std_no
	private String stdName;
	private String stdPhone;
	private String address;
	private String birthDate; // 1999-01-01
	private Date creationDate;
	
	//생성자
	
	//메소드
	public String briefShow() {
		return stdNo + "   " + stdName + "      " + stdPhone;
	}
}
