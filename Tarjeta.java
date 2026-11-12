public class Tarjeta extends MetodoPago {
    private String numeroTarjeta;
    private boolean esCredito;

    public Tarjeta(String idTransaccion, String cliente, double montoBase, String numeroTarjeta, boolean esCredito) {
        super(idTransaccion, cliente, montoBase);
        this.numeroTarjeta = numeroTarjeta;
        this.esCredito = esCredito;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public boolean isEsCredito() {
        return esCredito;
    }

    public void setEsCredito(boolean esCredito) {
        this.esCredito = esCredito;
    }

    // Regla de Negocio 1: Comisión del 2.5% + $5.00 base. Si es crédito, +1.5% adicional por financiamiento.
    @Override
    public double calcularComision() {
        double porcentaje = esCredito ? 0.04 : 0.025;
        return (getMontoBase() * porcentaje) + 5.00;
    }

    @Override
    public boolean procesarPago() {
        if (numeroTarjeta != null && numeroTarjeta.length() == 16) {
            setEstado("APROBADO");
            return true;
        } else {
            setEstado("RECHAZADO (Número de tarjeta inválido)");
            return false;
        }
    }

    // Método específico de la subclase
    public int calcularPuntosRecompensa() {
        return (int) (getMontoBase() / 10.0);
    }

    @Override
    public String toString() {
        String tipo = esCredito ? "Crédito" : "Débito";
        return super.toString() + String.format(" | Tipo: Tarjeta %s (**** %s)", tipo, numeroTarjeta.substring(12));
    }
}