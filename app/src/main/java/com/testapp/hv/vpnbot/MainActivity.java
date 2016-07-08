package com.testapp.hv.vpnbot;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//http://stackoverflow.com/questions/37779720/android-local-vpn-service-cant-get-response
public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onClick(View v) {
                Intent intent = VpnService.prepare(getApplicationContext());

                if (intent != null) {
                    startActivityForResult(intent, 0);
                    tv.setText("Stop Service");
                } else {
                    onActivityResult(0, RESULT_OK, null);
                    Toast.makeText(getApplicationContext(),"Checking !! Not Disturb",Toast.LENGTH_LONG).show();
                    tv.setText("Start Service");
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MyVpnService.class);
            startService(intent);
            Toast.makeText(getApplicationContext(),"Result",Toast.LENGTH_LONG).show();
        }
    }

}
