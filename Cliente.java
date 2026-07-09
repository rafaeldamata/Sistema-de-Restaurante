package com.mycompany.sstema.restaurante;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author rafin
 */
public class Cliente {

    
    private String nome,cpf,email;
    private Conta conta;
    private float bonus;
    private ArrayList<Pedido> listaPedidos;
    private Pagamento pagamento;
    private Mesa mesa;
    
      
    public Cliente(String nome, String cpf, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.bonus = 0f;
        this.conta = new Conta();
        this.listaPedidos = new ArrayList<>();
     }
    public String getCPF(){
        return this.cpf;
    }
    public Conta getConta(){
        return this.conta;
    }
    public ArrayList<Pedido> getListadePedidos(){
        return this.listaPedidos;
    }
    public String getNome(){
        return this.nome;
    }
    public float getBonus(){
        return this.bonus;
    }
    public String getEmail(){
        return this.email;
    }
    public Mesa getMesa(){
        return this.mesa;
    }
    public void addPedido(Pedido p, Cozinha cozinha){
        this.listaPedidos.add(p);
        cozinha.addPedido(p);
        this.conta.adicionarValor(p.valorPedido());
    }
    public float fecharConta(){
        return this.conta.getValorTotal();
    }
    public void setMesa(Mesa mesa){
        this.mesa = mesa;
    }
    public boolean pagar(){

        return this.conta.pagar(this.pagamento);
    }
    public boolean pagar(int pessoas){
        return this.conta.pagar(this.pagamento,pessoas );
    }
    public void setBonus(){
        this.bonus = this.conta.calculaBonus();
    }
    public void calculaBonus(float valorTotal){
        this.bonus = valorTotal * 0.1f;
    }
    public void removerDados(){
        this.listaPedidos.clear();
        this.conta.setValorTotal(0f);
        this.mesa = null;
    }
    public void removerItemPedido(String nome, int quantidade){
    for (Pedido p : this.getListadePedidos()){
        for (ItemPedido item : p.getPedido()){
            if (item.getItemCardapio().getNome().equals(nome) && item.getQuantidade() == quantidade){
                p.removerPedido(item);
                return;
            }
        }
    }
    
}

    public static void main(String args[]) {
        // TODO code application logic here
    }
}
