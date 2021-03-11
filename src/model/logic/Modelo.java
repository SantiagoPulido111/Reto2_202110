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
import model.data_structures.ListaEncadenada;
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
	private ILista<YoutubeVideo> datos;
	private ILista<YoutubeVideo> sublista;
	private ILista<CategoriaVideo> listaCategorias;
	
	private final static String ID = "data/category-id.csv";

	private View vista = new View();

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @throws ParseException 
	 */
	public Modelo(String ruta, boolean listaEncadenada) throws IOException, ParseException
	{
		cargarDatos(ruta, listaEncadenada);
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
	public void agregar(YoutubeVideo dato, int i)
	{	
		//pruebas
		datos.addElement(dato,i);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public YoutubeVideo buscar(YoutubeVideo dato)
	{
		return datos.getElement(datos.isPresent(dato));
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public YoutubeVideo eliminar(YoutubeVideo dato)
	{
		return datos.removeElement(datos.isPresent(dato));
	}


	//metodo para poder retornar los elementos del modelo enview
	public YoutubeVideo darItem(int i)
	{
		return datos.getElement(i);
	}

	public void cambiar (int i, int j)
	{
		datos.exchange(i, i);
	}
	
	public ILista<CategoriaVideo> getCategorias() 
	{
		return listaCategorias;
	}

	//TODO arreglar este metodo, no sirve desde el for
	
	public void cargarDatos(String ruta, boolean listaEncadenada) throws IOException, ParseException
	{
		Stopwatch timer = new Stopwatch();
		Reader in = new FileReader(ruta);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);


		if(listaEncadenada)
		{
			datos= new ListaEncadenada<YoutubeVideo>();
		}
		else
		{
			datos= new ArregloDinamico<YoutubeVideo>(1);
		}


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

			YoutubeVideo temp=new YoutubeVideo(trending_date, title, channel_title,views,likes,dislikes,thumbnail_link,country );

			datos.addLast(temp);
		}

		vista.printMessage("Arreglo creado"); 
		double time = timer.elapsedTime();
		vista.printMessage("Tiempo tomado (milisegundos): "+ time);
		
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
	}

	public void sublista(int i)
	{
		if(i>0)
			
		{
			i=i<datos.size()?i:datos.size();
			setSublista(datos.sublista(i));
		}

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
		vista.printMessage("Tiempo tomado (milisegundos): "+ time);
	}

}