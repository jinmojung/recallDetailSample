package kr.codesolutions

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import groovy.transform.CompileStatic
import kr.codesolutions.common.Const
import kr.codesolutions.vo.LabelValueVo
import kr.codesolutions.vo.RequestModelQueryPageable
import kr.codesolutions.vo.RequestModelQueryPageableSortOrders

@CompileStatic
public class MyApplication extends Application {
    List<LabelValueVo> modelLabelValueList;
    List<String> pageQueryPageSizeList;
    List<Integer> showFieldsCheckedList
    RequestModelQueryPageable pageQuery
    List pageQuerySortOrders
    RequestModelQueryPageableSortOrders pageQuerySortOrder
    boolean searchQueryEnable
    String searchQueryEqualField
    String searchQueryEqualValue

    private void addModelLabelValueList(String value,String label) {
        LabelValueVo vo = new LabelValueVo();
        vo.setValue(value);
        vo.setLabel(label);
        modelLabelValueList.add(vo);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Const.TAG+getClass().getName(),"onCreate")
        modelLabelValueList = new ArrayList<LabelValueVo>();
        addModelLabelValueList("harmLevel","위해수준");
        addModelLabelValueList("harmCause","위해원인");
        addModelLabelValueList("harmFlawInfo","위해 결함정보");
        addModelLabelValueList("recallAction","리콜조치원인");
        addModelLabelValueList("recallMeans","리콜방법");
        addModelLabelValueList("publicDate","공표일자");
        addModelLabelValueList("makeTerm1","제조기간1");
        addModelLabelValueList("makeTerm2","제조기간2");
        addModelLabelValueList("sellTerm1","판매기간1");
        addModelLabelValueList("sellTerm2","판매기간2");
        addModelLabelValueList("actionTerm1","조치기간1");
        addModelLabelValueList("actionTerm2","조치기간2");
        addModelLabelValueList("makeAmount","제조수량");
        addModelLabelValueList("sellAmount","판매수량");
        addModelLabelValueList("recallActionVol","리콜수량");
        addModelLabelValueList("saleCompany","판매업체");
        addModelLabelValueList("importCompany","수입업체");
        addModelLabelValueList("linkURL","이미지 링크");
        addModelLabelValueList("linkID","이미지 고유번호");
        addModelLabelValueList("closeCheck","종결여부");
        addModelLabelValueList("productName","제품명");
        addModelLabelValueList("trademark","상표");
        addModelLabelValueList("model","모델명");
        addModelLabelValueList("serialNumber","일련번호");
        addModelLabelValueList("makingNation","제조국");
        addModelLabelValueList("recallState","리콜 상태");
        addModelLabelValueList("actions","조치사항");
        addModelLabelValueList("productContents","제품상세내용");
        addModelLabelValueList("recallAmount","판매수량");
        addModelLabelValueList("harmContents","위해내용");
        addModelLabelValueList("accidentExam","사고사례");
        addModelLabelValueList("rc_req_No","리콜 제품 id");
        addModelLabelValueList("recallNationType","리콜형태");
        addModelLabelValueList("confirmUID","인증번호UID");
        addModelLabelValueList("recallType","리콜종류");
        addModelLabelValueList("companyName","제보사업자명");
        showFieldsCheckedList = new ArrayList<Integer>();
        modelLabelValueList.eachWithIndex {LabelValueVo it, index ->
            if(it.value == 'model'){
                this.showFieldsCheckedList.add(index as Integer)
            }
        }

        pageQueryPageSizeList = new ArrayList<String>();
        (1..10).each{
            this.pageQueryPageSizeList.add(it.toString())
        }
        pageQuery = new RequestModelQueryPageable();
        pageQuery.enable = true
        pageQuery.pageSize = 10
        pageQuerySortOrders = new ArrayList<RequestModelQueryPageableSortOrders>();
        pageQuerySortOrder = new RequestModelQueryPageableSortOrders();
        searchQueryEnable = true
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
