/* A lógica de transição (temp > 300C) está encapsulada aqui. */

package Exercicio3;

public class EstadoOperacaoNormal implements EstadoUsina {
    
    @Override
    public void verificarSensores(UsinaNuclear contexto, Sensores dados) {
        // [REGRA] OPERACAO_NORMAL → ALERTA_AMARELO: se temperatura > 300°C
        if (dados.getTemperatura() > 300) {
            System.out.println("ALERTA: Temperatura > 300C. Indo para ALERTA_AMARELO.");
            contexto.setEstado(new EstadoAlertaAmarelo());
        } else {
            System.out.println("Operação normal. Temperatura: " + dados.getTemperatura() + "C");
        }
    }

    @Override
    public void ligar(UsinaNuclear contexto) {
        System.out.println("Usina já está em operação normal.");
    }

    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("Desligando usina...");
        contexto.setEstado(new EstadoDesligada());
    }
}