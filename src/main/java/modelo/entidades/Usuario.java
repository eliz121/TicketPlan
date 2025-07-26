package modelo.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

public abstract class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioId;
	@Column(nullable = false, unique = true)
	private String correo;
	@Column(nullable=false)
	private String contraseña;
	@Column(name = "celular", length = 15)
	private String celular;
	@Column(name = "direccion", length = 100)
	private String direccion;
	@Column(name = "cedula", length = 10, unique = true)
	private String cedula;
	@Column(name = "nombre", length = 50)
	private String nombre;


	public Usuario() {

	}

	public Usuario(Integer usuarioId, String correo, String contraseña, String celular, String direccion, String cedula,
			String nombre) {
		this.usuarioId = usuarioId;
		this.correo = correo;
		this.contraseña = contraseña;
		this.celular = celular;
		this.direccion = direccion;
		this.cedula = cedula;
		this.nombre = nombre;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", correo=" + correo + ", contraseña=" + contraseña + ", celular="
				+ celular + ", direccion=" + direccion + ", cedula=" + cedula + ", nombre=" + nombre + "]";
	}


}
