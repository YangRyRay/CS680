import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarTest {

    private String[] carToStringArray(Car subj){
        String[] carInfo = {subj.getMake(),
                            subj.getModel(),
                            String.valueOf(subj.getYear())};
        return carInfo;
    }

    @Test
    public void verifyCarEqualityWithMakeModelYear(){
        String[] expected = {"Toyota", "RAV4", "2018"};
        Car car1 = new Car("Toyota", "RAV4",2000,2018,10000);
        assertArrayEquals(expected,carToStringArray(car1));
    }
    @Test
    public void verifyCarEqualityWithMakeModelYear2Car(){
        Car car1 = new Car("Toyota", "RAV4",2000,2018,10000);
        Car car2 = new Car("Toyota", "RAV4",2320,2018,11350);
        assertArrayEquals(carToStringArray(car1),carToStringArray(car2));
    }
}
