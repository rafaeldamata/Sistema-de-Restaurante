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
public class Pagamento {
    private String tipo, senha;
    
    public Pagamento(String tipo, String senha){
        this.tipo = tipo;
        this.senha = senha;
    }
    public boolean pagar(float valor){
        return true;
    }
    public static void main(String args[]){
    
    }
}
