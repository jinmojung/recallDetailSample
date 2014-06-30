package kr.codesolutions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView
import groovy.transform.CompileStatic
import kr.codesolutions.R
import kr.codesolutions.vo.RecallDetailContent;

import java.util.ArrayList;
import java.util.List;

@CompileStatic
public class RecallDetailContentListAdapter extends BaseAdapter {

    private List<RecallDetailContent> recallDetailContentList = new ArrayList<RecallDetailContent>();
    private Context context;
    private int layout;
    private LayoutInflater inflater;

    public RecallDetailContentListAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<RecallDetailContent> recallDetailContentList) {
        this.recallDetailContentList.addAll(recallDetailContentList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recallDetailContentList.size();
    }

    @Override
    public RecallDetailContent getItem(int position) {
        return recallDetailContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        RecallDetailContent recallDetailContent = getItem(position);

        setRowData(R.id.harmLevelTxt, R.id.harmLevelLinear,recallDetailContent.getHarmLevel(),convertView);
        setRowData(R.id.harmCauseTxt, R.id.harmCauseLinear,recallDetailContent.getHarmCause(),convertView);
        setRowData(R.id.harmFlawInfoTxt, R.id.harmFlawInfoLinear,recallDetailContent.getHarmFlawInfo(),convertView);
        setRowData(R.id.recallActionTxt, R.id.recallActionLinear,recallDetailContent.getRecallAction(),convertView);
        setRowData(R.id.recallMeansTxt, R.id.recallMeansLinear,recallDetailContent.getRecallMeans(),convertView);
        setRowData(R.id.publicDateTxt, R.id.publicDateLinear,recallDetailContent.getPublicDate(),convertView);
        setRowData(R.id.makeTerm1Txt, R.id.makeTerm1Linear,recallDetailContent.getMakeTerm1(),convertView);
        setRowData(R.id.makeTerm2Txt, R.id.makeTerm2Linear,recallDetailContent.getMakeTerm2(),convertView);
        setRowData(R.id.sellTerm1Txt, R.id.sellTerm1Linear,recallDetailContent.getSellTerm1(),convertView);
        setRowData(R.id.sellTerm2Txt, R.id.sellTerm2Linear,recallDetailContent.getSellTerm2(),convertView);
        setRowData(R.id.actionTerm1Txt, R.id.actionTerm1Linear,recallDetailContent.getActionTerm1(),convertView);
        setRowData(R.id.actionTerm2Txt, R.id.actionTerm2Linear,recallDetailContent.getActionTerm2(),convertView);
        setRowData(R.id.makeAmountTxt, R.id.makeAmountLinear,recallDetailContent.getMakeAmount(),convertView);
        setRowData(R.id.sellAmountTxt, R.id.sellAmountLinear,recallDetailContent.getSellAmount(),convertView);
        setRowData(R.id.recallActionVolTxt, R.id.recallActionVolLinear,recallDetailContent.getRecallActionVol(),convertView);
        setRowData(R.id.saleCompanyTxt, R.id.saleCompanyLinear,recallDetailContent.getSaleCompany(),convertView);
        setRowData(R.id.importCompanyTxt, R.id.importCompanyLinear,recallDetailContent.getImportCompany(),convertView);
        setRowData(R.id.linkURLTxt, R.id.linkURLLinear,recallDetailContent.getLinkURL(),convertView);
        setRowData(R.id.linkIDTxt, R.id.linkIDLinear,recallDetailContent.getLinkID(),convertView);
        setRowData(R.id.closeCheckTxt, R.id.closeCheckLinear,recallDetailContent.getCloseCheck(),convertView);
        setRowData(R.id.productNameTxt, R.id.productNameLinear,recallDetailContent.getProductName(),convertView);
        setRowData(R.id.trademarkTxt, R.id.trademarkLinear,recallDetailContent.getTrademark(),convertView);
        setRowData(R.id.modelTxt, R.id.modelLinear,recallDetailContent.getModel(),convertView);
        setRowData(R.id.serialNumberTxt, R.id.serialNumberLinear,recallDetailContent.getSerialNumber(),convertView);
        setRowData(R.id.makingNationTxt, R.id.makingNationLinear,recallDetailContent.getMakingNation(),convertView);
        setRowData(R.id.recallStateTxt, R.id.recallStateLinear,recallDetailContent.getRecallState(),convertView);
        setRowData(R.id.actionsTxt, R.id.actionsLinear,recallDetailContent.getActions(),convertView);
        setRowData(R.id.productContentsTxt, R.id.productContentsLinear,recallDetailContent.getProductContents(),convertView);
        setRowData(R.id.recallAmountTxt, R.id.recallAmountLinear,recallDetailContent.getRecallAmount(),convertView);
        setRowData(R.id.harmContentsTxt, R.id.harmContentsLinear,recallDetailContent.getHarmContents(),convertView);
        setRowData(R.id.accidentExamTxt, R.id.accidentExamLinear,recallDetailContent.getAccidentExam(),convertView);
        setRowData(R.id.rc_req_NoTxt, R.id.rc_req_NoLinear,recallDetailContent.getRc_req_No(),convertView);
        setRowData(R.id.recallNationTypeTxt, R.id.recallNationTypeLinear,recallDetailContent.getRecallNationType(),convertView);
        setRowData(R.id.confirmUIDTxt, R.id.confirmUIDLinear,recallDetailContent.getConfirmUID(),convertView);
        setRowData(R.id.recallTypeTxt, R.id.recallTypeLinear,recallDetailContent.getRecallType(),convertView);
        setRowData(R.id.companyNameTxt, R.id.companyNameLinear,recallDetailContent.getCompanyName(),convertView);

        return convertView;
    }

    private void setRowData(int txtViewId, int linearViewId,String rowData,View convertView) {
        TextView txtView = (TextView) convertView.findViewById(txtViewId);
        LinearLayout linearView = (LinearLayout) convertView.findViewById(linearViewId);
        if(rowData != null &&  !rowData.equals("")){
            txtView.setText(rowData);
        }else{
            linearView.setVisibility(View.GONE);
        }
    }
}
