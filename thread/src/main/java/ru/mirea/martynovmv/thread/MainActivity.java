package ru.mirea.martynovmv.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.martynovmv.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Integer countLessons;
    private Integer countStDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView infoTextView = findViewById(R.id.txtview);
        EditText editOne = findViewById(R.id.edone);
        EditText editTwo = findViewById(R.id.edtwo);
        TextView textViewRes = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 1, НОМЕР ПО СПИСКУ: 20, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Лето 85");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));
        binding.btnMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
                while	(System.currentTimeMillis()	<	endTime)	{
                    synchronized	(this)	{
                        try	{
                            wait(endTime	- System.currentTimeMillis());
                        }	catch	(Exception	e)	{
                            throw	new	RuntimeException(e);
                        }
                    }
                }
            }
        });
        binding.button2.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                countLessons = Integer.parseInt(editOne.getText().toString());
                countStDays = Integer.parseInt(editTwo.getText().toString());
                int AVG = countLessons/countStDays;
                textViewRes.setText("Среднее количество пар в день равно:"+AVG);
            }
        });

    }
}