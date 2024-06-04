package com.example.philemon_calculatoe;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private EditText scrn;
    private Button n1;
    private Button n2;
    private Button n3;
    private Button n4;
    private Button n5;
    private Button n6;
    private Button n7;
    private Button n8;
    private Button n9;
    private Button n0;

    private Button addbtn;
    private Button oppsub;
    private Button oppmult;
    private Button oppdiv;
    public int st=0;
    public String opera="+";
    public double eval(double n1,double n2){
        double result=0;
        switch (opera){
            case "+":
                result= n1+n2;
                break;
            case "-":
                result= n1-n2;
            break;
            case "/":
                result= n1/n2;
            break;
            case "X":
                result= n1*n2;
            break;
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                newGame();
                return true;
            case R.id.help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AtomicReference<Float> result = new AtomicReference<>((float) 0);
        final double[] num1 = {0};
        int state = 0;
        TextView myopp= (TextView)  findViewById(R.id.myopp);
        scrn = (EditText) findViewById(R.id.scrn);
        n0 = (Button) findViewById(R.id.n0);
        n1 = (Button) findViewById(R.id.n1);
        n2 = (Button) findViewById(R.id.n2);
        n3 = (Button) findViewById(R.id.n3);
        n4 = (Button) findViewById(R.id.n4);
        n5 = (Button) findViewById(R.id.n5);
        n6 = (Button) findViewById(R.id.n6);
        n7 = (Button) findViewById(R.id.n7);
        n8 = (Button) findViewById(R.id.n8);
        n9 = (Button) findViewById(R.id.n9);
        n7 = (Button) findViewById(R.id.n7);
        n8 = (Button) findViewById(R.id.n8);
        n9 = (Button) findViewById(R.id.n9);
        addbtn = (Button) findViewById(R.id.addbtn);
        oppdiv = (Button) findViewById(R.id.oppdiv);
        oppmult = (Button) findViewById(R.id.oppmult);
        oppsub = (Button) findViewById(R.id.oppsub);
       Button clear = (Button)  findViewById(R.id.btnc);
       Button equal=(Button) findViewById(R.id.btnequal);
       Button dot=(Button) findViewById(R.id.btndot);
       Button square = (Button) findViewById(R.id.square);
       Button del = (Button) findViewById(R.id.btndel);
       clear.setOnClickListener(view -> {
           scrn.setText("0");
           myopp.setText("");
           st=0;
           num1[0]=0;
           opera="+";
       });
       ArrayList<Button> nums = new ArrayList<>();
        nums.add(n0);
        nums.add(n1);
        nums.add(n2);
        nums.add(n3);
        nums.add(n4);
        nums.add(n5);
        nums.add(n6);
        nums.add(n7);
        nums.add(n8);
        nums.add(n9);
        scrn.setFocusable(false);
        ArrayList<Button> opparators = new ArrayList<>();
        opparators.add(addbtn);
        opparators.add(oppmult);
        opparators.add(oppsub);
        opparators.add(oppdiv);
        Button nega = (Button) findViewById(R.id.sign);
        nega.setOnClickListener(view->{
            float value=Float.parseFloat(scrn.getText().toString())*-1;
            scrn.setText(""+value);

        });

        for (Button num : nums) {
            num.setOnClickListener(view -> {
                String text = scrn.getText().toString();
                String inputText = num.getText().toString();
                if (text.equals("0") || st==2) {
                    scrn.setText(inputText);
                    st=1;
                }
                else {
                    scrn.setText(text + inputText);

                }
                myopp.setText("");
            });}

        for (Button opp : opparators) {
            opp.setOnClickListener(view -> {
                double num2 = Double.parseDouble(scrn.getText().toString());
                double res = eval(num1[0],num2);

                if (res == Math.floor(res)) {
                    // No decimal part, display as an integer
                    scrn.setText(String.format("%.0f", res));
                } else {
                    // Decimal part exists, display up to a certain number of decimal places
                    scrn.setText(String.format("%.8f", res)); // Adjust the number of decimal places as needed
                }
                opera = opp.getText().toString();
                myopp.setText(opera);
                st=2;
                num1[0] =res;
            });
        }

        equal.setOnClickListener((View)->{
            double answer = eval(num1[0],Double.parseDouble(scrn.getText().toString()));
            st=0;
            num1[0]=0;
            opera="+";
            if (answer == Math.floor(answer)) {
                // No decimal part, display as an integer
                scrn.setText(String.format("%.0f", answer));
            } else {
                // Decimal part exists, display up to a certain number of decimal places
                scrn.setText(String.format("%.8f", answer)); // Adjust the number of decimal places as needed
            }

        });

        ArrayList<Button> btns = new ArrayList<>();
        btns.add(dot);
        btns.add(square);
        btns.add(del);

        for (Button btn: btns){
            double value=Double.parseDouble(scrn.getText().toString());
            btn.setOnClickListener(View->{
                if(btn==dot && !scrn.getText().toString().contains(".")){
                    if(st==1){
                        scrn.setText(scrn.getText().toString() + ".");
                    }else{
                        scrn.setText("0.");

                    }
                }
                else if (btn==del && !scrn.getText().toString().equals("0")) {
                    if(scrn.getText().toString().length()==1){
                        scrn.setText("0");
                        myopp.setText("");
                    }else{
                        scrn.setText(scrn.getText().toString().substring(0, scrn.getText().toString().length() - 1));
                    }
                }
                else if (btn==square){
                    double x=Double.parseDouble(scrn.getText().toString());

                    scrn.setText(""+(x*x));
                }
            });

        }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }