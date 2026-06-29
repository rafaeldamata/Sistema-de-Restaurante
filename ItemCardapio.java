package com.mycompany.sstema.restaurante;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author rafin
 */
public abstract class ItemCardapio {
    protected float preco;
    protected String nome;
    
    
    public ItemCardapio(String nome, float preco){
        this.nome = nome;
        this.preco = preco;
    }
    public float getPreco(){
        return this.preco;
    }
    public String getNome(){
        return this.nome;
    }
        
    
    public static void main(String args[]) {
        System.out.println("Hello world");
           }
        // TODO code application logic here
    }


