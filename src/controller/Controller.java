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
			case 1:
				//TODO falta 

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


				view.printMessage("Numero actual de duplas <K,V> para Limear Probing " + modelo.darTuplasLP()+ "\n=======================" + "");
				view.printMessage("Numero actual de duplas <K,V> para Separate Chain " + modelo.darTuplasSC()+ "\n=======================" + "");


				view.printMessage("Tiempo promedio para ejecutar el metodo put en milisegundos para Linear Probing: " + (modelo.getTiempoPutTotalLP()/modelo.getNumVideos())+ "\n=======================" + "");
				view.printMessage("Tiempo promedio para ejecutar el metodo put en milisegundos para Separate Chain: " + (modelo.getTiempoPutTotalSC()/modelo.getNumVideos())+ "\n=======================" + "");



				view.printMessage("===================Tabla solicitada===============");

				view.printMessage("===================Linear Probing ===============");
				view.printMessage("Numero Tuplas: "+ modelo.darTuplasLP());
				view.printMessage("Tamano inicial: "+ 53);
				view.printMessage("Tamano final: "+ modelo.darTamanoLP());
				view.printMessage("Factor carga porcentual: "+ modelo.darTuplasLP()*100/modelo.darTamanoLP()+"%");
				view.printMessage("Numero de rehashes: "+ modelo.darReHashesLP());


				view.printMessage("===================Separate Chaining  ===============");
				view.printMessage("Numero Tuplas: "+ modelo.darTuplasSC());
				view.printMessage("Tamano inicial: "+ 53);
				view.printMessage("Tamano final: "+ modelo.darTamanoSC());
				view.printMessage("Factor carga porcentual : "+ modelo.darTuplasSC()*100/modelo.darTamanoSC()+"%");
				view.printMessage("Numero de rehashes: "+ modelo.darReHashesSC());














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

				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}


				view.printMessage("Dar pais");
				String pais2= lector.next();
				pais2+=lector.nextLine();


				view.printMessage("Dar categoria");
				String categ2= lector.next();
				categ2+=lector.nextLine();

				ArregloDinamico<YoutubeVideo> listvid =modelo.darVideosPaisCtegLP(pais2, categ2);
				if(listvid!=null) {
					view.printMessage("El numero total de videos es: "+ listvid.size() );	
					view.printMessage("Los titulos son los sigueintes:");	
					for(int i=1; i<listvid.size()+1;i++)
					{
						YoutubeVideo temp= listvid.getElement(i);
						view.printMessage("============== \n"+i+".) "+ temp.getTitle());
						view.printMessage("Views: "+ temp.getViews());
						view.printMessage("likes: "+ temp.getLikes());
						view.printMessage("Dislikes: "+ temp.getDislikes());
					}
				}
				else view.printMessage("No hay videos con ese criterio");
				break;
			case 3:
				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}




				view.printMessage("Dar pais");
				String pais3= lector.next();
				pais3+=lector.nextLine();


				view.printMessage("Dar categoria");
				String categ3= lector.next();
				categ3+=lector.nextLine();

				ArregloDinamico<YoutubeVideo> listvid3 =modelo.darVideosPaisCtegSC(pais3, categ3);

				if(listvid3!=null) {
					view.printMessage("El numero total de videos es: "+ listvid3.size() );	
					view.printMessage("Los titulos son los sigueintes:");	
					for(int i=1; i<listvid3.size()+1;i++)
					{
						YoutubeVideo temp= listvid3.getElement(i);
						view.printMessage("============== \n"+i+".) "+ temp.getTitle());
						view.printMessage("Views: "+ temp.getViews());
						view.printMessage("likes: "+ temp.getLikes());
						view.printMessage("Disikes: "+ temp.getDislikes());

					}
				}
				else view.printMessage("No hay videos con ese criterio");


				break;
				
			case 4:
				if(modelo == null) 
				{
					view.printMessage("-------- Primero carge el modelo -----------") ;
					break;
				}

				
				modelo.pruabaDeDesemprno();
				view.printMessage("Tiempo promedio para ejecutar el metodo get en milisegundos para Linear Probing: " + (modelo.getTiempoPutTotalLP()/1000)+ "\n=======================" + "");
				view.printMessage("Tiempo promedio para ejecutar el metodo get en milisegundos para Separate Chain: " + (modelo.getTiempoPutTotalSC()/1000)+ "\n=======================" + "");

				
				
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