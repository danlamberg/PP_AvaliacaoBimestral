/* Implementa a interface EstadoUsina, a lógica de transição é encapsulada aqui. 
Note que não há transição para ALERTA ou EMERGÊNCIA, prevenindo transições perigosas */

package Exercicio3;

public class EstadoDesligada implements EstadoUsina {
    
    @Override
    public void ligar(UsinaNuclear contexto) {
        System.out.println("Ligando usina... entrando em Operação Normal.");
        contexto.setEstado(new EstadoOperacaoNormal());
    }

    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("Usina já está desligada.");
    }

    @Override
    public void verificarSensores(UsinaNuclear contexto, Sensores dados) {
        System.out.println("Usina desligada. Sensores inativos.");
    }
}