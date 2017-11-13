package com.android.timesheet.user_operations.list.headerSerialize;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.activities.BaseActivity;
import com.android.timesheet.shared.models.HeaderParams;
import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class HeaderSerialize extends BaseActivity<HeaderSerializePresenter> implements
        BaseViewBehavior<TimeSheetResponse>, AdapterView.OnItemSelectedListener {

    private static final String TAG = "HeaderSerialize";
    ListView lv;

    List<TimeSheet> todayPojoList;
    String todayDate;

    @BindView(R.id.textViewToolbarTitle)
    CustomFontTextView textViewToolbarTitle;

   android.support.v7.widget.Toolbar toolbar;
    @Override
    protected int layoutRestID() {
        return R.layout.activity_header_serialize;
    }

    @Override
    protected String title() {
        return "Header Serialize";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected HeaderSerializePresenter providePresenter() {
        return new HeaderSerializePresenter(this, this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Parcelable parcelable = getIntent().getParcelableExtra(Constant.KEYS.TIME_SHEET_DETAIL_KEY);
        todayPojoList = Parcels.unwrap(parcelable);
        todayDate = String.valueOf(intent.getSerializableExtra(Constant.KEYS.TIME_SHEET_HEADER_KEY));

        textViewToolbarTitle.setText(todayDate);

        Log.i(TAG, "Header date : " + todayDate);

        User user = presenter().getCurrentUser();
        if (todayDate != null) {
            HeaderParams headerParams = new HeaderParams(user.empCode, todayDate);
            presenter().dayTimeSheet(headerParams);
        }

        lv = (ListView) findViewById(R.id.date_listView);
        if (todayPojoList != null) {
            DateInfoAdapter adapter = new DateInfoAdapter(getApplicationContext(), R.layout.activity_date_info, todayPojoList);
            lv.setAdapter(adapter);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_date_info, null, true);
        }

      /*todayDate=data.get(0).getDate();*/

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(TimeSheetResponse data) {

        if (data != null && data.status) {
            todayPojoList = data.getTimeSheetList();
            if (todayPojoList != null) {
                DateInfoAdapter adapter = new DateInfoAdapter(getApplicationContext(), R.layout.activity_date_info, todayPojoList);
                lv.setAdapter(adapter);
            }
        }

    }

    @Override
    public void onFailed(Throwable e) {

    }
}
