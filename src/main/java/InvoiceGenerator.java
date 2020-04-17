public class InvoiceGenerator {
    private static final double COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;
    public final TypesOfCab typesOfCab;
    private static final double PREMIUM_COST_PER_KILOMETER = 15;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double PREMIUM_MIN_FARE = 20;

    RideRepository rideRepository = new RideRepository();

    public InvoiceGenerator(TypesOfCab typesOfCab) {
        this.typesOfCab = typesOfCab;
    }

    public static void main(String[] args) {
        System.out.println("welcome");
    }

    public double calculateFare(double distance, int time) {
        if (this.typesOfCab.equals(TypesOfCab.NORMAL_RIDE)) {
            double fare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
            return fare < MINIMUM_FARE ? MINIMUM_FARE : fare;
        } else if (this.typesOfCab.equals(TypesOfCab.PREMIUM_RIDE)) {
            double fare = distance * PREMIUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
            return fare < PREMIUM_MIN_FARE ? PREMIUM_MIN_FARE : fare;
        }
        return 0;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double fare = 0;
        for (Ride ride:rides){
            fare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length,fare);
    }

    public void userRides(String userId, Ride[] rides) {
        rideRepository.userRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
