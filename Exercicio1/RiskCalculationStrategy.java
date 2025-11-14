/*Interface Strategy - define a interface comum para todos 
os algortimos de cálculo de risco*/

public interface RiskCalculationStrategy {
    void calculateRisk(FinancialContext context);
}