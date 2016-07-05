package developers.gitanio.es.gitanio.services;

import android.app.Activity;
import android.content.SharedPreferences;

import developers.gitanio.es.gitanio.model.Usuario;

/**
 * Created by paulo on 05/07/16.
 */
public class PreferencesDAO {
    private String PREFS_NAME = "MyPrefsFile";
    private SharedPreferences settings;
    SharedPreferences.Editor editor;

    public PreferencesDAO(Activity activity){

        this.settings = activity.getSharedPreferences(PREFS_NAME, 0);
    }

    public boolean hasDataStored(){
        return settings.getBoolean("saved", false);
    }

    public void storeUserData(Usuario usuario){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", usuario.getUsername());
        editor.putString("name", usuario.getName());

        // Commit the edits!
        editor.putBoolean("saved", true);
        editor.apply();
    }

    /** Restore persistent user data
     *
     */
    public Usuario restoreUserData(){
        Usuario usuario = new Usuario();
        usuario.setName(settings.getString("name", null));
        usuario.setUsername(settings.getString("username", null));
        return usuario;
    }
}
