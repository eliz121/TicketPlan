package modelo.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.BDDConnection;

public class Cliente extends Usuario {
	private static List<Cliente> clientes = null; //BDD en memoria
	private static final long serialVersionUID = 1L;
	public Cliente() {
		super();
	}


	public Cliente(Integer usuarioId, String correo, String contraseña, String celular, String direccion, String cedula,
			String nombre) {
		super(usuarioId, correo, contraseña, celular, direccion, cedula, nombre);
		// TODO Auto-generated constructor stub
	}
	
	
	/////////////// METODOS DEL NEGOCIO ///////////////

	public static Cliente iniciarSesion(String correo, String contraseña) {
	    String _SQL_AUTORIZAR_ = "SELECT * FROM cliente WHERE correo=? and contraseña=?";
	    try {
	        System.out.println("Consultando en BD: " + correo + " / " + contraseña);
	        PreparedStatement pstmt = BDDConnection.getConexion().prepareStatement(_SQL_AUTORIZAR_);
	        pstmt.setString(1, correo);
	        pstmt.setString(2, contraseña);
	        ResultSet rs = pstmt.executeQuery();
	        Cliente clienteAutorizado = null;
	        if(rs.next()) {
	            System.out.println("Usuario encontrado en BD: " + rs.getString("correo"));
	            System.out.println(">>> CORRIENDO CÓDIGO NUEVO <<<");
	            clienteAutorizado = new Cliente(rs.getInt("usuario_id"),
	                                           rs.getString("correo"),
	                                           rs.getString("contraseña"),
	                                           rs.getString("celular"),
	                                           rs.getString("direccion"),
	                                           rs.getString("cedula"),
	                                           rs.getString("nombre"));
	        } else {
	            System.out.println("No se encontró usuario con esas credenciales");
	        }
	        BDDConnection.cerrar(rs);
	        BDDConnection.cerrar(pstmt);
	        // BDDConnection.cerrar();
	        return clienteAutorizado;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	public static List<Cliente> extraerListaClientes(){
		String _SQL_GET_ALL_ = "SELECT * FROM cliente";
		try {
			PreparedStatement pstmt = BDDConnection.getConexion().prepareStatement(_SQL_GET_ALL_);
			ResultSet rs = pstmt.executeQuery();
			List <Cliente> clientes = new ArrayList<Cliente>();
			while(rs.next()) {
				clientes.add(new Cliente(rs.getInt("usuario_id"),rs.getString("correo"), rs.getString("contraseña"), rs.getString("celular"), rs.getString("direccion"), rs.getString("cedula"), rs.getString("nombre")));
			}
			BDDConnection.cerrar(rs);
			BDDConnection.cerrar(pstmt);
			BDDConnection.cerrar();
			
			return clientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		/*
		if(clientes == null) {
			clientes = new ArrayList<>();
			clientes.add(new Cliente(1, "juandisuarez87@hotmail.com", "123", "0984775210", "De los Anturios & Clavelinas", "1750880005", "Juan Suárez"));
			clientes.add(new Cliente(2, "juandisuarez88@hotmail.com", "123", "0984775211", "De los Anturios 2 & Clavelinas", "1750880006", "Abi Delgado"));
			clientes.add(new Cliente(3, "juandisuarez89@hotmail.com", "123", "0984775212", "De los Anturios 3 & Clavelinas", "1750880006", "Pedro Pérez"));

		}

		return clientes;
		*/
	}

	public static Cliente obtenerInformaciónCliente(int usuarioId) {
		for(Cliente cliente: extraerListaClientes()) {
			if(cliente.getUsuarioId() == usuarioId) {
				return cliente;
			}
		}
		return null;
	}

	public static boolean crearCliente(Cliente cliente) {
		int max = 0;
		for(Cliente clienteIterado: extraerListaClientes()) {
			if(max<clienteIterado.getUsuarioId()) {
				max = clienteIterado.getUsuarioId();
			}
		}
		cliente.setUsuarioId(max+1);
		extraerListaClientes().add(cliente);
		return true;
		}

	public static boolean eliminarCliente(int usuarioId) {
	    return extraerListaClientes().removeIf(c -> c.getUsuarioId().equals(usuarioId));
	}

	public static boolean actualizarCliente(Cliente cliente) {
	    List<Cliente> listaClientes = extraerListaClientes();
	    for (Cliente existente : listaClientes) {
	        if (existente.getUsuarioId().equals(cliente.getUsuarioId())) {
	            // Actualizamos todos los campos
	            existente.setCorreo(cliente.getCorreo());
	            existente.setContraseña(cliente.getContraseña());
	            existente.setCelular(cliente.getCelular());
	            existente.setDireccion(cliente.getDireccion());
	            existente.setCedula(cliente.getCedula());
	            existente.setNombre(cliente.getNombre());
	            return true;
	        }
	    }
	    return false; // No se encontró el cliente con ese ID
	}




}
