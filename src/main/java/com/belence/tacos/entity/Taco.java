package com.belence.tacos.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data // 注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@Table // 默认映射到 Taco 表
public class Taco {

    @Id // @Id 可以让 Spring data 知道哪个字段代表了对象的唯一标识，Spring Data JDBC 才知道如何持久化它们
    private Long id;

    private Date createAt = new Date();

    @NotNull
    @Size(min = 5,message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1,message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient taco){
        this.ingredients.add(new IngredientRef(taco.getId()));
    }
}
