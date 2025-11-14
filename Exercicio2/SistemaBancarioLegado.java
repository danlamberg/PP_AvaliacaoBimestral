/* Interface legada que não podemos alterar.*/

package Exercicio2;

import java.util.HashMap;

public class SistemaBancarioLegado {

    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("[LEGADO] Recebendo transação com parâmetros: " + parametros);

        HashMap<String, Object> respostaLegada = new HashMap<>();

        // --- NOVA VALIDAÇÃO ---
        // Primeiro, pegamos os valores de dentro do Map
        Object cartao = parametros.get("NUM_CARTAO");
        Double valor = (Double) parametros.getOrDefault("VALOR_TOTAL", 0.0);
        Integer moeda = (Integer) parametros.getOrDefault("COD_MOEDA", 0);
        // --- FIM DA NOVA VALIDAÇÃO ---

        // 1. Verifique se o legado está LENDO as chaves corretas
        // Esta validação de "containsKey" é boa, mas incompleta.
        if (!parametros.containsKey("NUM_CARTAO") ||
            !parametros.containsKey("VALOR_TOTAL") ||
            !parametros.containsKey("COD_MOEDA") ||
            !parametros.containsKey("CODIGO_LOJA_LEGADO")) {
            
            respostaLegada.put("COD_STATUS", 500);
            respostaLegada.put("MENSAGEM_ERRO", "DADOS OBRIGATORIOS AUSENTES");

        // --- NOVA VALIDAÇÃO ---
        // Agora verificamos os VALORES (para a Transação 2)
        } else if (cartao == null || valor <= 0) {
            
            respostaLegada.put("COD_STATUS", 400); // 400 = Bad Request
            respostaLegada.put("MENSAGEM_ERRO", "DADOS INVALIDOS (Cartao nulo ou Valor negativo)");
        // --- FIM DA NOVA VALIDAÇÃO ---

        } else if (moeda == 0) { // Lógica da Transação 3 (estava correta)
             respostaLegada.put("COD_STATUS", 501);
             respostaLegada.put("MENSAGEM_ERRO", "MOEDA NAO SUPORTADA");
             
        } else {
            // Lógica de Sucesso (Transação 1)
            respostaLegada.put("COD_STATUS", 200); // OK
            respostaLegada.put("ID_TRANSACAO_LEGADA", "LEGADO_" + System.currentTimeMillis());
        }
        
        System.out.println("[LEGADO] Enviando resposta: " + respostaLegada);
        return respostaLegada;
    }
}