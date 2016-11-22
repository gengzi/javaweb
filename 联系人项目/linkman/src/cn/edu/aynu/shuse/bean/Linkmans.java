package cn.edu.aynu.shuse.bean;

public class Linkmans {
	private int id ;  // 联系人的id
	private String name ; // 联系人的姓名
	private String tel ; // 联系人的电话
	private String address ; // 联系人的地址
	private String zipcode ; // 联系人的邮编
	private int userId ; // 外键 userid
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
