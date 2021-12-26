import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorTest {
    private static Car car1 = new Car(2020, "Toyota", "Model1", 1234, 200, 13000);
    private static Car car2 = new Car(2015, "Toyota", "Model2", 55555, 10, 4000);
    private static Car car3 = new Car(2021, "Fancy", "Model3", 0, 50, 70000);
    private static Car car4 = new Car(2017, "Fancy", "Model4", 15000, 800, 13001);
    private static Car carA = new Car(2021,"Best", "Car1", 0, 100, 100);
    private static Car carB = new Car(2020,"Best", "Car2", 0, 100, 100);
    private static Car carC = new Car(2020, "Best", "Car3", 100, 0, 100);
    private static Car carD = new Car(2020, "Best", "Car4", 100, 0, 200);
    private static LinkedList<Car> carList = new LinkedList<Car>();
    private static LinkedList<Car> paretoCarList = new LinkedList<Car>();
    @BeforeAll
    public static void Setup(){
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);

        paretoCarList.add(carB);
        paretoCarList.add(carC);
        paretoCarList.add(carA);
        paretoCarList.add(carD);
    }

    @Test
    public void PriceComparatorTest(){
        Collections.sort(carList,(Car c1, Car c2)->c1.getPrice()-c2.getPrice());
        LinkedList<Car> expected = new LinkedList<Car>();
        expected.add(car2);
        expected.add(car1);
        expected.add(car4);
        expected.add(car3);
        assertEquals(expected,carList);
    }

    @Test
    public void YearComparatorTest(){
        Collections.sort(carList,(Car c1, Car c2)->c2.getYear()-c1.getYear());
        LinkedList<Car> expected = new LinkedList<Car>();
        expected.add(car3);
        expected.add(car1);
        expected.add(car4);
        expected.add(car2);
        assertEquals(expected,carList);
    }
    @Test
    public void MileageComparatorTest(){
        Collections.sort(carList,(Car c1, Car c2)->c1.getMileage()-c2.getMileage());
        LinkedList<Car> expected = new LinkedList<Car>();
        expected.add(car3);
        expected.add(car1);
        expected.add(car4);
        expected.add(car2);
        assertEquals(expected,carList);
    }
    @Test
    public void ParetoComparatorTest(){
        for(int i=0;i<paretoCarList.size();i++){
            for(int j=0;j<paretoCarList.size();j++){
                Car c1 = paretoCarList.get(i);
                Car c2 = paretoCarList.get(j);
                if (c1.getMileage()<c2.getMileage()&&
                        c1.getYear()>=c2.getYear()&&
                        c1.getPrice()<=c2.getPrice()){
                    c1.setDominationCount(c1.getDominationCount()+1);
                }
                else if(c1.getYear()>c2.getYear()&&
                        c1.getMileage()<=c2.getMileage()&&
                        c1.getPrice()<=c2.getPrice()){
                    c1.setDominationCount(c1.getDominationCount()+1);
                }
                else if(c1.getPrice()<c2.getPrice()&&
                        c1.getMileage()<=c2.getMileage()&&
                        c1.getYear()>=c2.getYear()){
                    c1.setDominationCount(c1.getDominationCount()+1);
                }
            }

        }
        Collections.sort(paretoCarList,(Car c1, Car c2)->c2.getDominationCount()-c1.getDominationCount());
        LinkedList<Car> expected = new LinkedList<Car>();
        expected.add(carA);
        expected.add(carB);
        expected.add(carC);
        expected.add(carD);
        assertEquals(expected,paretoCarList);

    }

}
