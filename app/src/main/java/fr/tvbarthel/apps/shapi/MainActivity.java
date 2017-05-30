package fr.tvbarthel.apps.shapi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import fr.tvbarthel.apps.shapi.core.ShapiApplication;

/**
 * Main Activity.
 * <p>
 * Never rename or move package.
 * https://android-developers.googleblog.com/2011/06/things-that-cannot-change.html
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Dummy injection example.
     */
    @Inject
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShapiApplication.component().inject(this);

        Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show();
    }
}
