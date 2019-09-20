package com.fsx.doublelist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fsx.doublelist.dialog.Dialog1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        Button button = findViewById(R.id.btn);
        Button button2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.tv);
        textView2 = findViewById(R.id.tv2);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                showDialog();
                break;
            case R.id.btn2:
                startActivityForResult(new Intent(this,
                        DistinctPickerActivity.class), 1);
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        final Dialog1 dialog1 = new Dialog1(this, "");
        dialog1.show();
        dialog1.setClicklistener(new Dialog1.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                String province = dialog1.getProvince();
                String city = dialog1.getCity();
                dialog1.dismiss();
                textView.setText(province + "  " + city);
            }

            @Override
            public void doCancel() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 100) {
            textView2.setText(data.getStringExtra("result"));
        }
    }
}
