public abstract class Conta {
    protected int numero;
    protected String cliente;
    protected double saldo;

    public Conta(int numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public abstract boolean sacar(double valor);

    public abstract boolean transferir(Conta destino, double valor);

    public abstract String getTipoConta();

    @Override
    public String toString() {
        return "Número: " + numero +
               " | Cliente: " + cliente +
               " | Saldo: R$ " + String.format("%.2f", saldo) +
               " | Tipo: " + getTipoConta();
    }
}
