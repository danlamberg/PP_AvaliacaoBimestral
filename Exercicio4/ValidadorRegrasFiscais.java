/* Valida as Regras Fiscais */

package Exercicio4;

import java.util.concurrent.TimeoutException;

public class ValidadorRegrasFiscais implements IValidador {
    @Override
    public void validar(ValidationContext context) throws TimeoutException {
        System.out.println("3. Validando Regras Fiscais (Impostos)... OK.");
    }
    
    @Override
    public long getTimeoutMillis() { return 500; } // 0.5s
}