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
			System.out.println("1. Cargaro modelo");
			System.out.println("2. Req1: Numero y videos de un pais y nombre categoria");
			System.out.println("3. Preueba de desempeño");
	
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g.," + " 1):");	
		}
		

		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}		
		
		
}
