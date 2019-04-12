package kf.ylc.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kf.ylc.ifm_lib.CustomInterfaceHasParamAndResult;
import kf.ylc.ifm_lib.InterfaceManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_show;
    private Button btn_send,btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_show = findViewById(R.id.et_show);
        btn_next = findViewById(R.id.btn_next);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                startActivity(new Intent(this,TestActivity.class));
                break;
            case R.id.btn_send:
//                InterfaceManager.getInstance().invokeInterface("to_next_activity", "MainActivity", String.class, new CustomResultInterface<String>() {
//                    @Override
//                    public void function(String s) {
//                        et_show.setText("TestActivity返回的参数：\n");
//                        et_show.append(s);
//                    }
//                });
                InterfaceManager.getInstance().addInterface("test_key", new CustomInterfaceHasParamAndResult<String,String>() {
                    @Override
                    public String function(String s) {
                        et_show.setText(s);
                        return "mainActivity";
                    }
                });
                break;
        }
    }
}
