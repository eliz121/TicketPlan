package modelo.entidades;

import java.util.List;

public class Administrador extends Usuario {
	private static final long serialVersionUID = 1L;
	private List<Cliente> clientes;
	public Administrador() {
		super();
	}


	public Administrador(Integer usuarioId, String correo, String contraseña, String celular, String direccion,
			String cedula, String nombre) {
		super(usuarioId, correo, contraseña, celular, direccion, cedula, nombre);
		// TODO Auto-generated constructor stub
	}

	public static Administrador iniciarSesion(String correo, String contraseña) {
		return null;
	}
	


}
