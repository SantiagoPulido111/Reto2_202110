package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico<String>(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.addLast(""+1);
		}
	}

	@Test
	public void testArregloDinamico() {
		
		assertTrue(arreglo!=null);
		assertEquals(0, arreglo.darTamano()); 
		
	}

	@Test
	public void testDarElemento() {
		
		
		
		setUp2();
		for(int i =0; i< TAMANO*2; i++)
		{
		assertEquals("no encontro el elemento", ""+i , arreglo.darElemento(i));
		
		
		}
	}

}
