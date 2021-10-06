package com.albornoz.archivos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity2ViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> mostrar;

    public MainActivity2ViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getMostrar() {
        if (mostrar == null) {
            mostrar = new MutableLiveData<>();
        }
        return mostrar;
    }

    public void leer(){

        File ruta =new File(context.getFilesDir(),"datos.dat");

        try {
            FileInputStream nodo = new FileInputStream(ruta);
            BufferedInputStream buffer =new BufferedInputStream(nodo);
            DataInputStream dataBinding =new DataInputStream(buffer);

            String nom = null;
            int edad = 0;
            StringBuilder cadena=new StringBuilder();

            while(dataBinding.available()>0){
                nom = dataBinding.readUTF();
                edad = dataBinding.readInt();
                cadena.append(nom+" "+edad+"\n");
            }
            dataBinding.close();
            mostrar.setValue(cadena.toString());

        }catch (FileNotFoundException fo){
            mostrar.setValue("Error de archivo");
        }catch (IOException i){
            mostrar.setValue("Error de E/S");
        }
    }

}
