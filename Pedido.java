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
public class Pedido {

    protected ArrayList<ItemPedido> pedido;
    protected String status;
    
    public Pedido(){
        this.pedido = new ArrayList<>();
        this.status = "EM ABERTO";
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void addItem(ItemPedido item){
        this.pedido.add(item);
    }
    public void removerPedido(ItemPedido item){
        this.pedido.remove(item);
    }
    public ArrayList<ItemPedido> getPedido(){
        return this.pedido;
    }
   
    
    public float valorPedido(){
        float total = 0;
        for (ItemPedido item : this.pedido){
            total += item.getPreco() * item.getQuantidade();
        }
        return total;
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
