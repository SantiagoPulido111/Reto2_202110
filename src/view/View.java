package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargaro modelo");
			//System.out.println("2. Agregar String");
			//System.out.println("3. Buscar String");
			//System.out.println("4. Eliminar String");
			//System.out.println("5. Imprimir el Arreglo");
			System.out.println("6. Exit");
			//System.out.println("7. Exchange");
			
			
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g.," + " 1):");	
		}
		

		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			//System.out.println(modelo);
			int max= modelo.darTamano();
			for (int i=0; i<max;i++)
			{
				System.out.println(modelo.darItem(i));
			}
		}
}
