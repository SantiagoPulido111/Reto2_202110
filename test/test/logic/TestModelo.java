package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
//	private Modelo modelo;
//	private static int CAPACIDAD=100;
//	
//	@Before
//	public void setUp1() {
//		modelo= new Modelo(CAPACIDAD);
//	}
//
//	public void setUp2() {
//		for(int i =0; i< CAPACIDAD;i++){
//			modelo.agregar(""+i);
//		}
//	}
//
//	@Test
//	public void testModelo() {
//		assertTrue(modelo!=null);
//		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
//	}
//
//	@Test
//	public void testDarTamano() {
//		
//		setUp2();
//		assertEquals("El tamano deberia ser " +CAPACIDAD,CAPACIDAD ,modelo.darTamano());
//	}
//
//	@Test
//	public void testAgregar() {
//		
//		setUp1();
//		String s="prueba";
//		modelo.agregar(s);
//		assertEquals("El tamano deberia ser 1",1 ,modelo.darTamano());
//		assertEquals("deberia estar el elemento",s ,modelo.buscar(s));
//		
//		setUp1();
//		for(int i=1;i<10;i++)
//		{
//		modelo.agregar(""+i);
//		assertEquals("El tamano es erroneo",i ,modelo.darTamano());
//		assertEquals("deberia estar el elemento",""+i ,modelo.buscar(""+i));
//		}
//		
//	}
//
//	@Test
//	public void testBuscar() {
//		setUp2();
//		
//		for(int i =0; i< CAPACIDAD;i++)
//		{
//			assertEquals("deberia estar el elemento",""+i ,modelo.buscar(""+i));
//		}
//		
//		 assertEquals("deberia retornar null",null ,modelo.buscar("prubasuper"));
//		
//	}
//
//	@Test
//	public void testEliminar() {
//		setUp2();
//		
//		for(int i =0; i< CAPACIDAD;i++)
//		{
//			assertEquals("deberia retornar el elemento",""+i ,modelo.eliminar(""+i));
//			assertEquals("deberia retornar null",null ,modelo.eliminar(""+i));
//			
//		}	
//		
//		
//	}

}
