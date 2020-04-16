public class InvoiceGenerator {
    private static final double COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    public static void main(String[] args) {
        System.out.println("welcome");
    }

    public double calculateFare(double distance, int time) {
        double fare =  distance* COST_PER_KILOMETER + time * COST_PER_MINUTE;
        if(fare < MINIMUM_FARE)
            return MINIMUM_FARE;
        return fare;
    }

    public double calculateFare(Ride[] rides) {
        double fare = 0;
        for (Ride ride:rides){
            fare += this.calculateFare(ride.distance, ride.time);
        }
        return fare;
    }
}
