package developers.gitanio.es.gitanio.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import developers.gitanio.es.gitanio.R;
import developers.gitanio.es.gitanio.controller.ToolbarSupport;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        Toolbar toolbar = new Toolbar(getApplicationContext());
        ToolbarSupport.startToolbarWithArrow(this, toolbar, R.id.sobre_toolbar,"Sobre");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // If the user click to go back, the app goes to MainActivity
        finish();
        return super.onOptionsItemSelected(item);
    }
}
