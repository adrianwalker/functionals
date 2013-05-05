package org.adrianwalker.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Functionals {

  private static final String EMPTY_STRING = "";
  private static final List EMPTY_LIST = Collections.EMPTY_LIST;

  private Functionals() {
  }

  public static <T1, T2> T2 foldr(final Function<T1, T2> f, final T2 b, final List<T1> lst) {
    if (lst.isEmpty()) {
      return b;
    }

    return f.call(head(lst), foldr(f, b, tail(lst)));
  }

  public static <T1, T2> T2 foldl(final Function<T1, T2> f, final T2 b, final List<T1> lst) {
    if (lst.isEmpty()) {
      return b;
    }

    return foldl(f, f.call(head(lst), b), tail(lst));
  }

  public static <T1, T2> List<T2> map(final Function<T1, T2> f, final List<T1> lst) {
    return foldr(new Function<T1, List<T2>>() {

      @Override
      public List<T2> call(final T1 t1, final List<T2> t2) {
        return cons(f.call(t1), t2);
      }
    }, (List<T2>) EMPTY_LIST, lst);
  }

  public static <T1> List<T1> cons(final T1 x, final List<T1> xs) {
    List<T1> lst = newList(xs.size() + 1);
    lst.add(0, x);
    lst.addAll(1, xs);
    return Collections.unmodifiableList(lst);
  }

  public static <T1> T1 head(final List<T1> lst) {
    return lst.get(0);
  }

  public static <T1> List<T1> tail(final List<T1> lst) {
    List<T1> xs = newList(lst.size() - 1);
    xs.addAll(0, lst.subList(1, lst.size()));
    return Collections.unmodifiableList(xs);
  }

  public static List<Character> explode(final String string) {
    if (string.isEmpty()) {
      return (List<Character>) EMPTY_LIST;
    }

    return cons(string.charAt(0), explode(string.substring(1)));
  }

  public static String implode(final List<Character> lst) {
    return foldl(new Function<Character, String>() {

      @Override
      public String call(final Character t1, final String t2) {
        return t2 + t1;
      }
    }, EMPTY_STRING, lst);
  }

  public static <T1> List<T1> rev(final List<T1> lst) {
    return foldl(new Function<T1, List<T1>>() {

      @Override
      public List<T1> call(final T1 t1, final List<T1> t2) {
        return cons(t1, t2);
      }
    }, (List<T1>) EMPTY_LIST, lst);
  }

  private static <E> List<E> newList(final int initialCapacity) {
    return new ArrayList<E>(initialCapacity);
  }
}
