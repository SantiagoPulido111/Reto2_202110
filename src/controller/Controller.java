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
				int esLista = 1; //lector.nextInt();
				boolean esListab = esLista == 2;
				view.printMessage("--------- \nCrear Arreglo \nDigite 1, para videos-small, y 2 videos-All ");
				int rutai = lector.nextInt();

				String ruta = (rutai == 1)? SMALL:ALL;
				try 
				{
					modelo = new Modelo(ruta,esListab);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					view.printMessage("Error al cargar");
				} 

				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n=======================" + "");


				//Mostrar la siguiente informaci�n del primer video cargado (title, cannel_title, trending_date,
				//	country, views, likes, dislikes).

				YoutubeVideo primero = modelo.darItem(1);

				if(primero != null)
				{
					view.printMessage("Informacion Primer video cargado");
					view.printMessage("------\nTitulo: " + primero.getTitle() + "\nCanal: " + primero.getChannel_title() + "\nTrending Date: " + primero.getTrending_date() + "\nCountry : " + primero.getCountry() + "\nViews: " + primero.getViews()+"\nlikes: " + primero.getLikes() + "\nDislikes: " + primero.getDislikes());		
				}

				view.printMessage("======================");
				view.printMessage("Informacion Categorias");
				ILista<CategoriaVideo> categorias = modelo.getCategorias();
				for (int i = 1; i <categorias.size(); i++) 
				{
					view.printMessage("----------\nID:" + categorias.getElement(i).getCategory_id() + "\nNombre:" + categorias.getElement(i).getCategory_name());
				}

				view.printMessage("=====================");
				//La lista de las categor�as cargadas mostrando su id y nombre.
				
				break;

			case 2:
				if(modelo==null) 
				{view.printMessage("-------- Primero carge el modelo -----------") ;
					break;}
				view.printMessage("--------- \nCrear Arreglo \nEscriba el tama�o de la sublista");

				int tamano = lector.nextInt();
		
		modelo.sublista(tamano);

				view.printMessage("Numero actual de elementos sublista " + modelo.getSublista().size()+ "\n---------");

				break;

			case 3:
				if(modelo==null) 
				{view.printMessage("-------- Primero carge el modelo -----------") ;
					break;}
				if(modelo.getSublista()!=null)
				{
					view.printMessage("--------- \nOrdenar Arreglo \nOpci�n : \n1 para Insertion \n2 para Shell \n3 para Merge \n4 para Quicksort");
					
					int opcion = lector.nextInt();
					
					view.printMessage("--------- \n ordenar por fecha(1) o por likes(2) ");
					int tipoOrd = lector.nextInt();
					boolean tipoOrdb=tipoOrd==1;
					
					view.printMessage("--------- \n ordenar por ascendente(1) o por descendente(2) ");
					int ascen = lector.nextInt();
					boolean ascenb=ascen==1;
					modelo.sortSublista(opcion,tipoOrdb,ascenb);


					if(modelo.getSublista().size() < 20)
					{
						view.printMessage("La sublista es de " + modelo.getSublista().size() + " elementos.");
						for (int i = 1; i < modelo.getSublista().size()+1; i++) 
						{
							view.printMessage("------\nTitulo: " + modelo.getSublista().getElement(i).getTitle() + "\nTrending Date: " + modelo.getSublista().getElement(i).getTrending_date()+"\nlikes: " +modelo.getSublista().getElement(i).getLikes());
						}
						view.printMessage("----------------------------");
				
					}	
					else
					{
						view.printMessage("Primero 10 elementos");
						for (int i = 1; i < 11; i++) 
						{
							view.printMessage("------\nTitulo: " + modelo.getSublista().getElement(i).getTitle() + "\nTrending Date: " + modelo.getSublista().getElement(i).getTrending_date()+"\nlikes: " +modelo.getSublista().getElement(i).getLikes());		
						}
						view.printMessage("Ultimos 10 elementos");

						for (int i = modelo.getSublista().size() - 8; i < modelo.getSublista().size()+1; i++) 
						{
							view.printMessage("------\nTitulo: " + modelo.getSublista().getElement(i).getTitle() + "\nTrending Date: " + modelo.getSublista().getElement(i).getTrending_date()+"\nlikes: " +modelo.getSublista().getElement(i).getLikes());
						}
						view.printMessage("----------------------------");
					}
				}
				else view.printMessage("--------\n sublista vacia \n --------");
				break;

			case 4:
				if(modelo==null) 
				{view.printMessage("-------- Primero carge el modelo -----------") ;
					break;}
				if(modelo.getSublista()!=null)
				{
					view.printMessage("La sublista es de " + modelo.getSublista().size() + " elementos.");
					for (int i = 1; i < modelo.getSublista().size()+1; i++) 
					{
						view.printMessage((i) +". ------\nTitulo: " + modelo.getSublista().getElement(i).getTitle() + "\nTrending Date: " + modelo.getSublista().getElement(i).getTrending_date()+"\nlikes: " +modelo.getSublista().getElement(i).getLikes());
					}
					view.printMessage("----------------------------");
				}
				else view.printMessage("--------\n sublista vacia \n--------");
				break;

			case 5: 
				break;

			case 6: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			case 7: 
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}