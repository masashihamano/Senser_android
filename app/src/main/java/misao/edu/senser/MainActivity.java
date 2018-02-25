package misao.edu.senser;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{//②SensorEventListenerをinterface

    TextView textView;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        textView = findViewById( R.id.textview );
        //①sensorManagerを宣言、初期化
        sensorManager = (SensorManager) this.getSystemService( SENSOR_SERVICE);
        //③加速度センサーの取得
        sensorManager.registerListener( this,sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER ),SensorManager.SENSOR_DELAY_NORMAL );
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //センサーの値を出力する
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float value[] = sensorEvent.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];

            float asr = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if (asr >= 2) {
                Random r = new Random();
                int i = r.nextInt( 10 );
                textView.setText( "" + i );
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
