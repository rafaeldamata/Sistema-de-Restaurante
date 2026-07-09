package com.mycompany.sstema.restaurante.view.telaprincipal;
import com.mycompany.sstema.restaurante.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaConta extends JFrame {
    private Mesa mesa;
    private Cliente clienteAtual;
    private JTable tabelaItens;
    private DefaultTableModel tableModel;
    private JLabel lblTotal;
    private JButton btnPagar;
    private float valorTotalGeral = 0.0f;

    public TelaConta(Mesa mesa, Cliente clienteAtual) {
        this.mesa = mesa;
        this.clienteAtual = clienteAtual;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conta do Restaurante - Mesa " + mesa.getId());
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // --- TOPO: Título ---
        JLabel lblTitulo = new JLabel("Detalhamento da Conta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- CENTRO: Tabela ---
        String[] colunas = {"Cliente", "Produto", "Preço Unit.", "Qtd.", "Subtotal"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela apenas leitura
            }
        };
        tabelaItens = new JTable(tableModel);
        tabelaItens.setFillsViewportHeight(true);
        tabelaItens.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(tabelaItens);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        add(scrollPane, BorderLayout.CENTER);

        // --- RODAPÉ: Total e Botão de Pagamento ---
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        // Label do Total
        lblTotal = new JLabel("TOTAL: R$ 0,00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotal.setForeground(new Color(40, 110, 40));

        // Botão de Pagamento
        btnPagar = new JButton("Efetuar Pagamento");
        btnPagar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPagar.setBackground(new Color(50, 120, 240));
        btnPagar.setForeground(Color.WHITE);
        btnPagar.setFocusPainted(false);
        btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Ação do Botão
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                efetuarPagamento();
            }
        });

        painelRodape.add(lblTotal, BorderLayout.WEST);
        painelRodape.add(btnPagar, BorderLayout.EAST);
        
        add(painelRodape, BorderLayout.SOUTH);

        // Carrega os dados da Mesa recebida por parâmetro
        carregarDados();
    }

    private void carregarDados() {
        valorTotalGeral = 0.0f;
        tableModel.setRowCount(0); // Limpa tabela

        for (Cliente cliente : mesa.getClientes()) {
            for (Pedido pedido : cliente.getListadePedidos()) {
                for (ItemPedido item : pedido.getPedido()) {
                    
                    float subtotal = item.getSubtotal();
                    valorTotalGeral += subtotal;

                    tableModel.addRow(new Object[]{
                        cliente.getNome(), 
                        item.getItemCardapio().getNome(), 
                        String.format("R$ %.2f", item.getItemCardapio().getPreco()), 
                        item.getQuantidade(), 
                        String.format("R$ %.2f", subtotal)
                    });
                }
            }
        }

        lblTotal.setText(String.format("TOTAL: R$ %.2f", valorTotalGeral));
        clienteAtual.getConta().setValorTotal(valorTotalGeral);
    }

    private void efetuarPagamento() {
        TelaPagamento telaPagamento = new TelaPagamento(this.mesa, this.clienteAtual);
        telaPagamento.setLocationRelativeTo(this);
        telaPagamento.setVisible(true);
    }
}