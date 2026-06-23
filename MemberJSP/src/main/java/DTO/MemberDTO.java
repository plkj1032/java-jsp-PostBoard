package DTO;

public class MemberDTO {
	private int id;
	private String name;
	private int age;
	private String email;
	private String password;
	private String role;
	private String snsId;
	private String snsType;
	
	public MemberDTO() {};
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return email;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getRole()
	{
		return role;
	}
	
	public String getSnsId() {
        return snsId;
    }

    public void setSnsId(String snsId) {
        this.snsId = snsId;
    }

    public String getSnsType() {
        return snsType;
    }

    public void setSnsType(String snsType) {
        this.snsType = snsType;
    }
}
