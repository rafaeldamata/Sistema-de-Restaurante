package com.mycompany.sstema.restaurante;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author rafin
 */
public class ItemPedido {

    private ItemCardapio item;
    private int quantidade;
    
    public ItemPedido(ItemCardapio item, int quantidade){
        this.item = item;
        this.quantidade = quantidade;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public ItemCardapio getItemCardapio(){
        return this.item;
    }
    public float getPreco(){
        return this.item.getPreco();
    }
    public float getSubtotal() {
        return item.getPreco() * quantidade;
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
