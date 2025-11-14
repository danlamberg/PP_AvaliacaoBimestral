/* Classe Cliente para demonstrar o Padrão Adapter,
 o cliente usa a interface moderna, enquanto o adaptador lida com o legado.
*/
package Exercicio2;

public class SistemaFinanceiro {
    public static void main(String[] args) {
        // Instância do sistema legado que precisa ser adaptado
        SistemaBancarioLegado sistemaLegado = new SistemaBancarioLegado();
        ProcessadorTransacoes meuProcessador = new TransacaoLegadoAdapter(sistemaLegado);
 
        System.out.println("Executando Transação 1 (Sucesso)");
        
        // 2. O cliente faz a chamada usando a interface moderna e limpa (Envia "BRL")
        RespostaTransacao resp1 = meuProcessador.autorizar("1234-5678-9012-3456", 250.75, "BRL");
        System.out.println("[CLIENTE MODERNO] Resposta recebida: " + resp1);

        System.out.println("\nExecutando Transação 2 (Falha)");
        
        // 3. O cliente faz outra chamada (Envia "USD")
        RespostaTransacao resp2 = meuProcessador.autorizar(null, -10.0, "USD");
        System.out.println("[CLIENTE MODERNO] Resposta recebida: " + resp2);

        //Teste para a lógica de moeda da restrição 
        // Este novo teste verifica o caso de uma moeda não suportada ("JPY"),
        // que o Adapter deve traduzir para 0.
        System.out.println("\nExecutando Transação 3 (Moeda não suportada)");
        RespostaTransacao resp3 = meuProcessador.autorizar("1111-2222-3333-4444", 100.0, "JPY"); 
        System.out.println("[CLIENTE MODERNO] Resposta recebida: " + resp3);
    }
}