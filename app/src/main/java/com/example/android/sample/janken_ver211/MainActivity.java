package com.example.android.sample.janken_ver211;


        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.renderscript.ScriptGroup;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.InputFilter;
        import android.text.Spanned;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ボタンを認識してOnClickに飛ばす
        Button nb = (Button) findViewById(R.id.new_game);
        Button cb = (Button) findViewById(R.id.continue_game);
        nb.setOnClickListener(this);
        cb.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.new_game:
                //インテントに情報を入れて向こうに送る
                Intent intent1 = new Intent(MainActivity.this, InputActivity.class);
                intent1.putExtra("n_or_c","はじめから");
                startActivity(intent1);
                break;
            case R.id.continue_game:
                Intent intent2 = new Intent(MainActivity.this, InputActivity.class);
                intent2.putExtra("n_or_c","つづきから");
                startActivity(intent2);
                break;
        }

    }
}