/* Classe de Demonstração para testar os padrões State e Decorator*/

package Exercicio3;

public class SistemaUsinaNuclear {
    public static void main(String[] args) {
        
        UsinaNuclear usina = new UsinaNuclear();
        
        // 1. Tenta verificar sensores com ela desligada
        usina.verificarSensores(new Sensores(25, false));
        usina.desligar(); // Tenta desligar (já está desligada)
        
        System.out.println("---");
        
        // 2. Liga a usina
        usina.ligar(); // Transição: Desligada -> OperacaoNormal
        usina.verificarSensores(new Sensores(280, false)); // Temp normal
        
        System.out.println("---");
        
        // 3. Aumenta a temperatura (Gatilho Amarelo)
        // Regra: > 300C
        usina.verificarSensores(new Sensores(310, false)); // Transição: Normal -> Amarelo
        
        System.out.println("---");
        
        // 4. Temperatura baixa (Gatilho de volta ao Normal)
        // Regra: <= 300C
        usina.verificarSensores(new Sensores(295, false)); // Transição: Amarelo -> Normal
        
        System.out.println("---");
        
        // 5. Aumenta de novo (Amarelo) e depois (Vermelho - Teste dos 30s)
        usina.verificarSensores(new Sensores(350, false)); // Transição: Normal -> Amarelo
        
        System.out.println("--- SIMULANDO ESPERA (30s) ---");
        try {
            // Primeira checagem (inicia o timer)
            usina.verificarSensores(new Sensores(410, false));
            
            // Simula 10 segundos (timer < 30s)
            Thread.sleep(10000); 
            usina.verificarSensores(new Sensores(415, false)); // Ainda não deve transicionar
            
            // Simula mais 21 segundos (total > 30s)
            Thread.sleep(21000);
            
            // Regra: > 400C por 30s
            usina.verificarSensores(new Sensores(420, false)); // Transição: Amarelo -> Vermelho
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("---");
        
        // 6. Diminui temperatura (Gatilho de volta ao Amarelo)
        // Regra: <= 400C
        usina.verificarSensores(new Sensores(390, false)); // Transição: Vermelho -> Amarelo
        
        System.out.println("---");
        
        // 7. Sobe de novo (Amarelo -> Vermelho) - pulando os 30s para o próximo teste
        usina.setEstado(new EstadoAlertaVermelho()); // Atalho para o teste
        usina.verificarSensores(new Sensores(450, false)); // Apenas para mostrar que está em Vermelho
        
        System.out.println("---");
        
        // 8. Falha no Resfriamento (Gatilho de Emergência)
        // Regra: Falha no resfriamento
        usina.verificarSensores(new Sensores(450, true)); // Transição: Vermelho -> Emergencia
        
        System.out.println("---");
        
        // 9. Tenta qualquer ação em Emergência
        usina.verificarSensores(new Sensores(500, true)); // Bloqueado
        usina.desligar(); // Bloqueado
        usina.ligar(); // Bloqueado


        // --- CENÁRIO 2: Teste do DECORATOR (Manutenção) ---
        System.out.println("\n==============================================");
        System.out.println("INICIANDO SIMULAÇÃO 2: MODO MANUTENÇÃO");
        System.out.println("==============================================");
        
        UsinaNuclear usina2 = new UsinaNuclear();
        usina2.ligar();
        usina2.verificarSensores(new Sensores(310, false)); // Leva para ALERTA_AMARELO
        
        System.out.println("---");
        
        // 1. Entra em Manutenção
        usina2.entrarManutencao(); // Decorator é aplicado
        
        // 2. Simula um superaquecimento
        System.out.println("... Simulando evento de ALERTA_VERMELHO enquanto em manutenção ...");
        usina2.verificarSensores(new Sensores(500, false)); // Deve ser ignorado pelo Decorator
        usina2.verificarSensores(new Sensores(500, true)); // Falha de resfriamento (deve ser ignorada)
        
        System.out.println("---");

        // 3. Sai da Manutenção
        usina2.sairManutencao(); // Decorator é removido, estado ALERTA_AMARELO é restaurado
        
        // 4. Verifica sensores de novo (com a falha)
        System.out.println("... Verificando sensores 1s após sair da manutenção ...");
        
        // O estado Amarelo foi restaurado. A temperatura > 400C
        // vai iniciar o timer de 30s do estado Amarelo.
        usina2.verificarSensores(new Sensores(450, false)); 
        
        System.out.println("Simulação concluída.");
    }
}