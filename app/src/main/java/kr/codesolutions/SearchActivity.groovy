package kr.codesolutions

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Switch
import android.widget.TextView
import groovy.transform.CompileStatic
import kr.codesolutions.common.Const
import kr.codesolutions.vo.LabelValueVo

@CompileStatic
public class SearchActivity extends BaseActivity {

    TextView showFields;
    Switch pagingEnable;
    LinearLayout pagingLayout;
    TextView mPageSize;
    Button searchBtn;
    ListView showFieldsBuilderListView;
    Switch mSearchQueryEnable;
    LinearLayout mSearchQueryLayout
    AlertDialog.Builder showFieldsBuilder;
    AlertDialog.Builder pageSizeBuilder;

    private DialogInterface.OnClickListener showFieldsBuilderOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d(Const.TAG+getClass().getName(),"showFieldsBuilderOnClickListener")
            showFieldsCheckedList.clear()
            showDialogCheckedItemInView()
            // hide dialog
            dialog.dismiss();
        }
    };

    private DialogInterface.OnClickListener pageSizeBuilderOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d(Const.TAG+getClass().getName(),"pageSizeBuilderOnClickListener")
            pageQuery.pageSize = pageQueryPageSizeList[which].toInteger()
            showPageSize();
            dialog.dismiss();
        }

    };

    private showPageSize() {
        mPageSize.setText("출력 페이지 사이즈=" + pageQuery.pageSize)
    }

    private void showDialogCheckedItemInView() {
        StringBuffer stringBuffer = new StringBuffer();
        def checkedItemPositions = showFieldsBuilderListView.getCheckItemIds()
        def showValueCheckedList = mMyApplication.showFieldsCheckedList
        def modelLabelValueList = mMyApplication.modelLabelValueList
        checkedItemPositions.each { Long checked ->
            showValueCheckedList.add(checked.toInteger())
            stringBuffer.append(modelLabelValueList.get(checked.toInteger()).label);
            stringBuffer.append(", ");
        }
        if (checkedItemPositions.length != 0) {
            stringBuffer.setLength(stringBuffer.length() - 2);
            showFields.setText("출력 필드=" + stringBuffer.toString());
        } else {
            showFields.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Const.TAG+getClass().getName(),"onCreate")
        setContentView(R.layout.activity_search);
        Log.d(Const.TAG+getClass().getName(),"staryt setViews")
        setViews()
        Log.d(Const.TAG+getClass().getName(),"end setViews")
        setBuilders()
        Log.d(Const.TAG+getClass().getName(),"end setBuilders")
        setListeners()
        Log.d(Const.TAG+getClass().getName(),"end setListeners")
        checkListViewFromCheckedList()
        Log.d(Const.TAG+getClass().getName(),"end checkListViewFromCheckedList")
        showDialogCheckedItemInView()
        Log.d(Const.TAG+getClass().getName(),"end showDialogCheckedItemInView")
        setViewFromPageQueryEnableData()
        setViewFromSearchQueryEnableData()
        Log.d(Const.TAG+getClass().getName(),"end setViewFromPageQueryEnableData")
        showPageSize();
    }

    private void setListeners() {
        showFields.onClickListener = {
            ViewGroup listViewParent = (ViewGroup)this.showFieldsBuilderListView.getParent();
            if(listViewParent != null){
                listViewParent.removeView(this.showFieldsBuilderListView);
            }
            this.showFieldsBuilderListView.clearChoices()
            checkListViewFromCheckedList()
            this.showFieldsBuilder.show();
        }
        pagingEnable.onCheckedChangeListener = {CompoundButton buttonView, boolean isChecked->
            this.pageQuery.enable = isChecked
            setViewFromPageQueryEnableData()
        } as CompoundButton.OnCheckedChangeListener

        mSearchQueryEnable.onCheckedChangeListener = {CompoundButton buttonView, boolean isChecked->
            this.searchQueryEnable = isChecked
            setViewFromSearchQueryEnableData()
        } as CompoundButton.OnCheckedChangeListener
        mPageSize.onClickListener = {
            this.pageSizeBuilder.show();
        }
        searchBtn.onClickListener = {
            startActivity(new Intent(this, ResultActivity.class))
        }
    }

    private void setViewFromPageQueryEnableData() {
        pagingEnable.checked = this.pageQuery.enable
        if (this.pageQuery.enable) {
            this.pagingLayout.visibility = View.VISIBLE
        } else {
            this.pagingLayout.visibility = View.GONE
        }
    }

    private void setViewFromSearchQueryEnableData() {
        mSearchQueryEnable.checked = this.searchQueryEnable
        if (this.searchQueryEnable) {
            this.mSearchQueryLayout.visibility = View.VISIBLE
        } else {
            this.mSearchQueryLayout.visibility = View.GONE
        }
    }

    private void setBuilders() {
        showFieldsBuilderListView = (ListView)getLayoutInflater().inflate(R.layout.alert_multiselect_list,null);
        ArrayAdapter<LabelValueVo> adapter = new ArrayAdapter<LabelValueVo>(this,android.R.layout.simple_list_item_multiple_choice, modelLabelValueList);
        showFieldsBuilderListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        showFieldsBuilderListView.setAdapter(adapter);
        showFieldsBuilder = new AlertDialog.Builder(this).setTitle("출력필드 선택")
                .setView(showFieldsBuilderListView)
                .setPositiveButton(android.R.string.ok, showFieldsBuilderOnClickListener)
        showFieldsBuilder.onCancelListener = {
            //Log.d(Const.TAG + getClass().getName(), "onCancelListener" + this.showFieldsCheckedList.size())
        }
        pageSizeBuilder = new AlertDialog.Builder(this).setTitle("출력 페이지 사이즈 선택")
                .setItems(pageQueryPageSizeList as CharSequence[],pageSizeBuilderOnClickListener)
    }

    private void setViews() {
        showFields = (TextView) findViewById(R.id.showFields);
        pagingEnable = (Switch) findViewById(R.id.pagingEnable);
        pagingLayout = (LinearLayout) findViewById(R.id.pagingLayout);
        mPageSize = (TextView) findViewById(R.id.pageSize);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        mSearchQueryEnable = (Switch) findViewById(R.id.searchQueryEnable);
        mSearchQueryLayout = (LinearLayout) findViewById(R.id.searchQueryLayout);
    }

    private Iterable<Integer> checkListViewFromCheckedList() {
        this.showFieldsCheckedList.each { checked ->
            this.showFieldsBuilderListView.setItemChecked(checked, true)
        }
    }

}
