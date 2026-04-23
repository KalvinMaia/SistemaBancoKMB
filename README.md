# SistemaBancoKMB
Este repositório trata da estrutura de um sistema bancário, escrito em Java, por meio do Eclipse IDE for Java Developers. Este projeto esta associado à disciplina Linguagem de Programação II, do Bacharelado em Tecnologia da Informação (BTI), do Instituto Metrópole Digital (IMD), da UFRN. O projeto visa simular o comportamento de um sistema bancário simples via console, com foco em conceitos de Programação Orientada a Objetos (POO).

## Objetivo

O sistema permite gerenciar contas bancárias de clientes, realizando operações básicas como:

- criação de contas
- depósito
- saque
- transferência
- listagem de contas
- cálculo de tributos

A proposta do projeto é aplicar conceitos fundamentais de Java e POO, como abstração, herança, polimorfismo e interfaces.

---

## Funcionalidades

O sistema apresenta um menu principal com as seguintes opções:

1. **Criar Conta**
   - cadastra uma nova conta
   - solicita o nome do cliente
   - permite escolher entre:
     - Conta Corrente
     - Conta Poupança
   - gera número de conta sequencial automaticamente a partir de `101`

2. **Realizar Depósito**
   - localiza a conta pelo número
   - adiciona o valor informado ao saldo

3. **Realizar Saque**
   - localiza a conta pelo número
   - verifica se há saldo suficiente
   - regras:
     - **Conta Corrente**: cobra taxa de 5% sobre o valor sacado
     - **Conta Poupança**: não cobra taxa

4. **Realizar Transferência**
   - transfere um valor de uma conta para outra
   - aplica a mesma regra de saque da conta de origem

5. **Listar Contas**
   - exibe todas as contas cadastradas
   - mostra:
     - número
     - nome do cliente
     - saldo
     - tipo da conta

6. **Calcular Tributos**
   - soma os tributos das contas tributáveis
   - apenas a **Conta Corrente** paga tributo
   - o tributo corresponde a **1% do saldo atual**

7. **Sair**
   - encerra a execução do programa

---

## Conceitos de Programação Orientada a Objetos utilizados

### Classe Abstrata
A classe `Conta` representa a estrutura base do sistema.  
Ela possui atributos e métodos comuns a todos os tipos de conta.

### Herança
As classes:

- `ContaCorrente`
- `ContaPoupanca`

herdam da classe `Conta`.

### Polimorfismo
Todas as contas são armazenadas em uma única lista:

```java
ArrayList<Conta>
