public class ContaCorrente extends Conta implements ITributavel {
    private static final double TAXA_SAQUE = 0.05;
    private static final double TRIBUTO = 0.01;

    public ContaCorrente(int numero, String cliente) {
        super(numero, cliente);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            return false;
        }

        double valorComTaxa = valor + (valor * TAXA_SAQUE);

        if (saldo >= valorComTaxa) {
            saldo -= valorComTaxa;
            return true;
        }

        return false;
    }

    @Override
    public boolean transferir(Conta destino, double valor) {
        if (destino == null || valor <= 0) {
            return false;
        }

        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        }

        return false;
    }

    @Override
    public double calculaTributos() {
        return saldo * TRIBUTO;
    }

    @Override
    public String getTipoConta() {
        return "Corrente";
    }
}
