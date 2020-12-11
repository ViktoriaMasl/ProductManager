package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRepository {
    private Product[] items = new Product[0];

    public void save(Product item) {
        int length = items.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Product[] getAll() {
        return items;
    }

    public void removeById(int id) {

        boolean hasProductWithId = false;
        int counter = 0;

        for (Product item : items) {
            if (item.getId() == id) {
                hasProductWithId = true;
                counter++;
            }
        }

        if (hasProductWithId) {
            int length = items.length - counter;
            Product[] tmp = new Product[length];
            int index = 0;

            for (Product item : items) {
                if (item.getId() != id) {
                    tmp[index] = item;
                    index++;
                }
            }
            items = tmp;
        }
    }
}
