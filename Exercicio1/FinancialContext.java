/* Auxiliar para agrupar os "múltiplos parâmetros financeiros */

public class FinancialContext {
    private double porfolioValue;
    private double volatility;
    private int timeHorizonDays;
    private double confidenceLevel;

    public FinancialContext(double porfolioValue, double volatility, int timeHorizonDays, double confidenceLevel) {
        this.porfolioValue = porfolioValue;
        this.volatility = volatility;
        this.timeHorizonDays = timeHorizonDays;
        this.confidenceLevel = confidenceLevel;
    }
}
