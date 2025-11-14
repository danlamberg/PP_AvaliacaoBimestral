/* Classe Cliente para demonstrar o Padrão Adapter,
 o cliente usa a interface moderna, enquanto o adaptador lida com o legado.
*/
package Exercicio2;

public class SistemaFinanceiro {
    public static void main(String[] args) {
        // Instância do sistema legado que precisa ser adaptado
        SistemaBancarioLegado sistemaLegado = new SistemaBancarioLegado();
        ProcessadorTransacoes meuProcessador = new TransacaoLegadoAdapter(sistemaLegado);
        
        System.out.println("Executando Transação 1 (Sucesso");
        // 2. O cliente faz a chamada usando a interface moderna e limpa
        RespostaTransacao resp1 = meuProcessador.autorizar("1234-5678-9012-3456", 250.75, "BRL");
        System.out.println("[CLIENTE MODERNO] Resposta recebida: " + resp1);
        
        System.out.println("\n Executando Transação 2 (Falha)");
        // 3. O cliente faz outra chamada (esta irá falhar)
        RespostaTransacao resp2 = meuProcessador.autorizar(null, -10.0, "USD");
        System.out.println("[CLIENTE MODERNO] Resposta recebida: " + resp2);
    }
}
 
