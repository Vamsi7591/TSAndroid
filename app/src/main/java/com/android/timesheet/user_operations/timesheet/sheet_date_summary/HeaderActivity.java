package com.android.timesheet.user_operations.timesheet.sheet_date_summary;

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
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CustomFontTextView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class HeaderActivity extends BaseActivity<HeaderPresenter> implements
        BaseViewBehavior<TimeSheetResponse>, AdapterView.OnItemSelectedListener {

    private static final String TAG = "HeaderActivity";
    ListView lv;

    List<TimeSheet> todayPojoList;
    String todayDate;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView toolbarTitleTv;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_timesheet_header;
    }

    @Override
    protected String title() {
        return "Day Summary";
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected HeaderPresenter providePresenter() {
        return new HeaderPresenter(this, this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Parcelable parcelable = getIntent().getParcelableExtra(Constant.KEYS.TIME_SHEET_DETAIL_KEY);
        todayPojoList = Parcels.unwrap(parcelable);
        todayDate = String.valueOf(intent.getSerializableExtra(Constant.KEYS.TIME_SHEET_HEADER_KEY));

        toolbarTitleTv.setText(title());
        toolbarTitleTv.setTypeface(FontUtils.getTypeFace(this, getString(R.string.roboto_thin)));

        Log.i(TAG, "Header date : " + todayDate);

        User user = presenter().getCurrentUser();
        if (todayDate != null) {
            HeaderParams headerParams = new HeaderParams(user.empCode, todayDate);
            presenter().dayTimeSheet(headerParams);
        }

        lv = (ListView) findViewById(R.id.date_listView);
        if (todayPojoList != null) {
            HeaderDateAdapter adapter = new HeaderDateAdapter(getApplicationContext(), R.layout.activity_timesheet_header_inflator, todayPojoList);
            lv.setAdapter(adapter);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_timesheet_header_inflator, null, true);
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
            todayPojoList = new ArrayList<>();
            todayPojoList = data.getTimeSheetList();
            if (todayPojoList != null) {
                HeaderDateAdapter adapter = new HeaderDateAdapter(this, R.layout.activity_timesheet_header_inflator, todayPojoList);
                lv.setAdapter(adapter);
            }
        }

    }

    @Override
    public void onFailed(Throwable e) {

    }
}
