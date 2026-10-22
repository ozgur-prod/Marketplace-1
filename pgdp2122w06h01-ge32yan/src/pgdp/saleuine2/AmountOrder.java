package pgdp.saleuine2;

import java.math.BigDecimal;

public class AmountOrder extends TradeOrder{

    private final int targetAmountAnchovies;
    private final int targetAmountCrustaceans;
    private final int targetAmountSardines;
    private int currentAmountAnchovies;
    private int currentAmountCrustaceans;
    private int currentAmountSardines;

    public AmountOrder(int targetAmountAnchovies, int targetAmountCrustaceans, int targetAmountSardines){
        this.targetAmountAnchovies = targetAmountAnchovies;
        this.targetAmountCrustaceans = targetAmountCrustaceans;
        this.targetAmountSardines = targetAmountSardines;
        currentAmountAnchovies = 0;
        currentAmountCrustaceans = 0;
        currentAmountSardines = 0;
    }

    public int getTargetAmountAnchovies() {
        return targetAmountAnchovies;
    }

    public int getTargetAmountCrustaceans() {
        return targetAmountCrustaceans;
    }

    public int getTargetAmountSardines() {
        return targetAmountSardines;
    }

    public int getCurrentAmountAnchovies() {
        return currentAmountAnchovies;
    }

    public int getCurrentAmountCrustaceans() {
        return currentAmountCrustaceans;
    }

    public int getCurrentAmountSardines() {
        return currentAmountSardines;
    }

    @Override
    public String orderType() {
        return "Anzahl: [" + getTargetAmountAnchovies() + "," + getTargetAmountCrustaceans() + "," + getTargetAmountSardines() + "]";
    }

    @Override
    public String toString() {
        return "Die Bestellung(Anzahl: [" + getCurrentAmountAnchovies() + "," + getCurrentAmountCrustaceans() + ","
                + getCurrentAmountSardines() + "]) hat ein Gesamtgewicht von " +
                getCurrentWeight() + "g und kostet "+ getTotalCost() + "PD.";
    }

    @Override
    public boolean isOrderFulfilled() {
        return getCurrentAmountAnchovies() == getTargetAmountAnchovies() && getCurrentAmountCrustaceans() == getTargetAmountCrustaceans()
                && getCurrentAmountSardines() == getTargetAmountSardines();
    }

    @Override
    public boolean supplyOrder(PinguFood supply, BigDecimal cost) {

        if (supply.isEdible() && !isOrderFulfilled()) {
            if (supply instanceof Anchovie) {
                boolean is = super.supplyOrder(supply,cost);
                currentAmountAnchovies++;
                return is;
            } else if (supply instanceof Crustacean) {
                boolean is = super.supplyOrder(supply,cost);
                currentAmountCrustaceans++;
                return is;
            } else if (supply instanceof Sardine) {
                boolean is = super.supplyOrder(supply,cost);
                currentAmountSardines++;
                return is;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

}
