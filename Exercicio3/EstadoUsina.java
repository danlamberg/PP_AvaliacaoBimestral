/* Define a interface comum para todos os estados, permitindo que o Contexto (UsinaNuclear)
 trate todos da mesma forma.*/

package Exercicio3;

public interface EstadoUsina {
    // Passa os dados dos sensores para o estado atual.
    void verificarSensores(UsinaNuclear contexto, Sensores dados);
    void ligar(UsinaNuclear contexto);
    void desligar(UsinaNuclear contexto);
}
