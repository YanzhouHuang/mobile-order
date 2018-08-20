package com.android.yanzhou.huang.yingji.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.android.yanzhou.huang.yingji.model.Product;

public final class Constant {
    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();
//input the entree name, price, picture in the array list
    static {
        for (int i = 1; i < 11; i++) QUANTITY_LIST.add(i);
    }
    public static final Product PRODUCT1 = new Product(1, "SUMMER ROLLS", BigDecimal.valueOf(6.00),
            "Wrapped with shrimp, lettuce, carrot, basil, cilantro and rice noodle","summer_roll");
    public static final Product PRODUCT2 = new Product(2, "SPRING ROLL (4)", BigDecimal.valueOf(6.00),
            "Wrapped with sauteed pork, cabbage and mushrooms","spring_roll");
    public static final Product PRODUCT3 = new Product(3, "SHRIMP DUMPLING (4)", BigDecimal.valueOf(6.00),
            "Steamed shrimp filled dumpling with crystal wrapper","shrimp_dumpling");
    public static final Product PRODUCT4 = new Product(4, "WONTON SOUP (4)", BigDecimal.valueOf(6.00),
            "Ground pork and vegetable filled","woton");
    public static final Product PRODUCT5 = new Product(5, "Pai Thai Chicken", BigDecimal.valueOf(10.00), "Rice noodles sauteed with chicken and eggs garnished with vegetable and ground peanuts","pai_thai_chicken");
    public static final Product PRODUCT6 = new Product(6, "YuXiang Flavor Pork", BigDecimal.valueOf(12.00), "Shredded pork, green pepper, bamboo shoot, shredded carrot and Chinese mushrooms stir-fried with house special sauce","yuxiang_flvaor_pork");
    public static final Product PRODUCT7 = new Product(7, "Sweet and Sour Chicken", BigDecimal.valueOf(12.00), "Deep fried chicken with green pepper, pineapple and onion in homemade sweet and sour sauce","sweet_and_sour_chicken");
    public static final Product PRODUCT8 = new Product(8, "BLACK PEPPER BEEF", BigDecimal.valueOf(18.00),
            "Sauteed Diced Beef Filet with Black Pepper","black_pepper_beef");
    public static final List<Product> PRODUCT_LIST = new ArrayList<Product>();

    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
        PRODUCT_LIST.add(PRODUCT4);
        PRODUCT_LIST.add(PRODUCT5);
        PRODUCT_LIST.add(PRODUCT6);
        PRODUCT_LIST.add(PRODUCT7);
        PRODUCT_LIST.add(PRODUCT8);

    }

    public static final String CURRENCY = "$";
}
