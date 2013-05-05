package org.adrianwalker.functional;

public abstract class Function<T1, T2> {

  public T2 call(final T1 t1) {
    throw new UnsupportedOperationException();
  }

  public T2 call(final T1 t1, final T2 t2) {
    throw new UnsupportedOperationException();
  }
}
