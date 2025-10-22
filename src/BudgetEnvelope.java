/**
 * Enhanced interface for the BudgetEnvelope component.
 * Extends the kernel with additional helper methods for comparison
 * and goal tracking between envelopes.
 *
 * @author Jeongui Hong
 */
public interface BudgetEnvelope extends BudgetEnvelopeKernel {
    /**
     * Transfers a given amount from this envelope to another one
     * @param target
     *            the BudgetEnvelope to receidve funds
     * @param amount
     *            the amount to transfer
     * @requires this.currentBalance() >= amount
     * @updates this, target
     * @ensures
     *     this.currenBalance = #this.currentBalance - amount and
     *     target.currentBalance = #target.currentBalance + amount
     */
    void transferTo(BudgetEnvelope target, int amount);

    /**
     * Checks whether this envelope has a negative balance
     * @return
     *         true if balance is below zero, false otherwise
     * @ensures
     *         isDeficit = (currentBalance <0)
     */
    boolean isDeficit( );

    /**
     * Determines if the balance meets or exceeds a goal amount
     * @param goal
     *            the target savings amount
     * @return
     *         true if current balance >= goal
     * @ensures
     *         reachedGoal = (currentBalance >= goal)
     */
    boolean reachedGoal(int goal);
}
