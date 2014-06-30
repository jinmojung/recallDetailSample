package kr.codesolutions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
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
    ProgressBar mProgressBar
    RecallDetailContentListAdapter listAdapter;
    Gson gson
    boolean isLoading


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int pageNumber = 1
        listView = (ListView) findViewById(R.id.listViews);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        listAdapter =  new RecallDetailContentListAdapter(getApplicationContext(),R.layout.adapter_retail_list);
        listView.setAdapter(listAdapter);
        gson = new Gson();
        RequestModelQueryPageable pageQuery = new RequestModelQueryPageable();
        List sortOrders = new ArrayList<RequestModelQueryPageableSortOrders>();
        RequestModelQueryPageableSortOrders sortOrder = new RequestModelQueryPageableSortOrders();
        sortOrder.setDirection(1);
        sortOrder.setProperty("linkID");
        sortOrders.add(sortOrder);
        pageQuery.setEnable(this.pageQuery.enable);
        pageQuery.pageNumber = pageNumber
        pageQuery.setPageSize(this.pageQuery.pageSize);
        pageQuery.setSortOrders(sortOrders);

        String pageQueryJson = gson.toJson(pageQuery);
        Log.d(Const.TAG, "pageQuery="+pageQueryJson);
        RequestParams params = new RequestParams();
        params.put("model_query_pageable", pageQueryJson);
        Map<String,Integer> modelQueryFields = new HashMap<String, Integer>();
        showFieldsCheckedList.each {checked->
            modelQueryFields.put(this.modelLabelValueList.get(checked).value,1);
        }
        String model_query_fieldsresult = gson.toJson(modelQueryFields);
        Log.d(Const.TAG, "model_query_fields="+model_query_fieldsresult);
        params.put("model_query_fields", model_query_fieldsresult);
        listView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            void onScroll(AbsListView view, int firstVisibleItem,
                          int visibleItemCount, int totalItemCount) {
                Log.d(Const.TAG, "firstVisibleItem=" + firstVisibleItem);
                Log.d(Const.TAG, "visibleItemCount=" + visibleItemCount);
                Log.d(Const.TAG, "totalItemCount=" + totalItemCount);
                if(isLastItem(firstVisibleItem,visibleItemCount,totalItemCount)){
                    pageNumber++
                    pageQuery.pageNumber = pageNumber
                    params.put("model_query_pageable", gson.toJson(pageQuery));
                    getList(params, gson, listAdapter);
                    Log.d(Const.TAG, "333333333333");
                }
            }
        })
        getList(params, gson, listAdapter);
        Log.d(Const.TAG, "333333333333");



    }

    private boolean isLastItem(int firstVisibleItem,
                               int visibleItemCount, int totalItemCount) {
        return (!isLoading && totalItemCount != 0 && (firstVisibleItem + visibleItemCount) == totalItemCount)
    }

    private void showProgressBar() {
        isLoading = true
        mProgressBar.visibility = View.VISIBLE
    }

    private void hideProgressBar() {
        isLoading = false
        mProgressBar.visibility = View.GONE
    }

    private getList(RequestParams params, Gson gson, RecallDetailContentListAdapter listAdapter) {
        showProgressBar()
        HttpClient.get(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.d(Const.TAG, "content=" + content);
                RecallDetail result1 = (RecallDetail) gson.fromJson(content, new TypeToken<RecallDetail>() {
                }.getType());
                Log.d(Const.TAG, "content=" + result1.getContent().get(0).getHarmLevel());
                listAdapter.setData(result1.getContent());
                hideProgressBar()
            }


            @Override
            void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error)
                Log.d(Const.TAG, "statusCode=" + statusCode);
                Log.d(Const.TAG, "headers=" + headers);
                Log.d(Const.TAG, "responseBody=" + responseBody);
                Log.d(Const.TAG, "error=" + error);
                hideProgressBar()
            }
        })
    }

}
