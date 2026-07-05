/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sstema.restaurante.view.telaprincipal;
import com.mycompany.sstema.restaurante.ItemCardapio;
import com.mycompany.sstema.restaurante.Tablet;
import com.mycompany.sstema.restaurante.Pedido;
import com.mycompany.sstema.restaurante.ItemPedido;

import java.util.ArrayList;

/**
 *
 * @author rafin
 */
public class TelaPedido extends javax.swing.JFrame {
    // Estas três listas guardam as coisas na MESMA ordem. 
// O checkbox da posição 0 pertence ao item da posição 0 e ao spinner da posição 0.
private ArrayList<ItemCardapio> itensExibidos = new java.util.ArrayList<>();
private ArrayList<javax.swing.JCheckBox> checkBoxes = new java.util.ArrayList<>();
private ArrayList<javax.swing.JSpinner> spinnersQuantidade = new java.util.ArrayList<>();
private Tablet tablet;
private Pedido pedido;
private TelaCliente telaCliente;

    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPedido.class.getName());

    /**
     * Creates new form TelaPedido
     */
    public TelaPedido(Tablet tablet, Pedido pedido, TelaCliente telaCliente) {
        initComponents();
        scrollPane.setViewportView(painelCardapio); 
        painelCardapio.setLayout(new javax.swing.BoxLayout(painelCardapio, javax.swing.BoxLayout.Y_AXIS));
    
    // Opcional: Garante que o painel interno tenha um fundo visível para teste
        painelCardapio.setBackground(java.awt.Color.WHITE);
        this.tablet = tablet;
        this.pedido = pedido;
        this.telaCliente = telaCliente;
        this.construirMenuDinamico();
    }

    public final void construirMenuDinamico() {
    // 1. Limpa o painel e as listas de controle
    painelCardapio.removeAll(); 
    itensExibidos.clear();
    checkBoxes.clear();
    spinnersQuantidade.clear();
    
    ArrayList<ItemCardapio> listaProdutos = tablet.getListaProdutos();
    
    // --- SEÇÃO DE COMIDAS ---
    // Adiciona o título da seção de comidas
    javax.swing.JLabel lblComidas = new javax.swing.JLabel("--- COMIDAS ---");
    lblComidas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    lblComidas.setForeground(java.awt.Color.DARK_GRAY);
    lblComidas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 0));
    painelCardapio.add(lblComidas);
    
    for (ItemCardapio item : listaProdutos) {
        // Verifica se o item herda ou é uma instância de Comida
        if (item instanceof com.mycompany.sstema.restaurante.Comida) {
            adicionarItemAoPainel(item);
        }
    }
    
    // --- SEÇÃO DE BEBIDAS ---
    // Adiciona o título da seção de bebidas
    javax.swing.JLabel lblBebidas = new javax.swing.JLabel("--- BEBIDAS ---");
    lblBebidas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    lblBebidas.setForeground(java.awt.Color.DARK_GRAY);
    lblBebidas.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 5, 0));
    painelCardapio.add(lblBebidas);
    
    for (ItemCardapio item : listaProdutos) {
        // Verifica se o item herda ou é uma instância de Bebida
        if (item instanceof com.mycompany.sstema.restaurante.Bebida) {
            adicionarItemAoPainel(item);
        }
    }
    
    // Atualiza a interface gráfica do Swing para renderizar as novidades
    painelCardapio.revalidate();
    painelCardapio.repaint();
}

/**
 * Método auxiliar criado para reaproveitar a lógica de desenho de cada linha
 * sem precisar duplicar o código do Checkbox e do Spinner.
 */
