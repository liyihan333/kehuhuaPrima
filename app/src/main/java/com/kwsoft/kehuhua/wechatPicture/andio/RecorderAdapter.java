package com.kwsoft.kehuhua.wechatPicture.andio;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwsoft.kehuhua.adcustom.R;

import java.util.List;

public class RecorderAdapter extends ArrayAdapter<Recorder> {


    private LayoutInflater inflater;

    private int mMinItemWith;// 设置对话框的最大宽度和最小宽度
    private int mMaxItemWith;

    public RecorderAdapter(Context context, List<Recorder> dataList) {
        super(context, -1, dataList);
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);

        // 获取系统宽度
        WindowManager wManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wManager.getDefaultDisplay().getMetrics(outMetrics);
        mMaxItemWith = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWith = (int) (outMetrics.widthPixels * 0.15f);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.audio_item_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.seconds = (TextView) convertView.findViewById(R.id.recorder_time);
            viewHolder.length = convertView.findViewById(R.id.recorder_length);
            viewHolder.item_icon_right = (ImageView) convertView.findViewById(R.id.item_icon_right);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.item_icon_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SelectPictureActivity.hideListView();
//            }
//        });

        viewHolder.seconds.setText(Math.round(getItem(position).time) + "\"");
        ViewGroup.LayoutParams lParams = viewHolder.length.getLayoutParams();
        lParams.width = (int) (mMinItemWith + mMaxItemWith / 60f * getItem(position).time)*3;
        viewHolder.length.setLayoutParams(lParams);

        return convertView;
    }

    class ViewHolder {
        TextView seconds;// 时间
        View length;// 对话框长度
        ImageView item_icon_right;//删除按钮
    }

}
