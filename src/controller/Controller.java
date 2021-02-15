package controller;

import java.io.IOException;
import java.util.Scanner;


import model.logic.Modelo;
import view.View;

public class Controller {

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
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nCrear Arreglo \nEscriba (1) para lista encadenada, (0) para arreglo dinamico ");
				    int esLista = lector.nextInt();
				    boolean esListab = esLista==1;
				    view.printMessage("--------- \nCrear Arreglo \nDigite 1, para videos-small, y 2 videos-small ");
				    int rutai = lector.nextInt();
				    
				    String ruta=(rutai==1)?SMALL:ALL;
				try {
					modelo = new Modelo(ruta,esListab);
				} catch (IOException e) {
					
					e.printStackTrace();
					view.printMessage("Error al cargar");
					
				} 
				    view.printMessage("Arreglo Dinamico creado");
				    view.printMessage("Titulo primer elemento: " +modelo.darItem(0).getTitle());
				    view.printMessage("Pais primer elemento: " +modelo.darItem(0).getCountry());
				    view.printMessage("Likes primer elemento: " +modelo.darItem(0).getLikes());
				    view.printMessage("Fecha de tendencia primer elemento: " +modelo.darItem(0).getTrending_date());
				    
				    int posFinal= modelo.darTamano()-1;
				   
				    view.printMessage("Arreglo Dinamico creado");
				    view.printMessage("Titulo primer elemento: " +modelo.darItem(posFinal).getTitle());
				    view.printMessage("Pais primer elemento: " +modelo.darItem(posFinal).getCountry());
				    view.printMessage("Likes primer elemento: " +modelo.darItem(posFinal).getLikes());
				    view.printMessage("Fecha de tendencia primer elemento: " +modelo.darItem(posFinal).getTrending_date());
				    view.printMessage("Total de videos: " + modelo.darTamano());
				    //TODO dar el tiempo que tarda
				    view.printMessage("Tiempo tardado en cargar");
				    view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");	
				    
				    
				    
				    
				    
				    
					break;

				case 2:
					break;
					//TEMPORALMENTE INUTIL
//					view.printMessage("--------- \nDar cadena (simple) a ingresar: ");
//					dato = lector.next();
//					view.printMessage("--------- \nDar pos? : ");
//					int c = lector.nextInt();
//					//modelo.agregar(dato,c);
//					view.printMessage("Dato agregado");
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
//					break;

				case 3:
					break;
					//TEMPORALMENTE INUTIL
					//view.printMessage("--------- \nDar cadena (simple) a buscar: ");
					//dato = lector.next();
					//respuesta = modelo.buscar(dato);
//					if ( respuesta != null)
//					{
//						view.printMessage("Dato encontrado: "+ respuesta);
//					}
//					else
//					{
//						view.printMessage("Dato NO encontrado");
//					}
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
//					break;

				case 4:
					break;
					//TEMPORALMENTE INUTIL
//					view.printMessage("--------- \nDar cadena (simple) a eliminar: ");
//					dato = lector.next();
//					//respuesta = modelo.eliminar(dato);
//					if ( respuesta != null)
//					{
//						view.printMessage("Dato eliminado "+ respuesta);
//					}
//					else
//					{
//						view.printMessage("Dato NO eliminado");							
//					}
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
//					break;

				case 5: 
					break;
					//TEMPORALMENTE INUTIL
//					view.printMessage("--------- \nContenido del Arreglo: ");
//					view.printModelo(modelo);
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
//					break;	
//					
				case 6: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	
					
				case 7: 
					break;
//					//TEMPORALMENTE INUTIL
//					view.printMessage("dar pos1"); 
//					int i = lector.nextInt();
//					view.printMessage("dar pos1"); 
//					int j = lector.nextInt();
//					
//					modelo.cambiar(i, j);
//					
//					break;
				

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}