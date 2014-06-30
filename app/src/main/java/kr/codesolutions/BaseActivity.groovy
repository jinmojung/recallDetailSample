package kr.codesolutions

import android.app.Activity
import android.os.Bundle
import groovy.transform.CompileStatic
import kr.codesolutions.vo.LabelValueVo
import kr.codesolutions.vo.RequestModelQueryPageable

@CompileStatic
public class BaseActivity extends Activity {

    MyApplication mMyApplication
    List<LabelValueVo> modelLabelValueList;
    List<Integer> showFieldsCheckedList
    RequestModelQueryPageable pageQuery
    List<String> pageQueryPageSizeList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApplication = (MyApplication)getApplication()
        modelLabelValueList = mMyApplication.modelLabelValueList
        showFieldsCheckedList = mMyApplication.showFieldsCheckedList
        pageQuery = mMyApplication.pageQuery
        pageQueryPageSizeList = mMyApplication.pageQueryPageSizeList
    }

}
