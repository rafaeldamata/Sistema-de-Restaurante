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
public class Mesa {
    private ArrayList<Cliente> listaClientes;
    private int id;
    
    public Mesa(int id){
        this.id = id;
        this.listaClientes = new ArrayList<>();
    }
    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes;
    }
    public int getId(){
        return this.id;
    }
    public void addCliente(Cliente c){
        listaClientes.add(c);
    }
    public void removerCliente(Cliente c){
        listaClientes.remove(c);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
