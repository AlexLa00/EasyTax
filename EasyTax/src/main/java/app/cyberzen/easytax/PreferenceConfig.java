package app.cyberzen.easytax;
//CyberZen
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferenceConfig {
    private static String LIST_KEY;

    public static void writeListInPref(Context context, List<RegisterUser> HomeFragment){
        Gson gson =new Gson();
        String jsonString=gson.toJson(HomeFragment);

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }
    public static List<RegisterUser> readListFromPref(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString=pref.getString(LIST_KEY,"");

        Gson gson = new Gson();
        Type type=new TypeToken<ArrayList<RegisterUser>>(){}.getType();
        List<RegisterUser> list=gson.fromJson(jsonString,type);
        return list;
    }


}