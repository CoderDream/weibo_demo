package com.coderdream.weibo.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.coderdream.weibo.R;

public class AuthActivity extends Activity {

	private Dialog dialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.auth);

		View dialogView = View.inflate(this, R.layout.authorize_dialog, null);

		dialog = new Dialog(this, R.style.auth_dialog);
		dialog.setContentView(dialogView);
		dialog.show();

		// 获得开始按钮
		Button btnBegin = (Button) dialogView.findViewById(R.id.btn_auth_begin);
		// 设置点击事件
		btnBegin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AuthActivity.this,
						WebViewActivity.class);
				startActivity(intent);
			}
		});

	}
}
