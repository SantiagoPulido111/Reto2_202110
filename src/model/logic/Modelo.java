package model.logic;

import model.data_structures.ArregloDinamico;

import model.data_structures.IArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ILista<String> datos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ListaEncadenada<String>();
	}
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ListaEncadenada<String>();
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato, int i)
	{	
		
		//pruebas
		datos.addElement(dato,i);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return datos.getElement(datos.isPresent(dato));
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return datos.removeElement(datos.isPresent(dato));
	}
	
	
	//metodo para poder retornar los elementos del modelo enview
	public String darItem(int i)
	{
		return datos.getElement(i);
	}
	
	public void cambiar (int i, int j)
	{
		
		datos.exchange(i, i);
	}
	
	

}
