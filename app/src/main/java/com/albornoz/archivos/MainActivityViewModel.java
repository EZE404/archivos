package com.albornoz.archivos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> mensaje;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    // Guardando datos primitivos - Se vale de un flujo de bytes.
    public void guardar(String nombre, String edad) {
        File ruta = new File(context.getFilesDir(), "datos.dat");
        try {
            FileOutputStream nodo = new FileOutputStream(ruta, true);
            BufferedOutputStream buffer = new BufferedOutputStream(nodo);
            DataOutputStream dataBinding = new DataOutputStream(buffer);

            dataBinding.writeUTF(nombre);
            dataBinding.writeInt(Integer.parseInt(edad));

            dataBinding.flush();
            dataBinding.close();

            mensaje.setValue("Datos guardados");

        } catch (FileNotFoundException e) {
            Log.d("catch", "guardar: "+e.getMessage());
            mensaje.setValue(e.getMessage());
        } catch (IOException e) {
            Log.d("catch", "guardar: "+e.getMessage());
            mensaje.setValue(e.getMessage());
        }
    }

}
