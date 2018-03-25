package com.example.sravanreddy.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText webText, location, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webText=findViewById(R.id.website_edittext);
        location=findViewById(R.id.location_edittext);
        message=findViewById(R.id.message_edittext);
    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.button_search:
                String url=webText.getText().toString();
                Intent searchIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if(searchIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(searchIntent);
                }
                else{
                    Log.d("ImplicitIntents", "Can't handle this intent!");}
                break;
            case R.id.location_button:
                String loc=location.getText().toString();
                Intent findIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + loc));
                if(findIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(findIntent);
                }
                break;
            case R.id.share_button:
                String msg=message.getText().toString();
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plain")
                        .setChooserTitle("Select an application")
                        .setText(msg)
                        .startChooser();
        }
    }
}
