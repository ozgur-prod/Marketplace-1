package pgdp.saleuine2;

import java.math.BigDecimal;

public class TradeOrder {

    private BigDecimal totalCost;
    private int currentWeight;

    public TradeOrder(){
        totalCost = BigDecimal.ZERO;
        currentWeight = 0;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public String orderType(){
        return "Einzeln";
    }

    public String toString(){
        return "Die Bestellung(Einzeln) hat ein Gesamtgewicht von " + getCurrentWeight() + "g und kostet " + getTotalCost() + "PD.";
    }

    public boolean isOrderFulfilled(){
        return getCurrentWeight() > 0;
    }

    public boolean supplyOrder(PinguFood supply, BigDecimal cost){
        if (!isOrderFulfilled()) {
            if (supply.isEdible()) {

                totalCost = totalCost.add(cost);
                currentWeight += supply.getWeight();
                return true;

            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }



}
