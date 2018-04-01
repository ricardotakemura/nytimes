package br.org.venturus.ricardotakemura.nytimes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.util.Scheduler;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private final long WAIT_MILISECONDS = 2000;

    /**
     * @see AppCompatActivity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * @see Runnable#run()
     */
    @Override
    public void run() {
        Scheduler.sleep(WAIT_MILISECONDS);
        SplashActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_fade_in,
                android.support.v7.appcompat.R.anim.abc_fade_out);
    }
}