private void adicionarItemAoPainel(ItemCardapio item) {
    // Cria uma "linha" horizontal para organizar o item atual
    javax.swing.JPanel linhaItem = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    
    // Cria o Checkbox com o nome do prato e o preço
    String texto = item.getNome() + " - R$ " + item.getPreco();
    javax.swing.JCheckBox chk = new javax.swing.JCheckBox(texto);
    
    // Cria o seletor de quantidade (mínimo 1, máximo 99)
    javax.swing.SpinnerNumberModel modeloNumero = new javax.swing.SpinnerNumberModel(1, 1, 99, 1);
    javax.swing.JSpinner spinner = new javax.swing.JSpinner(modeloNumero);
    spinner.setPreferredSize(new java.awt.Dimension(50, 25));
    spinner.setEnabled(false); 
    
    // Listener para ativar/desativar a quantidade baseado no checkbox
    chk.addActionListener(e -> {
        spinner.setEnabled(chk.isSelected());
    });
    
    // Adiciona os componentes na linha
    linhaItem.add(chk);
    linhaItem.add(new javax.swing.JLabel(" Qtd: "));
    linhaItem.add(spinner);
    
    // Coloca a linha dentro do painel principal
    painelCardapio.add(linhaItem);
    
    // Guarda os elementos de forma sincronizada para o fechamento do pedido continuar funcionando
    itensExibidos.add(item);
    checkBoxes.add(chk);
    spinnersQuantidade.add(spinner);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        painelCardapio = new javax.swing.JPanel();
        btnFecharpedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setText("Tela de Pedidos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));
        getContentPane().add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 450, 180));

        painelCardapio.setLayout(new javax.swing.BoxLayout(painelCardapio, javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(painelCardapio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 360, 120));

        btnFecharpedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFecharpedido.setText("Fechar Pedido");
        btnFecharpedido.addActionListener(this::btnFecharpedidoActionPerformed);
        getContentPane().add(btnFecharpedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharpedidoActionPerformed
         
        boolean temItensSelecionados = false;

    // 2. Varremos as nossas listas de controle da tela
    for (int i = 0; i < checkBoxes.size(); i++) {
        javax.swing.JCheckBox chk = checkBoxes.get(i);
        
        // Se o cliente marcou este item...
        if (chk.isSelected()) {
            temItensSelecionados = true;
            
            // Recuperamos o ItemCardapio e a quantidade daquela linha
            ItemCardapio itemDoCardapio = itensExibidos.get(i);
            int qtd = (int) spinnersQuantidade.get(i).getValue();
            
            // 3. Criamos o objeto ItemPedido associando o item à quantidade
            ItemPedido itemPedido = new ItemPedido(itemDoCardapio, qtd);
            
            // 4. Adicionamos esse item dentro do ArrayList do seu objeto novoPedido
            // (Assumindo que sua classe Pedido tenha um método para adicionar itens, ex: adicionarItem)
            pedido.addItem(itemPedido); 
            
        }
    }

    // Validação básica caso o usuário clique em fechar sem marcar nada
    if (!temItensSelecionados) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Por favor, selecione pelo menos um item para fechar o pedido.", 
            "Aviso", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 5. Exibindo o resumo na tela puxando os dados do objeto Pedido recém-criado
    StringBuilder resumo = new StringBuilder("=== PEDIDO ENVIADO COM SUCESSO ===\n\n");
    float totalGeral = 0.0f;
 
    // Varre o ArrayList de ItemPedido que está dentro do objeto novoPedido
    
        
        totalGeral = pedido.valorPedido();
        
        String mensagemFinal = String.format("PEDIDO ENVIADO COM SUCESSO!\n\nTOTAL DO PEDIDO: R$ %.2f", totalGeral);

    // Exibe a caixinha de sucesso direta para o usuário
    javax.swing.JOptionPane.showMessageDialog(this, mensagemFinal);
    telaCliente.atualizarListaDePedidos(this.pedido);
    
    setVisible(false);
    dispose();
    
    }//GEN-LAST:event_btnFecharpedidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /* Create and display the form */
java.awt.EventQueue.invokeLater(() -> {
    // 1. Criamos a variável de teste com null
    Tablet tabletTeste = new Tablet();
    Pedido pedidoTeste = new Pedido();
    // 2. Passamos a variável para dentro do construtor da tela
    new TelaPedido(tabletTeste,pedidoTeste,null).setVisible(true);
});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFecharpedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel painelCardapio;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
