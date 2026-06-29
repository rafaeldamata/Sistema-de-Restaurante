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
public class Comida extends ItemCardapio{
    private String descricao;
    private ArrayList<String> ingredientes;
    
    public Comida(String descricao, String nome, float preco){
        super(nome,preco);
        this.descricao = descricao;
        this.ingredientes = new ArrayList<>();
    }
    public void addIngrediente(String ingrediente){
        this.ingredientes.add(ingrediente);
    }
    public void setIngredientes(ArrayList<String> ingredientes){
        this.ingredientes = ingredientes;
    }
    
    public static void main(String args[]) {
        System.out.println("Hello world");
    }
}
