//package com.example.Pizza_Project;
//
//import com.example.Pizza_Project.controller.CafeController;
//import com.example.Pizza_Project.entity.Cafe;
//import com.example.Pizza_Project.entity.Pizza;
//import com.example.Pizza_Project.entity.Size;
//import com.example.Pizza_Project.repository.CafeRepository;
//import com.example.Pizza_Project.repository.PizzaRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Controller;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Controller
//public class InitDatabase implements CommandLineRunner {
//    private static final Logger log = LoggerFactory.getLogger(CafeController.class);
//    @Autowired
//    private CafeRepository cafeRepository;
//    @Autowired
//    private PizzaRepository pizzaRepository;
//
//    @Autowired
//    private InitDatabase(CafeRepository cafeRepository, PizzaRepository pizzaRepository) {
//        this.cafeRepository = cafeRepository;
//        this.pizzaRepository = pizzaRepository;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//            log.info("saving in Database");
//            cafeRepository.saveAll(List.of(
//                    new Cafe("JenkinsPizza", "Stuttgart", "Frankfurter 12", "jenkPizza@gmail.com", "017647879821", "11:00", "22:00", null),
//                    new Cafe("ItalianoPizza", "Köln", "Kant 41", "PizzaIt@gmail.com", "017647845321", "10:00", "23:00", null),
//                    new Cafe("MargaritaPizza", "München", "Steinmühle 5", "Margarita@gmail.com", "0176478987875", "12:00", "00:00", null)
//            ));
//            pizzaRepository.saveAll(List.of(
//                    new Pizza("Margarita", Size.XL, cafeRepository.findById(2L).get(), "Tomaten, Mozarello, Sauce", BigDecimal.valueOf(10)),
//                    new Pizza("ChickenPizza", Size.XXL, cafeRepository.findById(2L).get(), "Chicken, SandwichSauce, Cheese", BigDecimal.valueOf(11)),
//                    new Pizza("Salami", Size.XXL, cafeRepository.findById(1L).get(), "Tomaten, Salami, Cheese", BigDecimal.valueOf(5)),
//                    new Pizza("Pizza Hawaii", Size.XL, cafeRepository.findById(1L).get(), "Tomaten, Schinken, Cheese, Ananas", BigDecimal.valueOf(9)),
//                    new Pizza("Mediterranea", Size.XXL, cafeRepository.findById(1L).get(), "Tomaten, Spinat, Weichkäse, Knoblauch", BigDecimal.valueOf(11)),
//                    new Pizza("Pizza Rustica", Size.XXL, cafeRepository.findById(3L).get(), "Tomaten, Champignons, Cheese, Salami", BigDecimal.valueOf(15))
//            ));
//
//    }
//}
//
