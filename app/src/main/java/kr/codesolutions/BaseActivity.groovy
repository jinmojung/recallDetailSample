package kr.codesolutions

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import groovy.transform.CompileStatic
import kr.codesolutions.adapter.RecallDetailContentListAdapter
import kr.codesolutions.common.Const
import kr.codesolutions.http.HttpClient
import kr.codesolutions.vo.LabelValueVo
import kr.codesolutions.vo.RecallDetail
import kr.codesolutions.vo.RequestModelQueryPageable
import kr.codesolutions.vo.RequestModelQueryPageableSortOrders
import org.apache.http.Header

@CompileStatic
public class BaseActivity extends Activity {

    MyApplication mMyApplication
    List<LabelValueVo> modelLabelValueList;
    List<Integer> showValueCheckedList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApplication = (MyApplication)getApplication()
        modelLabelValueList = mMyApplication.modelLabelValueList
        showValueCheckedList = mMyApplication.showValueCheckedList
    }

}
