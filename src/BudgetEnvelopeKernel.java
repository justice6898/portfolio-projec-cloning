import components.standard.Standard;

/**
 * Kernel interface for BudgetEnvelope.
 * Provides minimal methods to model a simple budget tracking component
 *
 * @author Jeongui Hong
 *
 */
public interface BudgetEnvelopeKernel extends Standard<BudgetEnvelope> {
    /**
     * Adds an expense to the current budget
     * @param amount
     *            the expanse amount to add
     * @updates this
     * @ensures currentBudget = #currentBudget - amount
     */
    void addExpense(int amount );

    /**
     * Adds income to the current budget.
     * @param amount
     *            the income amount to add
     * @updates this
     * @ensures currentBudget = #currenntBudget + amount
     */
    void addIncome(int amount);

    /**
     * Resets the budget envelope to zero.
     * @clears this
     * @ensures currentBudget = 0
     */
    void clearBudget();

    /**
     * Reports the current balance.
     * @return the remaining budget
     * @ensures currentBudget = #currentBudget
     */
    int currentBalance( );
}
