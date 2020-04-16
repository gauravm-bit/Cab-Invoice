public class InvoiceGenerator {
    private static final double COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final double MIN_FARE = 5;

    public static void main(String[] args) {
        System.out.println("welcome");
    }

    public double calculateFare(double distance, int time) {
        double fare =  distance* COST_PER_KILOMETER + time * COST_PER_MINUTE;
        if(fare < MIN_FARE)
            return MIN_FARE;
        return fare;
    }
}
