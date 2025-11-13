/* Demonstrar o Padrão Strategy em ação. */

public class FinancialSystemDemo {
    
    public static void main(String[] args) {
        
        // 1. Cria o "contexto complexo" que será compartilhado
        FinancialContext context = new FinancialContext(1000000.0, 0.15, 30, 0.95);

        // 2. Cria o processador (O Contexto do padrão)
        RiskAnalysisProcessor processor = new RiskAnalysisProcessor(context);

        // 3. Necessidade de Negócios: "Análise de risco padrão (VaR)"
        System.out.println("\n[NECESSIDADE DE NEGÓCIOS: Análise padrão VaR]");
        processor.setStrategy(new ValueAtRiskStrategy());
        processor.processRiskAnalysis();

        // 4. Necessidade de Negócios: "Analisar perdas extremas (Stress Test)"
        // O algoritmo é trocado DINAMICAMENTE
        System.out.println("\n[NECESSIDADE DE NEGÓCIOS: Simular crise de mercado]");
        processor.setStrategy(new StressTestingStrategy());
        processor.processRiskAnalysis();
        
        // 5. Necessidade de Negócios: "Calcular perda média na 'cauda' (ES)"
        // O algoritmo é trocado novamente
        System.out.println("\n[NECESSIDADE DE NEGÓCIOS: Calcular perda média em cenários ruins]");
        processor.setStrategy(new ExpectedShortfallStrategy());
        processor.processRiskAnalysis();

        System.out.println("\n...Análises de Risco concluídas.");
    }
}