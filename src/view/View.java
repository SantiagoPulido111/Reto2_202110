package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
//	    public View()
//	    {
//	    	
//	    }
	    
		public void printMenu()
		{
			System.out.println("0. Cargar videos");
			System.out.println("1.REQ1: n videos tendencia de una categoria-Pais");
			System.out.println("2.REQ2: video con mas dias de tendencia de un pais");
			System.out.println("3.REQ3: video con mas dias de tendencia para una categoria");
			//TODO falta req 4
			System.out.println("5. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g.," + " 1):");	
		}
		

		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}		
		
		
}
