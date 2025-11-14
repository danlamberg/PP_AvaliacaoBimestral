/* EEsta classe implementa a lógica de validação complexa
 dos "30 segundos". O próprio estado mantém um estado interno
 (o timestamp) para gerenciar isso */

package Exercicio3;

public class EstadoAlertaAmarelo implements EstadoUsina {

    // O estado agora tem sua própria memória (um timer)
    private long timestampInicioAlerta = 0;

    @Override
    public void verificarSensores(UsinaNuclear contexto, Sensores dados) {
        
        // [REGRA] ALerta amarelo para vermelho: se temp > 400°C por mais de 30s
        if (dados.getTemperatura() > 400) {
            
            if (timestampInicioAlerta == 0) {
                // É a primeira vez que vemos temp > 400, inicia o timer
                System.out.println("ALERTA_AMARELO: Temp > 400C. Iniciando timer de 30s...");
                this.timestampInicioAlerta = System.currentTimeMillis();
            
            } else if ((System.currentTimeMillis() - timestampInicioAlerta) > 30000) {
                // Já se passaram 30 segundos com temp > 400
                System.out.println("PERIGO: Temp > 400C por 30s. Indo para ALERTA_VERMELHO.");
                contexto.setEstado(new EstadoAlertaVermelho());
            } else {
                // Temp > 400, mas ainda não deu 30s. Apenas espera.
                System.out.println("ALERTA_AMARELO: Temp > 400C. Monitorando... (" 
                    + (System.currentTimeMillis() - timestampInicioAlerta) / 1000 + "s)");
            }

        } else if (dados.getTemperatura() <= 300) {
            // [REQ 2] Transição Bidirecional (Amarelo -> Normal)
            System.out.println("INFO: Temperatura estabilizada. Voltando para OPERACAO_NORMAL.");
            contexto.setEstado(new EstadoOperacaoNormal());
        
        } else {
             // Temp está entre 301-400. Fica em Amarelo, mas reseta o timer.
             if (this.timestampInicioAlerta != 0) {
                System.out.println("ALERTA_AMARELO: Temp baixou de 400C. Timer resetado.");
             }
             this.timestampInicioAlerta = 0;
             System.out.println("ALERTA_AMARELO: Monitorando temperatura: " + dados.getTemperatura() + "C");
        }
    }
    //Métodos da interface EstadoUsina
    @Override
    public void ligar(UsinaNuclear contexto) {
        System.out.println("Ação 'ligar' inválida no estado ALERTA_AMARELO.");
        /* Ignorado */
    }

    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("Ação 'desligar' inválida no estado ALERTA_AMARELO.");
        /* Ignorado */
    }
}