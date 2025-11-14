/* CHAIN OF RESPONSIBILITY- Define o contrato para todos 
os validadores (handlers) na cadeia.*/

package Exercicio4;

import java.util.concurrent.TimeoutException;

public interface IValidador {
    void validar(ValidationContext context) throws TimeoutException;
    long getTimeoutMillis();
}