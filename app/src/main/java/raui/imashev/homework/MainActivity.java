package raui.imashev.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ArrayList<Button> buttons = new ArrayList<>();
        editText = findViewById(R.id.editTextText);
        buttons.add(findViewById(R.id.buttonC));
        buttons.add(findViewById(R.id.buttonDelete));
        buttons.add(findViewById(R.id.buttonPlus));
        buttons.add(findViewById(R.id.buttonMinus));
        buttons.add(findViewById(R.id.buttonMultiply));
        buttons.add(findViewById(R.id.buttonDivide));
        buttons.add(findViewById(R.id.buttonComma));
        buttons.add(findViewById(R.id.buttonEquals));
        buttons.add(findViewById(R.id.buttonRightHawk));
        buttons.add(findViewById(R.id.buttonLeftHawk));
        buttons.add(findViewById(R.id.button1));
        buttons.add(findViewById(R.id.button2));
        buttons.add(findViewById(R.id.button3));
        buttons.add(findViewById(R.id.button4));
        buttons.add(findViewById(R.id.button5));
        buttons.add(findViewById(R.id.button6));
        buttons.add(findViewById(R.id.button7));
        buttons.add(findViewById(R.id.button8));
        buttons.add(findViewById(R.id.button9));
        buttons.add(findViewById(R.id.button0));
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View view)
    {
        String text = editText.getText().toString();
        if (text.equals("0")) text = "";
        switch(view.getId())  {
            case  R.id.buttonC:
                editText.setText("0");
                break;
            case R.id.button0:
                editText.setText(text+"0");
                break;
            case R.id.button1:
                editText.setText(text+"1");
                break;
            case R.id.button2:
                editText.setText(text+"2");
                break;
            case R.id.button3:
                editText.setText(text+"3");
                break;
            case R.id.button4:
                editText.setText(text+"4");
                break;
            case R.id.button5:
                editText.setText(text+"5");
                break;
            case R.id.button6:
                editText.setText(text+"6");
                break;
            case R.id.button7:
                editText.setText(text+"7");
                break;
            case R.id.button8:
                editText.setText(text+"8");
                break;
            case R.id.button9:
                editText.setText(text+"9");
                break;
            case R.id.buttonPlus:
                editText.setText(text+"+");
                break;
            case R.id.buttonMinus:
                editText.setText(text+"-");
                break;
            case R.id.buttonMultiply:
                editText.setText(text+"×");
                break;
            case R.id.buttonDivide:
                editText.setText(text+"÷");
                break;
            case R.id.buttonLeftHawk:
                editText.setText(text+"(");
                break;
            case R.id.buttonRightHawk:
                editText.setText(text+")");
                break;
            case R.id.buttonComma:
                editText.setText(text+",");
                break;
            case R.id.buttonDelete:
                if (text.length() >1) {
                    editText.setText(text.substring(0, text.length() - 2));
                } else {
                    editText.setText("0");
                }
                break;
                case R.id.buttonEquals:
                StringBuilder result = new StringBuilder();
                String expression = editText.getText().toString();
                for (int i = 0; i < expression.length(); i++) {
                    if (expression.charAt(i) == '×') {
                        result.append('*');
                    } else if (expression.charAt(i) == '÷') {
                        result.append('/');
                    }else if (expression.charAt(i) == ',') {
                        result.append('.');
                    } else {
                        result.append(expression.charAt(i));
                    }
                }
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                String math = result.toString();
                    try {
                        Object res = engine.eval(math);
                        String resStr = res.toString();
                        resStr = resStr.replace(".",",");
                        if (resStr.endsWith(",0")) {
                            resStr = resStr.substring(0,resStr.length()-2);
                        }
                        editText.setText(text+"\n"+resStr);
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }
        }
    }


}