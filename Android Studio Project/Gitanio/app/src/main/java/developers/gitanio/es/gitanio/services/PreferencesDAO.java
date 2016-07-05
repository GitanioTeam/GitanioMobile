package developers.gitanio.es.gitanio.services;

import android.app.Activity;
import android.content.SharedPreferences;

import developers.gitanio.es.gitanio.model.Validation;

/**
 * Created by paulo on 05/07/16.
 */
public class PreferencesDAO {
    private String PREFS_NAME = "MyPrefsFile";
    private SharedPreferences settings;

    public PreferencesDAO(Activity activity){

        this.settings = activity.getSharedPreferences(PREFS_NAME, 0);
    }

    public boolean hasDataStored(){
        return settings.getBoolean("saved", false);
    }

    public void storeUserData(Validation usuario){

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email", usuario.getEmail());
        editor.putString("senha", usuario.getSenha());

        // Commit the edits!
        editor.putBoolean("saved", true);
        editor.apply();
    }

    /** Restore persistent user data
     *
     */
    public Validation restoreUserData(){

        Validation usuario = new Validation();
        usuario.setEmail(settings.getString("email", null));
        usuario.setSenha(settings.getString("senha", null));

        return usuario;
    }
}
