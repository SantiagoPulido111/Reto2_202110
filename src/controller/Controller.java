package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ILista;
import model.logic.CategoriaVideo;
import model.logic.Modelo;
import model.logic.YoutubeVideo;
import view.View;

public class Controller 
{
	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	private final static String SMALL = "data/videos-small.csv";
	private final static String ID = "data/category-id.csv";
	private final static String ALL = "data/videos-all.csv";
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
	}

	public void run() throws ParseException 
	{
Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin )
		{
			view.printMenu();

			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				
				
				view.printMessage("--------- \nCrear Arreglo \nDigite 1, para videos-small, y 2 videos-All ");
				int rutai = lector.nextInt();

				String ruta = (rutai == 1)? SMALL:ALL;
				try 
				{
					modelo = new Modelo(ruta);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					view.printMessage("Error al cargar");
				} 

				view.printMessage("\n=======================" + "");

				
				view.printMessage("Numero actual de videos: " + modelo.getNumVideos() + "\n=======================" + "");

				
				view.printMessage("Numero actual de duplas <K,V>: " + modelo.darTamano() + "\n=======================" + "");

				
				view.printMessage("Tiempo promedio para ejecutar el metodo put en milisegundos " + (modelo.getTiempoPutTotal()/modelo.getNumVideos())+ "\n=======================" + "");


				break;

			case 2: 
				//TODO opcion 2 
				break;
			case 3:
				//TODO opcion 3
		
			case 4: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	


			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}