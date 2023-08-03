package service;

import java.util.List;

public interface CRUD<E> {
    void add(E e);
    List<E> read();
    void write(List<E> obj);

    void delete();
    void search();
}

