package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int score = 0;
        setContentView(R.layout.activity_practical_test01_var05_secondary);
        EditText gained = (EditText)findViewById(R.id.textGained);
        EditText points = (EditText)findViewById(R.id.textPoints);
        Intent intent = getIntent();
        if (intent!=null){
            int number1 = intent.getIntExtra("number1", -1);
            int number2 = intent.getIntExtra("number2", -1);
            int number3 = intent.getIntExtra("number3", -1);
            int howManyChecked = intent.getIntExtra("howManyChecked", -1);

            if ((number1 == 0 && number2 == 0) || (number1 == 0 && number3 ==0) || (number2 == 0 && number3 == 0) || (number1 == number2 && number2 == number3) || (number1 == 0 && number2 == number3) || (number2 == 0 && number1==number3) || (number3 == 0 && number1 == number2 )){
                gained.setText("Gained");
                if (howManyChecked == 0){
                    score = 100;
                    points.setText("100");
                } else if (howManyChecked == 1) {
                    score = 50;
                    points.setText("50");
                } else {
                    score = 10;
                    points.setText("10");
                }
            }
            Intent intentToParent = new Intent();
            intentToParent.putExtra("score", score);
            setResult(Activity.RESULT_OK, intentToParent);
            finish();

        }
    }
}
