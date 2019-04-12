package kf.ylc.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_message;
    private Button btn_add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        et_message = findViewById(R.id.et_message);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
//                InterfaceManager.getInstance().addInterface("to_next_activity", new CustomInterfaceHasParamAndResult<String,String>() {
//                    @Override
//                    public String function(String s) {
//                        et_message.setText("mainActivity传来的参数：\n");
//                        et_message.append(s);
//                        return "TestActivity";
//                    }
//                });
               String str =  InterfaceManager.getInstance().invokeInterface("test_key","TestActivity",String.class);
               et_message.setText(str);
                break;
        }
    }
}
