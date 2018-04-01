package br.org.venturus.ricardotakemura.nytimes.util;

import android.util.Log;

import java.util.Date;

public final class Scheduler {

    private Thread thread;

    public interface SchedulerDelegate {
        void execute();
    }

    public static void sleep(final long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            Log.e("Scheduler","Sleeping Error", e);
        }
    }

    public void schedule(final Date date, final long miliseconds, final SchedulerDelegate delegate) {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    final Date now = new Date();
                    if (now.getTime() - date.getTime() > miliseconds) {
                        if (delegate != null) {
                            delegate.execute();
                        }
                        break;
                    }
                    Scheduler.sleep(miliseconds);
                } while (true);
            }
        });
        thread.start();
    }
}
