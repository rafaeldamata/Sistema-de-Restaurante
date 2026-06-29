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
    
      
    public Cliente(String nome, String cpf, String email,MeusClientes meusCli){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.bonus = 0f;
        this.conta = new Conta();
        this.listaPedidos = new ArrayList<Pedido>();
        meusCli.addCliente(this);
     }
    public String getCPF(){
        return this.cpf;
    }
    public void addPedido(Pedido p, Cozinha cozinha){
        this.listaPedidos.add(p);
        cozinha.addPedido(p);
        this.conta.adicionarValor(p.valorPedido());
    }
    public float fecharConta(){
        return this.conta.getValorTotal();
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
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
