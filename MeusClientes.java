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
public class MeusClientes {
    private ArrayList<Cliente> listaClientes;
    
    public MeusClientes(){
        this.listaClientes = new ArrayList<>();
    }
    public void addCliente(Cliente c){
        this.listaClientes.add(c);
    }
    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes;
    }
    public Cliente encontrarCliente(String cpf){
        for (Cliente c : this.listaClientes){
            if (cpf.equals(c.getCPF())){
                return c;
            }
        }
        return null;
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
