package com.belence.tacos.controller;

import com.belence.tacos.entity.Ingredient;
import com.belence.tacos.entity.Taco;
import com.belence.tacos.entity.TacoOrder;
import com.belence.tacos.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.belence.tacos.entity.Ingredient.Type;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design") // 类级别使用@RequestMapping注解，据此定义该控制器所处理的基本请求模式
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute // 这个方法也会在请求处理的时候被调用，构建一个包含Ingredient的配料列表并放到模型中
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.finaAll();

        Type[] types = Ingredient.Type.values();
        for(Type type:types){
            // 添加到 servlet request wrap属性
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }

    }


    @ModelAttribute(name = "tacoOrder") // 请求调用时会被调用
    public TacoOrder order(){
        return new TacoOrder();
    }


    @ModelAttribute(name = "taco") // 请求调用时会被调用
    public Taco taco(){
        return new Taco();
    }

    @GetMapping // @GetMapping指明了showDesignForm()方法用来处理Get类型的请求
    public String showDesignForm(){
        return "design";
    }


    // @Valid 提示Spring MVC对 Taco对象进行校验
    // 校验时机是在它绑定完表单数据之后、调用processDesign()之前
    // 错误将会捕获到一个Errors对象中并传递给processTaco()
    @PostMapping// @PostMapping指明了processTaco()方法用来处理Post类型的请求
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if (errors.hasErrors()){
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        return StreamSupport.stream(ingredients.spliterator(),false).filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

}
