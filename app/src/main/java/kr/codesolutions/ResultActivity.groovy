package kr.codesolutions

import android.app.Activity
import android.content.Intent
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
import kr.codesolutions.vo.RecallDetail
import kr.codesolutions.vo.RequestModelQueryPageable
import kr.codesolutions.vo.RequestModelQueryPageableSortOrders
import org.apache.http.Header

@CompileStatic
public class ResultActivity extends BaseActivity {

    ListView listView;
    RecallDetailContentListAdapter listAdapter;
    Gson gson

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView = (ListView) findViewById(R.id.listViews);
        listAdapter =  new RecallDetailContentListAdapter(getApplicationContext(),R.layout.adapter_retail_list);
        listView.setAdapter(listAdapter);
        gson = new Gson();
        RequestModelQueryPageable pageQuery = new RequestModelQueryPageable();
        List sortOrders = new ArrayList<RequestModelQueryPageableSortOrders>();
        RequestModelQueryPageableSortOrders sortOrder = new RequestModelQueryPageableSortOrders();
        sortOrder.setDirection(1);
        sortOrder.setProperty("linkID");
        sortOrders.add(sortOrder);
        pageQuery.setEnable(true);
        pageQuery.setPageNumber(2);
        pageQuery.setPageSize(2);
        pageQuery.setSortOrders(sortOrders);

        String result = gson.toJson(pageQuery);
        Log.d(Const.TAG, "pageQuery="+result);
        RequestParams params = new RequestParams();
        params.put("model_query_pageable", result);
        Map<String,Integer> modelQueryFields = new HashMap<String, Integer>();
        showValueCheckedList.each {checked->
            modelQueryFields.put(this.modelLabelValueList.get(checked).value,1);
        }
        String model_query_fieldsresult = gson.toJson(modelQueryFields);
        Log.d(Const.TAG, "model_query_fields="+model_query_fieldsresult);
        params.put("model_query_fields", model_query_fieldsresult);
        HttpClient.get(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.d(Const.TAG, "content=" + content);
                RecallDetail result1 = (RecallDetail) gson.fromJson(content, new TypeToken<RecallDetail>() {
                }.getType());
                Log.d(Const.TAG, "content=" + result1.getContent().get(0).getHarmLevel());
                listAdapter.setData(result1.getContent());
            }


            @Override
            void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error)
                Log.d(Const.TAG, "statusCode=" + statusCode);
                Log.d(Const.TAG, "headers=" + headers);
                Log.d(Const.TAG, "responseBody=" + responseBody);
                Log.d(Const.TAG, "error=" + error);
            }
        });
        Log.d(Const.TAG, "333333333333");



    }

}
