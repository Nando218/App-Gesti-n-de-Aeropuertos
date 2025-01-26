package Clases;

import java.sql.Date;
import java.sql.Time;

public class Vuelo {

 // ---- ATRIBUTOS

	private long origen;
	private long destino;
	private String aerolinea;
	private Date fechaSalida;
	private Date fechaLlegada;
	private Time hora_salida;
	private Time hora_llegada;
	
 // ---- GETTERS & SETTERS
	
	public long getOrigen() {
		return origen;
	}
	public void setOrigen(long origen) {
		this.origen = origen;
	}
	public long getDestino() {
		return destino;
	}
	public void setDestino(long destino) {
		this.destino = destino;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Date getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	 		
	public String getId() {
	    return null;
	}
	public Time getHora_salida() {
		return hora_salida;
	}
	public void setHora_salida(Time hora_salida) {
		this.hora_salida = hora_salida;
	}
	public Time getHora_llegada() {
		return hora_llegada;
	}
	public void setHora_llegada(Time hora_llegada) {
		this.hora_llegada = hora_llegada;
	}
	
// ---- CONSTRUCTORES
	
	public Vuelo(long origen2, long destino2, String aerolinea, Date fechaSalida, Date fechaLlegada, Time time,
			Time time2) {
		super();
		this.origen = origen2;
		this.destino = destino2;
		this.aerolinea = aerolinea;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.hora_salida = time;
		this.hora_llegada = time2;
	}
	
		

}
