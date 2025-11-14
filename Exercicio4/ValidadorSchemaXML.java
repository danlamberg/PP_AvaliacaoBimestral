/* Validador de Schema XML */

package Exercicio4;

import java.util.concurrent.TimeoutException;

public class ValidadorSchemaXML implements IValidador {
    @Override
    public void validar(ValidationContext context) throws TimeoutException {
        // Simula uma validação rápida
        System.out.println("1. Validando Schema XML... OK.");
    }
    
    @Override
    public long getTimeoutMillis() { return 1000; } // 1s
}