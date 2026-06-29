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
public class Tablet {
    private ArrayList<ItemCardapio> listaProdutos;
    
    public Tablet(){
        this.listaProdutos = new ArrayList<>();
    }
    public void addProduto(ItemCardapio item){
        this.listaProdutos.add(item);
    }
    public void removerProduto(ItemCardapio item){
        this.listaProdutos.remove(item);
    }
    public ArrayList<ItemCardapio> getListaProdutos(){
        return this.listaProdutos;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
