package net.gamma_02.svcReconnecter;

import net.fabricmc.api.ModInitializer;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Supplier;

public class SvcReconnecter implements ModInitializer {

    public static final String MODID = "svc-reconnecter";

    public static final Queue<Runnable> StepWorkQueue = new ArrayBlockingQueue<>(16);

    @Override
    public void onInitialize() {
    }

    /**
     * Executes the work on the "main" thread -- the RenderThread on the client or the main server thread
     */
    public static void onStep(){

        try {
            while (!StepWorkQueue.isEmpty()) {
                Runnable work = StepWorkQueue.poll();

                work.run();
            }
        } catch (Exception e) {
            //ignored
        }
    }
}
