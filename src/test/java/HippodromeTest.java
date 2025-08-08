import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void nullName(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullNameChecked(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void emptyName(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

   @Test
   public void emptyNameChecked(){
       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
       assertEquals("Horses cannot be empty.", exception.getMessage());


   }

   @Test
    public void getHorses(){
       List<Horse> horses = new ArrayList<>();
       for (int i = 1; i < 30; i++) {
           horses.add(new Horse("" +i, i,i));

       }
       Hippodrome hippodrome = new Hippodrome(horses);
       assertEquals(horses,hippodrome.getHorses());
   }

   @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
       for (int i = 0; i < 50; i++) {
           horses.add(mock(Horse.class));
           
       }
       
       Hippodrome hippodrome = new Hippodrome(horses);
       
       hippodrome.move();
       for (Horse horse : horses) {
           verify(horse).move();
       }

   }

   @Test
    public void getWinner(){
        Horse horse1 = new Horse("Pony", 15,450);
        Horse horse2 = new Horse("Arabic", 45,3005);
        Horse horse3 = new Horse("Small", 30,65);
        Horse horse4 = new Horse("Big", 5,45);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertSame(horse2,hippodrome.getWinner());
   }




}
