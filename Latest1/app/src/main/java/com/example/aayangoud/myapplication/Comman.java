package com.example.aayangoud.myapplication;

/**
 * Created by Aayan Goud on 8/21/2016.
 */
public class Comman {

    public static int convertToInt(String value){
        int result =0;
        if(value.trim().length() > 0){
            result = Integer.parseInt(value);
        }else{
            return result;
        }
        return result;
    }

    public static boolean isNullOrEmpty(String value){
        boolean result=false;
        if(value != null && !value.equals("")){
            result=false;
        }else{
            result=true;
        }
        return result;
    }
}
