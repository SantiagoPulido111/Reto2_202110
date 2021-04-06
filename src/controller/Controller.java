package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
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
			case 0:
			
            //TODO Optimizar
				view.printMessage("--------- \nCrear Arreglo \nDigite 1, para videos-small, y 2 videos-All ");
				int rutai = lector.nextInt();

				String ruta = (rutai == 1)? SMALL:ALL;
				try 
				{
					modelo=null;
					modelo = new Modelo(ruta);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					view.printMessage("Error al cargar");
				} 

				view.printMessage("\n=======================" + "");


				view.printMessage("Numero actual de videos: " + modelo.getNumVideos() + "\n=======================" + "");

				view.printMessage("======================");
				
				view.printMessage("Informacion Categorias");
				ILista<CategoriaVideo> categorias = modelo.getCategorias();
				for (int i = 1; i <categorias.size()+1; i++) 
				{
					view.printMessage("----------\nID:" + categorias.getElement(i).getCategory_id() + "\nNombre:" + categorias.getElement(i).getCategory_name());
				}

				view.printMessage("=====================");
				view.printMessage("=====================\n" + "Caragado exitosamente"+"\n=====================");
						
				break;

			case 1: 

				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}


				view.printMessage("Dar tamaño de la lista: ");
				int n = lector.nextInt();


				view.printMessage("Dar pais : ");

				String pais = lector.next();
				pais+=lector.nextLine();
				//parce toco hacer esto porque next line como que salta todo


				view.printMessage("Dar nombre categoria: ");

				String categoria_s = lector.next();
				categoria_s += lector.nextLine();
				categoria_s = categoria_s.replaceAll("\\s+","");


				int categoria = modelo.darIDCategoria(categoria_s);


				ILista<YoutubeVideo>lista = modelo.req1(pais, categoria_s);


				//Est se hace porque usamos una lista ArregllDinamico y pues cuando se inicialzia se crean casillas null, antes de rellenarlas
				if(lista!=null&&!lista.isEmpty() && lista.getElement(1) != null)
				{
					boolean stop = false;
					for (int i = 1; i < n + 1 && !stop; i++) 
					{
						YoutubeVideo actual = lista.getElement(i);
						if (actual != null)
						{

							view.printMessage("------------------------");	
							view.printMessage("Titulo: " + actual.getTitle());	
							view.printMessage("Fecha tendencia: " + actual.getTrending_date());
							view.printMessage("Canal: " + actual.getChannel_title());
							view.printMessage("Fecha publicacion: " + actual.getPublish_time());
							view.printMessage("Views: " + actual.getViews());
							view.printMessage("Likes: " + actual.getLikes());
							view.printMessage("Dislikes: " + actual.getDislikes());
						}
						else
						{
							stop = true;
							view.printMessage("Solo hay " + (i-1) + " elementos de esa categoria y pais"); 
						}
					}
					view.printMessage("-------------------Este es el top -----------------"); 
				}
				else 
				{
					view.printMessage("--------------No hay elementos que coincidan con su busqueda-------------"); 
				}

				
				break;
			case 2:
				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}




				view.printMessage("Dar pais: ");
				String pais2 = lector.next();
				pais2 += lector.nextLine();

				modelo.req2(pais2);
				break;

			case 3:
				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}

				view.printMessage("Dar categoria: ");
				String categoria3_s = lector.next();

				categoria3_s += lector.nextLine();
				categoria3_s = categoria3_s.replaceAll("\\s+","");
				modelo.req3(categoria3_s);

				break;
			case 4:
				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}

				//TODO hacer 
				break;

			case 5: 
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