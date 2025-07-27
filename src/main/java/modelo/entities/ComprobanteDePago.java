package modelo.entities;

import jakarta.persistence.*;

@Entity
public class ComprobanteDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comprobanteId;

    private String fechaEnvio;
    private double monto;
    private boolean estado;

    @Lob
    private byte[] archivoComprobante;

    // Getters y Setters
    public int getComprobanteId() {
        return comprobanteId;
    }

    public void setComprobanteId(int comprobanteId) {
        this.comprobanteId = comprobanteId;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public byte[] getArchivoComprobante() {
        return archivoComprobante;
    }

    public void setArchivoComprobante(byte[] archivoComprobante) {
        this.archivoComprobante = archivoComprobante;
    }
}
