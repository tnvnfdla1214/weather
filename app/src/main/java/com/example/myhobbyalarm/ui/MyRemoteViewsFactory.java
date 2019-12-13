package com.example.myhobbyalarm.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.myhobbyalarm.R;
import com.example.myhobbyalarm.data.DatabaseHelper;
import com.example.myhobbyalarm.model.Alarm;
import com.example.myhobbyalarm.util.AlarmUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.myhobbyalarm.ui.WidgetListviewProvider.EXTRA_ITEM_POSITION;

/**
 * 런처 앱에 리스트뷰의 어뎁터 역할을 해주는 클래스
 */
class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{


    //context 설정하기
    public Context context = null;
    public RemoteViews views;
    public ArrayList<Alarm> alarms= new ArrayList<Alarm>();
    List<Alarm> alarmsDataList;
    public MyRemoteViewsFactory(Context context) {
        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 MyRemoteViewsFactory");
        this.context = context;
    }

    public void setData() {
        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 setData");

        alarmsDataList = DatabaseHelper.getInstance(context).getAlarms();
        alarms.removeAll(alarms);
        for (Alarm alarm : alarmsDataList){
            alarms.add(alarm);
        }
    }
    //이 모든게 필수 오버라이드 메소드

    //실행 최초로 호출되는 함수
    @Override
    public void onCreate() {
        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 onCreate");
        setData();

        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 onCreate");
    }

    //항목 추가 및 제거 등 데이터 변경이 발생했을 때 호출되는 함수
    //브로드캐스트 리시버에서 notifyAppWidgetViewDataChanged()가 호출 될 때 자동 호출
    @Override
    public void onDataSetChanged() {
        setData();
        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 onDataSetChanged");
    }

    //마지막에 호출되는 함수
    @Override
    public void onDestroy() {

        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 onDestroy");
    }

    // 항목 개수를 반환하는 함수
    @Override
    public int getCount() {

        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 getCount");
        return alarms.size();
    }

    //각 항목을 구현하기 위해 호출, 매개변수 값을 참조하여 각 항목을 구성하기위한 로직이 담긴다.
    // 항목 선택 이벤트 발생 시 인텐트에 담겨야 할 항목 데이터를 추가해주어야 하는 함수
    @Override
    public RemoteViews getViewAt(int position) {
//        String label = null;
//        long time=0;
//        if (position<alarmsDataList.size()){
//            time =alarmsDataList.get(position).getTime();
//            label=alarmsDataList.get(position).getLabel();
//        }


        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 getViewAt"+position);
        RemoteViews listviewWidget = new RemoteViews(context.getPackageName(), R.layout.item_collection);
        Log.d("ㅁㅁ","리모트 뷰 arrayList 포지션 "+position+"의 time 값 : "+alarms.get(position).getLabel());
        Log.d("ㅁㅁ","리모트 뷰 arrayList 포지션 "+position+"의 time 값 : "+ AlarmUtils.getReadableTime(alarms.get(position).getTime()));
//        listviewWidget.setTextViewText(R.id.txToDo, arrayList.get(position).getToDo());
//        listviewWidget.setTextViewText(R.id.txDate, arrayList.get(position).getDate());
//        listviewWidget.setTextViewText(R.id.txToDo, alarms.get(position).getLabel());
//        listviewWidget.setTextViewText(R.id.txDate, alarms.get(position).getTime()+"");
        listviewWidget.setTextViewText(R.id.txToDo, alarms.get(position).getLabel());
        listviewWidget.setTextViewText(R.id.txDate, AlarmUtils.getReadableTime(alarms.get(position).getTime())+"");
        listviewWidget.setTextViewText(R.id.ar_am_pm, AlarmUtils.getAmPm(alarms.get(position).getTime())+"");
//        listviewWidget.setTextViewText(R.id.ar_days, AlarmsAdapter.buildSelectedDays(alarms.get(position)));



        Intent fillIntent = new Intent();
        fillIntent.putExtra(EXTRA_ITEM_POSITION,position);
        listviewWidget.setOnClickFillInIntent(R.id.lL,fillIntent);


//        클릭 이벤트로 프래그먼트열기
//
//        Intent configIntent = new Intent(context, MainActivity.class);
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_collection);
//        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 WIDGET_LIST_ITEM_EVENT_CHECK_STRING = "+WIDGET_LIST_ITEM_EVENT_CHECK_STRING);
//        Log.d("ㅁㅁ","MyRemoteViewsFactory 의 position = "+position);
//        configIntent.putExtra(WIDGET_LIST_ITEM_EVENT_CHECK_STRING, position);
//        configIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
//        listviewWidget.setOnClickPendingIntent(R.id.txToDo,configPendingIntent);
        return listviewWidget;
    }

    //로딩 뷰를 표현하기 위해 호출, 없으면 null
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    //항목의 타입 갯수를 판단하기 위해 호출, 모든 항목이 같은 뷰 타입이라면 1을 반환하면 된다.
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    //각 항목의 식별자 값을 얻기 위해 호출
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 같은 ID가 항상 같은 개체를 참조하면 true 반환하는 함수
    @Override
    public boolean hasStableIds() {
        return false;
    }


}
