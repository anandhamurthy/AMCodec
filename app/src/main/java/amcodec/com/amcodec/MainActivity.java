package amcodec.com.amcodec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import amcodec.com.amendecoder.EnDecoder;

public class MainActivity extends AppCompatActivity {

    private EditText Input;
    private TextView Output;
    private Button EncodeorDecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input = findViewById(R.id.input);
        Output = findViewById(R.id.output);
        EncodeorDecode = findViewById(R.id.decode);

        EncodeorDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EncodeorDecode.getText().equals("Encode")){
                    if(!Input.getText().toString().isEmpty()){
                        String encoding_text = Input.getText().toString();
                        Output.setText(EnDecoder.AMEncode(encoding_text));
                        EncodeorDecode.setText("Decode");
                    }else{
                        Toast.makeText(MainActivity.this, "Encoding Text is Empty", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(!Output.getText().toString().isEmpty()){
                        String decoding_text = Output.getText().toString();
                        Output.setText(EnDecoder.AMDecode(decoding_text));
                        EncodeorDecode.setText("Encode");
                    }
                }

            }
        });

    }
}
