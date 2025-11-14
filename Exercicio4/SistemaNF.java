/* Demonstar Chain e Mediator: Gerencia a cadeia de validadores,responsável 
por implementar os requisitos complexos que os validadores individuais não devem conhecer */

package Exercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SistemaNF {

    private final List<IValidador> chain = new ArrayList<>();

    public void addValidator(IValidador validator) {
        this.chain.add(validator);
    }

    public boolean processar(String documento) {
        ValidationContext context = new ValidationContext(documento);
        boolean criticalPathSuccess = true;

        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("Iniciando processamento da NF-e: " + documento + "\n");
        
        for (IValidador validator : chain) {

            if (context.getFailureCount() >= 3) {
                System.out.println("CIRCUIT BREAKER: 3 falhas. Interrompendo cadeia.");
                break;
            }

            if ((validator instanceof ValidadorRegrasFiscais || validator instanceof ValidadorSefaz)
                && !criticalPathSuccess) {
                System.out.println("Pulando: " + validator.getClass().getSimpleName() + " (falha na validação crítica).");
                continue;
            }

            try {
                Callable<Void> task = () -> {
                    validator.validar(context);
                    return null;
                };
                Future<Void> future = executor.submit(task);
                future.get(validator.getTimeoutMillis(), TimeUnit.MILLISECONDS);

            } catch (TimeoutException e) {
                context.addError("Timeout no validador: " + validator.getClass().getSimpleName());
            } catch (Exception e) {
                context.addError("Erro Inesperado: " + e.getMessage());
            }

            // Atualiza a flag da Restrição 
            if (context.temFalhas() && (validator instanceof ValidadorSchemaXML || validator instanceof ValidadorCertificado)) {
                criticalPathSuccess = false;
            }
        }
        
        executor.shutdownNow();        
        if (context.temFalhas()) {
            System.out.println("\nProcessamento Falhou. " + context.getFailureCount() + " erro(s) encontrado(s):");
            context.getErrors().forEach(System.out::println);
            context.executarRollback();
            return false;
        } else {
            System.out.println("\nProcessamento concluído com sucesso.");
            return true;
        }
    }

    public static void main(String[] args) {
        SistemaNF orchestrator = new SistemaNF();
        orchestrator.addValidator(new ValidadorSchemaXML());       
        orchestrator.addValidator(new ValidadorCertificado());  
        orchestrator.addValidator(new ValidadorRegrasFiscais());   
        orchestrator.addValidator(new ValidadorDbDuplicidade());   
        orchestrator.addValidator(new ValidadorSefaz());           

        // Simulação de teste
        orchestrator.processar("NFe-123");
    }
}