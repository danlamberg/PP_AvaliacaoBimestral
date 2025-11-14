/* Prevenindo transições circulares perigosas. */

package Exercicio3;

public class EstadoEmergencia implements EstadoUsina {

    @Override
    public void verificarSensores(UsinaNuclear contexto, Sensores dados) {
        System.out.println("EMERGÊNCIA: EVACUAR! EVACUAR! Comunicação bloqueada.");
    }
    
    //Métodos da interface EstadoUsina
    @Override
    public void ligar(UsinaNuclear contexto) {
        System.out.println("Ação 'ligar' bloqueada em EMERGÊNCIA.");
    }

    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("Ação 'desligar' bloqueada em EMERGÊNCIA.");
    }
}