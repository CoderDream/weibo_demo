package com.coderdream.weibo.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coderdream.weibo.R;

/**
 * 
 */
public class WebViewActivity extends Activity {

	private WebView webView;

	private ProgressDialog progressDialog;
	private static final int CLOSE_DLG = 1;
	private String url = "http://www.sina.com";

	/**
	 * 进度条的Handler
	 */
	private Handler handler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.webview);

		init();
		
		load(url, webView);
		
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				// 如果传入的值为1，则关闭进度条
				if (CLOSE_DLG == msg.what) {
					progressDialog.dismiss();
				}
			};
		};

		
		//
		// Task task = new Task(Task.GET_AUTH_URL, null);
		//
		// MainService.newTask(task);
		// MainService.addActivity(this);
		//
		// handler = new Handler() {
		//
		// public void handleMessage(android.os.Message msg) {
		// if (msg.what == CLOSE_DLG) {
		// progressDialog.dismiss();
		// }
		//
		// };
		// };

	}

	@SuppressLint("SetJavaScriptEnabled")
	public void init() {

		// 如果进度条不存在，则创建一个进度条
		if (null == progressDialog) {
			progressDialog = new ProgressDialog(this);
		}
		// 设置进度条标题
		progressDialog.setMessage(getString(R.string.loading_auth_url));
		// 显示进度条
		progressDialog.show();

		// 得到 WebView 控件
		webView = (WebView) this.findViewById(R.id.wv_oauth);
		webView.getSettings().setJavaScriptEnabled(true);

		// webView.addJavascriptInterface(new JavascriptInterface(), "Methods");
		//
		webView.setWebViewClient(new WebViewClient() {

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				load(url, webView);
				return super.shouldOverrideUrlLoading(view, url);
				// return true;
			}

			public void onPageFinished(WebView view, String url) {
				// if (url.equals("http://api.t.sina.com.cn/oauth/authorize")) {
				// view.loadUrl("javascript:window.Methods.getPin('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
				//
				// Intent intent = new Intent(WebViewActivity.this,
				// AccessTokenActivity.class);
				// startActivity(intent);
				// }
				//
				// super.onPageFinished(view, url);
			}

		});

		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int newProgress) {
				// 如果进度条为100，则调用 Hander 里面的方法，关闭进度条
				if (100 == newProgress) {
					handler.sendEmptyMessage(CLOSE_DLG);
				}
				super.onProgressChanged(view, newProgress);
			}

		});

	}

	public void load(final String url, final WebView view) {

		if (null == url || "".equals(url)) {
			return;
		}

		new Thread() {
			public void run() {
				view.loadUrl(url);
			}
		}.start();

	}
	//
	// @Override
	// public void refresh(Object... params) {
	// progressDialog.setMessage(getString(R.string.loading));
	// url = (String) params[0];
	// Log.i("WebViewActivity", "url" + url);
	// load(url, webView);
	// }

}
