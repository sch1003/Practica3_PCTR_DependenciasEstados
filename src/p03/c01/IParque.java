// PAQUETE
package src.p03.c01;

/**
 * Interfaz que determina los métodos abstractos de entrada y salida de un
 * parque.
 * 
 * @author Alberto Alegre Madrid
 * @author Sergio Castañeira Hoyos
 */
public interface IParque {

	/**
	 * Método que implementa la entrada al parque. Comprueba que se pueda entrar
	 * antes de hacerlo, y en caso de no poder, se queda esperando a que otro hilo
	 * le notifique que ya puede realizar una entrada.
	 * 
	 * @param puerta Puerta por la que se entra.
	 */
	public abstract void entrarAlParque(String puerta);

	/**
	 * Método que implementa la salida del parque. Comprueba que se pueda salir
	 * antes de hacerlo, y en caso de no poder, se queda esperando a que otro hilo
	 * le notifique que ya puede realizar una salida.
	 * 
	 * @param puerta Puerta por la que se sale.
	 */
	public abstract void salirDelParque(String puerta);

}
