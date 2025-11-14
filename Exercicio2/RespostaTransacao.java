/* Data Transfer Object - moderno para encapsular a resposta da transação
 * de forma limpa, em vez de usar um HashMap.
 */

package Exercicio2;

public class RespostaTransacao {
    private final boolean autorizada;
    private final String idTransacao;
    private final String mensagemErro;

public RespostaTransacao(boolean autorizada, String idTransacao, String mensagemErro) {
        this.autorizada = autorizada;
        this.idTransacao = idTransacao;
        this.mensagemErro = mensagemErro;
    }

public boolean isAutorizada() {
    return autorizada;
}

public String getIdTransacao() {
    return idTransacao;
}

public String getMensagemErro() {
    return mensagemErro;
}

@Override
public String toString() {
    return "RespostaTransacao{" +
            "autorizada=" + autorizada +
            ", idTransacao='" + idTransacao + '\'' +
            ", mensagemErro='" + mensagemErro + '\'' +
            '}';
}
    
}
