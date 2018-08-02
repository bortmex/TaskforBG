package test.test3;

import test.test3.language.Language;
import test.test3.translate.Translate;

import static test.test1.Utils.inputStr;

public class YandexTranslate {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите текст на английском:");
        Translate.setKey("trnsl.1.1.20180802T200514Z.4a1a1e66fa5699b7.590aac2afa8d154cfd4a240970e36484961f8b8c");
        String text = "";
        while (true){
            text = inputStr();
            if((text).equals("exit")) {
                System.out.println("You want exit? [yes]");
                if (inputStr().equals("yes")) break;
            }
            System.out.println(Translate.execute(text, Language.ENGLISH, Language.RUSSIAN));
        }

        String translatedText = Translate.execute("Hello, world", Language.ENGLISH, Language.RUSSIAN);
        System.out.println(translatedText);
    }
}
