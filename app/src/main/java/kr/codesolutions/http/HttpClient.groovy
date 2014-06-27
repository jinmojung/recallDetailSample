package kr.codesolutions.http;


import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams
import groovy.transform.CompileStatic;
@CompileStatic
public class HttpClient {
    private static final String API_KEY = "dfe4e1cd8372facb6e689ebb83735bc1";
    private static final String BASE_URL = "http://www.ibtk.kr/recallDetail_api/"+API_KEY;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i("test11", BASE_URL);
        client.get(BASE_URL, params, responseHandler);
    }

    public static void post(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(BASE_URL, params, responseHandler);
    }
}
