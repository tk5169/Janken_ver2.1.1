package com.example.android.sample.janken_ver211;

/**
 * Created by Admin on 2017/09/20.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.media.Image;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQ_RESULT = 1;

    int win = 0;
    int lose = 0;
    double percent = 0;

    int user = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent i = getIntent();
        int user = i.getIntExtra("user_w_l",0);


        if(user==1) {

            //保存した勝ち負けの数を上書き
            SharedPreferences prefs = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
            win = prefs.getInt("win", 0);
            lose = prefs.getInt("lose", 0);

            TextView tvWin = (TextView) findViewById(R.id.win);
            tvWin.setText("勝利数：" + win);

            TextView tvLose = (TextView) findViewById(R.id.lose);
            tvLose.setText("敗北数：" + lose);

            percent = ((double) win / (double) (win + lose)) * 100; // 1/(1+2) = 0.3333
            TextView tvPercent = (TextView) findViewById(R.id.percent);
            tvPercent.setText("勝率：" + percent + "%");
        }


        ImageButton imageButton = (ImageButton) findViewById(R.id.left);
        imageButton.setOnClickListener(this);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.center);
        imageButton2.setOnClickListener(this);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.right);
        imageButton3.setOnClickListener(this);

        double percent = 0;
        if(win+lose==0){
            percent =0;
        }else {
            //        勝率　＝　勝った数　/　（勝った数＋負けた数）
            percent = ((double) win / (double) (win + lose))*100; // 1/(1+2) = 0.3333
        }

        TextView tvWin = (TextView)findViewById(R.id.win);
        tvWin.setText("勝利数："+ win);

        TextView tvLose  = (TextView)findViewById(R.id.lose);
        tvLose.setText("敗北数："+ lose);

        TextView tvPercent = (TextView)findViewById(R.id.percent);
        tvPercent.setText("勝率："+ percent + "%");



//        SharedPreferences prefs = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("string", inputname);
//        editor.apply();
//
//        String str = prefs.getString("string","aa");
//
//        Log.d("key","value"+str);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;

        if(requestCode == REQ_RESULT) {
            Bundle resultBundle = data.getExtras();
            if(!resultBundle.containsKey("WIN")) return;
            if(!resultBundle.containsKey("LOSE")) return;
            if(!resultBundle.containsKey("PERCENT")) return;


            win = resultBundle.getInt("WIN");
            lose = resultBundle.getInt("LOSE");
            double percent = resultBundle.getDouble("PERCENT");

            TextView tvWin = (TextView)findViewById(R.id.win);
            tvWin.setText("勝利数："+ win);

            TextView tvLose  = (TextView)findViewById(R.id.lose);
            tvLose.setText("敗北数："+ lose);

            TextView tvPercent = (TextView)findViewById(R.id.percent);
            tvPercent.setText("勝率："+ percent + "%");

        }

    }

    @Override
    public void onClick(View view) {


//        Intent intenta = new Intent(this, ResultActivity.class);
//        intenta.putExtra("result", "あなたはテスト");
//        intenta.putExtra("RESULT", 0);
//        intenta.putExtra("WIN", win);
//        intenta.putExtra("LOSE", lose);
//        startActivityForResult(intenta, REQ_RESULT);



        int id = view.getId();
        switch (id) {
            case R.id.left:
                Intent intent1 = new Intent(this, ResultActivity.class);
                intent1.putExtra("result", "あなたはグー");
                intent1.putExtra("RESULT", 0);
                intent1.putExtra("WIN", win);
                intent1.putExtra("LOSE", lose);
                startActivityForResult(intent1, REQ_RESULT);
                break;

            case R.id.center:
                Intent intent2 = new Intent(this, ResultActivity.class);
                intent2.putExtra("result", "あなたはチョキ");
                intent2.putExtra("RESULT", 1);
                intent2.putExtra("WIN", win);
                intent2.putExtra("LOSE", lose);
                startActivityForResult(intent2, REQ_RESULT);
                break;

            case R.id.right:
                Intent intent3 = new Intent(this, ResultActivity.class);
                intent3.putExtra("result", "あなたはパー");
                intent3.putExtra("RESULT", 2);
                intent3.putExtra("WIN", win);
                intent3.putExtra("LOSE", lose);
                startActivityForResult(intent3, REQ_RESULT);
                break;


        }

    }

}
