package Clases;

public class Aeropuerto {

// ---- ATRIBUTOS

    private String nombre;
    private String ciudad;
    private String pais;
    private long id;

// ---- CONSTRUCTORES	
    public Aeropuerto(long id, String ciudad, String pais, String nombre) {
	super();
	this.nombre = nombre;
	this.ciudad = ciudad;
	this.pais = pais;
	this.id = id;
    }

    public Aeropuerto(String nombre, String ciudad, String pais) {
	super();
	this.nombre = nombre;
	this.ciudad = ciudad;
	this.pais = pais;

    }

// ---- GETTERS & SETTERS
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getCiudad() {
	return ciudad;
    }

    public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
    }

    public String getPais() {
	return pais;
    }

    public void setPais(String pais) {
	this.pais = pais;
    }

    public long getId() {
	return id;
    }

}
