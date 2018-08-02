
package test.test3.detect;

import test.test3.ApiKeys;
import test.test3.YandexTranslatorAPI;
import test.test3.language.Language;
import test.test3.translate.Translate;

import java.net.URL;
import java.net.URLEncoder;

public final class Detect extends YandexTranslatorAPI {
    private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/detect?";
    private static final String DETECTION_LABEL = "lang";

    private Detect(){};

	  public static Language execute(final String text) throws Exception {
	    validateServiceState(text); 
	    final String params = 
	        PARAM_API_KEY + URLEncoder.encode(apiKey,ENCODING) 
	        + PARAM_TEXT + URLEncoder.encode(text,ENCODING);
	    final URL url = new URL(SERVICE_URL + params);
      return Language.fromString(retrievePropString(url, DETECTION_LABEL));
	  }
        
    private static void validateServiceState(final String text) throws Exception {
    	final int byteLength = text.getBytes(ENCODING).length;
        if(byteLength>10240) {
            throw new RuntimeException("TEXT_TOO_LARGE - Yandex Translator (Detect) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }
    
    public static void main(String[] args) {
      try {
        Translate.setKey(ApiKeys.YANDEX_API_KEY);
        Language translation = Detect.execute("The quick brown fox jumps over the lazy dog.");
        System.out.println("Detected: " + translation.toString());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

}
