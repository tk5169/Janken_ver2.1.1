package com.example.android.sample.janken_ver211;

/**
 * Created by Admin on 2017/09/20.
 */


        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import org.w3c.dom.Text;
        import android.text.Editable;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    int win = 0;
    int lose = 0;
    double percent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button button = (Button) findViewById(R.id.btnNext);
        button.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.btnSave);
        button2.setOnClickListener(this);


        //ユーザーがグーチョキパーのうちどれを選んだのかを表示する
        //表示の結果はあらかじめresultキーに格納しておいた
        TextView txt = (TextView) findViewById(R.id.you);
        Intent i = getIntent();
        String s = i.getStringExtra("result");
        txt.setText(String.valueOf(s));


        //システムはランダムにグーチョキパーの選択をして、それを画面に表示する
        int n = (int) (Math.floor(Math.random() * 3));
        switch (n) {
            case 0:
                TextView txt2 = (TextView) findViewById(R.id.my);
                txt2.setText("私はグー");
                break;
            case 1:
                TextView txt3 = (TextView) findViewById(R.id.my);
                txt3.setText("私はチョキ");
                break;
            case 2:
                TextView txt4 = (TextView) findViewById(R.id.my);
                txt4.setText("私はパー");
                break;
        }

        //じゃんけんの勝敗を判定し、結果を表示する
        //勝敗の判定はユーザーがグーチョキパーを選んだ際に
        //RESULTキーに格納された数字をこちらで取り出し、
        //それとシステムがランダムに作成した数字を比較して判定する
        int c = i.getIntExtra("RESULT", 0);
        win = i.getIntExtra("WIN", 0);
        lose = i.getIntExtra("LOSE", 0);
        int z = c - n;

        if (z == 0) {
            TextView txt5 = (TextView) findViewById(R.id.result_text);
            txt5.setText("あいこでした");
        } else if (z == 1 || z == -2) {
            TextView txt6 = (TextView) findViewById(R.id.result_text);
            txt6.setText("あなたの負け");
            lose++;
        } else if (z == 2 || z == -1) {
            TextView txt7 = (TextView) findViewById(R.id.result_text);
            txt7.setText("あなたの勝ち！");
            win++;


        }


        //        勝率　＝　勝った数　/　（勝った数＋負けた数）

        percent = 0;
        if (win + lose == 0) {
            percent = 0;
        } else {
            //        勝率　＝　勝った数　/　（勝った数＋負けた数）
            percent = ((double) win / (double) (win + lose)) * 100; // 1/(1+2) = 0.3333
        }

        TextView tvWin = (TextView) findViewById(R.id.win);
        tvWin.setText("勝利数：" + win);

        TextView tvLose = (TextView) findViewById(R.id.lose);
        tvLose.setText("敗北数：" + lose);

        TextView tvPercent = (TextView) findViewById(R.id.percent);
        tvPercent.setText("勝率：" + percent + "%");

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btnNext:
                Intent data = new Intent();
                data.putExtra("WIN", win);
                data.putExtra("LOSE", lose);
                data.putExtra("PERCENT", percent);
                setResult(RESULT_OK, data);
                finish();
                break;

            case R.id.btnSave:
                SharedPreferences prefs = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //勝利数、敗北数の情報を保存
                editor.putInt("win", win);
                editor.putInt("lose", lose);
                editor.apply();

//                保存した情報を取得する
//                int w = prefs.getInt("win", 0);
//                int l = prefs.getInt("lose", 0);


                break;


        }


    }

}
