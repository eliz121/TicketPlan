package modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entrada")
public class Entrada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntrada;
	
	@Column(name = "idCliente")
    private int idCliente;
	@Column (name = "idEvento")
    private int idEvento;
	@Column (name = "precio")
    private double precio;
	@Column (name = "tipo")
    private String tipo;
	@Column (name = "estado")
    private String estado;
	@Column (name = "codigoQR")
    private String codigoQR;

	public Entrada() {

	}


	public Entrada(int idEntrada, int idCliente, int idEvento, double precio, String tipo, String estado,
			String codigoQR) {
		super();
		this.idEntrada = idEntrada;
		this.idCliente = idCliente;
		this.idEvento = idEvento;
		this.precio = precio;
		this.tipo = tipo;
		this.estado = estado;
		this.codigoQR = codigoQR;
	}
	
	public int getIdEntrada() {
		return idEntrada;
	}


	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idUsuario) {
		this.idCliente = idUsuario;
	}


	public int getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getCodigoQR() {
		return codigoQR;
	}


	public void setCodigoQR(String codigoQR) {
		this.codigoQR = codigoQR;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void  listarEntradas(int idUsuario) {
	}

	public void obtenerDetalleEntrada(int idEntrada) {
	}

}
