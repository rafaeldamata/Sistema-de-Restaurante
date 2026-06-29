package com.mycompany.sstema.restaurante;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author rafin
 */
public class Bebida extends ItemCardapio {
    private String fornecedor;
    
    public Bebida(String fornecedor, float preco, String nome){
        super(nome,preco);
        this.fornecedor = fornecedor;
    }
    public String getFornecedor(){
        return this.fornecedor;
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
