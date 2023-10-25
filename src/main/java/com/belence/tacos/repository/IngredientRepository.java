package com.belence.tacos.repository;

import com.belence.tacos.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

/**
 *  CrudRepository 基础接口，提供了一些常见的操作
 *  Spring data会在运行时自动创建这些接口的实现，不需要我们自己实现
 */
public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
