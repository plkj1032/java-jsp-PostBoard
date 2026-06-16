package DTO;

public class CommentDTO {
	private int id;
	private int post_id;
	private int member_id;
	private String content;
	private String created_at;
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	
	public void setPost_id(int post_id)
	{
		this.post_id = post_id;
	}
	public int getPost_id()
	{
		return post_id;
	}
	
	public void setMember_id(int member_id)
	{
		this.member_id = member_id;
	}
	public int getMember_id()
	{
		return member_id;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}
	
	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}
	public String getCreated_at()
	{
		return created_at;
	}
}
