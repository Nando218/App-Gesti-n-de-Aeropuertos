package Repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Aeropuerto;
import Clases.MainGUI;
import Clases.Pasajero;
import Clases.Vuelo;

public class Repositorios {
	// ---- BBDD
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "gestion_aeropuerto";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	// ---- MÉTODO CREAR PASAJEROS
	public static void crearPasajero(Pasajero pasajero) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlInsert = "INSERT INTO pasajero (nombre, apellido, dni, vuelo_id) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
		preparedStatement.setString(1, pasajero.getNombre());
		preparedStatement.setString(2, pasajero.getApellido());
		preparedStatement.setString(3, pasajero.getDni());
		preparedStatement.setLong(4, pasajero.getVuelo_id());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	// ---- MÉTODO PARA CONSULTAR VUELOS Y CREAR PASAJEROS
	public static void consultarVueloYCrearPasajero(JTextField txtOrigenVuelo, JTextField txtDestinoVuelo,
			JTextField txtFechaSalida, JTextField txtFechaLlegada, JTextField txtHoraSalida, JTextField txtHoraLlegada,
			JTextField txtNombre, JTextField txtApellido, JTextField txtDNI, JPanel panel) {
		long idVuelo = 0;

		String consultaVuelo = "SELECT vuelo.id FROM vuelo INNER JOIN aeropuerto origen ON vuelo.origen = origen.id INNER JOIN aeropuerto destino ON vuelo.destino = destino.id WHERE "
				+ "origen.ciudad = ? AND destino.ciudad = ? AND vuelo.fecha_salida = ? AND vuelo.fecha_llegada = ? AND vuelo.hora_salida = ? AND vuelo.hora_llegada = ?";

		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(consultaVuelo)) {

			// Configuración de parámetros de la consulta
			preparedStatement.setString(1, txtOrigenVuelo.getText());
			preparedStatement.setString(2, txtDestinoVuelo.getText());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date parsedDateSalida = dateFormat.parse(txtFechaSalida.getText());
			java.sql.Date sqlDateSalida = new java.sql.Date(parsedDateSalida.getTime());
			java.util.Date parsedDateLlegada = dateFormat.parse(txtFechaLlegada.getText());
			java.sql.Date sqlDateLlegada = new java.sql.Date(parsedDateLlegada.getTime());

			preparedStatement.setDate(3, sqlDateSalida);
			preparedStatement.setDate(4, sqlDateLlegada);
			preparedStatement.setTime(5, java.sql.Time.valueOf(txtHoraSalida.getText()));
			preparedStatement.setTime(6, java.sql.Time.valueOf(txtHoraLlegada.getText()));

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					idVuelo = resultSet.getLong("vuelo.id");

					Pasajero pasajero = new Pasajero(txtNombre.getText(), txtApellido.getText(), txtDNI.getText(),
							idVuelo);

					Repositorios.crearPasajero(pasajero);
					JOptionPane.showMessageDialog(panel, "Pasajero añadido exitosamente");
				} else {
					JOptionPane.showMessageDialog(panel, "No se puede crear pasajero. Vuelo no encontrado.");
				}
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(panel, "No se ha podido añadir pasajero. (Error de base de datos)");
			e1.printStackTrace();
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(panel, "No se ha podido añadir pasajero. (Formato de fecha incorrecto)");
			e1.printStackTrace();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(panel, "No se ha podido añadir pasajero. (Error desconocido)");
			e1.printStackTrace();
		}

	}

	// ---- MÉTODO CREAR AEROPUERTOS
	public static void crearAeropuerto(Aeropuerto aeropuerto) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlInsert = "INSERT INTO Aeropuerto (nombre, ciudad, pais) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
		preparedStatement.setString(1, aeropuerto.getNombre());
		preparedStatement.setString(2, aeropuerto.getCiudad());
		preparedStatement.setString(3, aeropuerto.getPais());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	// ---- MÉTODO MODIFICAR AEROPUERTO POR NOMBRE

	public static void modificarAeropuertoPorNombre(String nombreAeropuerto, String nuevoNombre, String nuevaCiudad,
			String nuevoPais) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
			String sqlUpdate = "UPDATE Aeropuerto SET nombre = ?, ciudad = ?, pais = ? WHERE nombre = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
				preparedStatement.setString(1, nuevoNombre);
				preparedStatement.setString(2, nuevaCiudad);
				preparedStatement.setString(3, nuevoPais);
				preparedStatement.setString(4, nombreAeropuerto);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha modificado el aeropuerto correctamente.");
				} else {
					System.out.println("No se encontró ningún aeropuerto con ese nombre.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar modificar el aeropuerto.");
			e.printStackTrace();
		}
	}

	// ---- MÉTODO ELIMINAR AEROPUERTO POR NOMBRE
	public static void eliminarAeropuertoPorNombre(String nombreAeropuerto) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
			String sqlDelete = "DELETE FROM Aeropuerto WHERE nombre = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
				preparedStatement.setString(1, nombreAeropuerto);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha eliminado el aeropuerto correctamente.");
				} else {
					System.out.println("No se encontró ningún aeropuerto con ese nombre.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar eliminar el aeropuerto.");
			e.printStackTrace();
		}
	}

	// ---- LISTA LEER AEROPUERTOS
	public static List<Aeropuerto> leerAeropuertos() throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlSelect = "SELECT * FROM Aeropuerto";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<Aeropuerto> aeropuertos = new ArrayList<>();
		while (resultSet.next()) {
			Aeropuerto aeropuerto = new Aeropuerto(resultSet.getLong("id"), resultSet.getString("ciudad"),
					resultSet.getString("pais"), resultSet.getString("nombre"));
			aeropuertos.add(aeropuerto);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return aeropuertos;
	}

	public static List<Aeropuerto> leerAeropuertos(String nombre) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlSelect = "SELECT * FROM Aeropuerto WHERE nombre LIKE ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
		preparedStatement.setString(1, "%" + nombre + "%");
		ResultSet resultSet = preparedStatement.executeQuery();

		List<Aeropuerto> aeropuertos = new ArrayList<>();
		while (resultSet.next()) {
			Aeropuerto aeropuerto = new Aeropuerto(resultSet.getString("nombre"), resultSet.getString("ciudad"),
					resultSet.getString("pais"));
			aeropuertos.add(aeropuerto);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return aeropuertos;
	}

	// ---- CONSULTAR AEROPUERTO Y CREAR VUELO
	public static void consultarAeropuertoYCrearVuelo(JTextField txtOrigen, JTextField txtDestino,
			JTextField txtAerolinea, Date fechaSalida, Date fechaLlegada, JTextField txtHoraSalida,
			JTextField txtHoraLlegada, JPanel panel) {
		long idOrigen = 0;
		long idDestino = 0;
		try {
			String consultaCiudad = "SELECT ID FROM aeropuerto WHERE ciudad = ?";

			try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
					PreparedStatement preparedStatement = connection.prepareStatement(consultaCiudad)) {

				preparedStatement.setString(1, txtOrigen.getText());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						idOrigen = resultSet.getLong("ID");
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
					PreparedStatement preparedStatement = connection.prepareStatement(consultaCiudad)) {

				preparedStatement.setString(1, txtDestino.getText());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						idDestino = resultSet.getLong("ID");
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			long origen = idOrigen;
			long destino = idDestino;
			String aerolinea = txtAerolinea.getText();
			java.sql.Date sqlDateSalida = new java.sql.Date(fechaSalida.getTime());
			java.sql.Date sqlDateLlegada = new java.sql.Date(fechaLlegada.getTime());
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime horaSalida = LocalTime.parse(txtHoraSalida.getText(), dateFormatter);
			LocalTime horaLlegada = LocalTime.parse(txtHoraLlegada.getText(), dateFormatter);

			Vuelo vuelo = new Vuelo(origen, destino, aerolinea, sqlDateSalida, sqlDateLlegada, Time.valueOf(horaSalida),
					Time.valueOf(horaLlegada));
			Repositorios.crearVuelo(vuelo);

			// ---- EASTER EGG
			// Filtrar los parámetros concretos para mostrar la imagen
			if (txtOrigen.getText().equals("Boston") && txtDestino.getText().equals("Nueva York")) {
				ImageIcon icono = new ImageIcon(MainGUI.class.getResource("/Iconos/OBL.png"));
				JOptionPane.showMessageDialog(panel, "", "لا تحصل على هذه الرحلة", JOptionPane.INFORMATION_MESSAGE,
						icono);
			} else {
				JOptionPane.showMessageDialog(panel, "Vuelo creado exitosamente.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(panel, "Error al crear el vuelo");
		}
	}

	// ---- MÉTODO CREAR VUELO
	public static void crearVuelo(Vuelo vuelo) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlInsert = "INSERT INTO Vuelo (origen, destino, aerolinea, fecha_salida, fecha_llegada, hora_salida, hora_llegada) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
		preparedStatement.setLong(1, vuelo.getOrigen());
		preparedStatement.setLong(2, vuelo.getDestino());
		preparedStatement.setString(3, vuelo.getAerolinea());
		preparedStatement.setDate(4, vuelo.getFechaSalida());
		preparedStatement.setDate(5, vuelo.getFechaLlegada());
		preparedStatement.setObject(6, vuelo.getHora_salida());
		preparedStatement.setObject(7, vuelo.getHora_llegada());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	// ---- LISTA LEER VUELO
	public static List<Vuelo> leerVuelos(String aerolinea) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlSelect = "SELECT * FROM Vuelo WHERE aerolinea LIKE ?";
		String sqlOrigen = "SELECT * FROM aeropuerto WHERE id = ?";
		String sqlDestino = "SELECT * FROM aeropuerto WHERE id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

		preparedStatement.setString(1, "%" + aerolinea + "%");

		ResultSet resultSet = preparedStatement.executeQuery();

		List<Vuelo> vuelos = new ArrayList<>();
		while (resultSet.next()) {
			Vuelo vuelo = new Vuelo(resultSet.getLong("origen"), resultSet.getLong("destino"),
					resultSet.getString("aerolinea"), resultSet.getDate("fecha_salida"),
					resultSet.getDate("fecha_llegada"), resultSet.getTime("hora_salida"),
					resultSet.getTime("hora_llegada"));
			vuelos.add(vuelo);
			PreparedStatement preparedOrigen = connection.prepareStatement(sqlOrigen);
			PreparedStatement preparedDestino = connection.prepareStatement(sqlDestino);
			preparedOrigen.setString(1, vuelo.getId());
			ResultSet resultSet2 = preparedOrigen.executeQuery();
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return vuelos;
	}

	// ---- LISTA LEER VUELO
	public static List<Vuelo> leerVuelos() throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlSelect = "SELECT * FROM Vuelo";
		String sqlOrigen = "SELECT * FROM aeropuerto WHERE id = ?";
		String sqlDestino = "SELECT * FROM aeropuerto WHERE id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<Vuelo> vuelos = new ArrayList<>();
		while (resultSet.next()) {
			Vuelo vuelo = new Vuelo(resultSet.getLong("origen"), resultSet.getLong("destino"),
					resultSet.getString("aerolinea"), resultSet.getDate("fecha_salida"),
					resultSet.getDate("fecha_llegada"), resultSet.getTime("hora_salida"),
					resultSet.getTime("hora_llegada"));
			vuelos.add(vuelo);
			
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return vuelos;
	}

	public static void modificarVuelo(String nuevaAerolinea, Long nuevoOrigen, Long nuevoDestino, Long oldOrigen,
			Long oldDestino, String oldAerolina) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlUpdate = "UPDATE Vuelo SET origen = ?, destino = ?, aerolinea = ? WHERE origen = ? AND destino = ? AND aerolinea = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
		preparedStatement.setLong(1, nuevoOrigen);
		preparedStatement.setLong(2, nuevoDestino);
		preparedStatement.setString(3, nuevaAerolinea);
		preparedStatement.setLong(4, oldOrigen);
		preparedStatement.setLong(5, oldDestino);
		preparedStatement.setString(6, oldAerolina);
		int rowsAffected = preparedStatement.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Se ha modificado el vuelo correctamente.");
		} else {
			System.out.println("No se encontró ningún vuelo con ese nombre.");
		}

	}

	public static void eliminarVuelo(Long origen, Long destino, String aerolinea) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
			String sqlDelete = "DELETE FROM vuelo WHERE origen = ? AND destino = ? AND aerolinea = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
				preparedStatement.setLong(1, origen);
				preparedStatement.setLong(2, destino);
				preparedStatement.setString(3, aerolinea);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha eliminado el vuelo correctamente.");
				} else {
					System.out.println("No se encontró ningún vuelo con ese nombre.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar eliminar el vuelo.");
			e.printStackTrace();
		}
	}

	public static void modificarPasajeroPorNombre(String dni, String nuevoNombre, String nuevoApellido) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
			String sqlUpdate = "UPDATE pasajero SET nombre = ?, apellido = ? WHERE dni = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
				preparedStatement.setString(1, nuevoNombre);
				preparedStatement.setString(2, nuevoApellido);
				preparedStatement.setString(3, dni);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha modificado el pasajero correctamente.");
				} else {
					System.out.println("No se encontró ningún pasajero con ese nombre.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar modificar el vuelo.");
			e.printStackTrace();
		}
	}

	public static void eliminarPasajeroPorNombre(String dni) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
			String sqlDelete = "DELETE FROM pasajero WHERE dni = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
				preparedStatement.setString(1, dni);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha eliminado el pasajero correctamente.");
				} else {
					System.out.println("No se encontró ningún pasajero con ese dni.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar eliminar el pasajero.");
			e.printStackTrace();
		}
	}

	// ---- LISTA LEER PASAJEROS
	public static List<Pasajero> leerPasajeros(String pasajero) throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlSelect = "SELECT * FROM Pasajero WHERE dni LIKE ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

		preparedStatement.setString(1, "%" + pasajero + "%");

		ResultSet resultSet = preparedStatement.executeQuery();

		List<Pasajero> pasajeros = new ArrayList<>();
		while (resultSet.next()) {
			Pasajero pasajero1 = new Pasajero(resultSet.getLong("id"), resultSet.getString("nombre"),
					resultSet.getString("apellido"), resultSet.getString("dni"), resultSet.getLong("vuelo_id"));
			pasajeros.add(pasajero1);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return pasajeros;
	}

	public static List<Pasajero> leerPasajeros() throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		String sqlSelect = "SELECT * FROM Pasajero";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<Pasajero> pasajeros = new ArrayList<>();
		while (resultSet.next()) {
			Pasajero pasajero1 = new Pasajero(resultSet.getLong("id"), resultSet.getString("nombre"),
					resultSet.getString("apellido"), resultSet.getString("dni"), resultSet.getLong("vuelo_id"));
			pasajeros.add(pasajero1);
			
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return pasajeros;
	}

	// ---- MÉTODO CREAR BASE DE DATOS
	public static void crearBaseDeDatos() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
		statement.close();
		connection.close();
	}

	// ---- MÉTODO CREAR TABLAS
	public static void crearTablas() throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		Statement statement = connection.createStatement();

		// ---- TABLA AEROPUERTO
		String sqlCreateAeropuerto = "CREATE TABLE IF NOT EXISTS Aeropuerto (" + "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "nombre VARCHAR(100) NOT NULL," + "ciudad VARCHAR(100) NOT NULL," + "pais VARCHAR(100) NOT NULL"
				+ ")";
		statement.executeUpdate(sqlCreateAeropuerto);

		// ---- TABLA VUELO
		String sqlCreateVuelo = "CREATE TABLE IF NOT EXISTS Vuelo (" + "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "origen INT NOT NULL," + "destino INT NOT NULL," + "aerolinea VARCHAR(100) NOT NULL,"
				+ "fecha_salida DATE NOT NULL," + "fecha_llegada DATE NOT NULL," + "hora_salida TIME NOT NULL,"
				+ "hora_llegada time NOT NULL," + "FOREIGN KEY (origen) REFERENCES Aeropuerto(id) ON DELETE CASCADE,"
				+ "FOREIGN KEY (destino) REFERENCES Aeropuerto(id) ON DELETE CASCADE" + ")";
		statement.executeUpdate(sqlCreateVuelo);

		// ---- TABLA PASAJERO
		String sqlCreatePasajero = "CREATE TABLE IF NOT EXISTS Pasajero (" + "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "nombre VARCHAR(100) NOT NULL," + "apellido VARCHAR(100) NOT NULL," + "dni VARCHAR(100) NOT NULL,"
				+ "vuelo_id int NOT NULL," + "FOREIGN KEY (vuelo_id) REFERENCES Vuelo(id) ON DELETE CASCADE" +

				")";
		statement.executeUpdate(sqlCreatePasajero);

		statement.close();
		connection.close();
	}

	// ---- MÉTODO GENERAR CONTENIDO (genera datos en las tablas para operar con ellos.)

	public static void generarContenido() throws SQLException {
		Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
		Statement statement = connection.createStatement();

		String sqlInsertAeropuerto = "INSERT INTO Aeropuerto (nombre, ciudad, pais) VALUES "
				+ "('Aeropuerto Internacional Jorge Chávez', 'Lima', 'Perú'),"
				+ "('Aeropuerto Adolfo Suárez Madrid-Barajas', 'Madrid', 'España'),"
				+ "('Aeropuerto Internacional John F. Kennedy', 'Nueva York', 'Estados Unidos')";
		statement.executeUpdate(sqlInsertAeropuerto);

		String sqlInsertVuelo = "INSERT INTO Vuelo (origen, destino, aerolinea, fecha_salida, fecha_llegada, hora_salida, hora_llegada) VALUES "
				+ "(1, 2, 'LATAM', '2023-06-01', '2023-06-02', '10:00', '18:00' ),"
				+ "(2, 3, 'Iberia', '2023-07-01', '2023-07-02', '10:00', '18:00'),"
				+ "(3, 1, 'American Airlines', '2023-08-01', '2023-08-02', '10:00', '18:00')";
		statement.executeUpdate(sqlInsertVuelo);

		String sqlInsertPasajero = "INSERT INTO pasajero (nombre, apellido, dni, vuelo_id) VALUES "
				+ "('Pepe', 'Pérez', '12345678A',1)," + "('Fernando', 'Fernandez', '75489215B',1),"
				+ "('Ramón', 'Ramirez', '45789632C',1)";
		statement.executeUpdate(sqlInsertPasajero);

		statement.close();
		connection.close();
	}

}
