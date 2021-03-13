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

				view.printMessage("======================");
				view.printMessage("Informacion Categorias");
				ILista<CategoriaVideo> categorias = modelo.getCategorias();
				for (int i = 1; i <categorias.size(); i++) 
				{
					view.printMessage("----------\nID:" + categorias.getElement(i).getCategory_id() + "\nNombre:" + categorias.getElement(i).getCategory_name());
				}

				view.printMessage("=====================");
				break;

			case 2: 
				//TODO opcion 2 \

				view.printMessage("--------------\n Ingrese el pais: ");
				String pais2= lector.next();
				pais2+=lector.nextLine();


				view.printMessage("--------------------\n Ingrese la categoria: ");
				String categoria2_s= lector.next();
				categoria2_s+=lector.nextLine();
				categoria2_s = categoria2_s.replaceAll("\\s+","");

				String llave = modelo.llaveEnString(pais2, categoria2_s);
				ILista<YoutubeVideo>lista2 =modelo.buscar(llave);
				if(lista2!=null)
				{
					view.printMessage("\n=======================" + "");
					view.printMessage("Numero de videos con este pais y categoria: "+ lista2.size());
					for (int i = 1; i < lista2.size()+1; i++) 
					{
						YoutubeVideo actual = lista2.getElement(i);

						view.printMessage( "------------------------");	

						view.printMessage( "Titulo: " + actual.getTitle());	

						view.printMessage( "Views: " + actual.getViews());
						view.printMessage( "Likes: " + actual.getLikes());
						view.printMessage( "Dislikes: " + actual.getDislikes());



					}
				}
				else view.printMessage("No se encontraron videos para su solicitud");




				break;
			case 3:
				//TODO opcion 3

				modelo.pruabaDeDesemprno();
				view.printMessage("Tiempo promedio para ejecutar el metodo put en milisegundos " + (modelo.getTiempoPutTotal()/modelo.getNumVideos())+ "\n=======================" + "");
				break;

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