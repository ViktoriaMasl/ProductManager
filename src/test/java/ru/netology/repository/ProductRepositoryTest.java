package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    private Product first = new Book(1, "Дракула", 400, "Брэм Стокер");
    private Product second = new Book(2, "Мастер и Маргарита", 500, "Михаил Булгаков");
    private Product third = new Book(3, "Властелин колец", 600, "Джон Рональд Руэл Толкин");
    private Product fourth = new Smartphone(4, "XIAOMI Mi 10", 59999, "XIAOMI");
    private Product fifth = new Smartphone(5, "iPhone 12", 79990, "APPLE");
    private Product sixth = new Smartphone(6, "Galaxy S20 FE", 49990, "Samsung");

    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    void shouldRemoveExistId() {
        int idForRemove = 5;
        repository.removeById(idForRemove);
        assertArrayEquals(new Product[]{first, second, third, fourth, sixth}, repository.getAll());
    }

    @Test
    void shouldRemoveNotExistId() {
        int idForRemove = 7;
        assertThrows(NotFoundException.class, () -> repository.removeById(idForRemove));
        assertArrayEquals(new Product[]{first, second, third, fourth, fifth, sixth}, repository.getAll());
    }
}