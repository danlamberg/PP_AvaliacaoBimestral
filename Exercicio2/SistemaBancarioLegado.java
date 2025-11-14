/* Interface legada que não podemos alterar.*/

package Exercicio2;

import java.util.HashMap;

public class SistemaBancarioLegado {
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        // Simulação de processamento complexo de transação bancária
        System.out.println("Processando transação bancária legada com os seguintes parâmetros:" + parametros);
       
        //Simula uma lógica de processamento
        HashMap<String, Object> respostaLegada= new HashMap<>();

        if(parametros.get("Num_cartao") == null || (Double)parametros.get("Valor_total") <= 0){
            respostaLegada.put("Codigo_status", 500); //Erro
            respostaLegada.put("Mensagem", "Transação inválida");
        }else{
            respostaLegada.put("Codigo_status", 200); //Sucesso
            respostaLegada.put("Mensagem", "Transação aprovada");
        }

        System.out.println("Legado - Enviando resposta: " + respostaLegada);
        return respostaLegada;
    }
}
