package kr.codesolutions.vo;


import android.util.Log
import groovy.transform.CompileStatic
import kr.codesolutions.vo.BaseVo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@CompileStatic
public class RecallDetailContent extends BaseVo {
    String model;
    String recallNationType;
    String harmCause;
    String makeTerm1;
    String makeTerm2;
    String makeAmount;
    String serialNumber;
    String accidentExam;
    String actionTerm1;
    String confirmUID;
    String recallMeans;
    String recallState;
    String importCompany;
    String actionTerm2;
    String linkID;
    String harmLevel;
    String makingNation;
    String recallActionVol;
    String harmContents;
    String closeCheck;
    String recallType;
    String sellAmount;
    String sellTerm1;
    String sellTerm2;
    String companyName;
    String rc_req_No;
    String recallAction;
    String publicDate;
    String trademark;
    String productContents;
    String saleCompany;
    String linkURL;
    String recallAmount;
    String productName;
    String harmFlawInfo;
    String actions;

    public static List<String> getList(){
        List<String> result =  new ArrayList<String>();
        Class<RecallDetailContent> cls = RecallDetailContent.class;
        Field[] declaredFields = cls.getDeclaredFields();
        for(Field declaredField:declaredFields){
            result.add(new String(declaredField.getName()));
        }
        return result;
    }


}
