package com.oreooo.baselibrary.TipDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.oreooo.baselibrary.R;

/**
 * 提示对话框,用于退出时弹窗提示
 */
public class TipDialog extends Dialog {
    private static final String TAG = "TipDialog";
    private LayoutInflater inflater;//布局文件
    private Context context;//上下文
    private String tipTitle;//对话框标题
    private String tipText;//对话框显示提示文本
    private TextView title, tip, cancel, confirm;//标题、提示内容、取消、确定按钮

    /**
     * 点击回调监听
     */
    private TipConfirmListener tipConfirm;

    /**
     * 外部是否设置了点击回调监听事件
     *
     * @return
     */
    private boolean hasConfirm() {
        return tipConfirm != null;
    }

    /**
     * 获取本类中的点击回调监听事件
     *
     * @return
     */
    public TipConfirmListener getTipConfirm() {
        return tipConfirm;
    }

    /**
     * 设置点击回调监听事件
     *
     * @param tipConfirm
     */
    public void SetTipConfirmListener(TipConfirmListener tipConfirm) {
        this.tipConfirm = tipConfirm;
    }

    /**
     * 带参构造函数
     *
     * @param context  上下文
     * @param tipTitle 提示对话框的标题
     * @param tipText  提示对话框显示内容
     */
    public TipDialog(Context context, String tipTitle, String tipText) {
        super(context);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.tipTitle = tipTitle;
        this.tipText = tipText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局文件
        setContentView(R.layout.dialog_tip);
        //点击外部Dialog消失
        setCanceledOnTouchOutside(true);
        //初始化布局界面
        initView();
        Log.d(TAG, "Creat TipDialog");
    }

    @Override
    public void show() {
        super.show();
        try {
            /**
             * 设置对话框窗口大小
             */
            Window window = this.getWindow();//获取本对话框窗口
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();//获取屏幕分辨率信息
            WindowManager.LayoutParams lp = window.getAttributes();
            window.setBackgroundDrawableResource(android.R.color.transparent);//背景设置为透明
            manager.getDefaultDisplay().getMetrics(dm);
            WindowManager windowManager = ((Activity) context).getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.gravity = Gravity.CENTER;//Dialog窗口位置
            lp.width = display.getWidth() / 4 * 3;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //lp.height = display.getHeight()/4;
            window.setAttributes(lp);
            Log.d(TAG, "Show TipDialog");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化控件、设置按钮点击监听、触发回调监听
     */
    private void initView() {
        title = findViewById(R.id.tip_title);
        tip = findViewById(R.id.tip_text);
        cancel = findViewById(R.id.tip_cancel);
        confirm = findViewById(R.id.tip_confirm);

        title.setText(tipTitle);//设置对话框标题
        tip.setText(tipText);//设置对话框显示内容

        /**
         * 设置取消按钮监听
         */
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();//关闭对话框
            }
        });

        /**
         * 设置确定按钮监听
         */
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();//关闭对话框
                if (hasConfirm()) {//外部设置了点击回调监听
                    boolean isConfirm = true;
                    tipConfirm.Confirm(isConfirm);//确定按钮被按下，返回true，相应处理由接收方负责
                }
            }
        });
    }
}
