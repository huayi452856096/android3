package  com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;

//public class MainActivity extends AppCompatActivity{

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initView();
//    }
//    private void initView() {
//        Intent intent = getIntent();
//        String url = intent.getStringExtra("url");
//        if(TextUtils.isEmpty(url)) {
//            url = "http://www.baidu.com";
//        }
//        WebView webView = (WebView) findViewById(R.id.wbvw);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webView.loadUrl(url);
//    }
//}

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String strUriHome = null;
    WebView webView;//浏览器
    EditText edtttUri;//地址栏
    Button btnGo;//打开网页按钮
    Button btnHome;//首页按钮
    Button btnBack;//返回按钮
    Button btnForward;//前进按钮
    Button btnRefresh;//刷新按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String url = intent.getData().toString();
        if(TextUtils.isEmpty(url)) {
            url = "http://www.baidu.com";
        }
        strUriHome = url;

        webView = (WebView)findViewById(R.id.wbvw);
        edtttUri = (EditText)findViewById(R.id.edtttUri);
        btnGo = (Button)findViewById(R.id.btnGo);
        btnHome = (Button)findViewById(R.id.btnHome);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnForward = (Button)findViewById(R.id.btnForeward);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);

        webView.getSettings().setJavaScriptEnabled(true);//让浏览器支持javascript
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onLoadResource(WebView view, String url) {
                edtttUri.setText(view.getUrl());//当前WebView网址更新显示到地址栏
                super.onLoadResource(view, url);
            }
        });  //当跳转网页时，仍在WebView中打开。
        webView.loadUrl(strUriHome);

        edtttUri.setText(strUriHome);

        btnGo.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //转到
            case R.id.btnGo:
                try {
                    String strUri = uriHttpFirst(edtttUri.getText().toString());//网址协议判断
                    webView.loadUrl(strUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //首页
            case R.id.btnHome:
                try {
                    webView.loadUrl(strUriHome);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //返回
            case R.id.btnBack:
                try {
                    webView.goBack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //前进
            case R.id.btnForeward:
                try {
                    webView.goForward();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //刷新
            case R.id.btnRefresh:
                try {
                    webView.reload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    //地址HTTP协议判断，无HTTP打头的，增加http://，并返回。
    private String uriHttpFirst(String strUri){

        if(strUri.indexOf("http://",0) != 0 && strUri.indexOf("https://",0) != 0 ){
            strUri = "http://" + strUri;
        }

        return strUri;
    }


}
