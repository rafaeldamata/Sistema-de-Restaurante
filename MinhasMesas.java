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
public class MinhasMesas {
    private ArrayList<Mesa> listaMesas;
    
    public MinhasMesas(){
        this.listaMesas = new ArrayList<>();
    }
    public void addMesa(Mesa m){
        listaMesas.add(m);
    }
    public void removerMesa(Mesa m){
        listaMesas.remove(m);
    }
    public Mesa encontarMesa(Mesa mesa){
        for (Mesa m : listaMesas){
            if (m.equals(mesa))
                return m;
        }
        return null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
