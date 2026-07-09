package com.mycompany.sstema.restaurante.view.telaprincipal;
import com.mycompany.sstema.restaurante.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TelaCozinha extends JFrame {

    private Cozinha cozinha;
    private JTable tabelaPedidos;
    private PedidoTableModel tableModel;
    private JButton btnEntregar;

    public TelaCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        // Configurações básicas da janela
        setTitle("Painel de Controle da Cozinha");
        setSize(600, 400);
        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Inicializa o TableModel customizado e o JTable
        tableModel = new PedidoTableModel(cozinha.getPedidos());
        tabelaPedidos = new JTable(tableModel);
        tabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas um pedido por vez

        // Adiciona a tabela a um JScrollPane (necessário para barras de rolagem e cabeçalho)
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        add(scrollPane, BorderLayout.CENTER);

        // 2. Criação do painel inferior para o botão
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEntregar = new JButton("Entregar Pedido");
        btnEntregar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEntregar.setBackground(new Color(46, 204, 113)); // Verde institucional
        btnEntregar.setForeground(Color.WHITE);
        painelBotoes.add(btnEntregar);
        add(painelBotoes, BorderLayout.SOUTH);

        // 3. Ação do Botão Entregar
        btnEntregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregarPedidoSelecionado();
            }
        });
    }

    // Método que gerencia a lógica de entrega
    private void entregarPedidoSelecionado() {
        int linhaSelecionada = tabelaPedidos.getSelectedRow();

        // Valida se o usuário realmente selecionou uma linha
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Por favor, selecione um pedido na tabela para entregar.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtém o pedido correspondente à linha selecionada
        Pedido pedido = cozinha.getPedidos().get(linhaSelecionada);
        
        // Altera o status do pedido
        pedido.setStatus("Entregue");

        // Notifica o TableModel que os dados mudaram, forçando o JTable a se atualizar em tela
        tableModel.fireTableRowsUpdated(linhaSelecionada, linhaSelecionada);

        JOptionPane.showMessageDialog(this, 
                "Pedido entregue com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    // --- INNER CLASS: MODELO DE TABELA CUSTOMIZADO ---
    // Essencial para mapear o ArrayList<Pedido> diretamente nas colunas do JTable
    private class PedidoTableModel extends AbstractTableModel {
        private final ArrayList<Pedido> lista;
        private final String[] colunas = {"ID", "Descrição", "Status"};

        public PedidoTableModel(ArrayList<Pedido> lista) {
            this.lista = lista;
        }

        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        
public Object getValueAt(int rowIndex, int columnIndex) {
    Pedido pedido = lista.get(rowIndex);
    
    switch (columnIndex) {
        case 0: 
            return "Pedido #" + (rowIndex + 1);
            
        case 1: 
            // Criamos um StringBuilder para concatenar os itens de forma eficiente
            StringBuilder sb = new StringBuilder();
            
            // Percorre a lista de itens do pedido
            for (ItemPedido item : pedido.getPedido()) { // Certifique-se de que o método no seu Pedido chama getItensPedido() ou similar
                String nomeProduto = item.getItemCardapio().getNome(); // Pega o nome do ItemCardapio
                int quantidade = item.getQuantidade();                 // Pega a quantidade do ItemPedido
                
                // Formata como: "2x Hambúrguer, "
                sb.append(quantidade).append("x ").append(nomeProduto).append(", ");
            }
            
            // Remove a última vírgula e espaço sobressalente, se a lista não estiver vazia
            String resultado = sb.toString();
            if (resultado.endsWith(", ")) {
                resultado = resultado.substring(0, resultado.length() - 2);
            }
            
            return resultado.isEmpty() ? "Nenhum item" : resultado;
            
        case 2: 
            return pedido.getStatus();
            
        default: 
            return null;
    }
}
    }
    // --- MÉTODO MAIN PARA TESTAR A TELA ---
    public static void main(String[] args) {
        // Simulando a criação da cozinha com alguns dados fictícios
        Cozinha minhaCozinha = new Cozinha();
        

        // Executa a interface na Thread do Swing (Boa prática)
        SwingUtilities.invokeLater(() -> {
            TelaCozinha tela = new TelaCozinha(minhaCozinha);
            tela.setVisible(true);
        });
    }
}