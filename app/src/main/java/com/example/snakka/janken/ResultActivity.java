package com.example.snakka.janken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    final int JANKEN_GU = 0;
    final int JANKEN_CHOKI = 1;
    final int JANKEN_PA = 2;

    private ImageView myHandImageView;
    private ImageView comHandImageView;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        myHandImageView = (ImageView) findViewById(R.id.my_hand_image);
        comHandImageView = (ImageView) findViewById(R.id.com_hand_image);
        resultText = (TextView) findViewById(R.id.result_label);

        /*Bundleを使う方法だと、containsKey等でkeyの有無を調べたり、拡張性がある
        * Bundle型の変数にgetIntent().getExtras();でbundleを取得。bundle変数.getInt("MY_HAND", 0);で"MY_HAND"のint値を取得。
        * 取り出せなかった場合のデフォルトを0に指定。containsKeyでkeyの有無を調べることもできる。
        int id;  Bundle bundle = getIntent().getExtras();  if(bundle.containsKey("MY_HAND")) id = bundle.getInt("MY_HAND", 0);
        */
        /*Intentを使う方法だと、一つ手順が少なくなるため片手間で使うときにいいが、containsKey等は使えない
        * Intent型の変数にgetIntent()でintentを取得。intent変数.getIntExtra("MY_HAND",0);で"MY_HAND"のint値を取得。
        * 取り出せなかった場合のデフォルトを0に指定。
        */
        Intent intent = getIntent();
        int myHand = intent.getIntExtra("MY_HAND", 0);

        int comHand = (int)(Math.random() * 3);
        drawHandView(myHand, comHand); //自分の手の画像を変更

    }


    private void drawHandView(int myHandId, int comHand){
        int myHand = 0;
        switch (myHandId){
            case R.id.gu:
                myHandImageView.setImageResource(R.drawable.gu);
                myHand = JANKEN_GU;
                break;
            case R.id.choki:
                myHandImageView.setImageResource(R.drawable.choki);
                myHand = JANKEN_CHOKI;
                break;
            case R.id.pa:
                myHandImageView.setImageResource(R.drawable.pa);
                myHand = JANKEN_PA;
                break;
        }

        switch(comHand){
            case JANKEN_GU:
                comHandImageView.setImageResource(R.drawable.com_gu);
                break;
            case JANKEN_CHOKI:
                comHandImageView.setImageResource(R.drawable.com_choki);
                break;
            case JANKEN_PA:
                comHandImageView.setImageResource(R.drawable.com_pa);
                break;
        }

        judgeJanken(myHand, comHand);
    }


    private void judgeJanken(int myHand, int comHand){
        int gameResult = (comHand - myHand + 3) % 3;
        switch(gameResult){
            case 0:
                resultText.setText(R.string.result_draw);
                break;
            case 1:
                resultText.setText(R.string.result_win);
                break;
            case 2:
                resultText.setText(R.string.result_lose);
                break;
        }
    }

}
