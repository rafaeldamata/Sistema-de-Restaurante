package com.mycompany.sstema.restaurante.view.telaprincipal;
import com.mycompany.sstema.restaurante.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class TelaPagamento extends JFrame {

    private Mesa mesaAtual;
    private Cliente clienteAtual;
    private JLabel lblValorTotal;
    private JList<String> listClientes;
    private DefaultListModel<String> listModel;
    private JButton btnPagarIndividual;
    private JButton btnRacharConta;
    private JComboBox<String> cbFormaPagamento; // Nova ComboBox para o tipo de pagamento

    public TelaPagamento(Mesa mesa, Cliente cliente) {
        this.mesaAtual = mesa;
        this.clienteAtual = cliente;

        // Configurações básicas da janela
        setTitle("Pagamento de Conta - Restaurante");
        setSize(480, 400);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        initComponentes();
    }

    private void initComponentes() {
        // --- Painel Superior: Informações da Conta ---
        JPanel painelSuperior = new JPanel(new GridLayout(2, 1, 5, 5));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        float valorTotal = clienteAtual.getConta().getValorTotal();
        
        lblValorTotal = new JLabel(String.format("Valor Total da Conta: R$ %.2f", valorTotal));
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(lblValorTotal);
        
        JLabel lblClienteLogado = new JLabel("Cliente Operando: " + clienteAtual.getNome());
        lblClienteLogado.setFont(new Font("Arial", Font.ITALIC, 12));
        painelSuperior.add(lblClienteLogado);
        
        add(painelSuperior, BorderLayout.NORTH);

        // --- Painel Central: Lista de Clientes e Forma de Pagamento ---
        JPanel painelCentral = new JPanel(new BorderLayout(5, 5));
        
        // Subpainel para a Lista de Clientes
        JPanel painelLista = new JPanel(new BorderLayout());
        painelLista.setBorder(BorderFactory.createTitledBorder("Clientes na Mesa"));
        listModel = new DefaultListModel<>();
        for (Cliente c : mesaAtual.getListaClientes()) {
            listModel.addElement(c.getNome() + " (CPF: " + c.getCPF() + ") - Bônus: R$ " + c.getBonus());
        }
        listClientes = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listClientes);
        painelLista.add(scrollPane, BorderLayout.CENTER);
        
        painelCentral.add(painelLista, BorderLayout.CENTER);
        
        // Subpainel para seleção da Forma de Pagamento
        JPanel painelFormaPagto = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelFormaPagto.add(new JLabel("Selecione a Forma de Pagamento:"));
        
        String[] opcoesPagamento = {"Débito", "Crédito", "Pix"};
        cbFormaPagamento = new JComboBox<>(opcoesPagamento);
        painelFormaPagto.add(cbFormaPagamento);
        
        painelCentral.add(painelFormaPagto, BorderLayout.SOUTH);
        
        add(painelCentral, BorderLayout.CENTER);

        // --- Painel Inferior: Botões de Ação ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnPagarIndividual = new JButton("Pagar Individual");
        btnRacharConta = new JButton("Rachar Conta");
        
        painelBotoes.add(btnPagarIndividual);
        painelBotoes.add(btnRacharConta);
        
        add(painelBotoes, BorderLayout.SOUTH);

        // --- Ouvintes de Eventos (ActionListeners) ---
        
        btnPagarIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagarIndividualmente();
            }
        });

        btnRacharConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                racharContaMesa();
            }
        });
    }

    // Método auxiliar injetado para associar o objeto Pagamento à classe Cliente com segurança
    private void injetarPagamentoNoCliente(Cliente cliente, String tipo) {
        try {
            Pagamento p = new Pagamento(tipo, "1234"); // Inicializa o tipo selecionado (Débito, Crédito ou Pix)
            Field campoPagamento = Cliente.class.getDeclaredField("pagamento");
            campoPagamento.setAccessible(true);
            campoPagamento.set(cliente, p);
        } catch (Exception ex) {
            System.err.println("Não foi possível injetar o pagamento automaticamente: " + ex.getMessage());
        }
    }

    private void pagarIndividualmente() {
        String formaSelecionada = (String) cbFormaPagamento.getSelectedItem();
        
        // Garante a injeção do tipo selecionado para evitar NullPointerException
        injetarPagamentoNoCliente(clienteAtual, formaSelecionada);

        Conta conta = clienteAtual.getConta();
        float valorTotal = conta.getValorTotal();
        float bonus = clienteAtual.getBonus();
        float valorFinal = valorTotal - bonus;
        
        if (valorFinal < 0) valorFinal = 0;

        conta.setValorTotal(valorFinal);
        
        boolean sucesso = clienteAtual.pagar();
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, 
                String.format("Pagamento individual realizado com sucesso via %s!\n" +
                "Valor original: R$ %.2f\n" +
                "Bônus aplicado: R$ %.2f\n" +
                "Valor final pago: R$ %.2f\n\nConta Paga!", formaSelecionada, valorTotal, bonus, valorFinal),
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clienteAtual.calculaBonus(valorTotal);
            clienteAtual.removerDados();
            mesaAtual.removerTodosClientes();
            this.dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Falha no pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            conta.setValorTotal(valorTotal);
        }
    }

    private void racharContaMesa() {
        int totalPessoas = mesaAtual.getListaClientes().size();
        
        if (totalPessoas == 0) {
            JOptionPane.showMessageDialog(this, "Não há clientes na mesa para dividir.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String formaSelecionada = (String) cbFormaPagamento.getSelectedItem();
        Conta contaOriginal = clienteAtual.getConta();
        float valorPorPessoa = contaOriginal.dividirConta(totalPessoas);
        
        StringBuilder resumoPagamentos = new StringBuilder(String.format("Processando divisão via %s:\n\n", formaSelecionada));
        boolean todosPagaram = true;

        for (Cliente c : mesaAtual.getListaClientes()) {
            // Injeta o tipo selecionado para cada um dos clientes da mesa
            injetarPagamentoNoCliente(c, formaSelecionada);
            
            float bonusCliente = c.getBonus();
            float valorComDesconto = valorPorPessoa - bonusCliente;
            if (valorComDesconto < 0) valorComDesconto = 0;

            Conta contaTemporaria = new Conta();
            contaTemporaria.setValorTotal(valorComDesconto);
            
            // Cria o objeto dinâmico do pagamento com o tipo correto
            Pagamento pGeneric = new Pagamento(formaSelecionada, "1234");
            boolean pagou = contaTemporaria.pagar(pGeneric);
            
            if (pagou) {
                resumoPagamentos.append(String.format("- %s pagou R$ %.2f (Quota: R$ %.2f | Bônus: R$ %.2f)\n", 
                        c.getNome(), valorComDesconto, valorPorPessoa, bonusCliente));
                c.calculaBonus(valorPorPessoa);
                clienteAtual.removerDados();
            } else {
                todosPagaram = false;
                resumoPagamentos.append(String.format("- %s FALHOU no pagamento.\n", c.getNome()));
            }
        }
        mesaAtual.removerTodosClientes();
        if (todosPagaram) {
            resumoPagamentos.append("\nConta paga com sucesso por todos da mesa!");
            JOptionPane.showMessageDialog(this, resumoPagamentos.toString(), "Conta Paga", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Houve um problema no pagamento de um dos integrantes.", "Erro no Pagamento", JOptionPane.ERROR_MESSAGE);
        }
    }
}