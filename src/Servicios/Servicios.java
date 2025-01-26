package Servicios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;

import Clases.Aeropuerto;
import Clases.MainGUI;
import Clases.Pasajero;
import Clases.Vuelo;
import Repositorios.Repositorios;

public class Servicios {

	// ---- PANEL CREAR PASAJERO
	public static JPanel panelCrearPasajero() throws SQLException, ParseException {
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblNombre = new JLabel("Nombre :");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombre, gbc);
		JTextField txtNombre = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(txtNombre, gbc);

		JLabel lblApellido = new JLabel("Apellido :");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lblApellido, gbc);
		JTextField txtApellido = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(txtApellido, gbc);

		JLabel lblDNI = new JLabel("DNI:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblDNI, gbc);
		JTextField txtDNI = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(txtDNI, gbc);

		JLabel lblOrigenVuelo = new JLabel("Origen del vuelo: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblOrigenVuelo, gbc);
		JTextField txtOrigenVuelo = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(txtOrigenVuelo, gbc);

		JLabel lblDestinoVuelo = new JLabel("Destino del vuelo: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblDestinoVuelo, gbc);
		JTextField txtDestinoVuelo = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(txtDestinoVuelo, gbc);

		JLabel lblFechaSalida = new JLabel("Fecha de Salida: ");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblFechaSalida, gbc);
		JTextField txtFechaSalida = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(txtFechaSalida, gbc);

		JLabel lblFechaLlegada = new JLabel("Fecha de Llegada: ");
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(lblFechaLlegada, gbc);
		JTextField txtFechaLlegada = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel.add(txtFechaLlegada, gbc);

		JLabel lblHoraSalida = new JLabel("Hora Salida (HH:mm:ss):");
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(lblHoraSalida, gbc);
		JTextField txtHoraSalida = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(txtHoraSalida, gbc);

		JLabel lblHoraLlegada = new JLabel("Hora Llegada (HH:mm:ss):");
		gbc.gridx = 0;
		gbc.gridy = 8;
		panel.add(lblHoraLlegada, gbc);
		JTextField txtHoraLlegada = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 8;
		panel.add(txtHoraLlegada, gbc);

		JButton btnGuardar = new JButton("Guardar");
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		panel.add(btnGuardar, gbc);

		btnGuardar.addActionListener(e -> {
			Repositorios.consultarVueloYCrearPasajero(txtOrigenVuelo, txtDestinoVuelo, txtFechaSalida, txtFechaLlegada,
					txtHoraSalida, txtHoraLlegada, txtNombre, txtApellido, txtDNI, panel);
		});

		return panel;
	}

	// ---- CREAR AEROPUERTO
	public static void crearAeropuerto(JTextField txtNombre, JTextField txtCiudad, JTextField txtPais, JPanel panel) {

	}

	// ---- PANEL AEROPUERTO
	public static JPanel panelAeropuerto() {

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblNombre = new JLabel("Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombre, gbc);
		JTextField txtNombre = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(txtNombre, gbc);

		JLabel lblCiudad = new JLabel("Ciudad:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lblCiudad, gbc);
		JTextField txtCiudad = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(txtCiudad, gbc);

		JLabel lblPais = new JLabel("País:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblPais, gbc);
		JTextField txtPais = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(txtPais, gbc);

		JButton btnGuardar = new JButton("Guardar");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		panel.add(btnGuardar, gbc);

		btnGuardar.addActionListener(e -> {
			String nombre = txtNombre.getText();
			String ciudad = txtCiudad.getText();
			String pais = txtPais.getText();
			Aeropuerto aeropuerto = new Aeropuerto(nombre, ciudad, pais);
			try {
				Repositorios.crearAeropuerto(aeropuerto);
				JOptionPane.showMessageDialog(panel, "Aeropuerto creado exitosamente");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al crear el aeropuerto");
			}
		});

		return panel;

	}

	public static JPanel panelGestionAeropuerto() {
		JPanel panel = new JPanel(new GridBagLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// Campo de búsqueda
		JLabel lblNombreBuscar = new JLabel("Aeropuertos:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombreBuscar, gbc);

		JComboBox<String> comboNombreBuscar = new JComboBox<>();
		comboNombreBuscar.setEditable(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(comboNombreBuscar, gbc);

		JButton btnBuscar = new JButton("Listar");
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(btnBuscar, gbc);

		// Campos para modificar aeropuerto
		JLabel lblNombreModificar = new JLabel("Nombre a Modificar:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panel.add(lblNombreModificar, gbc);

		JTextField txtNombreModificar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(txtNombreModificar, gbc);

		JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblNuevoNombre, gbc);

		JTextField txtNuevoNombre = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(txtNuevoNombre, gbc);

		JLabel lblNuevaCiudad = new JLabel("Nueva Ciudad:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblNuevaCiudad, gbc);

		JTextField txtNuevaCiudad = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(txtNuevaCiudad, gbc);

		JLabel lblNuevoPais = new JLabel("Nuevo País:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblNuevoPais, gbc);

		JTextField txtNuevoPais = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(txtNuevoPais, gbc);

		JButton btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		panel.add(btnModificar, gbc);

		// Campo para eliminar aeropuerto
		JLabel lblNombreEliminar = new JLabel("Aeropuerto a Eliminar:");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		panel.add(lblNombreEliminar, gbc);

		JTextField txtNombreEliminar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(txtNombreEliminar, gbc);

		JButton btnEliminar = new JButton("Eliminar");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 3;
		panel.add(btnEliminar, gbc);

		// ActionListener para el botón de búsqueda
		btnBuscar.addActionListener(e -> {
			String nombre = (String) comboNombreBuscar.getSelectedItem();
			try {
				List<Aeropuerto> aeropuertos = Repositorios.leerAeropuertos(nombre);
				if (aeropuertos.isEmpty()) {
					aeropuertos = Repositorios.leerAeropuertos();
				}
				comboNombreBuscar.removeAllItems();
				for (Aeropuerto aeropuerto : aeropuertos) {
					comboNombreBuscar.addItem(
							aeropuerto.getNombre() + " - " + aeropuerto.getCiudad() + " - " + aeropuerto.getPais());
				}
				if (aeropuertos.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "No se encontraron aeropuertos con el nombre especificado.");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al buscar los aeropuertos");
			}
		});

		// ActionListener para cargar datos seleccionados en los campos

		comboNombreBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String) comboNombreBuscar.getSelectedItem();
				if (selectedValue != null && selectedValue != "") {
					String[] parts = selectedValue.split(" - ");
					String nombre = parts[0];
					String ciudad = parts[1];
					String pais = parts[2];

					txtNombreModificar.setText(nombre);
					txtNombreEliminar.setText(nombre);
					txtNuevoNombre.setText(nombre);
					txtNuevaCiudad.setText(ciudad);
					txtNuevoPais.setText(pais);
				}
			}
		});

		// KeyListener para actualizar el JComboBox mientras se escribe
		comboNombreBuscar.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				SwingUtilities.invokeLater(() -> {
					String text = (String) comboNombreBuscar.getEditor().getItem();
					if (text.length() >= 3) {
						try {
							List<Aeropuerto> aeropuertos = Repositorios.leerAeropuertos(text);
							comboNombreBuscar.removeAllItems();
							for (Aeropuerto aeropuerto : aeropuertos) {
								comboNombreBuscar.addItem(aeropuerto.getNombre());
							}
							comboNombreBuscar.getEditor().setItem(text);
							comboNombreBuscar.showPopup();
						} catch (SQLException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(panel, "Error al buscar los aeropuertos");
						}
					}
				});
			}
		});

		// ActionListener para el botón de eliminación
		btnEliminar.addActionListener(e -> {
			String nombre = txtNombreEliminar.getText();
			Repositorios.eliminarAeropuertoPorNombre(nombre);
			JOptionPane.showMessageDialog(panel, "Aeropuerto eliminado exitosamente.");

			txtNombreEliminar.setText("");
			txtNombreModificar.setText("");
			txtNuevoNombre.setText("");
			txtNuevaCiudad.setText("");
			txtNuevoPais.setText("");
		});

		// ActionListener para el botón de modificación
		btnModificar.addActionListener(e -> {
			String nombre = txtNombreModificar.getText();
			String nuevoNombre = txtNuevoNombre.getText();
			String nuevaCiudad = txtNuevaCiudad.getText();
			String nuevoPais = txtNuevoPais.getText();
			Repositorios.modificarAeropuertoPorNombre(nombre, nuevoNombre, nuevaCiudad, nuevoPais);
			JOptionPane.showMessageDialog(panel, "Aeropuerto modificado exitosamente.");
			comboNombreBuscar.setSelectedItem(""); // Limpiar el campo de búsqueda
			txtNombreEliminar.setText(""); // Limpiar el campo de eliminación
			txtNombreModificar.setText(""); // Limpiar el campo de modificación
			txtNuevoNombre.setText(""); // Limpiar el campo de nuevo nombre
			txtNuevaCiudad.setText(""); // Limpiar el campo de nueva ciudad
			txtNuevoPais.setText(""); // Limpiar el campo de nuevo país
		});

		return panel;
	}

	// Método principal para ejecutar la GUI
	public static void main(String[] args) {
		JFrame frame = new JFrame("Gestión de Aeropuertos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.add(panelGestionAeropuerto());
		frame.setVisible(true);
	}

	// ---- PANEL CREAR VUELO
	public static JPanel panelCrearVuelo() {
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblOrigen = new JLabel("Origen :");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblOrigen, gbc);
		JTextField txtOrigen = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(txtOrigen, gbc);

		JLabel lblDestino = new JLabel("Destino :");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lblDestino, gbc);
		JTextField txtDestino = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(txtDestino, gbc);

		JLabel lblAerolinea = new JLabel("Aerolínea:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblAerolinea, gbc);
		JTextField txtAerolinea = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(txtAerolinea, gbc);

		JLabel lblFechaSalida = new JLabel("Fecha Salida:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblFechaSalida, gbc);

		JSpinner spinnerFechaSalida = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor dateEditorFechaSalida = new JSpinner.DateEditor(spinnerFechaSalida, "dd/MM/yyyy");
		spinnerFechaSalida.setEditor(dateEditorFechaSalida);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(spinnerFechaSalida, gbc);

		JLabel lblFechaLlegada = new JLabel("Fecha Llegada:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblFechaLlegada, gbc);

		JSpinner spinnerFechaLlegada = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor dateEditorFechaLlegada = new JSpinner.DateEditor(spinnerFechaLlegada, "dd/MM/yyyy");
		spinnerFechaLlegada.setEditor(dateEditorFechaLlegada);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(spinnerFechaLlegada, gbc);

		JLabel lblHoraSalida = new JLabel("Hora Salida (HH:mm:ss):");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblHoraSalida, gbc);
		JTextField txtHoraSalida = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(txtHoraSalida, gbc);

		JLabel lblHoraLlegada = new JLabel("Hora Llegada (HH:mm:ss):");
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(lblHoraLlegada, gbc);
		JTextField txtHoraLlegada = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel.add(txtHoraLlegada, gbc);

		JButton btnGuardar = new JButton("Guardar");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		panel.add(btnGuardar, gbc);

		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Repositorios.consultarAeropuertoYCrearVuelo(txtOrigen, txtDestino, txtAerolinea,
						(java.util.Date) spinnerFechaSalida.getValue(), (java.util.Date) spinnerFechaSalida.getValue(),
						txtHoraSalida, txtHoraLlegada, panel);
			}
		});

		return panel;
	}

	// ---- PANEL BUSCAR VUELO
	public static JPanel panelGestionarVuelo() throws SQLException {
		JPanel panel = new JPanel(new GridBagLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// Campo de búsqueda
		JLabel lblNombreBuscar = new JLabel("Vuelo:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombreBuscar, gbc);

		JComboBox<String> comboNombreBuscar = new JComboBox<>();
		comboNombreBuscar.setEditable(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(comboNombreBuscar, gbc);

		JButton btnBuscar = new JButton("Listar");
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(btnBuscar, gbc);

		// Campos para modificar aeropuerto
		JLabel lblNuevoOrigen = new JLabel("Nuevo Origen:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblNuevoOrigen, gbc);

		JTextField txtNuevoOrigen = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(txtNuevoOrigen, gbc);

		JLabel lblNuevoDestino = new JLabel("Nueva Destino:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblNuevoDestino, gbc);

		JTextField txtNuevoDestino = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(txtNuevoDestino, gbc);

		JLabel lblNuevaAerolinea = new JLabel("Nueva Aerolinea:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblNuevaAerolinea, gbc);

		JTextField txtNuevaAerolinea = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(txtNuevaAerolinea, gbc);

		JButton btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		panel.add(btnModificar, gbc);

		// Campo para eliminar aeropuerto
		JLabel lblVueloOrigenEliminar = new JLabel("Vuelo: Origen a Eliminar:");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		panel.add(lblVueloOrigenEliminar, gbc);

		JTextField txtOrigenEliminar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(txtOrigenEliminar, gbc);

		JLabel lblVueloDestinoEliminar = new JLabel("Vuelo: Destino a Eliminar:");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		panel.add(lblVueloDestinoEliminar, gbc);

		JTextField txtDestinoEliminar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 8;
		panel.add(txtDestinoEliminar, gbc);

		JLabel lblVueloAerolineaEliminar = new JLabel("Vuelo: Aerolinea a Eliminar:");
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		panel.add(lblVueloAerolineaEliminar, gbc);

		JTextField txtAerolineaEliminar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 9;
		panel.add(txtAerolineaEliminar, gbc);

		JButton btnEliminar = new JButton("Eliminar");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 3;
		panel.add(btnEliminar, gbc);

		// ActionListener para el botón de búsqueda
		btnBuscar.addActionListener(e -> {
			String nombre = (String) comboNombreBuscar.getSelectedItem();

			try {
				List<Aeropuerto> aeropuertos = Repositorios.leerAeropuertos();
				List<Vuelo> vuelos = Repositorios.leerVuelos(nombre);
				if (vuelos.isEmpty()) {
					vuelos = Repositorios.leerVuelos();
				}
				comboNombreBuscar.removeAllItems();
				for (Vuelo vuelo : vuelos) {
					comboNombreBuscar.addItem(consultarCiudadAeropuerto(aeropuertos, vuelo.getOrigen()) + " - "
							+ consultarCiudadAeropuerto(aeropuertos, vuelo.getDestino()) + " - "
							+ vuelo.getAerolinea());
				}
				if (vuelos.isEmpty()) {
					JOptionPane.showMessageDialog(panel,
							"No se encontraron vuelos con el nombre de Aerolinea especificado.");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al buscar los vuelos");
			}
		});

		// ActionListener para cargar datos seleccionados en los campos
		comboNombreBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String) comboNombreBuscar.getSelectedItem();
				if (selectedValue != null && selectedValue != "") {
					String[] parts = selectedValue.split(" - ");
					String origen = parts[0];
					String destino = parts[1];
					String aerolinea = parts[2];

					txtNuevoOrigen.setText(origen);
					txtOrigenEliminar.setText(origen);
					txtNuevoDestino.setText(destino);
					txtDestinoEliminar.setText(destino);
					txtNuevaAerolinea.setText(aerolinea);
					txtAerolineaEliminar.setText(aerolinea);
				}
			}
		});

		// ActionListener para el botón de eliminación
		btnEliminar.addActionListener(e -> {
			String aerolinea = txtAerolineaEliminar.getText();
			String origen = txtOrigenEliminar.getText();
			String destino = txtDestinoEliminar.getText();
			List<Aeropuerto> aeropuertos;
			try {
				aeropuertos = Repositorios.leerAeropuertos();
				Repositorios.eliminarVuelo(consultarAeropuertoID(aeropuertos, origen),
						consultarAeropuertoID(aeropuertos, destino), aerolinea);
				JOptionPane.showMessageDialog(panel, "Vuelo eliminado exitosamente.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al borrar el vuelo");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al borrar el vuelo");
			}

			txtAerolineaEliminar.setText("");
			txtOrigenEliminar.setText("");
			txtDestinoEliminar.setText("");
			txtNuevaAerolinea.setText("");
			txtNuevoDestino.setText("");
			txtNuevoOrigen.setText("");

		});

		// ActionListener para el botón de modificación
		btnModificar.addActionListener(e -> {
			String aerolinea = txtNuevaAerolinea.getText();
			String origen = txtNuevoOrigen.getText();
			String destino = txtNuevoDestino.getText();
			List<Aeropuerto> aeropuertos;
			try {
				aeropuertos = Repositorios.leerAeropuertos();
				String[] parts = ((String) comboNombreBuscar.getSelectedItem()).split(" - ");
				String oldOrigen = parts[0];
				String oldDestino = parts[1];
				String oldAerolinea = parts[2];

				Repositorios.modificarVuelo(aerolinea, consultarAeropuertoID(aeropuertos, origen),
						consultarAeropuertoID(aeropuertos, destino), consultarAeropuertoID(aeropuertos, oldOrigen),
						consultarAeropuertoID(aeropuertos, oldDestino), oldAerolinea);
				JOptionPane.showMessageDialog(panel, "Aeropuerto modificado exitosamente.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al modificar el vuelo");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al borrar el vuelo");
			}

			comboNombreBuscar.setSelectedItem("");
			txtAerolineaEliminar.setText("");
			txtOrigenEliminar.setText("");
			txtDestinoEliminar.setText("");
			txtNuevaAerolinea.setText("");
			txtNuevoDestino.setText("");
			txtNuevoOrigen.setText("");
		});

		return panel;
	}

	// ---- CONSULTAR ID AEROPUERTOS
	private static String consultarCiudadAeropuerto(List<Aeropuerto> aeropuertos, long id) {
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getId() == id) {
				return aeropuerto.getCiudad();
			}
		}
		return null;
	}

	// ---- CONSULTAR ID AEROPUERTOS
	private static Long consultarAeropuertoID(List<Aeropuerto> aeropuertos, String ciudad) throws Exception {
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getCiudad().equals(ciudad)) {
				return aeropuerto.getId();
			}
		}
		throw new Exception("Aeropuerto no encontrado");
	}

	// ---- PANEL GESTION PASAJERO
	public static JPanel panelGestionPasajero() {
		JPanel panel = new JPanel(new GridBagLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// Campo de búsqueda
		JLabel lblNombreBuscar = new JLabel("Pasajero:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombreBuscar, gbc);

		JComboBox<String> comboNombreBuscar = new JComboBox<>();
		comboNombreBuscar.setEditable(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(comboNombreBuscar, gbc);

		JButton btnBuscar = new JButton("Listar");
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(btnBuscar, gbc);

		JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblNuevoNombre, gbc);

		JTextField txtNuevoNombre = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(txtNuevoNombre, gbc);

		JLabel lblNuevoApellido = new JLabel("Nuevo Apellido:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblNuevoApellido, gbc);

		JTextField txtNuevoApellido = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(txtNuevoApellido, gbc);

		JButton btnModificar = new JButton("Modificar");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		panel.add(btnModificar, gbc);

		// Campo para eliminar pasajero
		JLabel lblDNIEliminar = new JLabel("Pasajero a Eliminar:");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		panel.add(lblDNIEliminar, gbc);

		JTextField txtDNIEliminar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(txtDNIEliminar, gbc);

		JButton btnEliminar = new JButton("Eliminar");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 3;
		panel.add(btnEliminar, gbc);

		// ActionListener para el botón de búsqueda
		btnBuscar.addActionListener(e -> {
			String nombre = (String) comboNombreBuscar.getSelectedItem();
			try {
				List<Pasajero> pasajeros = Repositorios.leerPasajeros(nombre);
				if (pasajeros.isEmpty()) {
					pasajeros = Repositorios.leerPasajeros();
				}
				comboNombreBuscar.removeAllItems();
				for (Pasajero pasajero : pasajeros) {
					comboNombreBuscar
							.addItem(pasajero.getNombre() + " " + pasajero.getApellido() + " - " + pasajero.getDni());
				}
				if (pasajeros.isEmpty()) {
					JOptionPane.showMessageDialog(panel, "No se encontraron pasajeros con el nombre especificado.");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Error al buscar los pasajeros");
			}
		});

		// ActionListener para cargar datos seleccionados en los campos
		comboNombreBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String) comboNombreBuscar.getSelectedItem();
				if (selectedValue != null && !selectedValue.isEmpty()) {
					String[] parts = selectedValue.split(" - ");
					String nombreCompleto = parts[0];
					String dni = parts[1];
					String[] nombreParts = nombreCompleto.split(" ");
					String nombre = nombreParts[0];
					String apellido = nombreParts.length > 1 ? nombreParts[1] : "";

					txtDNIEliminar.setText(dni);
					txtNuevoNombre.setText(nombre);
					txtNuevoApellido.setText(apellido);
				}
			}
		});

		// KeyListener para actualizar el JComboBox mientras se escribe
		comboNombreBuscar.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				SwingUtilities.invokeLater(() -> {
					String text = (String) comboNombreBuscar.getEditor().getItem();
					if (text.length() >= 3) {
						try {
							List<Pasajero> pasajeros = Repositorios.leerPasajeros(text);
							comboNombreBuscar.removeAllItems();
							for (Pasajero pasajero : pasajeros) {
								comboNombreBuscar.addItem(pasajero.getNombre() + " " + pasajero.getApellido());
							}
							comboNombreBuscar.getEditor().setItem(text);
							comboNombreBuscar.showPopup();
						} catch (SQLException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(panel, "Error al buscar los pasajeros");
						}
					}
				});
			}
		});

		// ActionListener para el botón de eliminación
		btnEliminar.addActionListener(e -> {
			String dni = txtDNIEliminar.getText();
			Repositorios.eliminarPasajeroPorNombre(dni);
			JOptionPane.showMessageDialog(panel, "Pasajero eliminado exitosamente.");
			txtDNIEliminar.setText(""); // Limpiar el campo de eliminación
			txtNuevoNombre.setText(""); // Limpiar el campo de nuevo nombre
			txtNuevoApellido.setText(""); // Limpiar el campo de nuevo apellido
		});

		// ActionListener para el botón de modificación
		btnModificar.addActionListener(e -> {
			String selectedValue = (String) comboNombreBuscar.getSelectedItem();
			if (selectedValue != null && !selectedValue.isEmpty()) {
				String[] parts = selectedValue.split(" - ");
				String dni = parts[1];
				String nuevoNombre = txtNuevoNombre.getText();
				String nuevoApellido = txtNuevoApellido.getText();
				Repositorios.modificarPasajeroPorNombre(dni, nuevoNombre, nuevoApellido);
				JOptionPane.showMessageDialog(panel, "Pasajero modificado exitosamente.");
				comboNombreBuscar.setSelectedItem(""); // Limpiar el campo de búsqueda
				txtDNIEliminar.setText(""); // Limpiar el campo de eliminación
				txtNuevoNombre.setText(""); // Limpiar el campo de nuevo nombre
				txtNuevoApellido.setText(""); // Limpiar el campo de nuevo apellido
			}
		});

		return panel;
	}

	// ---- PANEL CREAR INICIO
	public static JPanel crearPanelInicio() {
		JPanel panel = new JPanel() {

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Aero.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new BorderLayout());

		JLabel mensajeBienvenida = new JLabel("Bienvenido a la Gestión de Aeropuertos y Vuelos", JLabel.CENTER);
		mensajeBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		panel.add(mensajeBienvenida, BorderLayout.CENTER);

		return panel;
	}

}
