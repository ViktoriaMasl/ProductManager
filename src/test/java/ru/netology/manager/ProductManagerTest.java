package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
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

    @ParameterizedTest
    @CsvFileSource(resources = "/SearchByExist.csv")
    void shouldSearchByExist(int index, String text) {
        assertEquals(repository.getAll()[index], manager.searchBy(text)[0]);
    }

    @Test
    void shouldSearchByNotExist() {
        assertArrayEquals(new Product[]{}, manager.searchBy("Nokia"));
    }
}