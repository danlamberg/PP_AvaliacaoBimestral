/* Valida o Certificado Digital */

package Exercicio4;

import java.util.concurrent.TimeoutException;

public class ValidadorCertificado implements IValidador {
    @Override
    public void validar(ValidationContext context) throws TimeoutException {
        System.out.println("2. Validando Certificado Digital...");
        System.out.println("...Certificado OK.");
    }
@Override
    public long getTimeoutMillis() { return 2000; } // 2s
}