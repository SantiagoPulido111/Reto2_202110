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
			System.out.println("1. Cargar videos");
			System.out.println("2. OPC2: Videos de un pais y nombre categoria, usando LP");
			System.out.println("3. OPC3: Videos de un pais y nombre categoria, usando SC");
			System.out.println("4. OPC4: prueba de desempeño");
			System.out.println("5. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g.," + " 1):");	
		}
		

		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}		
		
		
}
