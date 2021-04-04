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
import model.data_structures.TablaHashLinearProbing;
import model.data_structures.TablaHashSeparateChaining;
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
	private TablaHashLinearProbing<String, ArregloDinamico<YoutubeVideo>> LP;
	private TablaHashSeparateChaining<String,ArregloDinamico<YoutubeVideo>>  SC;

	private ILista<CategoriaVideo> listaCategorias;
	private int numVideos;

	//Para revisar lo del tiempo
	private double tiempoParaTimerTotalLP=0;
	private double tiempoParaTimerTotalSC=0;
	
	private int revicionTuplasLP=0;

	private int revicionTuplasSC=0;

	
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
	public int darTamanoLP()
	{
		return LP.size();
	}
	public int darTamanoSC()
	{
		return SC.size();
	}


	public int darTuplasLP()
	{
		return revicionTuplasLP;
	}
	public int darTuplasSC()
	{
		return revicionTuplasSC;
	}



	public int darReHashesLP()
	{
		return LP.darNumReHashes();
	}
	public int darReHashesSC()
	{
		return SC.darNumReHashes();
	}


	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(YoutubeVideo dato)
	{	

		String key = llaveEnString(dato.getCountry(),dato.getCategoria());
		ArregloDinamico<YoutubeVideo> temp;

     
		
		//Primero manejo LP
		if(LP.get(key)==null)
		{
			temp = new ArregloDinamico<YoutubeVideo>(1);
			revicionTuplasLP++;
			
		}
		else
		{
			
			temp= LP.remove(key);
		}

		temp.addLast(dato);

		Stopwatch timer = new Stopwatch();
		LP.put(key, temp);
		tiempoParaTimerTotalLP = getTiempoPutTotalLP() + timer.elapsedTime();


		temp=null;
		//TODO HAY UN PROBLEMA ACA PROBABLEMENETE EN EL REHASH 
		//Segundo manejo SC
		if(SC.get(key)==null)
		{
			temp = new ArregloDinamico<YoutubeVideo>(1);
			
			revicionTuplasSC++;
		}
		else
		{
			temp= SC.remove(key);
		}

		temp.addLast(dato);
		Stopwatch timer2 = new Stopwatch();

		SC.put(key, temp);
		tiempoParaTimerTotalSC = getTiempoPutTotalSC() + timer2.elapsedTime();

		numVideos++;
		
		//TODO pa debuggerar 
		if (revicionTuplasSC!=revicionTuplasLP)
		{
			ArregloDinamico<YoutubeVideo>debugSC=(SC.get(key));
			ArregloDinamico<YoutubeVideo>debugLP=(LP.get(key));
			vista.printMessage(key);
		}
	}

	/**
	 * Requerimiento buscar dato
	 * @param key Dato a buscar
	 * @return dato encontrado
	 */
	//	public ArregloDinamico<YoutubeVideo> buscar(String key)
	//	{
	//		return datos.get(key);
	//	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	//	public ArregloDinamico<YoutubeVideo> eliminar(String key)
	//	{
	//		return datos.remove(key);
	//	}


	public ILista<CategoriaVideo> getCategorias() 
	{
		return listaCategorias;
	}

	//TODO arreglar este metodo, no sirve desde el for

	public void cargarDatos(String ruta) throws IOException, ParseException
	{

		//hacer esto en cualquier metodo que clacule el tiempo tomado por el put 
		tiempoParaTimerTotalLP=0;
		tiempoParaTimerTotalSC=0;
		revicionTuplasLP=0;

		revicionTuplasSC=0;



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

		LP =new TablaHashLinearProbing<String,ArregloDinamico<YoutubeVideo>>(50, 0.75);
		SC =new TablaHashSeparateChaining<>(50, 5);



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
	
		
		vista.printMessage("Las tuplas para LP Y SC son:"+revicionTuplasLP + "y"+ revicionTuplasSC); 

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

	//	public ILista<YoutubeVideo> getSublista() 
	//	{
	//		return sublista;
	//	}
	//
	//	public void setSublista(ILista<YoutubeVideo> sublista) 
	//	{
	//		this.sublista = sublista;
	//	}





	//	public void sortSublista(int tipoOrdenamiento, boolean porfecha, boolean ascendente)
	//	{
	//		Stopwatch timer = new Stopwatch();
	//		Comparator<YoutubeVideo> comp= porfecha?new YoutubeVideo.ComparadorXfecha():new YoutubeVideo.ComparadorXLikes();
	//		Ordenamiento<YoutubeVideo> ord = new Ordenamiento<YoutubeVideo>();
	//
	//		switch(tipoOrdenamiento)
	//		{
	//		case 1:
	//			// insertion
	//			ord.ordenarInsercion(sublista,comp,ascendente);
	//			break;
	//		case 2:
	//			//shell
	//			ord.shell(sublista,comp,ascendente);
	//			break;
	//		case 3:
	//			//merge
	//			ord.ordenarMergeSort(sublista,comp,ascendente);
	//			break;
	//		case 4:
	//			//quick
	//			ord.ordenarQuickSort(sublista,comp,ascendente);
	//			break;
	//
	//		}
	//		vista.printMessage("Arreglo creado"); 
	//		double time = timer.elapsedTime();
	//		vista.printMessage("Tiempo tomado (milisegundos): " + time);
	//	}


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

	public double getTiempoPutTotalLP() 
	{
		return tiempoParaTimerTotalLP;
	}

	public double getTiempoPutTotalSC() 
	{
		return tiempoParaTimerTotalSC;
	}
	
	
	
	public ArregloDinamico<YoutubeVideo> darVideosPaisCtegLP(String Pais, String Categ)
	{
		return LP.get(llaveEnString(Pais, Categ));
	}
	
	public ArregloDinamico<YoutubeVideo> darVideosPaisCtegSC(String Pais, String Categ)
	{
		return SC.get(llaveEnString(Pais, Categ));
	}
	
	
	
	
	
	
	
	
	
	

	public void pruabaDeDesemprno()
	{
				//hacer esto en cualquier metodo que clacule el tiempo tomado por el put 
				tiempoParaTimerTotalLP = 0;
				tiempoParaTimerTotalSC = 0;
				
				//primero LP
				ArregloDinamico<String> listaTemp = (ArregloDinamico<String>)LP.keySet();
				for(int i = 1; i < 700 + 1; i++)
				{
					int j = (i > listaTemp.size())? 1 : i;
		
					String key= listaTemp.getElement(j);
		
					Stopwatch timer = new Stopwatch();
					LP.get(key);
					tiempoParaTimerTotalLP += timer.elapsedTime();
				}
		
				for(int i = 1; i < 300 + 1; i++)
				{
					String falseKey = "tangamandapia" + i;
		
					Stopwatch timer = new Stopwatch();
					LP.get(falseKey);
					tiempoParaTimerTotalLP += timer.elapsedTime();
				}
				
				
				//primero LP
//				ArregloDinamico<String> listaTemp = (ArregloDinamico<String>)LP.keySet();
				// puedo utilizar la misma lista pues son las mismas llaves
				for(int i = 1; i < 700 + 1; i++)
				{
					int j = (i > listaTemp.size())? 1 : i;
		
					String key= listaTemp.getElement(j);
		
					Stopwatch timer = new Stopwatch();
					SC.get(key);
					tiempoParaTimerTotalSC += timer.elapsedTime();
				}
		
				for(int i = 1; i < 300 + 1; i++)
				{
					String falseKey = "tangamandapia" + i;
		
					Stopwatch timer = new Stopwatch();
					SC.get(falseKey);
					tiempoParaTimerTotalSC += timer.elapsedTime();
				}
				
				
				
	}

}