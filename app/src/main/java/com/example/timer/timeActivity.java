package com.example.timer;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class timeActivity extends AppCompatActivity {

    public SpannableStringBuilder getTime(String location, String text)
    {
        Calendar data = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        data.setTimeZone(TimeZone.getTimeZone(location));
        String time = String.format("%02d:%02d:%02d", data.get(Calendar.HOUR_OF_DAY), data.get(Calendar.MINUTE), data.get(Calendar.SECOND));

        final SpannableStringBuilder sb = new SpannableStringBuilder(text + "\n" + time);

        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(0, 0, 198));

        sb.setSpan(fcs, 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return sb;
    }

    public void setUpText()
    {
        TextView t = findViewById(R.id.text1);
        t.setText(getTime("Europe/Warsaw", "Mobile time: "));

        TextView t1 = findViewById(R.id.t1);
        t1.setText(getTime("Canada/Atlantic", "Israel:"));

        TextView t2 = findViewById(R.id.t2);
        t2.setText(getTime("Israel", "Japan: "));

        TextView t3 = findViewById(R.id.t3);
        t3.setText(getTime("Asia/Tokyo", "Canada: "));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        setUpText();

        Thread t = new Thread() {

            @Override
            public void run()
            {
                try {
                    while (!isInterrupted())
                    {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                setUpText();
                            }
                        });
                    }
                } catch (InterruptedException e)
                {
                }
            }
        };

        t.start();

    }


}
