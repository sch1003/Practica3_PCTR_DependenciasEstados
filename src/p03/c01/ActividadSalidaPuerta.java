// PAQUETE
package src.p03.c01;

// IMPORTACIONES DE CLASES
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la clase Runnable. Implementa un hilo que representa la
 * salida por una puerta de un parque pasados por parámetro en el constructor de
 * la clase.
 * 
 * @author Alberto Alegre Madrid
 * @author Sergio Castañeira Hoyos
 */
public class ActividadSalidaPuerta implements Runnable {

	private static final int NUMSALIDAS = 20; // Número máximo de salidas por una puerta.
	private String puerta; // Puerta por la que se realizan salidas.
	private IParque parque; // Parque.

	/**
	 * Constructor que inicializa los atributos de la clase.
	 * 
	 * @param puerta Puerta por la que se realizan salidas
	 * @param parque Parque
	 */
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i < NUMSALIDAS; i++) {
			try {
				parque.salirDelParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
