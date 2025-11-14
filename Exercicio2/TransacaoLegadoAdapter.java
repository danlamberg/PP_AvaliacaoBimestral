/* A classe adaptadora que implementa a interface moderna (Target) e, internamente, 
"embrulha" uma instância do sistema legado (Adaptee) */

package Exercicio2;
import java.util.HashMap;

public class TransacaoLegadoAdapter implements ProcessadorTransacoes {

    // Referência ao sistema que precisa ser adaptado
    private final SistemaBancarioLegado sistemaLegado;

    //Decisão de Design: Usamos Injeção de Dependência aqui.
    public TransacaoLegadoAdapter(SistemaBancarioLegado sistemaLegado) {
        this.sistemaLegado = sistemaLegado;
    }
    /* Este é o núcleo do Adapter. Ele recebe a chamada moderna e a traduz para 
    o formato que o legado entende.*/
    @Override
    public RespostaTransacao autorizar(String cartao, double valor, String moeda) {
        System.out.println("[ADAPTER] Recebida chamada moderna. Convertendo para o formato legado...");
        
        // Mapeia os parâmetros modernos para o formato esperado pelo sistema legado
        HashMap<String, Object> parametrosLegado = new HashMap<>();
        parametrosLegado.put("Num_cartao", cartao);
        parametrosLegado.put("Valor_total", valor);
        parametrosLegado.put("Moeda", moeda);

        parametrosLegado.put("Código_loja_legado ", "Loja_Online_001 ");

        // 2. Delegação - Chama o método do sistema legado
        HashMap<String, Object> respostaLegada = sistemaLegado.processarTransacao(parametrosLegado);

        System.out.println("[ADAPTER] Resposta legada recebida. Convertendo para formato moderno...");
        
        // 3. Tradução (Response) - Converte a resposta legada (HashMap)
        // para o objeto de resposta moderno (bidirecional).
        return this.converterRespostaLegada(respostaLegada);
    }
    
    private RespostaTransacao converterRespostaLegada(HashMap<String, Object> respostaLegada) {
        // Lógica de tradução complexa
        int statusCode = (int) respostaLegada.getOrDefault("Código_status", 500);
        boolean autorizada = (statusCode == 200);
        
        String id = (String) respostaLegada.getOrDefault("Transação Legada", null);
        String erro = autorizada ? null : (String) respostaLegada.getOrDefault("Mensagem erro", "Erro desconhecido");

        return new RespostaTransacao(autorizada, id, erro);
    }
}
