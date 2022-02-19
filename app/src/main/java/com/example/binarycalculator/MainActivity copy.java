package com.example.binarycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    TextView t;
    Button zero;
    Button one;
    Button plus;
    Button equals;
    String output;
    int counter;
    int error;
    int iln1, iln2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.textView);
        zero = findViewById(R.id.button);
        one = findViewById(R.id.button2);
        plus = findViewById(R.id.button3);
        equals = findViewById(R.id.button4);
        output = "";
        counter = 0;
        error = 0;
        iln1 = 0;
        iln2 = 0;
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String c = t.getText().toString();
                    StringTokenizer st = new StringTokenizer(c, "+", true);
                    ArrayList<String> l = new ArrayList<String>();
                    while(st.hasMoreTokens() && counter <= 17){
                        l.add(st.nextToken());
                    }
                    int fNum = l.get(0).length();
                    int sNum = l.get(2).length();
                    // do the line below
                    if((iln1 + iln2) != 16|| fNum != 8 || sNum != 8)
                        error++;
                    //System.out.print("first number is" + l.get(0));
                    for(int i = 0; i < l.size()-1; i++){
                        if(l.get(i).equals("+")){
                            int[] tot = new int[8];
                            String a = l.get(i-1);
                            int[] a1 = new int[a.length()];
                            for(int k = 0; k < a.length(); k++)
                                a1[k] = a.charAt(k) - '0';
                            String b = l.get(i+1);
                            int[] b1 = new int[b.length()];
                            for(int j = 0; j < b.length(); j++)
                                b1[j] = b.charAt(j) - '0';
                            for(int f = 0; f < a.length(); f++){
                                int temp = a1[f] + b1[f];
                                tot[f] = temp;
                            }
                            output = "";
                            int[] ntot = new int[9];
                            for(int d = 1; d < ntot.length; d++)
                                ntot[d] = tot[d-1];
                            for(int x = ntot.length - 1; x >= 0; x--){
                                if(ntot[x] == 2){
                                    ntot[x] = 0;
                                    ntot[x-1] = ntot[x-1] + 1;
                                }
                                else if(ntot[x] == 3)
                                {
                                    ntot[x] = 1;
                                    ntot[x-1] = ntot[x-1]+1;
                                }
                                output += Integer.toString(ntot[x]);
                            }
                            String res = new StringBuffer(output).reverse().toString();
                            output = res;
                            t.setText(res);
                        }
                    }
                }
                catch (Exception e){
                    output = "Error";
                    t.setText("Error");
                }
                if(error != 0)
                    t.setText("Error");
            }
        });
    }
    public void onClickGo1(View v)
    {
        output += ((Button)v).getText();
        if(((Button)v).getText().toString().equals("0"))
            iln1++;
        if(((Button)v).getText().toString().equals("1"))
            iln2++;
        t.setText(output);
        counter++;
    }
    public void onClickGo2(View v)
    {
        output = "";
        t.setText(output);
        error = 0;
        iln1 = 0;
        iln2 = 0;
        counter = 0;
    }
}