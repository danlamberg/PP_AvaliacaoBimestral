/* Selecione qual exercício deseja executar*/

import java.util.InputMismatchException;
import java.util.Scanner;

import Exercicio2.SistemaFinanceiro;
import Exercicio3.SistemaUsinaNuclear;
import Exercicio4.SistemaNF;

public class AvaliacaoBimestral {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
    
        System.out.println("  AVALIAÇÃO PRÁTICA - Padrões de Projetos");
        System.out.println("  Universidade Positivo - Prof. Escobar");
        System.out.println("  Aluno: Daniel");

        while (running) {
            System.out.println("\n### MENU PRINCIPAL ###");
            System.out.println("1. Executar Questão 1 (Strategy)");
            System.out.println("2. Executar Questão 2 (Adapter)");
            System.out.println("3. Executar Questão 3 (State/Decorator)");
            System.out.println("4. Executar Questão 4 (Chain/Mediator)");
            System.out.println("0. Sair do Programa");
            System.out.print("\nEscolha uma atividade: ");
        
        try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n--- [Iniciando Questão 1: STRATEGY] ---");
                        FinancialSystem.main(null);
                        break;
                    case 2:
                        System.out.println("\n--- [Iniciando Questão 2: ADAPTER] ---");
                        SistemaFinanceiro.main(null);
                        break;
                    case 3:
                        System.out.println("\n--- [Iniciando Questão 3: STATE/DECORATOR] ---");
                        SistemaUsinaNuclear.main(null);
                        break;
                    case 4:
                        System.out.println("\n--- [Iniciando Questão 4: CHAIN/MEDIATOR] ---");
                        SistemaNF.main(null);
                        break;
                    case 0:
                        running = false;
                        System.out.println("\nEncerrando... \nBom final de ano!");
                        break;
                        
                    default:
                        System.out.println("\nOpção inválida. Por favor, escolha um número de 0 a 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nErro: Por favor, digite apenas números.");
                scanner.next(); // Limpa o buffer do scanner para evitar um loop infinito
            }
        }

        scanner.close();
    }
}