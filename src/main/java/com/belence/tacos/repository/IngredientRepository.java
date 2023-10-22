package com.belence.tacos.repository;

import com.belence.tacos.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> finaAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

}
