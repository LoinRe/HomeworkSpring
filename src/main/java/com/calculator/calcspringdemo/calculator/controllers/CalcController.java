package com.calculator.calcspringdemo.calculator.controllers;

import com.calculator.calcspringdemo.calculator.service.CalcServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalcController { //обрабатывает запросы и маршрутизирует внутри спринга
    private final CalcServiceImpl calcService;

    public CalcController(CalcServiceImpl calcService) { // инъекция зависимости
        this.calcService = calcService;
    }

    @GetMapping
    public String showGreetings() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    public String sumNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        int result = calcService.sum(num1, num2);
        return generateMessage(num1, num2, '+', result);
    }
    //Другой вариант
//    public Integer sumNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
//        return calcService.sum(num1, num2);
//    }

    @GetMapping("/minus")
    public String subtractNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        int result = calcService.subtract(num1, num2);
        return generateMessage(num1, num2, '-', result);
    }

    @GetMapping("/multiply")
    public String multiplyNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        int result = calcService.multiply(num1, num2);
        return generateMessage(num1, num2, '*', result);
    }

    @GetMapping("/divide")
    public String divideNumbers(@RequestParam Integer num1, @RequestParam Integer num2) {
        if(num2 == 0){
            return "Второй аргумент равен 0. Деление на 0 невозможно";
        }
        int result = calcService.divide(num1, num2);
        return generateMessage(num1, num2, '/', result);
    }

    private String generateMessage(Integer num1, Integer num2, char action, int result) {
        return num1 + " " + action + " " + num2 + " = " + result;
    }
}
