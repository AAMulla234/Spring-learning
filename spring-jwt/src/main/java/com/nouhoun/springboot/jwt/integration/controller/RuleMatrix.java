package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class RuleMatrix {

    static Map<Predicate<Cart>, Function<Cart, Double>> ruleMatrix = new HashMap<>();

    static {
        ruleMatrix.put(CartPredicate.WHEN_CART_VALUE_IS_SIX, CartFunction.MULTI_WITH_ONE_FIVE);
        ruleMatrix.put(CartPredicate.WHEN_CART_VALUE_IS_SEVEN, CartFunction.MULTI_WITH_TWO_FIVE);
        ruleMatrix.put(CartPredicate.WHEN_CART_VALUE_IS_SIX, CartFunction.MULTI_WITH_ONE_FIVE);
    }

    public static Function getRule(Cart cart) {
        return ruleMatrix.entrySet().stream().
                filter(entry -> entry.getKey().test(cart)).
                map(entry -> entry.getValue()).
                findFirst().
                orElse(CartFunction.DEFAULT);
    }
}

class CartPredicate {
    public static final Predicate<Cart> WHEN_CART_VALUE_IS_SIX = cart -> cart.getValue() == 6;
    public static final Predicate<Cart> WHEN_CART_VALUE_IS_SEVEN = cart -> cart.getValue() == 7;
}

class CartFunction {
    public static final Function<Cart, Double> MULTI_WITH_ONE_FIVE = cart -> cart.getValue() * 1.5;
    public static final Function<Cart, Double> MULTI_WITH_TWO_FIVE = cart -> cart.getValue() * 2.5;
    public static final Function<Cart, Double> DEFAULT = cart -> {
        return 1.5;
    };
}

