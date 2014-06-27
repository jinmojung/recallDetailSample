package kr.codesolutions

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import groovy.transform.CompileStatic
import kr.codesolutions.common.Const
import kr.codesolutions.vo.LabelValueVo

@CompileStatic
public class SearchActivity extends BaseActivity {

    EditText modelQueryFields;
    Button searchBtn;
    ListView listView;
    AlertDialog.Builder builder;

    private DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d(Const.TAG+getClass().getName(),"mOnClickListener")
            showValueCheckedList.clear()
            showDialogCheckedItemInView()
            // hide dialog
            dialog.dismiss();
        }
    };

    private void showDialogCheckedItemInView() {
        StringBuffer spinnerBuffer = new StringBuffer();
        def checkedItemPositions = listView.getCheckItemIds()
        def showValueCheckedList = mMyApplication.showValueCheckedList
        def modelLabelValueList = mMyApplication.modelLabelValueList
        checkedItemPositions.each { Long checked ->
            showValueCheckedList.add(checked.toInteger())
            spinnerBuffer.append(modelLabelValueList.get(checked.toInteger()).label);
            spinnerBuffer.append(", ");
        }
        if (checkedItemPositions.length != 0) {
            spinnerBuffer.setLength(spinnerBuffer.length() - 2);
            modelQueryFields.setText("출력 필드=" + spinnerBuffer.toString());
        } else {
            modelQueryFields.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Const.TAG+getClass().getName(),"onCreate")
        setContentView(R.layout.activity_search);
        listView = (ListView)getLayoutInflater().inflate(R.layout.alert_multiselect_list,null);
        ArrayAdapter<LabelValueVo> adapter = new ArrayAdapter<LabelValueVo>(this,android.R.layout.simple_list_item_multiple_choice, modelLabelValueList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        DialogInterface.OnClickListener click11 = {
            Log.d(Const.TAG+getClass().getName(),"mOnClickListener")
        } as DialogInterface.OnClickListener
        builder = new AlertDialog.Builder(this).setTitle(R.string.app_name)
                .setView(listView)
                .setPositiveButton(android.R.string.ok,mOnClickListener)
        builder.onCancelListener = {
            Log.d(Const.TAG+getClass().getName(),"onCancelListener"+this.showValueCheckedList.size())
        }
        modelQueryFields = (EditText)findViewById(R.id.modelQueryFields);
        searchBtn = (Button)findViewById(R.id.searchBtn);
        modelQueryFields.onClickListener = {
            ViewGroup listViewParent = (ViewGroup)this.listView.getParent();
            if(listViewParent != null){
                listViewParent.removeView(this.listView);
            }
            this.listView.clearChoices()
            checkListViewFromCheckedList()
            this.builder.show();
        }
        searchBtn.onClickListener = {
            startActivity(new Intent(this, ResultActivity.class))
        }
        checkListViewFromCheckedList()
        showDialogCheckedItemInView()
    }

    private Iterable<Integer> checkListViewFromCheckedList() {
        this.showValueCheckedList.each { checked ->
            this.listView.setItemChecked(checked, true)
        }
    }

}
