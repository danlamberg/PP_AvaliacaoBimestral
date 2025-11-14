/* Interface moderna e limpa que nosso sistema cliente espera usar. */

package Exercicio2;

public interface ProcessadorTransacoes {
    RespostaTransacao autorizar(String cartao, double valor, String moeda);
}
