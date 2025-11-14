/* Contém a lógica de transição para o estado emergência, satisfazendo 
o requisito de que só pode ser ativado a partir daqui.*/

package Exercicio3;

public class EstadoAlertaVermelho implements EstadoUsina {

    @Override
    public void verificarSensores(UsinaNuclear contexto, Sensores dados) {
        
        // Alerta vermelho: se sistema de resfriamento falhar
        if (dados.isFalhaResfriamento()) {
            System.out.println("CATASTROFE: Falha no resfriamento! Indo para EMERGENCIA.");
            contexto.setEstado(new EstadoEmergencia());
            
        } else if (dados.getTemperatura() <= 400) {
            // [REQ 2] Transição Bidirecional (Vermelho -> Amarelo)
            System.out.println("INFO: Temperatura reduzida. Voltando para ALERTA_AMARELO.");
            contexto.setEstado(new EstadoAlertaAmarelo());
        } else {
            System.out.println("ALERTA_VERMELHO: Risco iminente. Temp: " 
                + dados.getTemperatura() + "C. Aguardando estabilização.");
        }
    }
    
    //Métodos da interface EstadoUsina
    @Override
    public void ligar(UsinaNuclear contexto) {
        System.out.println("Ação 'ligar' inválida no estado ALERTA_VERMELHO.");
    }

    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("Ação 'desligar' inválida no estado ALERTA_VERMELHO.");
    }
}