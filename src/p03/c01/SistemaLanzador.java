// PAQUETE
package src.p03.c01;

/**
 * Clase que inicia la ejecución del programa. Crea un parque y dos hilos de
 * entrada y salida por cada puerta pasada por argumento por consola.
 * 
 * @author Alberto Alegre Madrid
 * @author Sergio Castañeira Hoyos
 */
public class SistemaLanzador {
	/**
	 * Método principal de la clase que se encarga de la ejecución.
	 * 
	 * @param args Argumentos escritos por consola
	 */
	public static void main(String[] args) {
		IParque parque = new Parque();
		char letra_puerta = 'A';

		System.out.println("¡Parque abierto!");

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {

			String puerta = "" + ((char) (letra_puerta++));

			// Creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread(entradas).start();

			// Creación de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread(salidas).start();
		}
	}
}