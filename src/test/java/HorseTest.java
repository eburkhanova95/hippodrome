import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void blankNameWithoutCheckedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));

    }

    @Test
    public void blankNameWithCheckedMessage() {
        try {
            new Horse(null, 1,1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());

        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t"})
    public void tabNameWithoutCheckedMessage(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t"})
    public void tabNameWithCheckedMessage(String name) {
        try{
            new Horse(name, 1,1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }

    }


    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, -100.0, -100})
    public void speedWithoutCheckedException(double speed) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -1,1));


    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, -100.0, -100})
    public void speedParamWithCheckedException(double speed) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -1,1));
        assertEquals("Speed cannot be negative.",exception.getMessage() );

    }


    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, -50.0, -5000})
    public void distanceParamWithoutCheckedException(double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 1,-40));

    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, -50.0, -5000})
    public void distanceParamWithCheckedException(double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 1,-20));
        assertEquals("Distance cannot be negative.",exception.getMessage() );
    }


    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException{
        Horse horse = new Horse("Pony", 1,1);


        assertEquals("Pony",horse.getName());



    }

    @Test
    public void getSpeed(){
        double expectedSpeed = 200;
        Horse horse = new Horse("Pony", expectedSpeed,1);
        assertEquals(expectedSpeed,horse.getSpeed());
    }

    @Test
    public void getDistance(){
        double expectedDistance = 100;
        Horse horse = new Horse("Pony", 1,expectedDistance);
        assertEquals(expectedDistance,horse.getDistance());
    }

    @Test
    public void zeroDistance(){

        Horse horse = new Horse("Pony", 1);
        assertEquals(0,horse.getDistance());
    }

    @Test
    public void move(){
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("Pony",50,234).move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 1.0, 999.9})
    public void move(double random){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Pony", 20, 450);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            horse.move();
            assertEquals(450+ 20*random,horse.getDistance());
        }
    }




}
