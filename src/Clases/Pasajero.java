package Clases;

public class Pasajero {
    
// ---- ATRIBUTOS
    
    Long id;
    String nombre;
    String apellido;
    String dni;
    Long vuelo_id;
    
// ---- CONSTRUCTORES	

    public Pasajero(Long id, String nombre, String apellido, String dni, Long vuelo_id) {

	this.id = id;
	this.nombre = nombre;
	this.apellido = apellido;
	this.dni = dni;
	this.vuelo_id = vuelo_id;
    }

    public Pasajero(String nombre, String apellido, String dni, Long vuelo_id) {

	this.nombre = nombre;
	this.apellido = apellido;
	this.dni = dni;
	this.vuelo_id = vuelo_id;
    }

 // ---- GETTERS & SETTERS

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public Long getVuelo_id() {
	return vuelo_id;
    }

    public void setVuelo_id(Long vuelo_id) {
	this.vuelo_id = vuelo_id;
    }

   

}
