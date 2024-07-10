package co.yedam.vo;

import java.util.Date;

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
	public String getStdNo() {
		return stdNo;
	}
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdPhone() {
		return stdPhone;
	}
	public void setStdPhone(String stdPhone) {
		this.stdPhone = stdPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "StudentVO [stdNo=" + stdNo + ", stdName=" + stdName + ", stdPhone=" + stdPhone + ", address=" + address
				+ ", birthDate=" + birthDate + ", creationDate=" + creationDate + "]";
	}
	
	public String briefShow() {
		return stdNo + "   " + stdName + "      " + stdPhone;
	}
}

