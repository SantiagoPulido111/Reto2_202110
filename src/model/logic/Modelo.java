package model.logic;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;


import model.data_structures.ILista;
import model.data_structures.ITablaSimbolos;
import model.data_structures.ListaEncadenada;
import model.data_structures.TablaSimbolos;
import model.utils.Ordenamiento;
import view.View;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	//private ILista<YoutubeVideo> datos;
	private ITablaSimbolos<String, ArregloDinamico<YoutubeVideo>> datos;
	private ILista<YoutubeVideo> sublista;
	private ILista<CategoriaVideo> listaCategorias;
	private int numVideos;

	//Para la opcion 1
	private double tiempoParaTimerTotal=0;

	//ESTO SOLO SRIVE PARA OPC 1
	private final static String ID = "data/category-id.csv";

	private View vista = new View();

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @throws ParseException 
	 */
	public Modelo(String ruta) throws IOException, ParseException
	{
		setNumVideos(0);
		cargarDatos(ruta);

	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */


	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(YoutubeVideo dato)
	{	
		//pruebas
		String key = llaveEnString(dato.getCountry(),dato.getCategoria());
		ArregloDinamico<YoutubeVideo> temp;
		if(datos.get(key)==null)
		{

			temp = new ArregloDinamico<YoutubeVideo>(1);

		}
		else
		{
			temp= datos.get(key);

		}

		temp.addLast(dato);
		Stopwatch timer = new Stopwatch();
		datos.put(key, temp);
		tiempoParaTimerTotal = getTiempoPutTotal() + timer.elapsedTime();

		numVideos++;
	}

	/**
	 * Requerimiento buscar dato
	 * @param key Dato a buscar
	 * @return dato encontrado
	 */
	public ArregloDinamico<YoutubeVideo> buscar(String key)
	{
		return datos.get(key);
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public ArregloDinamico<YoutubeVideo> eliminar(String key)
	{
		return datos.remove(key);
	}


	public ILista<CategoriaVideo> getCategorias() 
	{
		return listaCategorias;
	}

	//TODO arreglar este metodo, no sirve desde el for

	public void cargarDatos(String ruta) throws IOException, ParseException
	{

		//hacer esto en cualquier metodo que clacule el tiempo tomado por el put 
		tiempoParaTimerTotal=0;





		listaCategorias = new ArregloDinamico<CategoriaVideo > (44);
		Reader in2 = new FileReader(ID);
		Iterable<CSVRecord> records2 = CSVFormat.EXCEL.withHeader().parse(in2);

		for (CSVRecord record : records2)
		{
			String categor= record.get("id	name");
			String[] words = categor.split("	"); 

			words[0]=words[0].replaceAll("\\s+", "");
			words[1]=words[1].replaceAll("\\s+", "");
			CategoriaVideo actualCat = new CategoriaVideo(Integer.parseInt(words[0]), words[1]);

			listaCategorias.addLast(actualCat);
		}
		Stopwatch timer = new Stopwatch();
		Reader in = new FileReader(ruta);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

		datos =new TablaSimbolos<>(1000);

		for (CSVRecord record : records)
		{
			String trending_date_String = record.get("trending_date");
			Date trending_date;
			trending_date = new SimpleDateFormat("yy.dd.MM").parse(trending_date_String);
			//			System.out.println(trending_date);
			String title=record.get("title");
			String channel_title=record.get("channel_title");
			int views=Integer.parseInt(record.get("views"));
			int likes=Integer.parseInt(record.get("likes"));
			int dislikes=Integer.parseInt(record.get("dislikes"));
			String thumbnail_link=record.get("thumbnail_link");
			String country=record.get("country");
			String id = record.get("video_id");
			String tag = record.get("tags");
			String publish_time_S = record.get("publish_time");
			Date publish_time = new SimpleDateFormat("yyyy-MM-dd").parse(publish_time_S);
			int category_id = Integer.parseInt(record.get("category_id"));
			String categoria= darCategoriaID(category_id);
			//String categoria =category_id+"";

			YoutubeVideo temp=new YoutubeVideo(trending_date, title, channel_title,views,likes,dislikes,thumbnail_link,country,id,publish_time,category_id,tag,categoria);

			agregar(temp);
		}

		vista.printMessage("Arreglo creado"); 
		double time = timer.elapsedTime();
		vista.printMessage("Tiempo tomado (milisegundos): "+ time);


	}

	public int darIDCategoria(String nombreCat)
	{
		int id = 0;
		boolean encontrado = false;
		for (int i = 1; i < listaCategorias.size() && !encontrado; i++) 
		{
			if(listaCategorias.getElement(i).getCategory_name().equalsIgnoreCase(nombreCat))
			{
				id = listaCategorias.getElement(i).getCategory_id();
				encontrado = true;
			}
		}
		return id;
	}


	public String darCategoriaID(int id)
	{

		boolean encontrado = false;
		String categ=null;
		for (int i = 1; i < listaCategorias.size() && !encontrado; i++) 
		{
			if(listaCategorias.getElement(i).getCategory_id()==id)
			{
				categ = listaCategorias.getElement(i).getCategory_name();
				encontrado = true;
			}
		}
		return categ;
	}

	public ILista<YoutubeVideo> getSublista() 
	{
		return sublista;
	}

	public void setSublista(ILista<YoutubeVideo> sublista) 
	{
		this.sublista = sublista;
	}





	public void sortSublista(int tipoOrdenamiento, boolean porfecha, boolean ascendente)
	{
		Stopwatch timer = new Stopwatch();
		Comparator<YoutubeVideo> comp= porfecha?new YoutubeVideo.ComparadorXfecha():new YoutubeVideo.ComparadorXLikes();
		Ordenamiento<YoutubeVideo> ord = new Ordenamiento<YoutubeVideo>();

		switch(tipoOrdenamiento)
		{
		case 1:
			// insertion
			ord.ordenarInsercion(sublista,comp,ascendente);
			break;
		case 2:
			//shell
			ord.shell(sublista,comp,ascendente);
			break;
		case 3:
			//merge
			ord.ordenarMergeSort(sublista,comp,ascendente);
			break;
		case 4:
			//quick
			ord.ordenarQuickSort(sublista,comp,ascendente);
			break;

		}
		vista.printMessage("Arreglo creado"); 
		double time = timer.elapsedTime();
		vista.printMessage("Tiempo tomado (milisegundos): " + time);
	}


	public String llaveEnString (String pais, String categoria )
	{
		return pais + "-" + categoria;
	}

	public int getNumVideos() 
	{
		return numVideos;
	}

	public void setNumVideos(int numVideos) 
	{
		this.numVideos = numVideos;
	}

	public double getTiempoPutTotal() 
	{
		return tiempoParaTimerTotal;
	}

	public void pruabaDeDesemprno()
	{
		//hacer esto en cualquier metodo que clacule el tiempo tomado por el put 
		tiempoParaTimerTotal = 0;
		
		ArregloDinamico<String> listaTemp = (ArregloDinamico<String>)datos.keySet();

//		String[] paises = {"canada", "germany","france","united kingdom","mexico","russia","USA"};
//		String[] paisesFalsos= {"colombia", "tangamandapia", "chiquinquira","UEN","UPAC","llljd","unlikes wisdom"};
//		String[] categoriasMal= {"engennering & comedy", "hupiesfr", "yakak"};

		for(int i = 1; i < 700 + 1; i++)
		{
			int j = (i > listaTemp.size())? 1 : i;
					
			String key= listaTemp.getElement(j);

			Stopwatch timer = new Stopwatch();
			datos.get(key);
			tiempoParaTimerTotal += timer.elapsedTime();
		}

		for(int i = 1; i < 300 + 1; i++)
		{
			String falseKey = "tangamandapia" + i;

			Stopwatch timer = new Stopwatch();
			datos.get(falseKey);
			tiempoParaTimerTotal += timer.elapsedTime();
		}
	}

}