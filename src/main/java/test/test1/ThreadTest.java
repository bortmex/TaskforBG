package test.test1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static test.test1.Utils.*;

public class ThreadTest {

    public static Map<String, Integer> number_of_first_digit = new HashMap<>();
    public static Map<String, Integer> number_of_second_digit = new HashMap<>();
    public static Map<String, Integer> data_in_memory = new HashMap<>();

    static {
        int i = 0;
        number_of_first_digit.put("one", ++i);
        number_of_first_digit.put("two", ++i);
        number_of_first_digit.put("three", ++i);
        number_of_first_digit.put("four", ++i);
        number_of_first_digit.put("five", ++i);
        number_of_first_digit.put("six", ++i);
        number_of_first_digit.put("seven", ++i);
        number_of_first_digit.put("eight", ++i);
        number_of_first_digit.put("nine", ++i);
        number_of_second_digit.put("ten", ++i);
        number_of_second_digit.put("twenty", i = plusTen(i));
        number_of_second_digit.put("thirty", i = plusTen(i));
        number_of_second_digit.put("forty", i = plusTen(i));
        number_of_second_digit.put("fifty", i = plusTen(i));
        number_of_second_digit.put("sixty", i = plusTen(i));
        number_of_second_digit.put("seventy", i = plusTen(i));
        number_of_second_digit.put("eighty", i = plusTen(i));
        number_of_second_digit.put("ninety", plusTen(i));
    }

    public static void main(String[] args) throws IOException {
        Thread thread1 = new Thread(new ThreadDeleteEveryFiveSecond());
        Thread thread2 = new Thread(new ThreadAddData());
        thread1.start();
        thread2.start();
    }

    public static void addInMemory(String text) {
        int number = 0;
        int indexGo = 0;
        String localText = text;
        if(checkingForSpacesAndEmpty(text)) {
            System.out.println("Число не соответствует шаблону"); return;}
        while (!text.isEmpty() && !text.equals("")) {
            if (indexGo++ > 4) {
                System.out.println("Число не соответствует шаблону");
                break;
            }
            String[] arrText = text.split(" ");
            if (text.contains("thousand")) {
                number += number_of_first_digit.get(arrText[0]) * 1000;
                text = deletingCountedNumbers(2, arrText);
            } else if (text.contains("hundred")) {
                number += number_of_first_digit.get(arrText[0]) * 100;
                text = deletingCountedNumbers(2, arrText);
            } else if (text.contains("ten") || text.contains("ty")) {
                number += number_of_second_digit.get(arrText[0]);
                text = deletingCountedNumbers(1, arrText);
            } else if (number_of_first_digit.containsKey(text)) {
                number += number_of_first_digit.get(arrText[0]);
                text = deletingCountedNumbers(1, arrText);
            }
        }
        if(indexGo<=4){
            System.out.println("добавлено число " + number);
            data_in_memory.put(localText, number);
        }
    }

    public static String deletingCountedNumbers(int index, String[] arrayStr) {
        String result = "";
        for (int i = index; i < arrayStr.length; i++) {
            result += arrayStr[i] + " ";
        }
        return result.trim();
    }

    public static class ThreadDeleteEveryFiveSecond implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(5000);
                    data_in_memory = sortByValue(data_in_memory);
                    sortByValue(data_in_memory);
                    if (data_in_memory.size() != 0) {
                        Map.Entry<String, Integer> entry = data_in_memory.entrySet().iterator().next();
                        System.out.println("Удалено число: " + entry.getKey());
                        data_in_memory.remove(entry.getKey());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static class ThreadAddData implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    addInMemory(inputStr());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}