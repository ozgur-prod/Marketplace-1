package pgdp.saleuine2;

//import java.math.BigDecimal;

public class WeightOrder extends TradeOrder{

    private final int targetWeight;

    public WeightOrder(int targetWeight){
        this.targetWeight = targetWeight;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    @Override
    public String orderType() {
        return "Zielgewicht: " + getTargetWeight() + "g";
    }

    @Override
    public String toString() {
        return "Die Bestellung(Zielgewicht: " + getTargetWeight() + "g) hat ein Gesamtgewicht von " + getCurrentWeight() +
                "g und kostet "+ getTotalCost() + "PD.";
    }

    @Override
    public boolean isOrderFulfilled() {
        return getCurrentWeight() >= getTargetWeight();
    }

}
