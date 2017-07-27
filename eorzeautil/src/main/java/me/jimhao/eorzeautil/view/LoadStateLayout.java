package me.jimhao.eorzeautil.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.jimhao.eorzeautil.R;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/27
 * 包名： me.jimhao.eorzeautil.view
 * 文档描述：加载状态【加载中 加载失败 数据页 空页面】
 * [使用方法：
 * 需要将LoadStateLayout附加到界面的xml文件中
 * 可以使用app: error_img、error_text、no_data_img、no_data_text 四个属性 设置相关属性
 * 在代码中使用view.setDisplayMode(mode:int); 来显示
 * ]
 */
public class LoadStateLayout extends LinearLayout implements View.OnClickListener {

    private LayoutInflater mInflater = null;

    public static final int NETWORK_LOADING = 1;   //加载中
    public static final int NO_DATA = 2;   //无数据
    public static final int NETWORK_ERROR = 3;  //网络错误
    public static final int HIDE_LAYOUT = 4;   //隐藏

    private int mErrorState = NETWORK_LOADING; //初始化加载状态
    private String strNoDataContent;
    private String strErrorContent;
    private int imgNoDataImage = -1;
    private int imgErrorImage = -1;

    private ImageView img;
    private ProgressBar animProgress;
    private TextView tv;

    public OnClickListener listener ;
    public LoadStateLayout(Context context) {
        this(context,null);
    }

    public LoadStateLayout(Context context, AttributeSet attrs ){
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init(context,attrs);
    }

    private void init(Context context , AttributeSet attrs) {
        View view = mInflater.inflate(R.layout.layout_load_state, null);
        img = (ImageView) view.findViewById(R.id.img_error_layout);
        animProgress = (ProgressBar) view.findViewById(R.id.animProgress);
        tv = (TextView) view.findViewById(R.id.tv_error_layout);

        //从Attributes取出数据
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.LoadState);
        imgNoDataImage = array.getResourceId(R.styleable.LoadState_no_data_img,R.drawable.drawable_loading_nodata);
        imgErrorImage = array.getResourceId(R.styleable.LoadState_error_img,R.drawable.drawable_loading_error);
        strNoDataContent = array.getString(R.styleable.LoadState_no_data_text);
        strErrorContent = array.getString(R.styleable.LoadState_error_text);
        array.recycle();

        //初始化数据设置
        if(getVisibility() == View.GONE){
            //有数据
            setDisplayMode(HIDE_LAYOUT);
        }else{
            setDisplayMode(NETWORK_LOADING);
        }
        setOnClickListener(this);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v);
                }
            }
        });
        addView(view);

        //默认不显示
        setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    //自定义点击监听（图片会拦截 EmptyVIew的点击事件，所以也需要对图片进行设置点击事件）
    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    //判断3种状态
    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }
    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }
    public boolean isLoadingNoData() {
        return mErrorState == NO_DATA;
    }

    //传入不同状态的图片 文字
    public void setErrorImag(int imgResource) {
        imgErrorImage = imgResource;
    }
    public void setNoDataImag(int imgResource) {
        imgNoDataImage = imgResource;
    }
    public void setErrorContent(String msg) {
        strErrorContent = msg;
    }
    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    //设置显示状态
    public void setDisplayMode(int type) {
        setVisibility(View.VISIBLE);
        mErrorState = type;
        switch (type) {
            case NETWORK_LOADING:
                animProgress.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
                tv.setText("正在加载...");
                setVisibility(View.VISIBLE);
                break;
            case NO_DATA:
                animProgress.setVisibility(View.GONE);
                img.setImageResource(imgNoDataImage);
                img.setVisibility(View.VISIBLE);
                tv.setText(strNoDataContent == null ? "没有找到相关信息呢\n点击屏幕，重新加载" : strNoDataContent);
                tv.setVisibility(View.VISIBLE);
                setVisibility(View.VISIBLE);
                break;
            case NETWORK_ERROR:
                animProgress.setVisibility(View.GONE);
                img.setImageResource(imgErrorImage);
                img.setVisibility(View.VISIBLE);
                tv.setText(strErrorContent == null ? "点击屏幕，重新加载" : strErrorContent);
                tv.setVisibility(View.VISIBLE);
                setVisibility(View.VISIBLE);
                break;
            case HIDE_LAYOUT:
                setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE) {
            mErrorState = HIDE_LAYOUT;
        }
        super.setVisibility(visibility);
    }
}
