package model.logic;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;


import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
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

	private View vista = new View();

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo(String ruta, boolean listaEncadenada) throws IOException
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

	//TODO arreglar este metodo, no sirve desde el for
	public void cargarDatos(String ruta, boolean listaEncadenada) throws IOException
	{
		Stopwatch timer = new Stopwatch();
		Reader in = new FileReader(ruta);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);


		if(listaEncadenada)
		{
			datos= new ArregloDinamico<YoutubeVideo>(7);
		}
		else
		{
			datos= new ListaEncadenada<YoutubeVideo>();
		}


		for (CSVRecord record : records)
		{
			String trending_date=record.get("trending_date");
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
		vista.printMessage("Tiempo tomado: "+ time);
	}
	//TODO implementar el conteo del tiempo al cargar el modelo
}