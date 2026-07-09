package com.mycompany.sstema.restaurante;
public class Conta {

    private float valorTotal;
    private float valorPago;
    private boolean paga;

    public Conta(){
        valorTotal = 0;
        paga = false;
    }
    public void setValorTotal(float valorTotal){
        this.valorTotal = valorTotal;
    }
    public void adicionarValor(float valor){
        valorTotal += valor;
    }

    public float getValorTotal(){
        return valorTotal;
    }

    public float dividirConta(int pessoas){

        if(pessoas <= 0){
            throw new IllegalArgumentException(
                "Quantidade inválida"
            );
        }

        return valorTotal / pessoas;
    }

    public boolean pagar(Pagamento pagamento){

        paga = pagamento.pagar(valorTotal);

        return paga;
    }

    public boolean pagar(
            Pagamento pagamento,
            int pessoas){

        float valorIndividual =
                dividirConta(pessoas);

        System.out.println(
            "Conta dividida entre "
            + pessoas +
            " pessoas."
        );

        paga = pagamento.pagar(
                valorIndividual);

        return paga;
    }
    public float calculaBonus(){
        return this.valorTotal * 0.1f;
    }
    public static void main(String args[]){
    }
}