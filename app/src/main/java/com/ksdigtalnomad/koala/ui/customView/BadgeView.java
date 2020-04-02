package com.ksdigtalnomad.koala.ui.customView;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ksdigtalnomad.koala.BuildConfig;
import com.ksdigtalnomad.koala.R;
import com.ksdigtalnomad.koala.helpers.data.PreferenceGenericHelper;

public class BadgeView extends ViewPager {
    public enum Key { // Notice: version set must be 0.x.x (see BuildConfig.VERSION_NAME)\
        none(BuildConfig.BADGE_VERSION),

        v036_settingTapName(BuildConfig.BADGE_VERSION),
        v036_settingTapCalendarDesign(BuildConfig.BADGE_VERSION);

        private String version;

        Key(String version){
            this.version = version;
        }

        public static Key findByName(String name){
            Key[] keys = values();
            int length = keys.length;

            for(int i = 0; i < length; ++i){
                if(keys[i].name() == name) return keys[i];
            }

            return none;
        }
    }

    private Key key;

    public BadgeView(Context context) {
        super(context);
        setBackgroundResource(R.drawable.shape_badge);
    }
    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }





    @BindingAdapter("badgeName")
    public static void setKey (BadgeView view, Key key) {
        view.key = key;
        Log.d("ABC", "" + (Boolean) PreferenceGenericHelper.getInstance().getValue(key.name(), false));
        if(key == null || !key.version.equals(BuildConfig.VERSION_NAME) ||
                (Boolean) PreferenceGenericHelper.getInstance().getValue(key.name(), false)){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }
    public void setKey (Key key) {
        BadgeView.setKey(this, key);
    }


    public void onClick(){
        Log.d("ABC", "key: " + key.name());
        PreferenceGenericHelper.getInstance().setValue(key.name(), true);
        setVisibility(View.GONE);
    }
}
