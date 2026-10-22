package pgdp.saleuine2;

import static pgdp.PinguLib.*;

import java.math.BigDecimal;

public class PinguFoodLogistics {

	private TradeOrderQueue orderBook;
	private BigDecimal ppgAnchovies;
	private BigDecimal ppgCrustaceans;
	private BigDecimal ppgSardines;

	public PinguFoodLogistics(BigDecimal ppgAnchovies, BigDecimal ppgCrustaceans, BigDecimal ppgSardines){
		this.ppgAnchovies = ppgAnchovies;
		this.ppgCrustaceans = ppgCrustaceans;
		this.ppgSardines = ppgSardines;
		orderBook = new TradeOrderQueue();
	}

	public BigDecimal getPpgAnchovies() {
		return ppgAnchovies;
	}

	public BigDecimal getPpgCrustaceans() {
		return ppgCrustaceans;
	}

	public BigDecimal getPpgSardines() {
		return ppgSardines;
	}

	public TradeOrderQueue getOrderBook() {
		return orderBook;
	}

	public void acceptNewOrder(TradeOrder order){
		getOrderBook().add(order);
	}

	public void clearOrderBook(){

		System.out.println("Es können " + getOrderBook().size() + " Bestellungen abgearbeitet werden.");

		while (!getOrderBook().isEmpty()){

			TradeOrder firstOrder = getOrderBook().poll();
			while (!firstOrder.isOrderFulfilled()) {

				if (!(firstOrder instanceof AmountOrder)){

					PinguFood f = generatePinguFood();
					BigDecimal cost = f instanceof Anchovie ?
							getPpgAnchovies().multiply(BigDecimal.valueOf(f.getWeight())) : f instanceof Crustacean ?
							getPpgCrustaceans().multiply(BigDecimal.valueOf(f.getWeight())) : getPpgSardines().multiply(BigDecimal.valueOf(f.getWeight()));

					if (!firstOrder.supplyOrder(f, cost)) {
						registerUnusedFood(f);
					}
				}
				else {
					while (((AmountOrder) firstOrder).getCurrentAmountAnchovies() != ((AmountOrder) firstOrder).getTargetAmountAnchovies()) {
						PinguFood a = generateAnchovie();
						BigDecimal cost = getPpgAnchovies().multiply(BigDecimal.valueOf(a.getWeight()));
						if (!firstOrder.supplyOrder(a, cost)) {
							registerUnusedFood(a);
						}
					}

					while (((AmountOrder) firstOrder).getCurrentAmountCrustaceans() != ((AmountOrder) firstOrder).getTargetAmountCrustaceans()) {
						PinguFood c = generateCrustacean();
						BigDecimal cost2 = getPpgCrustaceans().multiply(BigDecimal.valueOf(c.getWeight()));
						if (!firstOrder.supplyOrder(c, cost2)) {
							registerUnusedFood(c);
						}
					}

					while (((AmountOrder) firstOrder).getCurrentAmountSardines() != ((AmountOrder) firstOrder).getTargetAmountSardines()) {
						PinguFood s = generateSardine();
						BigDecimal cost3 = getPpgSardines().multiply(BigDecimal.valueOf(s.getWeight()));
						if (!firstOrder.supplyOrder(s, cost3)) {
							registerUnusedFood(s);
						}
					}
				}

				}
			System.out.println(firstOrder.toString());
			}


	}

	private static int numberOfWaste;
	private static BigDecimal costOfWaste = BigDecimal.ZERO;
	private static int weightOfWaste;

	public static int getNumberOfWaste() {
		return numberOfWaste;
	}

	public static BigDecimal getCostOfWaste() {
		return costOfWaste;
	}

	public static int getWeightOfWaste() {
		return weightOfWaste;
	}

	private void registerUnusedFood(PinguFood food){
		numberOfWaste++;
		weightOfWaste += food.getWeight();

		if (food instanceof Anchovie){
			costOfWaste = costOfWaste.add(getPpgAnchovies().multiply(BigDecimal.valueOf(food.getWeight())));
		}
		else if (food instanceof Crustacean){
			costOfWaste = costOfWaste.add(getPpgCrustaceans().multiply(BigDecimal.valueOf(food.getWeight())));
		}
		else {
			costOfWaste = costOfWaste.add(getPpgSardines().multiply(BigDecimal.valueOf(food.getWeight())));
		}
	}

	public void printWasteStatistics(){
		System.out.println("Bisher konnten " + numberOfWaste + " Tiere mit einem Gesamtgewicht von " +
		weightOfWaste + "g nicht verwertet werden.\nClaudia und Karl-Heinz ist dadurch ein Profit von " +
		costOfWaste + "PD entgangen.");
	}













	public static void main(String[] args) {
		PinguFoodLogistics market = new PinguFoodLogistics(BigDecimal.ONE, BigDecimal.valueOf(0.5),
				BigDecimal.valueOf(2));
		market.acceptNewOrder(new TradeOrder());
		market.acceptNewOrder(new WeightOrder(1000));
		market.acceptNewOrder(new AmountOrder(2, 2, 2));
		market.clearOrderBook();
		market.printWasteStatistics();
	}

	/**
	 * The following methods generate Anchovie, Crustacean or Sardine object
	 * WARNING: do NOT change these methods unless you want to fail the tests
	 */

	// remove after implementing PinguFood + subclasses
	public static PinguFood generatePinguFood() {
		switch (randomInt(0, 2)) {
		case 0:
			return generateAnchovie();
		case 1:
			return generateCrustacean();
		case 2:
			return generateSardine();
		default:
			throw new SecurityException("You changed the code!");
		}
	}
	
	public static Anchovie generateAnchovie() {
		return new Anchovie(randomInt(0, 5), randomInt(1, 55));
	}
	
	public static Crustacean generateCrustacean() {
		return new Crustacean(randomInt(1, 10));
	}
	
	public static Sardine generateSardine() {
		return new Sardine(randomInt(0, 10), randomInt(20, 300), randomInt(1, 22));
	}
	// remove after implementing PinguFood + subclasses
}
