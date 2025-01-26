package Clases;

import javax.swing.*;

import Repositorios.Repositorios;
import Servicios.Servicios;

import java.sql.*;
import java.text.ParseException;

public class MainGUI {

    public static void main(String[] args) throws SQLException, ParseException {
//---- Métodos que generan contenido para operar con ellos.
//---- Se mantienen comentados para que no genere el mismo contenido una y otra vez cada vez que ejecuto la aplicación.

      // Crear la base de datos y las tablas
		// Repositorios.crearBaseDeDatos();
		// Repositorios.crearTablas();
		// Repositorios.generarContenido();

// ---- Crear la interfaz gráfica
	SwingUtilities.invokeLater(() -> {
	    try {
		createGUI();
	    } catch (SQLException e) {
		e.printStackTrace();
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	});
    }

// ---- Pantalla de Inicio
    private static void createGUI() throws SQLException, ParseException {
	JFrame frame = new JFrame("Gestión de Aeropuertos y Vuelos 1.1");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800, 600);

// ---- Panel principal con un diseño de pestañas
	JTabbedPane tabbedPane = new JTabbedPane();

// ---- Pestañas para las diferentes secciones
	tabbedPane.addTab("Inicio", Servicios.crearPanelInicio());
	tabbedPane.addTab("Crear Aeropuerto", Servicios.panelAeropuerto());
	tabbedPane.addTab("Gestionar Aeropuerto", Servicios.panelGestionAeropuerto());
	tabbedPane.addTab("Crear Vuelo", Servicios.panelCrearVuelo());
	tabbedPane.addTab("Gestionar Vuelo", Servicios.panelGestionarVuelo());
	tabbedPane.addTab("Crear Pasajero", Servicios.panelCrearPasajero());
	tabbedPane.addTab("Gestionar Pasajero", Servicios.panelGestionPasajero());

	frame.getContentPane().add(tabbedPane);
	frame.setVisible(true);
    }

}
