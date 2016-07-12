package com.jjke.baidudelivery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 6/13/2016.
 */
public class MeFragment extends Fragment {
    private LayoutInflater layoutInflater;
    private LinearLayout layout;
    private String[] texts = new String[]{"我的送餐地址", "我的代金券", "我的退款", "我的消息", "我的收藏",
            "我的评论", "百度钱包", "百度糯米", "常见问题"};
    private int[] images = new int[]{R.drawable.me_image_1, R.drawable.me_image_2, R.drawable.me_image_3,
            R.drawable.me_image_4, R.drawable.me_image_5, R.drawable.me_image_6, R.drawable.me_image_7,
            R.drawable.me_image_8, R.drawable.me_image_9};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);

        layoutInflater = LayoutInflater.from(getActivity());
        //使用ScrollView排布界面
        layout = (LinearLayout) rootView.findViewById(R.id.id_linearLayout);

        initView();//设置ScrollView

        return rootView;
    }
    public void initView() {

        for (int i = 0; i < images.length; i++) {

            View view = layoutInflater.inflate(R.layout.list_cell_me, layout, false);
            ImageView ivMe1 = (ImageView) view.findViewById(R.id.ivMe1);
            ivMe1.setImageResource(images[i]);
            TextView tvMe = (TextView) view.findViewById(R.id.tvMe);
            tvMe.setText(texts[i]);
            ImageView ivMe2 = (ImageView) view.findViewById(R.id.ivMe2);
            ivMe2.setImageResource(R.drawable.wallet_base_right_arrow);
            layout.addView(view);

            //添加横隔条（间距）
            if (i == 2 || i == 5 || i == 7 || i == 8) {
                View view2 = layoutInflater.inflate(R.layout.list_cell_me_2, layout, false);
                layout.addView(view2);
            }
        }

        View view3 = layoutInflater.inflate(R.layout.list_cell_me_3, layout, false);
        layout.addView(view3);
    }
}
