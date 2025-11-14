/* A classe adaptadora que implementa a interface moderna (Target) e, internamente, 
"embrulha" uma instância do sistema legado (Adaptee) */

package Exercicio2;
import java.util.HashMap;

public class TransacaoLegadoAdapter implements ProcessadorTransacoes {

    private final SistemaBancarioLegado sistemaLegado;

    public TransacaoLegadoAdapter(SistemaBancarioLegado sistemaLegado) {
        this.sistemaLegado = sistemaLegado;
    }

    @Override
    public RespostaTransacao autorizar(String cartao, double valor, String moeda) {
        System.out.println("[ADAPTER] Recebida chamada moderna. Convertendo para o formato legado...");
        
        HashMap<String, Object> parametrosLegado = new HashMap<>();

        // --- CORREÇÃO DAS CHAVES DE ENVIO ---
        // Padronizando para maiúsculas, sem acentos ou espaços
        parametrosLegado.put("NUM_CARTAO", cartao);
        parametrosLegado.put("VALOR_TOTAL", valor);
        parametrosLegado.put("COD_MOEDA", this.converterMoedaParaCodigo(moeda));
        parametrosLegado.put("CODIGO_LOJA_LEGADO", "Loja_Online_001");
        // ------------------------------------
      
        HashMap<String, Object> respostaLegada = sistemaLegado.processarTransacao(parametrosLegado);
        System.out.println("[ADAPTER] Resposta legada recebida. Convertendo para formato moderno...");
        return this.converterRespostaLegada(respostaLegada);
    }
    
    private int converterMoedaParaCodigo(String moeda) {
        if (moeda == null) return 0;
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: return 0;
        }
    }

    private RespostaTransacao converterRespostaLegada(HashMap<String, Object> respostaLegada) {
        
        // --- CORREÇÃO DAS CHAVES DE RECEBIMENTO ---
        // As chaves aqui DEVEM SER IDÊNTICAS às chaves que o 
        // SistemaBancarioLegado.java envia na resposta.
        
        int statusCode = (int) respostaLegada.getOrDefault("COD_STATUS", 500);
        boolean autorizada = (statusCode == 200);
        
        String id = (String) respostaLegada.getOrDefault("ID_TRANSACAO_LEGADA", null);
        String erro = autorizada ? null : (String) respostaLegada.getOrDefault("MENSAGEM_ERRO", "Erro desconhecido");
        // ------------------------------------------

        return new RespostaTransacao(autorizada, id, erro);
    }
}