package com.example.joseph50;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_start;
    Button btn_set;
    Button btn_reset;
    EditText edit_sum;
    EditText edit_start;
    EditText edit_count;

    PeopleView[] peopleView;
    TextView[] textViews;

    int sum = 0;
    int start = 0;
    int count = 0;
    int num = 0;

    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_set = (Button)findViewById(R.id.btn_set);
        btn_reset = (Button)findViewById(R.id.btn_reset);

        btn_start.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_reset.setOnClickListener(this);

        edit_sum = findViewById(R.id.sum);
        edit_start = findViewById(R.id.start);
        edit_count = findViewById(R.id.count);

        peopleView = new PeopleView[16];
        textViews = new TextView[16];

        peopleView[0] = findViewById(R.id.n1);
        peopleView[1] = findViewById(R.id.n2);
        peopleView[2] = findViewById(R.id.n3);
        peopleView[3] = findViewById(R.id.n4);
        peopleView[4] = findViewById(R.id.n5);
        peopleView[5] = findViewById(R.id.n6);
        peopleView[6] = findViewById(R.id.n7);
        peopleView[7] = findViewById(R.id.n8);
        peopleView[8] = findViewById(R.id.n9);
        peopleView[9] = findViewById(R.id.n10);
        peopleView[10] = findViewById(R.id.n11);
        peopleView[11] = findViewById(R.id.n12);
        peopleView[12] = findViewById(R.id.n13);
        peopleView[13] = findViewById(R.id.n14);
        peopleView[14] = findViewById(R.id.n15);
        peopleView[15] = findViewById(R.id.n16);

        textViews[0] = findViewById(R.id.t1);
        textViews[1] = findViewById(R.id.t2);
        textViews[2] = findViewById(R.id.t3);
        textViews[3] = findViewById(R.id.t4);
        textViews[4] = findViewById(R.id.t5);
        textViews[5] = findViewById(R.id.t6);
        textViews[6] = findViewById(R.id.t7);
        textViews[7] = findViewById(R.id.t8);
        textViews[8] = findViewById(R.id.t9);
        textViews[9] = findViewById(R.id.t10);
        textViews[10] = findViewById(R.id.t11);
        textViews[11] = findViewById(R.id.t12);
        textViews[12] = findViewById(R.id.t13);
        textViews[13] = findViewById(R.id.t14);
        textViews[14] = findViewById(R.id.t15);
        textViews[15] = findViewById(R.id.t16);

        btn_start.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set:
                if (edit_sum.getText().toString().isEmpty() || edit_start.getText().toString().isEmpty() || edit_count.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "输入有误，请重新输入", Toast.LENGTH_LONG).show();
                    break;
                }
                sum = Integer.parseInt(edit_sum.getText().toString());
                start = Integer.parseInt(edit_start.getText().toString());
                count = Integer.parseInt(edit_count.getText().toString());
                if (sum > 16 || sum < 1) {
                    Toast.makeText(MainActivity.this, "总人数不能超过16且不小于1", Toast.LENGTH_SHORT).show();
                    break;
                }
                for (int i=0; i<sum; i++) {
                    peopleView[i].setPaintshow();
                    String temp = (i+1)+"";
                    textViews[i].setText(temp);
                }
                game.CreateGame(sum);
                game.SetStart(start);
                Toast.makeText(MainActivity.this, "准备就绪", Toast.LENGTH_SHORT).show();
                btn_start.setEnabled(true);
                btn_set.setEnabled(false);
                break;
            case R.id.btn_start:
                if (num < sum-1) {
                    int out = game.runonce(count);
                    peopleView[out-1].setPaintshut();
                    textViews[out-1].setText(out + "号第"+ (num+1) + "局淘汰");
                    num++;
                    String output = "第" + out + "名玩家淘汰";
                    Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                }
                else if (num == sum-1) {
                    int out = game.runonce(count);
                    peopleView[out-1].speed = 10;
                    num++;
                    String output = "第" + out + "号玩家获胜";
                    textViews[out-1].setText(out + "号:劳资赢了！");
                    Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"游戏结束", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_reset:
                sum = 0;
                start = 0;
                count = 0;
                num = 0;
                for (int i=0; i<16; i++) {
                    peopleView[i].setPaintinit();
                    textViews[i].setText("");
                }
                game = new Game();
                btn_set.setEnabled(true);
                btn_start.setEnabled(false);
                edit_sum.setText("");
                edit_start.setText("");
                edit_count.setText("");
                Toast.makeText(MainActivity.this, "游戏已重置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
