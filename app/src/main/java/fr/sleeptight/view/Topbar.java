package fr.sleeptight.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.sleeptight.R;

/**
 * Created by Zhengrui on 2016/5/13.
 */
public class Topbar extends RelativeLayout {

    private String titleText;
    private int titleTextColor;
    private float titleTextSize;

    private TextView title;
    private ImageButton menu;

    public Topbar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //layout
        LayoutInflater.from(context).inflate(R.layout.topbar, this, true);
        //attribu
        TypedArray attributs=context.obtainStyledAttributes(attrs, R.styleable.topbar);
        titleText=attributs.getString(R.styleable.topbar_topBarTitleText);
        titleTextColor=attributs.getColor(R.styleable.topbar_topBarTitleTextColor, Color.BLACK);
        titleTextSize=attributs.getDimension(R.styleable.topbar_topBarTitleTextSize, 20f);
        //recycle
        attributs.recycle();
    }

    //http://www.cnblogs.com/ivan-xu/p/4545929.html
    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //获取子控件
        title= (TextView) findViewById(R.id.tv_topbar);
        menu= (ImageButton) findViewById(R.id.ib_topbar);

        //将从资源文件中加载的属性设置给子控件
        if (!TextUtils.isEmpty(titleText))
            setPageTitleText(titleText);
        setPageTitleTextColor(titleTextColor);
        setPageTitleTextSize(titleTextSize);

    }

    /**
     * 设置标题文字
     * @param text
     */
    public void setPageTitleText(String text) {
        title.setText(text);
    }

    /**
     * 设置标题文字颜色
     * @param color
     */
    public void setPageTitleTextColor(int color) {
        title.setTextColor(color);
    }

    /**
     * 设置标题文字大小
     * @param size
     */
    public void setPageTitleTextSize(float size) {
        title.setTextSize(size);
    }


}
