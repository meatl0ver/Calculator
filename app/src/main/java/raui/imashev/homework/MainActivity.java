package raui.imashev.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static raui.imashev.homework.R.id.buttonC;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private static final String TEXT = "text";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextText);
        List<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(buttonC));
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        text = editText.getText().toString();
        if (text.equals(getString(R.string._0))) text = "";
        switch (v.getId()) {
            case R.id.buttonC:
                editText.setText(getString(R.string._0));
                break;
            case R.id.button0:
                if (text.equals(getString(R.string._0))) {
                    editText.setText(getString(R.string._0));
                } else {
                    editText.setText(String.format("%s%s", text, getString(R.string._0)));
                }
                break;
            case R.id.button1:
                editText.setText(String.format("%s%s", text, getString(R.string._1)));
                break;
            case R.id.button2:
                editText.setText(String.format("%s%s", text, getString(R.string._2)));
                break;
            case R.id.button3:
                editText.setText(String.format("%s%s", text, getString(R.string._3)));
                break;
            case R.id.button4:
                editText.setText(String.format("%s%s", text, getString(R.string._4)));
                break;
            case R.id.button5:
                editText.setText(String.format("%s%s", text, getString(R.string._5)));
                break;
            case R.id.button6:
                editText.setText(String.format("%s%s", text, getString(R.string._6)));
                break;
            case R.id.button7:
                editText.setText(String.format("%s%s", text, getString(R.string._7)));
                break;
            case R.id.button8:
                editText.setText(String.format("%s%s", text, getString(R.string._8)));
                break;
            case R.id.button9:
                editText.setText(String.format("%s%s", text, getString(R.string._9)));
                break;
            case R.id.buttonPlus:
                if (editText.getText().toString().equals(getString(R.string._0))) {
                    editText.setText(String.format("%s+", getString(R.string._0)));
                } else {
                    editText.setText(String.format("%s+", text));
                }
                break;
            case R.id.buttonMinus:
                if (editText.getText().toString().equals("0")) {
                    editText.setText("0-");
                } else {
                    editText.setText(String.format("%s-", text));
                }
                break;
            case R.id.buttonMultiply:
                if (editText.getText().toString().equals(getString(R.string._0))) {
                    editText.setText(String.format("%s×", getString(R.string._0)));
                } else {
                    editText.setText(String.format("%s×", text));
                }
                break;
            case R.id.buttonDivide:
                if (editText.getText().toString().equals("0")) {
                    editText.setText(String.format("%s÷", getString(R.string._0)));
                } else {
                    editText.setText(String.format("%s÷", text));
                }
                break;
            case R.id.buttonLeftHawk:
                editText.setText(String.format("%s(", text));
                break;
            case R.id.buttonRightHawk:
                editText.setText(String.format("%s)", text));
                break;
            case R.id.buttonComma:
                if (editText.getText().toString().equals("0")) {
                    editText.setText(String.format("%s,", getString(R.string._0)));
                } else {
                    editText.setText(String.format("%s,", text));
                }
                break;
            case R.id.buttonDelete:
                if (text.length() > 1) {
                    editText.setText(text.substring(0, text.length() - 2));
                } else {
                    editText.setText(getString(R.string._0));
                }
                break;
            case R.id.buttonEquals:
                String expression = editText.getText().toString();
                if (expression.contains("+") || expression.contains("-")
                        || expression.contains("×") || expression.contains("÷")) {
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

    //кнопка для перехода к настройкам
    public void onClickToSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
