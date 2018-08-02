package test.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String inputStr() throws IOException {
        BufferedReader inputBuff = new BufferedReader(new InputStreamReader(System.in));
        return inputBuff.readLine();
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static int plusTen(int number){
        return number + 10;
    }

    public static boolean checkingForSpacesAndEmpty(String text){
        if(text.isEmpty()) return true;
        Pattern p = Pattern.compile("^[ ]+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }
}