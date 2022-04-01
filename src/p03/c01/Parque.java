// PAQUETE
package src.p03.c01;

// IMPORTACIONES DE CLASES
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase que implementa a la interfaz IParque. Representa un parque por el que
 * entran y salen personas.
 * 
 * @author Alberto Alegre Madrid
 * @author Sergio Castañeira Hoyos
 */
public class Parque implements IParque {

	private int contadorPersonasTotales; // Número de personas totales en el parque.
	private Hashtable<String, Integer> contadoresPersonasPuerta; // Número de personas que han entrado y salido por cada
																	// puerta.

	/**
	 * Constructor de la clase parque que inicializa sus atributos.
	 */
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();

	}

	@Override
	public synchronized void entrarAlParque(String puerta) {
		// Si no hay entradas por esa puerta, inicializamos.
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Antes de entrar hay que comprobar que se pueda.
		comprobarAntesDeEntrar();

		// Aumentamos el contador total y el individual.
		contadorPersonasTotales++;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) + 1);

		// Imprimimos el estado del parque.
		imprimirInfo(puerta, "Entrada");
		// Notificamos al resto de hilos.
		notifyAll();
		// Comprobamos la invariante.
		checkInvariante();
	}

	@Override
	public synchronized void salirDelParque(String puerta) {
		// Antes de salir hay que comprobar que se pueda.
		comprobarAntesDeSalir();

		// Decrementamos el contador total y el individual.
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		// Imprimimos el estado del parque.
		imprimirInfo(puerta, "Salida");
		// Notificamos al resto de hilos.
		notifyAll();
		// Comprobamos la invariante.
		checkInvariante();
	}

	/**
	 * Método que muestra el movimiento de personas en el momento de entrar o salir
	 * por una puerta del parque.
	 * 
	 * @param puerta     Puerta por la que se entra o sale
	 * @param movimiento Entrada o salida
	 */
	private void imprimirInfo(String puerta, String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales);

		// Iteramos por todas las puertas e imprimimos sus entradas
		for (String p : contadoresPersonasPuerta.keySet()) {
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}

	/**
	 * Método que calcula la suma de todas las personas en el parque en el momento
	 * actual y lo devuelve.
	 * 
	 * @return número de personas actual en el parque
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
		Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();

		while (iterPuertas.hasMoreElements()) {
			sumaContadoresPuerta += iterPuertas.nextElement();
		}

		return sumaContadoresPuerta;
	}

	/**
	 * Método que comprueba la invariante. En caso de que la suma de todas las
	 * personas sea distinto al contador de personas, salta un mensaje indicando un
	 * error en la invariante.
	 */
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales
				: "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";

	}

	/**
	 * Método que comprueba que se pueda entrar al parque. En caso de no poder,
	 * espera a que otro hilo le notifique que ya puede.
	 */
	protected void comprobarAntesDeEntrar() {
		try {
			if (contadorPersonasTotales == 50) {
				wait();
			}
		} catch (InterruptedException exc) {
			System.out.println("Parque Completo.");
		}
	}

	/**
	 * Método que comprueba que se pueda salir del parque. En caso de no poder,
	 * espera a que otro hilo le notifique que ya puede.
	 */
	protected void comprobarAntesDeSalir() {
		try {
			while (contadorPersonasTotales <= 0) {
				wait();
			}
		} catch (InterruptedException exc) {
			System.out.println("Parque Vacio.");
		}
	}
}
