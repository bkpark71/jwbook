package ch05;

import java.util.*;

public class ProductService {
    Map<String, Product> products = new HashMap<>();

    public ProductService() {
        Product p1 = new Product("101","갤럭시","삼성", 100, "2023-10-19");
        products.put("101", p1);
        Product p2 = new Product("102","엘지","엘지", 110, "2023-10-10");
        products.put("102", p2);
        Product p3 = new Product("103","아이폰","애플", 120, "2023-10-11");
        products.put("103", p3);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(String id){
        return products.get(id);
    }

    public void update(String id, int price){
        Product p = findById(id);
        p.setPrice(price);
        products.put(id, p);
    }

    public void insert(Product product){
        products.put(product.getId(), product);
    }
}
