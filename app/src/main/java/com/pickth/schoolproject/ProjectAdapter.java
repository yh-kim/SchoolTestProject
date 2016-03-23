package com.pickth.schoolproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kim on 2016-03-16.
 */
public class ProjectAdapter  extends ArrayAdapter<ProjectListItem> {
    //LayoutInflater -> XML을 동적으로 만들 때 필요
    private LayoutInflater inflater = null;
    //Context -> Activity Class의 객체
    private Context context = null;


    public ProjectAdapter(Context context, int resource, ArrayList<ProjectListItem> objects) {
        super(context, resource, objects);

        //context는 함수를 호출한 activiy
        //resource는 row_xxx.xml 의 정보
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    //ArrayList에 저장되어있는 데이터를 fragment에 넣는 method
    //List 하나마다 getView가 한번 실행된다
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position -> List번호
        ViewHolder holder;

        //XML 파일이 비어있는 상태라면
        if (convertView == null) {
            //layout 설정
            convertView = inflater.inflate(R.layout.row_main, null);

            holder = new ViewHolder();

            //row에 있는 정보들을 holder로 가져옴
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvExplanation = (TextView) convertView.findViewById(R.id.tvExplanation);

            convertView.setTag(holder);
        }


        holder = (ViewHolder) convertView.getTag();

        ProjectListItem item = getItem(position);

        holder.tvName.setText(item.getName());
        holder.tvExplanation.setText(item.getExplanation());


        char[] test = item.getDate().toCharArray();
        if((int)test[0] == 48){
            // month > 9
            holder.tvDate.setText(test[1] + "/" + test[2]+test[3]);
        }else{
            holder.tvDate.setText(test[0] + "" + test[1] + "/" + test[2] + "" + test[3]);
        }

        return convertView;
    }

    class ViewHolder {
        TextView tvName, tvDate, tvExplanation;
    }

}