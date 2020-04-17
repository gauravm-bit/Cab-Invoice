import org.junit.Assert;
import org.junit.Test;

public class InvoiceGeneratorTest {

    InvoiceGenerator invoiceGenerator = new InvoiceGenerator(TypesOfCab.NORMAL_RIDE);
    InvoiceGenerator premium_invoice = new InvoiceGenerator(TypesOfCab.PREMIUM_RIDE);

    @Test
    public void givenDistanceAndTime__ThenShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0);
    }

    @Test
    public void givenDistanceOrTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 1)
        };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 1)
        };
        invoiceGenerator.userRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedSummary, summary);
    }

    @Test
    public void givenDistanceAndTime__ThenReturnPremiumTotalFare() {
        double distance = 2.0;
        int time = 5;

        double fare = premium_invoice.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0);
    }

    @Test
    public void givenDistanceOrTime_ShouldReturnPremiumMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = premium_invoice.calculateFare(distance, time);
        Assert.assertEquals(20, fare, 0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnPremiumSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = premium_invoice.calculateFare(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnPremiumSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        premium_invoice.userRides(userId, rides);
        InvoiceSummary summary = premium_invoice.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedSummary, summary);
    }

}

