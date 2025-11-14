/* Esta classe "embrulha" o estado atual para sobrescrever seu comportamento sem alterá-lo.*/

package Exercicio3;

public class EstadoManutencaoDecorator implements EstadoUsina {

    // O estado real que está sendo "pausado"
    private final EstadoUsina estadoEmbrulhado;

    public EstadoManutencaoDecorator(EstadoUsina estadoParaEmbrulhar) {
        this.estadoEmbrulhado = estadoParaEmbrulhar;
        System.out.println("Atenção: Entrando em modo de manutenção.");
        System.out.println("(Estado anterior " + estadoEmbrulhado.getClass().getSimpleName() + " foi salvo)");
    }
    
    //Isso "sobrescreve" o comportamento de alerta.
    @Override
    public void verificarSensores(UsinaNuclear contexto, Sensores dados) {
        System.out.println("[MANUTENÇÃO] Sensores lidos (Temp: " + dados.getTemperatura() + "), mas alertas estão suspensos.");
    }

    @Override
    public void ligar(UsinaNuclear contexto) {
        System.out.println("[MANUTENÇÃO] Ações de ligar/desligar bloqueadas.");
    }

    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("[MANUTENÇÃO] Ações de ligar/desligar bloqueadas.");
    }
}