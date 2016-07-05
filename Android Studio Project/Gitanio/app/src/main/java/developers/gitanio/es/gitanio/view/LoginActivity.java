package developers.gitanio.es.gitanio.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import developers.gitanio.es.gitanio.R;
import developers.gitanio.es.gitanio.ToolbarSupport;
import developers.gitanio.es.gitanio.model.Usuario;
import developers.gitanio.es.gitanio.services.PreferencesDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferencesDAO preferencesDAO = new PreferencesDAO(this);

        if(preferencesDAO.hasDataStored()){

            Usuario usuario = preferencesDAO.restoreUserData();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", usuario.getUsername() );
            intent.putExtra("name", usuario.getName());
            startActivity(intent);

        }else{
            setContentView(R.layout.activity_login);
            Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
            ToolbarSupport.startToolbar(this, toolbar, "login");

        }


    }

    public void attemptLogin(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}
