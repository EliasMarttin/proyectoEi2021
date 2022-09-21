package viajes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;


public class ViajesLocal {


	/**
	 * Muestra el menu de opciones y lee repetidamente de teclado hasta obtener una opcion válida
	 * @param teclado	stream para leer la opción elegida de teclado
	 * @return			opción elegida
	 */
	public static int menu(Scanner teclado) {
		int opcion;
		System.out.println("\n\n");
		System.out.println("=====================================================");
		System.out.println("============            MENU        =================");
		System.out.println("=====================================================");
		System.out.println("0. Salir");
		System.out.println("1. Consultar viajes con un origen dado");
		System.out.println("2. Reservar un viaje");
		System.out.println("3. Anular una reserva");
		System.out.println("4. Ofertar un viaje");
		System.out.println("5. Borrar un viaje");
		do {
			System.out.print("\nElige una opcion (0..5): ");
			opcion = teclado.nextInt();
		} while ( (opcion<0) || (opcion>5) );
		teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
		return opcion;
	}


	/**
	 * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
	 * 
	 * @param args	no se usan argumentos de entrada al programa principal
	 */
	public static void main(String[] args)  {

		Scanner teclado = new Scanner(System.in);

		// Crea un gestor de viajes
		GestorViajes gestor = new GestorViajes();

		System.out.print("Introduce tu codigo de cliente: ");
		String codcli = teclado.nextLine();

		int opcion; 
		do {
			opcion = menu(teclado);
			switch (opcion) {
			case 0: // Guardar los datos en el fichero y salir del programa
				gestor.guardaDatos();
				System.exit(1);

				// TODO

				break;

			case 1: { // Consultar viajes con un origen dado

				//TODO
				System.out.println("Introduce el origen");
				String origen = teclado.nextLine();
				//Esto es para que el origen que metan siempre empiece en mayúsculas, pero no lo vamos a poner porque no funciona otras partes del código
				/*origen.toLowerCase();
				origen = origen.substring(0, 1).toUpperCase() + origen.substring(1);*/
				JSONArray viajesOfertados = gestor.consultaViajes(origen);
				if(viajesOfertados.size()==0){
					System.out.println("No se ha encontrado viajes desde este origen");
				}else {
					System.out.println("Viajes disponibles");
					System.out.println(viajesOfertados.toJSONString());
				}
				break;
			}

			case 2: { // Reservar un viaje
				//TODO
				System.out.println("Introduce el código del pasajero .");
				System.out.println("Por favor introduce el código del viaje que quieres hacer la reserva ");
				String codViajeReserva = teclado.nextLine();
				JSONObject viaje = gestor.reservaViaje(codViajeReserva,codcli);
				if(viaje.size()==0){
					System.out.println("error al reservar");
				}else {
					System.out.println("Datos del viaje "+viaje.toJSONString());
				}
				break;
			}

			case 3: { // Anular una reserva
				// TODO
				System.out.println("Introduce el código del viaje que quieres anular la reserva.");
				String codViajeAnular = teclado.nextLine();
				JSONObject viaje = gestor.anulaReserva(codViajeAnular,codcli);
				//hay que tocar que esto si se mete un código erróneo que no funcione
				System.out.println("Datos del viaje "+viaje.toJSONString());
				break;
			}

			case 4: { // Ofertar un viaje

				// TODO
				System.out.println("Por favor rellene los datos que se le pide para reservar un viaje. ");
				System.out.println("Introduce el Origen");
				String origenOferta = teclado.nextLine();
				System.out.println("Introduce el destino ");
				String destinoOferta = teclado.nextLine();
				System.out.println("Introduce la fecha con formato dd-MM-yyyy");
				String fechaOferta = teclado.nextLine();
				System.out.println("Introduce el Precio en €");
				long precioOferta = teclado.nextLong();
				System.out.println("Introduce el número de plazas");
				long plazasOferta = teclado.nextLong();
				JSONObject viajeOfertado = gestor.ofertaViaje(codcli, origenOferta, destinoOferta, fechaOferta, precioOferta, plazasOferta);
				System.out.println("Datos del viaje Ofertado " +viajeOfertado.toJSONString());
				break;
			}

			case 5: { // Borrar un viaje ofertado
				// TODO
				System.out.println("Introduce el código del viaje que quieres borrar .");
				String codViajeBorrar = teclado.nextLine();
				JSONObject viaje = gestor.borraViaje(codViajeBorrar,codcli);
				if(viaje.size()==0){
					System.out.println("Hubo un error y no se ha borrado nada");
				}else {
					System.out.println("Datos del viaje borrado" + viaje.toJSONString());
				}
				break;
			}

			} // fin switch

		} while (opcion != 0);

	} // fin de main

} // fin class
