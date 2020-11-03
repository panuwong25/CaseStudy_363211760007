import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExhangeAPI {
    //class attributer
    private String result;
    private String time_last_updata_utc;
    private String time_nexy_update_utc;
    private String base_code;
    private JSONObject conversion_rates;

    private static String url_API = "https://v6.exchangerate-api.com/v6/b84686b850e46d327858b744/latest/";
    private static JSONObject jsonObject;

    //connect seryer
    public boolean getConnection (String base_code){
        String json = "";
        URL url = null;
        HttpURLConnection request = null;


        try {
            url = new URL(url_API + base_code);
            request = (HttpURLConnection) url.openConnection();

            //connect to server
            request.connect();
            //read data from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            if (line.length()> 0) {
                json += line;
            }
            //json to lsonObject
            jsonObject = new JSONObject(json);
            if (jsonObject == null) {
                return false;
            }
            this.result = jsonObject.getString("result");
            this.time_last_updata_utc = jsonObject.getString("time_last_updata_utc");
            this.time_nexy_update_utc = jsonObject.getString("time_nexy_update_utc");
            this.base_code = jsonObject.getString(" base_code");
            this.conversion_rates = jsonObject.getJSONObject("conversion_rates");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  true ;


    }//getConnection

    public String getResult() {
        return result;
    }

    public String getTime_last_updata_utc() {
        return time_last_updata_utc;
    }

    public String getTime_nexy_update_utc() {
        return time_nexy_update_utc;
    }

    public String getBase_code() {
        return base_code;
    }

    public JSONObject getConversion_rates() {
        return conversion_rates;
    }

    public double getEachRate(String newCurrency){
        double eachRate = 0.0;
        try {
            eachRate = this.conversion_rates.getDouble(newCurrency);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  eachRate;
    }

}//class
