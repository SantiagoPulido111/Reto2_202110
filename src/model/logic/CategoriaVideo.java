package model.logic;

public class CategoriaVideo implements Comparable<CategoriaVideo>
{
	private int category_id;
	private String category_name;
	
	public CategoriaVideo(int id, String name)
	{
		setCategory_id(id);
		setCategory_name(name);
	}

	public int getCategory_id() 
	{
		return category_id;
	}

	public void setCategory_id(int category_id) 
	{
		this.category_id = category_id;
	}

	public String getCategory_name() 
	{
		return category_name;
	}

	public void setCategory_name(String category_name) 
	{
		this.category_name = category_name;
	}

	@Override
	public int compareTo(CategoriaVideo o) 
	{
		
		return this.category_id-o.getCategory_id();
	}
}
