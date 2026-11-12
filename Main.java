import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("           SISTEMA DE PASARELA DE PAGOS DIGITALES - ADA 3 POO             ");
        System.out.println("==========================================================================\n");

        
        ArrayList<MetodoPago> listaPagos = new ArrayList<>();

        // Registrar objetos con distintos escenarios de prueba
        listaPagos.add(new Tarjeta("TXN-101", "Mariana Cocom", 1500.00, "1234567890123456", true));  // Crédito Aprobado
        listaPagos.add(new Tarjeta("TXN-102", "Angel Cocom", 450.00, "9876543210129999", false));   // Débito Aprobado
        listaPagos.add(new TransferenciaSPEI("TXN-103", "Carlos Pech", 12500.00, "012180015012345678", "BBVA")); // SPEI Aprobado (>10k)
        listaPagos.add(new TransferenciaSPEI("TXN-104", "Sofia Uc", 800.00, "012180015012345678", "Citibanamex")); // SPEI  (CLABE completa)
        listaPagos.add(new PagoQR("TXN-105", "David Tun", 350.00, "QR-PAY-998822", false));          // QR Aprobado con Cashback
        listaPagos.add(new PagoQR("TXN-106", "Elena Canul", 620.00, "QR-PAY-112233", true));          // QR Rechazado (Vencido)

        System.out.println("--- 1. PROCESAMIENTO POLIMÓRFICO DE TRANSACCIONES ---");
        for (MetodoPago pago : listaPagos) {
            pago.procesarPago(); // Invocación polimórfica del método sobrescrito
            System.out.println(pago);
        }

        System.out.println("\n--- 2. APLICACIÓN DE VINCULACIÓN DINÁMICA (DOWNCASTING Y REGLAS ESPECÍFICAS) ---");
        for (MetodoPago pago : listaPagos) {
            
            if (pago instanceof Tarjeta) {
                Tarjeta t = (Tarjeta) pago;
                if ("APROBADO".equals(t.getEstado())) {
                    System.out.printf("-> [Tarjeta %s] Cliente: %s asignó %d Puntos de Recompensa.%n",
                            t.getIdTransaccion(), t.getCliente(), t.calcularPuntosRecompensa());
                }
            } else if (pago instanceof PagoQR) {
                PagoQR qr = (PagoQR) pago;
                if ("APROBADO".equals(qr.getEstado())) {
                    System.out.printf("-> [Pago QR %s] Cliente: %s recibió un Cashback directo de $%.2f MXN.%n",
                            qr.getIdTransaccion(), qr.getCliente(), qr.calcularCashback());
                }
            }
        }

        System.out.println("\n==========================================================================");
        System.out.println("                    SIMULACIÓN FINALIZADA CON ÉXITO                       ");
        System.out.println("==========================================================================");
    }
}