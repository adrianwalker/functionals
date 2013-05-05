package org.adrianwalker.functional.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.adrianwalker.functional.Function;
import org.adrianwalker.functional.Functionals;
import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionalsTest {

  @Test
  public void foldrSum() {
    
    List<Integer> input = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

    Integer output = Functionals.foldr(new Function<Integer, Integer>() {

      @Override
      public Integer call(final Integer t1, final Integer t2) {
        return t1 + t2;
      }
    }, 0, input);

    assertEquals(55, output.intValue());
  }

  @Test
  public void foldlPartialSum() {

    List<Integer> input = Arrays.asList(new Integer[]{1, 4, 8, 3, 7, 9});

    List<Integer> output = Functionals.rev(Functionals.foldl(new Function<Integer, List<Integer>>() {

      @Override
      public List<Integer> call(final Integer t1, final List<Integer> t2) {
        return Functionals.cons(t1 + Functionals.head(t2), t2);
      }
    }, new LinkedList<Integer>(Arrays.asList(new Integer[]{0})), input));

    assertArrayEquals(new Integer[]{0, 1, 5, 13, 16, 23, 32}, output.toArray());
  }

  @Test
  public void mapSquare() {

    List<Integer> input = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

    List<Integer> output = Functionals.map(new Function<Integer, Integer>() {

      @Override
      public Integer call(final Integer t1) {
        return t1 * t1;
      }
    }, input);

    assertArrayEquals(new Integer[]{1, 4, 9, 16, 25, 36, 49, 64, 81, 100}, output.toArray());
  }

  @Test
  public void mapImplodeRevExplode() {
    List<String> input = Arrays.asList(new String[]{"my", "happy", "cat"});

    List<String> output = Functionals.rev(Functionals.map(new Function<String, String>() {

      @Override
      public String call(final String t1) {
        return Functionals.implode(Functionals.rev(Functionals.explode(t1)));
      }
    }, input));

    assertArrayEquals(new String[]{"tac", "yppah", "ym"}, output.toArray());
  }
}
