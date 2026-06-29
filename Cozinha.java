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
public class Cozinha {

    private ArrayList<Pedido> listaPedidos;
    
    public Cozinha(){
        this.listaPedidos = new ArrayList<Pedido>();
    }
    public void addPedido(Pedido p){
        this.listaPedidos.add(p);
    }
    public void entregarPedido(Pedido p){
        String status = "ENTREGUE";
        p.setStatus(status);
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
