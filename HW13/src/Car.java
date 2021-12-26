public class Car {
    private int year;
    private String make;
    private String model;
    private int mileage;
    private int distance;
    private int price;
    private  int dominationCount;
    public Car(int year, String make, String model, int mileage, int distance, int price){
        this.year=year;
        this.make=make;
        this.model=model;
        this.mileage=mileage;
        this.distance=distance;
        this.price=price;
    }
    public int getYear(){
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setDominationCount(int dominationCount) {
        this.dominationCount = dominationCount;
    }
}
