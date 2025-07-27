package modelo.dao;

import modelo.entities.ComprobanteDePago;
import java.util.List;

public interface ComprobanteDAO {
    List<ComprobanteDePago> listarComprobantes(int comprobanteId);
    byte[] getArchivoComprobante(int comprobanteId);
    boolean getEstadoComprobante(int comprobanteId);
    void guardarComprobante(ComprobanteDePago comprobante);
}
