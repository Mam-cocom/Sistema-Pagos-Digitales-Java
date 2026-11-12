public class PagoQR extends MetodoPago {
    private String codigoQR;
    private boolean qrVencido;

    public PagoQR(String idTransaccion, String cliente, double montoBase, String codigoQR, boolean qrVencido) {
        super(idTransaccion, cliente, montoBase);
        this.codigoQR = codigoQR;
        this.qrVencido = qrVencido;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public boolean isQrVencido() {
        return qrVencido;
    }

    public void setQrVencido(boolean qrVencido) {
        this.qrVencido = qrVencido;
    }

    // Regla de Negocio 3: Comisión del 1.0% fijo por transacción vía QR.
    @Override
    public double calcularComision() {
        return getMontoBase() * 0.01;
    }

    @Override
    public boolean procesarPago() {
        if (!qrVencido && codigoQR != null && !codigoQR.isEmpty()) {
            setEstado("APROBADO");
            return true;
        } else {
            setEstado("RECHAZADO (Código QR expirado o inválido)");
            return false;
        }
    }

    // Método específico de la subclase
    public double calcularCashback() {
        if ("APROBADO".equals(getEstado())) {
            return getMontoBase() * 0.02; // Reembolso del 2%
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Ref. QR: %s | Estado QR: %s", 
                codigoQR, qrVencido ? "Vencido" : "Vigente");
    }
}