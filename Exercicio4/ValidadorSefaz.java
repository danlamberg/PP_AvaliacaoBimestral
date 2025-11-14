/* Validador SEFAZ (Simula falha para testar o Rollback) */

package Exercicio4;

import java.util.concurrent.TimeoutException;

public class ValidadorSefaz implements IValidador {
    @Override
    public void validar(ValidationContext context) throws TimeoutException {
        System.out.println("Validando online na SEFAZ...");
        
        // [RESTRIÇÃO 2: TESTE DE ROLLBACK]
        // Simulamos uma falha aqui. O Orquestrador deverá
        // acionar o rollback do ValidadorDbDuplicidade.
        System.out.println("... ERRO: SEFAZ offline.");
        context.addError("Serviço SEFAZ indisponível.");
    }
    
    @Override
    public long getTimeoutMillis() { return 10000; } // 10s
}