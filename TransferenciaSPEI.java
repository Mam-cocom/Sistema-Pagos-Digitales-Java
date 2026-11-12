public class TransferenciaSPEI extends MetodoPago {
    private String clabeDestino;
    private String bancoDestino;

    public TransferenciaSPEI(String idTransaccion, String cliente, double montoBase, String clabeDestino, String bancoDestino) {
        super(idTransaccion, cliente, montoBase);
        this.clabeDestino = clabeDestino;
        this.bancoDestino = bancoDestino;
    }

    public String getClabeDestino() {
        return clabeDestino;
    }

    public void setClabeDestino(String clabeDestino) {
        this.clabeDestino = clabeDestino;
    }

    public String getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    // Regla de Negocio 2: Gratuito si el monto <= $10,000. Si supera, tarifa fija de $12.00 MXN.
    @Override
    public double calcularComision() {
        if (getMontoBase() <= 10000.00) {
            return 0.0;
        }
        return 12.00;
    }

    @Override
    public boolean procesarPago() {
        // Validar que la CLABE tenga 18 dígitos
        if (clabeDestino != null && clabeDestino.length() == 18 && clabeDestino.matches("\\d+")) {
            setEstado("APROBADO");
            return true;
        } else {
            setEstado("RECHAZADO (CLABE debe tener 18 dígitos)");
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | SPEI Banco: %s | CLABE: %s", bancoDestino, clabeDestino);
    }
}