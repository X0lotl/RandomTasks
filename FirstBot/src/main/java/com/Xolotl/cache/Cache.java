package com.Xolotl.cache;

import java.util.List;

public interface Cache <T>{
    void add(T t);
    void remove(T t);
    T findBy(Long Id);
    List<T> getAll();
}
