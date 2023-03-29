public class Main {
    public static void main(String[] args) {

        Highway highway = new Highway();

        // Change the interchange name to test it
        String startingInterchange = "Derry Road";
        String endInterchange = "QEW";


        double distance = highway.calculateDistanceBetweenTwoInterchange(startingInterchange, endInterchange);
        double cost = highway.calculatePriceForToll(distance);

        System.out.println("Trip Cost Between " + startingInterchange + " - " + endInterchange);
        System.out.println("DISTANCE : " + distance + " km");
        System.out.println("COST : $" + cost);

    }


}
