/* Esta é a classe cliente que usa o padrão Strategy. Responsável por 
gerenciar o contexto financeiro e delegar o cálculo de risco para a 
estratégia atualmente configurada.*/

public class RiskAnalysisProcessor {
    private RiskCalculationStrategy currentStrategy;
    private FinancialContext financialContext;

    public RiskAnalysisProcessor(FinancialContext context) {
        this.financialContext = financialContext;
        this.currentStrategy = null;
    }

    public void setStrategy(RiskCalculationStrategy strategy) {
        this.currentStrategy = strategy;
        System.out.println("Estratégia de cálculo de risco alterada");
    }

    public void processRiskAnalysis() {
        if (currentStrategy == null) {
            System.out.println("Nenhuma estratégia de cálculo de risco configurada.");
            return;
        }
        currentStrategy.calculateRisk(financialContext);
    }
}
