package DTO;

public class PostDTO {
	private int id;
	private int member_id;
	private String title;
	private String content;
	private String post_writer;
	private int view_count;
	private  String created_at;
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	
	public void setMember_id(int member_id)
	{
		this.member_id = member_id;
	}
	public int getMember_id()
	{
		return member_id;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}
	
	public void setPost_writer(String post_writer)
	{
		this.post_writer = post_writer;
	}
	public String getPost_writer()
	{
		return post_writer;
	}
	
	public void setView_count(int view_count)
	{
		this.view_count = view_count;
	}
	public int getView_count()
	{
		return view_count;
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
