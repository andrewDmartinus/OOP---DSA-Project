class Driver {
    private String driverID;
    private String driverName;
    private double baseSalary;
    private int performanceScore;

    public Driver(String driverID, String driverName) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.baseSalary = 0.0;
        this.performanceScore = 0;

    }

    public Driver(double baseSalary) {
        this.baseSalary = baseSalary;
        this.driverID = "Unknown";
        this.driverName = "Unknown";
        this.performanceScore = 1;
    }

    public Driver(String driverID, String driverName, double baseSalary, int performanceScore) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.baseSalary = baseSalary;
        this.performanceScore = performanceScore;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(int performanceScore) {
        if (performanceScore >= 1 && performanceScore <= 5) {
            this.performanceScore = performanceScore;
        } else {
            this.performanceScore = 1;
        }
    }

    public double calculateBonus() {
        return calculateBonus(this.performanceScore);

    }

    public double calculateBonus(int customScore) {
        double bonusPercentage = 0.0;

        switch (customScore) {
            case 5:
                bonusPercentage = 0.25;
                break;

            case 4:
                bonusPercentage = 0.20;
                break;

            case 3:
                bonusPercentage = 0.15;
                break;

            case 2:
                bonusPercentage = 0.10;
                break;

            case 1:
                bonusPercentage = 0.0;
                break;

            default:
                bonusPercentage = 0.0;
        }
        return baseSalary * bonusPercentage;
    }

    public double calculateTotalSalary() {
        return baseSalary + calculateBonus();
    }

    public void displayDriverDetails() {
        System.out.println("Driver ID: " + driverID);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Performance Score: " + performanceScore);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Total Salary: " + calculateTotalSalary());

    }
}
    public class Main{
        public static void main(String[] args){
            Driver driver1 = new Driver("0001", "John Doe", 4000.0, 5);
            Driver driver2 = new Driver("0002", "Emily Davis", 3500.0, 3);
            Driver driver3 = new Driver("0003", "Robert Johnson", 3200.0, 1);

            driver1.displayDriverDetails();
            driver2.displayDriverDetails();
            driver3.displayDriverDetails();
        }
    }



