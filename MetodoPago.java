public abstract class MetodoPago {
    private String idTransaccion;
    private String cliente;
    private double montoBase;
    private String estado;

    public MetodoPago(String idTransaccion, String cliente, double montoBase) {
        this.idTransaccion = idTransaccion;
        this.cliente = cliente;
        this.montoBase = montoBase;
        this.estado = "PENDIENTE";
    }

    // Métodos abstractos a sobrescribir por las subclases
    public abstract boolean procesarPago();
    public abstract double calcularComision();

    // Getter y Setter
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(double montoBase) {
        this.montoBase = montoBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double calcularMontoTotal() {
        return montoBase + calcularComision();
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Cliente: %s | Monto Base: $%.2f | Comisión: $%.2f | Total: $%.2f | Estado: %s",
                idTransaccion, cliente, montoBase, calcularComision(), calcularMontoTotal(), estado);
    }
}