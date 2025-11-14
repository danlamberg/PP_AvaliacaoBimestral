/* Carrega o documento e o estado compartilhado pela cadeia. */

package Exercicio4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ValidationContext {
    private String documento; // Simulação de um documento NF-e
    private List<String> errors = new ArrayList<>();
    private int failureCount = 0;

private Deque<Runnable> rollbackStack = new ArrayDeque<>();
    public ValidationContext(String documento) {
        this.documento = documento;
    }

    public void addError(String message) {
        this.failureCount++;
        this.errors.add(message);
    }

    public int getFailureCount() {
        return this.failureCount;
    }

    public void pushRollback(Runnable undoOperation) {
        this.rollbackStack.push(undoOperation);
    }

    // Acionado pelo Orquestrador se a cadeia falhar.
    public void executarRollback() {
        System.out.println("Rollback: Iniciando reversão de " + rollbackStack.size() + " operações...");
        while (!rollbackStack.isEmpty()) {
            rollbackStack.pop().run(); // Executa o Runnable (undo)
        }
    }

    public boolean temFalhas() {
        return this.failureCount > 0;
    }
    
    public String getDocumento() { return documento; }
    public List<String> getErrors() { return errors; }
}