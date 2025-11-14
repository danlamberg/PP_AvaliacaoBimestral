/* Classe simples para transportar os dados dos sensores */

package Exercicio3;

public class Sensores {
    private double temperatura;
    private boolean falhaResfriamento;
   
    public Sensores(double temperatura, boolean falhaResfriamento) {
        this.temperatura = temperatura;
        this.falhaResfriamento = falhaResfriamento;
    }
    
    public double getTemperatura() { return temperatura; }
    public boolean isFalhaResfriamento() { return falhaResfriamento; }
}

