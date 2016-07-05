package developers.gitanio.es.gitanio.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import developers.gitanio.es.gitanio.R;
import developers.gitanio.es.gitanio.ToolbarSupport;
import developers.gitanio.es.gitanio.model.Usuario;
import developers.gitanio.es.gitanio.services.PreferencesDAO;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferencesDAO preferencesDAO = new PreferencesDAO(this);

        if(preferencesDAO.hasDataStored()){
            // Se já possui dados do usuário, os recupera e passa a proxima activity
            Usuario usuario = preferencesDAO.restoreUserData();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", usuario.getUsername() );
            intent.putExtra("name", usuario.getName());
            startActivity(intent);

        }else{
            // Senão, carrega os layouts de login
            setContentView(R.layout.activity_login);
            Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
            ToolbarSupport.startToolbar(this, toolbar, "login");

            mEmailView = (EditText) findViewById(R.id.email);

            mPasswordView = (EditText) findViewById(R.id.password);
            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin(null);
                        return true;
                    }
                    return false;
                }
            });

            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);

        }


    }

    public void attemptLogin(View view){
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Armazena os valores na tentativa de login
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Checa se a senha é válida, caso o usuário entrou com uma
        if(TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if(!isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Checa se o email é válido, caso o usuário entrou com um
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // Se possui erro, mostra o erro
            focusView.requestFocus();
        } else {
            // Se o formato dos dados está correto,
            // tente fazer login aqui

            startActivity(new Intent(this,MainActivity.class));
        }

    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

}
