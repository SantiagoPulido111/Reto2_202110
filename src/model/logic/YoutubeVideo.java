package model.logic;

import java.util.Comparator;

import java.util.Date;


public class YoutubeVideo implements Comparable<YoutubeVideo>
{
	private Date trending_date;
	private String title;
	private String channel_title;
	private int views;
	private int likes;
	private int dislikes;
	private String thumbnail_link;
	private String country;
	private String id;
	private Date publish_time;
	private int category_id;
	private long dias_tendencia;
	
	


	public YoutubeVideo(Date tdate, String tit, String channel_t,int vi, int li, int disli,String thumbnail,String count,String id, Date publish_time, int category_id)
	{	
		setTrending_date(tdate);
		setTitle(tit);
		setChannel_title(channel_t);
		setViews(vi);
		setLikes(li);
		setDislikes(disli);
		setThumbnail_link(thumbnail);
		setCountry(count);
		setId(id);
		setPublish_time(publish_time);
		setCategory_id(category_id);
		
		diasTendencia();
	}

	//	public int compareTo(YoutubeVideo arg0)
	//	{	
	//		return this.trending_date.compareTo(arg0.getTrending_date());
	//	}

	public Date getTrending_date() 
	{
		return trending_date;
	}

	public void setTrending_date(Date trending_date) 
	{
		this.trending_date = trending_date;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getChannel_title() 
	{
		return channel_title;
	}

	public void setChannel_title(String channel_title) 
	{
		this.channel_title = channel_title;
	}

	public int getViews() 
	{
		return views;
	}

	public void setViews(int views) 
	{
		this.views = views;
	}

	public int getLikes() 
	{
		return likes;
	}

	public void setLikes(int likes) 
	{
		this.likes = likes;
	}

	public int getDislikes() 
	{
		return dislikes;
	}

	public void setDislikes(int dislikes) 
	{
		this.dislikes = dislikes;
	}

	public String getThumbnail_link() 
	{
		return thumbnail_link;
	}

	public void setThumbnail_link(String thumbnail_link) 
	{
		this.thumbnail_link = thumbnail_link;
	}

	public String getCountry() 
	{
		return country;
	}

	public void setCountry(String country) 
	{
		this.country = country;
	}

	//	// Definicion de atributos
	//	/** Comparación natural de acuerdo a algún atributo con identificación única
	//	 * @return valor 0 si this y otro son iguales. Numero negativo si this es menor a otro. 
	//	 * Numero positivo this es mayor a otro */

	public int compareTo(YoutubeVideo arg0)
	{	
		return this.trending_date.compareTo(arg0.getTrending_date());
	}

	
	
	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}



	public Date getPublish_time() 
	{
		return publish_time;
	}

	public void setPublish_time(Date publish_time) 
	{
		this.publish_time = publish_time;
	}



	public int getCategory_id() 
	{
		return category_id;
	}

	public void setCategory_id(int category_id) 
	{
		this.category_id = category_id;
	}



	public static class ComparadorXfecha implements Comparator<YoutubeVideo> 
	{
		public int compare(YoutubeVideo video1, YoutubeVideo video2) 
		{
			return video1.getTrending_date().compareTo(video2.getTrending_date());
		}
		
	}
	
	//	Comparador alterno de 2 videos por número de likes
	
	public static class ComparadorXLikes implements Comparator<YoutubeVideo> 
	{

		/** Comparador alterno de acuerdo al número de likes
		 * @return valor 0 si video1 y video2 tiene los mismos likes.
	 valor negativo si video1 tiene menos likes que video2.
	 valor positivo si video1 tiene más likes que video2. */

		public int compare(YoutubeVideo video1, YoutubeVideo video2) 
		{
			return video1.getLikes() - video2.getLikes();
		}

	}

	public long diasTendencia ()
	{
		long trendingTime = trending_date.getTime();
		long publishTime = publish_time.getTime();
		
		return (long) ((trendingTime - publishTime) /  864e+5);
	}

	public long getDias_tendencia() 
	{
		return dias_tendencia;
	}

	public void setDias_tendencia(long dias_tendencia) 
	{
		this.dias_tendencia = dias_tendencia;
	}

	

}