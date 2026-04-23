import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Conta> contas = new ArrayList<>();
    private static int proximoNumeroConta = 101;

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    listarContas();
                    break;
                case 6:
                    calcularTotalTributos();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println();
        } while (opcao != 7);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("========================================");
        System.out.println("BEM VINDO AO BANCO KMG! ESCOLHA A OPÇÃO DESEJADA");
        System.out.println("========================================");
        System.out.println("1. Criar Conta");
        System.out.println("2. Realizar Depósito");
        System.out.println("3. Realizar Saque");
        System.out.println("4. Realizar Transferência");
        System.out.println("5. Listar Contas");
        System.out.println("6. Calcular Total de Tributos");
        System.out.println("7. Sair");
        System.out.println("========================================");
    }

    private static void criarConta() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.println("Tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        int tipo = lerInteiro("Escolha o tipo: ");

        Conta novaConta;

        if (tipo == 1) {
            novaConta = new ContaCorrente(proximoNumeroConta++, nome);
        } else if (tipo == 2) {
            novaConta = new ContaPoupanca(proximoNumeroConta++, nome);
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        contas.add(novaConta);
        System.out.println("Conta criada com sucesso.");
        System.out.println("Número da conta: " + novaConta.getNumero());
    }

    private static void realizarDeposito() {
        int numeroConta = lerInteiro("Número da conta: ");
        double valor = lerDouble("Valor do depósito: ");

        Conta conta = buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (valor <= 0) {
            System.out.println("Valor inválido para depósito.");
            return;
        }

        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso.");
        System.out.println("Novo saldo: R$ " + String.format("%.2f", conta.getSaldo()));
    }

    private static void realizarSaque() {
        int numeroConta = lerInteiro("Número da conta: ");
        double valor = lerDouble("Valor do saque: ");

        Conta conta = buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (conta.sacar(valor)) {
            System.out.println("Saque realizado com sucesso.");
            System.out.println("Novo saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    private static void realizarTransferencia() {
        int numeroOrigem = lerInteiro("Número da conta de origem: ");
        int numeroDestino = lerInteiro("Número da conta de destino: ");
        double valor = lerDouble("Valor da transferência: ");

        Conta origem = buscarContaPorNumero(numeroOrigem);
        Conta destino = buscarContaPorNumero(numeroDestino);

        if (origem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        if (destino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }

        if (origem.getNumero() == destino.getNumero()) {
            System.out.println("Não é possível transferir para a mesma conta.");
            return;
        }

        if (origem.transferir(destino, valor)) {
            System.out.println("Transferência realizada com sucesso.");
            System.out.println("Saldo da conta de origem: R$ " + String.format("%.2f", origem.getSaldo()));
            System.out.println("Saldo da conta de destino: R$ " + String.format("%.2f", destino.getSaldo()));
        } else {
            System.out.println("Transferência não realizada. Saldo insuficiente ou valor inválido.");
        }
    }

    private static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        System.out.println("========== LISTA DE CONTAS ==========");
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }

    private static void calcularTotalTributos() {
        double totalTributos = 0.0;

        for (Conta conta : contas) {
            if (conta instanceof ITributavel) {
                ITributavel contaTributavel = (ITributavel) conta;
                totalTributos += contaTributavel.calculaTributos();
            }
        }

        System.out.println("\n========================================");
        System.out.println("Total de tributos a recolher: R$ " + String.format("%.2f", totalTributos));
        System.out.println("========================================");
    }

    private static Conta buscarContaPorNumero(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }
}
