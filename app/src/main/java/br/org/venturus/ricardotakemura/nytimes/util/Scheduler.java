package br.org.venturus.ricardotakemura.nytimes.util;

import android.util.Log;

import java.util.Date;

/**
 * Classe útil para gerenciamenot de threads
 * @author ricardotakemura
 */
public final class Scheduler {

    private Thread thread;

    /**
     * Interface que delega a execução de um agendamento
     * @author ricardotakemura
     */
    public interface SchedulerDelegate {
        /**
         * Função que executa no agendamento
         */
        void execute();
    }

    /**
     * A thread dorme por um determinado tempo (em milisegundos)
     * @param sleeptime milisegundos(long)
     */
    public static void sleep(final long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            Log.e("Scheduler","Sleeping Error", e);
        }
    }

    /**
     * Agenda uma execução depois de milisegundos apos a data passada
     * @param date Date
     * @param miliseconds milisegundos (long)
     * @param delegate interface que delega a execução
     */
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
