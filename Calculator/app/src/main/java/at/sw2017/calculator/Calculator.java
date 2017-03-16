package at.sw2017.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.button;

public class Calculator extends Activity implements View.OnClickListener  {
    public enum State {
        ADD, SUB, MUL, DIV, INIT, NUM
    }

    private Button buttonplus;
    private Button buttondivide;
    private Button buttonminus;
    private Button buttonmultiply;
    private Button buttonequals;
    private Button buttonC;
    private TextView numberView;
    private int firstNumber=0;
    private State state;

    private ArrayList<Button> numberButtons;

    private void clearTextView() {
        numberView.setText("0");
        firstNumber = 0;
        state = State.INIT;
    }

    public void setUpNumberButtonListener​(){
        for (int i = 0; i<= 9; i++) {
            String buttonName = "button" + i;
            int id = getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());

            Button button = ( Button ) findViewById(id);
            button.setOnClickListener(this);


            numberButtons.add(button);
        }

    }

    private void calculateResult() {
        int secondNumber = 0;
        String tempString = numberView.getText().toString();
        if(!tempString.equals("")){
            secondNumber = Integer.valueOf(tempString);
        }
        int result;
        switch(state){
            case ADD:
                result = Calculations.doAddition(firstNumber, secondNumber);
                break;
            case SUB:
                result = Calculations.doSubtraction(firstNumber, secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber, secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber, secondNumber);
                break;
            default:
                result = secondNumber;
        }
        numberView.setText(Integer.toString(result));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        numberButtons = new ArrayList<Button>();

        setUpNumberButtonListener​();

        buttonplus = (Button) findViewById(R.id.buttonplus);
        buttonminus = (Button) findViewById(R.id.buttonminus);
        buttondivide = (Button) findViewById(R.id.buttondivide);
        buttonmultiply = (Button) findViewById(R.id.buttonmultiply);
        buttonC = (Button) findViewById(R.id.buttonc);
        buttonequals = (Button) findViewById(R.id.buttonequals);
        numberView = (TextView) findViewById(R.id.textView);

        buttonplus.setOnClickListener(this);
        buttonminus.setOnClickListener(this);
        buttondivide.setOnClickListener(this);
        buttonmultiply.setOnClickListener(this);
        buttonequals.setOnClickListener(this);
        buttonC.setOnClickListener(this);
    }



    private void clearNumberView() {
        String tempString = numberView.getText().toString();
        if(!tempString.equals("")){
            firstNumber = Integer.valueOf(tempString);
        }
        numberView.setText("");
    }


    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.buttonplus:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonminus:
                clearNumberView();
                state = State.SUB;
                break;
            case R.id.buttonmultiply:
                clearNumberView();
                state = State.MUL;
                break;
            case R.id.buttondivide:
                clearNumberView();
                state = State.DIV;
                break;
            case R.id.buttonequals:
                calculateResult();
                state = State.INIT;
                break;
            case R.id.buttonc:
                clearTextView();
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if (state == State.INIT) {
                    recentNumber = "";
                    state = State.NUM;
                }
                if (recentNumber.equals("0")) {
                    recentNumber = "";
                }
                recentNumber += clickedButton.getText().toString();
                numberView.setText(recentNumber);
        }
    }
}


