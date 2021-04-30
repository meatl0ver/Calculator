package raui.imashev.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private final String TEXT = "text";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextText);
        ArrayList<Button> buttons = new ArrayList<>();
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT, text);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString(TEXT);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        text = editText.getText().toString();
        if (text.equals("0")) text = "";
        switch (v.getId()) {
            case R.id.buttonC:
                editText.setText("0");
                break;
            case R.id.button0:
                if (text.equals("0")) {
                    editText.setText("0");
                } else {
                    editText.setText(text + "0");
                }
                break;
            case R.id.button1:
                editText.setText(text + "1");
                break;
            case R.id.button2:
                editText.setText(text + "2");
                break;
            case R.id.button3:
                editText.setText(text + "3");
                break;
            case R.id.button4:
                editText.setText(text + "4");
                break;
            case R.id.button5:
                editText.setText(text + "5");
                break;
            case R.id.button6:
                editText.setText(text + "6");
                break;
            case R.id.button7:
                editText.setText(text + "7");
                break;
            case R.id.button8:
                editText.setText(text + "8");
                break;
            case R.id.button9:
                editText.setText(text + "9");
                break;
            case R.id.buttonPlus:
                if (editText.getText().toString().equals("0")) {
                    editText.setText("0+");
                } else {
                    editText.setText(text + "+");
                }
                break;
            case R.id.buttonMinus:
                if (editText.getText().toString().equals("0")) {
                    editText.setText("0-");
                } else {
                    editText.setText(text + "-");
                }
                break;
            case R.id.buttonMultiply:
                if (editText.getText().toString().equals("0")) {
                    editText.setText("0×");
                } else {
                    editText.setText(text + "×");
                }
                break;
            case R.id.buttonDivide:
                if (editText.getText().toString().equals("0")) {
                    editText.setText("0÷");
                } else {
                    editText.setText(text + "÷");
                }
                break;
            case R.id.buttonLeftHawk:
                editText.setText(text + "(");
                break;
            case R.id.buttonRightHawk:
                editText.setText(text + ")");
                break;
            case R.id.buttonComma:
                if (editText.getText().toString().equals("0")) {
                    editText.setText("0,");
                } else {
                    editText.setText(text + ",");
                }
                break;
            case R.id.buttonDelete:
                if (text.length() > 1) {
                    editText.setText(text.substring(0, text.length() - 2));
                } else {
                    editText.setText("0");
                }
                break;
            case R.id.buttonEquals:
                String expression = editText.getText().toString();
                if (expression.contains("+") || expression.contains("-")
                        || expression.contains("×") || expression.contains("÷"))
                {
                    editText.setText(String.format("%s\n%s", text, multiply(expression)));
                }
                break;
        }
    }

    public String multiply(String expression) {
        StringBuilder result = new StringBuilder();
        String resStr;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '×') {
                result.append('*');
            } else if (expression.charAt(i) == '÷') {
                result.append('/');
            } else if (expression.charAt(i) == ',') {
                result.append('.');
            } else {
                result.append(expression.charAt(i));
            }
        }
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        String math = result.toString();
        Object res = null;
        try {
            res = engine.eval(math);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        resStr = res.toString();
        resStr = resStr.replace(".", ",");
        if (resStr.endsWith(",0")) {
            resStr = resStr.substring(0, resStr.length() - 2);
        }
        return resStr;
    }

}
