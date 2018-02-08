package rrk.dev.andcodephilo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 */

public class FileIOUtil {

    public static String getFileValue(Context context, String fileName){

        File getCash = new File ( context.getCacheDir() , fileName );
        String result="";
        if (getCash.exists()) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(getCash);

//                    byte[] tmpArray = new byte[50];

                int resultInt = -1;

                List<Integer> IntegerList = new ArrayList<>();
                while((resultInt = inputStream.read()) > 0){
                    IntegerList.add(resultInt);

                }

//                    while (inputStream.read(tmpArray) > 0) {}
                byte byteTmp  [] = new byte[IntegerList.size()];

                for(int k = 0; k< IntegerList.size(); k++){
                    byteTmp[k] = IntegerList.get(k).byteValue();
                }


                result = new String(byteTmp);
//                    Log.d(getClass().getSimpleName(), "getFileValue result : " + result );
            } catch (IOException e) {
                e.printStackTrace();
                result = "";
            }finally {
                try {
                    inputStream.close();
                }catch (IOException e ){
                    e.printStackTrace();
                    result = "";
                }
            }

        }

        return result;

    }


    public static boolean createFile(Context context,String fileName,String content){

        boolean isSuccess = false;

        File cashFile = new File( context.getCacheDir(),fileName);
        FileOutputStream outputStream = null;


        if(! cashFile.exists()) {

            try {
                if (cashFile.createNewFile()) {
                    outputStream = new FileOutputStream(cashFile);

                    outputStream.write(content.getBytes());

                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }


        return isSuccess;
    }
    public static boolean overWriteFileValue(Context context,String fileName,String content){

        boolean isSuccess = false;

        File cashFile = new File( context.getCacheDir(),fileName);
        if(cashFile.exists()){

            FileOutputStream outputStream = null;


            try{
                outputStream = new FileOutputStream( cashFile );
                outputStream.write( content.getBytes() );
            }catch (IOException e ){
                e.printStackTrace();
            }finally {
                if( outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }


        return isSuccess;
    }




}
