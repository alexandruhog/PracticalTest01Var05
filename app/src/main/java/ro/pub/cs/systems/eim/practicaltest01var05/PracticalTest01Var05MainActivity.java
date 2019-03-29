package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private EditText number1 = null;
    private EditText number2 = null;
    private EditText number3 = null;
    private CheckBox checkBox1 = null;
    private CheckBox checkBox2 = null;
    private CheckBox checkBox3 = null;
    private Button playButton = null;

    private int score = 0;

    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey("score")){
                score = savedInstanceState.getInt("score");
            }
        }
        setContentView(R.layout.activity_practical_test01_var05_main);

        number1 = (EditText)findViewById(R.id.textNumber1);
        number2 = (EditText)findViewById(R.id.textNumber2);
        number3 = (EditText)findViewById(R.id.textNumber3);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);

        playButton = (Button) findViewById(R.id.buttonPlay);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int howManyChecked = 0;
                int number_1, number_2, number_3;
                number_1 = random.nextInt()%4;
                if (!checkBox1.isChecked()) {

                    if (number_1 == 0) {
                        number1.setText("*");
                    } else {
                        if (number_1 < 0) {
                            number_1 *= -1;
                        }
                        number1.setText(String.valueOf(number_1));
                    }
                }
                number_2 = random.nextInt()%4;
                if (!checkBox2.isChecked()) {
                    if (number_2 == 0) {
                        number2.setText("*");
                    } else {
                        if (number_2 < 0) {
                            number_2 *= -1;
                        }
                        number2.setText(String.valueOf(number_2));
                    }
                }
                number_3 = random.nextInt()%4;
                if (!checkBox3.isChecked()) {
                    if (number_3 == 0) {
                        number3.setText("*");
                    } else {
                        if (number_3 < 0) {
                            number_3 *= -1;
                        }
                        number3.setText(String.valueOf(number_3));
                    }
                }

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                intent.putExtra("number1", number_1);
                intent.putExtra("number2", number_2);
                intent.putExtra("number3", number_3);
                if (checkBox1.isChecked()){
                    howManyChecked ++;
                }
                if (checkBox2.isChecked()){
                    howManyChecked ++;
                }
                if (checkBox3.isChecked()){
                    howManyChecked++;
                }
                intent.putExtra("howManyChecked", howManyChecked);
                startActivityForResult(intent, 11);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("score", score);
    }
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        if (saveInstanceState.containsKey("score")){
            score = saveInstanceState.getInt("score");
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == 11){
            int localScore = intent.getIntExtra("score", 0);
            score+=localScore;
            Toast.makeText(this, "Score is " + score, Toast.LENGTH_LONG).show();

            if (score >= 300) {
                Intent intentService = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                intent.putExtra("score", score);
                getApplicationContext().startService(intentService);
            }
        }
    }
}
