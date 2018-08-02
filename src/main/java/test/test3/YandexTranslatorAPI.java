package test.test3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public abstract class YandexTranslatorAPI {

  protected static final String ENCODING = "UTF-8";

  protected static String apiKey;
  private static String referrer;

  protected static final String PARAM_API_KEY = "key=",
      PARAM_LANG_PAIR = "&lang=",
      PARAM_TEXT = "&text=";

  public static void setKey(final String pKey) {
    apiKey = pKey;
  }

  public static void setReferrer(final String pReferrer) {
    referrer = pReferrer;
  }

  private static String retrieveResponse(final URL url) throws Exception {
    final HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
    if(referrer!=null)
      uc.setRequestProperty("referer", referrer);
    uc.setRequestProperty("Content-Type","text/plain; charset=" + ENCODING);
    uc.setRequestProperty("Accept-Charset",ENCODING);
    uc.setRequestMethod("GET");

    try {
      final int responseCode = uc.getResponseCode();
      final String result = inputStreamToString(uc.getInputStream());
      if(responseCode!=200) {
        throw new Exception("Error from Yandex API: " + result);
      }
      return result;
    } finally { 
      if(uc!=null) {
        uc.disconnect();
      }
    }
  }

  protected static String retrievePropString(final URL url, final String jsonValProperty) throws Exception {
    final String response = retrieveResponse(url);
    JSONObject jsonObj = (JSONObject)JSONValue.parse(response);
    return jsonObj.get(jsonValProperty).toString();
  }

  protected static String retrievePropArrString(final URL url, final String jsonValProperty) throws Exception {
      final String response = retrieveResponse(url);
      String[] translationArr = jsonObjValToStringArr(response, jsonValProperty);
      String combinedTranslations = "";
      for (String s : translationArr) {
        combinedTranslations += s;
      }
      return combinedTranslations.trim();
  }

  private static String[] jsonObjValToStringArr(final String inputString, final String subObjPropertyName) throws Exception {
    JSONObject jsonObj = (JSONObject)JSONValue.parse(inputString);
    JSONArray jsonArr = (JSONArray) jsonObj.get(subObjPropertyName);
    return jsonArrToStringArr(jsonArr.toJSONString(), null);
  }

  private static String[] jsonArrToStringArr(final String inputString, final String propertyName) throws Exception {
    final JSONArray jsonArr = (JSONArray)JSONValue.parse(inputString);
    String[] values = new String[jsonArr.size()];

    int i = 0;
    for(Object obj : jsonArr) {
      if(propertyName!=null&&propertyName.length()!=0) {
        final JSONObject json = (JSONObject)obj;
        if(json.containsKey(propertyName)) {
          values[i] = json.get(propertyName).toString();
        }
      } else {
        values[i] = obj.toString();
      }
      i++;
    }
    return values;
  }

  private static String inputStreamToString(final InputStream inputStream) throws Exception {
    final StringBuilder outputBuilder = new StringBuilder();

    try {
      String string;
      if (inputStream != null) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, ENCODING));
        while (null != (string = reader.readLine())) {
          outputBuilder.append(string.replaceAll("\uFEFF", ""));
        }
      }
    } catch (Exception ex) {
      throw new Exception("[yandex-translator-api] Error reading translation stream.", ex);
    }
    return outputBuilder.toString();
  }

  protected static void validateServiceState() throws Exception {
    if(apiKey==null||apiKey.length()<27) {
      throw new RuntimeException("INVALID_API_KEY - Please set the API Key with your Yandex API Key");
    }
  }

}
