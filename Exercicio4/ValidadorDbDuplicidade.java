/* A classe só valida duplicidade e fornece seu próprio rollback */

package Exercicio4;

import java.util.concurrent.TimeoutException;

public class ValidadorDbDuplicidade implements IValidador {
    @Override
    public void validar(ValidationContext context) throws TimeoutException {
        String docNum = context.getDocumento();
        System.out.println("4. Validando Duplicidade DB: Verificando NFe " + docNum);
        
        // Simula a inserção de um registro "Pendente" no DB
        System.out.println("Inserindo NFe " + docNum + " como pendente no DB.");

        // Implementação do rollback
        // Criamos um "Command" (Runnable) que sabe como desfazer esta ação.
        Runnable undoCommand = () -> {
            System.out.println("... ROLLBACK DB: Removendo NFe " + docNum + " do DB.");
        };
        
        // Adiciona o "undo" à pilha do contexto.
        context.pushRollback(undoCommand);
    }
    
    @Override
    public long getTimeoutMillis() { return 3000; } // 3s
}