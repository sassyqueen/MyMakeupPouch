package sg.edu.rp.g913.mymakeuppouch;

import android.app.Application;
import android.content.Context;

/**
 * Created by 15004557 on 12/11/2016.
 */

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}


