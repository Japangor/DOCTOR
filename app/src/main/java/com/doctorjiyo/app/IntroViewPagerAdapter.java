package com.doctorjiyo.app;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;


public class IntroViewPagerAdapter extends PagerAdapter {

   Context mContext ;
   List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        TextView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);
        RelativeLayout bgColor=layoutScreen.findViewById(R.id.screenBg);

        String text=mListScreen.get(position).getScreenImg();

        title.setText(mListScreen.get(position).getTitle());
        title.setTextColor(Color.parseColor("#ffffff"));

        description.setText(mListScreen.get(position).getDescription());
        description.setTextColor(Color.parseColor("#ffffff"));

        imgSlide.setText(Html.fromHtml("&#x"+text+";"));
        imgSlide.setTextColor(Color.parseColor("#ffffff"));

        bgColor.setBackgroundColor(Color.parseColor("#"+mListScreen.get(position).getBgcolor()));






        container.addView(layoutScreen);

        return layoutScreen;





    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }
}
