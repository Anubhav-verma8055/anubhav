//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        float distance = 240;

        Transport car = new Car();
        Transport bike = new Bike();
        Transport train = new Train();

        float carTime = car.travelTime(distance);
        float bikeTime = bike.travelTime(distance);
        float trainTime = train.travelTime(distance);

        String fastestMode;
        float minTime = carTime;

        if (bikeTime < minTime) {
            minTime = bikeTime;
            fastestMode = "Bike";
        } else {
            fastestMode = "Car";
        }

        if (trainTime < minTime) {
            minTime = trainTime;
            fastestMode = "Train";
        }
        System.out.println("The transport that takes minimum time is: " + fastestMode);
        System.out.println("Minimum travel time: " + minTime + " hours");
    }
}