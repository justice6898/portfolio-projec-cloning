import components.map.Map;
import components.map.Map1L;

public class BudgetEnvelope {

    // Each category name (key) is paired with its balance (value)
    private Map<String, Integer> envelopes;

    // Constructor: initialize empty budget map
    public BudgetEnvelope() {
        envelopes = new Map1L<>();
    }

    // Kernel methods
    // Add a new envelope with an initial budget
    public void addEnvelope(String category, int amount) {
        if (!envelopes.hasKey(category)) {
            envelopes.add(category, amount);
        }
    }

    // Record an expense by subtracting from the selected category
    public void recordExpense(String category, int amount) {
        if (envelopes.hasKey(category)) {
            int balance = envelopes.value(category);
            balance -= amount;
            envelopes.replaceValue(category, balance);
        }
    }

    // Check remaining balance in the chosen category
    public int checkBalance(String category) {
        if (envelopes.hasKey(category)) {
            return envelopes.value(category);
        } else {
            return 0;
        }
    }

    // Secondary methods
    // Calculate the total amount across all envelopes
    public int totalBalance() {
        int total = 0;
        Map<String, Integer> temp = envelopes.newInstance();
        temp.transferFrom(envelopes);

        while (temp.size() > 0) {
            Map.Pair<String, Integer> pair = temp.removeAny();
            total += pair.value();
        }

        // Restore envelopes to original state
        envelopes.transferFrom(temp);
        return total;
    }

    // Check if spending in a category exceeds its balance
    public boolean isOverspent(String category) {
        if (envelopes.hasKey(category)) {
            int balance = envelopes.value(category);
            return balance < 0;
        } else {
            return false;
        }
    }

    // Display a simple summary of all categories and balances
    public Map<String, Integer> monthlySummary() {
        Map<String, Integer> summary = new Map1L<>();
        Map<String, Integer> temp = envelopes.newInstance();
        temp.transferFrom(envelopes);

        while (temp.size() > 0) {
            Map.Pair<String, Integer> pair = temp.removeAny();
            summary.add(pair.key(), pair.value());
        }

        // Restore original data
        envelopes.transferFrom(temp);
        return summary;
    }

    // Main method (Proof of Concept)
    public static void main(String[] args) {
        BudgetEnvelope budget = new BudgetEnvelope();

        budget.addEnvelope("Food", 300);
        budget.addEnvelope("Rent", 1000);
        budget.recordExpense("Food", 50);
        budget.recordExpense("Rent", 600);

        int foodBal = budget.checkBalance("Food");
        int total = budget.totalBalance();
        boolean rentOver = budget.isOverspent("Rent");
        Map<String, Integer> report = budget.monthlySummary();

        // Example: client handles the printing (not inside component)
        System.out.println("Food balance: $" + foodBal);
        System.out.println("Total balance: $" + total);
        System.out.println("Rent overspent? " + rentOver);
        System.out.println("=== Monthly Summary ===");
        while (report.size() > 0) {
            Map.Pair<String, Integer> p = report.removeAny();
            System.out.println(p.key() + ": $" + p.value());
        }
    }
}
