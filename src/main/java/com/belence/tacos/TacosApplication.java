package com.belence.tacos;

import com.belence.tacos.entity.Ingredient;
import com.belence.tacos.entity.Ingredient.Type;
import com.belence.tacos.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacosApplication.class, args);
	}

	/**
	 * ApplicationRunner 和 CommandLineRunner 用于在应用启动的时候执行一定的逻辑
	 * 都是函数式接口，都需要实现一个run()方法
	 * 使用CommandLineRunner预加载数据
	 */
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository ingredientRepository){
		return args -> {
			ingredientRepository.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
			ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}

}
