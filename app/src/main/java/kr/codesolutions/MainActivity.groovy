package kr.codesolutions

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import groovy.transform.CompileStatic

@CompileStatic
public class MainActivity extends Activity {
    String test = "test"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.onClickListener = {
            String local = owner.test
        } as View.OnClickListener
    }
}
