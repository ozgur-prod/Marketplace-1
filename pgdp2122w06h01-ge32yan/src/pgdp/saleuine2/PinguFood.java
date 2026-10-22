package pgdp.saleuine2;

public class PinguFood {

    private int age;
    private int weight;

    public PinguFood(int age, int weight){
        this.age = age;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isEdible(){
        return false;
    }

    public String toString(){
        return "Alter: " + getAge() + " Jahre, Gewicht: " + getWeight() + "g";
    }


}
