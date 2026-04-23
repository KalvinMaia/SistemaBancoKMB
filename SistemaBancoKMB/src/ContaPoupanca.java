public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numero, String cliente) {
        super(numero, cliente);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            return false;
        }

        if (saldo >= valor) {
            saldo -= valor;
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
    public String getTipoConta() {
        return "Poupança";
    }
}
