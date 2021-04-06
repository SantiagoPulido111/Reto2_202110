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

	private TablaHashSeparateChaining<String,ArregloDinamico<YoutubeVideo>>  SC1;
	private TablaHashSeparateChaining<String,ArregloDinamico<YoutubeVideo>>  SC2;
	private TablaHashSeparateChaining<String,ArregloDinamico<YoutubeVideo>>  SC3;
	private TablaHashSeparateChaining<String,ArregloDinamico<YoutubeVideo>>  SC4;
	private ILista<CategoriaVideo> listaCategorias;
	private int numVideos;

	//Para revisar lo del tiempo
	private double tiempoParaTimerTotalLP=0;
	private double tiempoParaTimerTotalSC=0;

	private int revicionTuplasLP=0;

	private int revicionTuplasSC=0;
	boolean primerbug=false;


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

	public int darTamanoSC1()
	{
		return SC1.size();
	}
	public int darTamanoSC2()
	{
		return SC2.size();
	}
	public int darTamanoSC3()
	{
		return SC3.size();
	}
	public int darTamanoSC4()
	{
		return SC4.size();
	}


	public int darTuplasLP()
	{
		return revicionTuplasLP;
	}
	public int darTuplasSC()
	{
		return revicionTuplasSC;
	}




	public int darReHashesSC1()
	{
		return SC1.darNumReHashes();
	}


	public int darReHashesSC2()
	{
		return SC2.darNumReHashes();
	}


	public int darReHashesSC3()
	{
		return SC3.darNumReHashes();
	}

	public int darReHashesSC4()
	{
		return SC4.darNumReHashes();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(YoutubeVideo dato)
	{	




		String keyReq1 = llaveEnString_PaisCateg(dato.getCountry(),dato.getCategoria());
		String keyReq2 = llaveEnString_Pais(dato.getCountry());
		String keyReq3 = llaveEnString_Categ(dato.getCategoria());
		if(numVideos%20000==0) {
			vista.printMessage("Se han cargado "+ numVideos+ " videos");
		}
		String[] keyReq4 = ArregloDellaveEnString_tag(dato.getTags());
		numVideos++;



		//Requerimiento 1 LISTO
		ArregloDinamico<YoutubeVideo> temp;
		if(SC1.get(keyReq1)==null)
		{
			temp = new ArregloDinamico<YoutubeVideo>(1);


		}
		else
		{
			temp= SC1.remove(keyReq1);
		}

		temp.addLast(dato);

		//Ordenamiento<YoutubeVideo> ord = new Ordenamiento<YoutubeVideo>();
		//ord.ordenarInsercion(temp, new YoutubeVideo.ComparadorXViews(), true);

		SC1.put(keyReq1, temp);




		//Requerimiento 2 LISTO
		temp=null;
		if(SC2.get(keyReq2)==null)
		{
			temp = new ArregloDinamico<YoutubeVideo>(1);


		}
		else
		{
			temp= SC2.remove(keyReq2);
		}

		temp.addLast(dato);
		//ord.ordenarInsercion(temp, new YoutubeVideo.ComparadorXtitulo(), true);
		SC2.put(keyReq2, temp);






		//Requerimiento 3 LISTO
		temp=null;
		if(SC3.get(keyReq3)==null)
		{
			temp = new ArregloDinamico<YoutubeVideo>(1);


		}
		else
		{
			temp= SC3.remove(keyReq3);
		}

		temp.addLast(dato);
		//ord.ordenarInsercion(temp, new YoutubeVideo.ComparadorXtitulo(), true);
		SC3.put(keyReq3, temp);

	}
	//TODO req4



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

		int factordecarga=4;
		SC1 =new TablaHashSeparateChaining<>(50000, factordecarga);
		SC2 =new TablaHashSeparateChaining<>(50000, factordecarga);
		SC3 =new TablaHashSeparateChaining<>(50000, factordecarga);
		SC4 =new TablaHashSeparateChaining<>(50000, factordecarga);



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

			//aca esta toda la complejidad
			agregar(temp);


		}

		ILista<String> llaves1=SC1.keySet();

		ArregloDinamico<String> llaves2=(ArregloDinamico<String>) SC2.keySet();
		
		ArregloDinamico<String> llaves3=(ArregloDinamico<String>) SC3.keySet();
		Ordenamiento<YoutubeVideo> ord = new Ordenamiento<YoutubeVideo>();
		
		vista.printMessage(""+llaves1.size());
				
		for (int i = 1; i < llaves1.size()+1; i++) 
		{
			
			ArregloDinamico<YoutubeVideo> temporalorden= SC1.remove(llaves1.getElement(i));
			ord.ordenarMergeSort(temporalorden, new YoutubeVideo.ComparadorXViews(), false);
			SC1.put(llaves1.getElement(i), temporalorden);
		
		}
		
		for (int i = 1; i < llaves2.size()+1; i++) 
		{
			ArregloDinamico<YoutubeVideo> temporalorden= SC2.remove(llaves2.getElement(i));
			ord.ordenarMergeSort(temporalorden, new YoutubeVideo.ComparadorXtitulo(), true);
			SC2.put(llaves2.getElement(i), temporalorden);
		
		}
		
		
		for (int i = 1; i < llaves3.size()+1; i++) 
		{
			ArregloDinamico<YoutubeVideo> temporalorden= SC3.remove(llaves3.getElement(i));
			ord.ordenarMergeSort(temporalorden, new YoutubeVideo.ComparadorXtitulo(), true);
			SC3.put(llaves3.getElement(i), temporalorden);
		
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
		for (int i = 1; i < listaCategorias.size()+1 && !encontrado; i++) 
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


	public String llaveEnString_PaisCateg (String pais, String categoria )
	{
		return pais + "-" + categoria;
	}


	public String llaveEnString_Pais (String pais)
	{
		return pais;
	}

	public String llaveEnString_Categ (String categ)
	{
		return categ;
	}




	public String[] ArregloDellaveEnString_tag (String tags)
	{


		return tags.split("\\|");
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

	public ArregloDinamico<YoutubeVideo> req1(String pais,String categoria)
	{
		return SC1.get(llaveEnString_PaisCateg(pais, categoria));
	}





	public void req2(String pais)
	{
		ArregloDinamico<YoutubeVideo> temporal=SC2.get(llaveEnString_Pais(pais));

		Comparator<YoutubeVideo> comparador = new YoutubeVideo.ComparadorXtitulo();

		int numAparicionesMax = 1;
		YoutubeVideo masAparecio = temporal.getElement(1); 
		int numActual = 1; 

		//Complejidad O(n) nuevamente, el n es reducido

		for (int i = 2; i < temporal.size(); i++) 
		{ 
			if (comparador.compare(temporal.getElement(i),temporal.getElement(i-1)) == 0) 
				numActual++; 
			else 
			{ 
				if (numActual > numAparicionesMax) 
				{ 
					numAparicionesMax = numActual; 
					masAparecio = temporal.getElement(i-1);
				} 
				numActual = 1; 
			} 
		} 


		if (numActual > numAparicionesMax) 
		{ 
			numAparicionesMax = numActual;
			masAparecio = temporal.getElement(temporal.size() - 1);
		} 

		if(masAparecio != null)
		{
			System.out.println("------------------------");	

			System.out.println("Titulo: " + masAparecio.getTitle());	
			System.out.println("Canal: " + masAparecio.getChannel_title());
			System.out.println("Pais: " + masAparecio.getCountry());
			System.out.println("Numero dias de tendencia: " + numAparicionesMax);

			System.out.println("------------------------");
		}
		else System.out.println("No hay elmentos ");	
	}



	public void req3(String categ)
	{
		ArregloDinamico<YoutubeVideo> temporal=SC3.get(llaveEnString_Categ(categ));

		Comparator<YoutubeVideo> comparador = new YoutubeVideo.ComparadorXtitulo();

		int numAparicionesMax = 1;
		YoutubeVideo masAparecio = temporal.getElement(1); 
		int numActual = 1; 

		//Complejidad O(n) nuevamente, el n es reducido

		for (int i = 2; i < temporal.size(); i++) 
		{ 
			if (comparador.compare(temporal.getElement(i),temporal.getElement(i-1)) == 0) 
				numActual++; 
			else 
			{ 
				if (numActual > numAparicionesMax) 
				{ 
					numAparicionesMax = numActual; 
					masAparecio = temporal.getElement(i-1);
				} 
				numActual = 1; 
			} 
		} 


		if (numActual > numAparicionesMax) 
		{ 
			numAparicionesMax = numActual;
			masAparecio = temporal.getElement(temporal.size() - 1);
		} 

		if(masAparecio != null)
		{
			System.out.println("------------------------");	

			System.out.println("Titulo: " + masAparecio.getTitle());	
			System.out.println("Canal: " + masAparecio.getChannel_title());
			System.out.println("Pais: " + masAparecio.getCountry());
			System.out.println("Numero dias de tendencia: " + numAparicionesMax);

			System.out.println("------------------------");
		}
		else System.out.println("No hay elmentos ");	
	}











}