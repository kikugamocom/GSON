package th.vu.cs.nectec_gson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rasta on 17/9/2560.
 */

public class CustomerAdapter extends BaseAdapter {

    private ViewHolder mViewHolder;

    private LayoutInflater mInflater;
    List<Post>mpost;
    private Post mPost;


    public CustomerAdapter(Activity activity,List<Post> posts){
            mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mpost = posts;



    }





    @Override
    public int getCount() {
        return mpost.size();
    }

    @Override
    public Object getItem(int position) {
        return mpost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){

            convertView = mInflater.inflate(R.layout.post,parent,false);
            mViewHolder = new ViewHolder();

           mViewHolder.description = (TextView)convertView.findViewById(R.id.textView2) ;
            mViewHolder.main = (TextView)convertView.findViewById(R.id.textView3) ;
           mViewHolder.id = (TextView)convertView.findViewById(R.id.textView5) ;
            mViewHolder.icon = (TextView)convertView.findViewById(R.id.textView7);


            convertView.setTag(mViewHolder);



        }else {
            mViewHolder = (ViewHolder)convertView.getTag();
        }
        mPost = mpost.get(position);
        mViewHolder.description.setText(mPost.description);
        mViewHolder.main.setText(mPost.main);
        mViewHolder.id.setText(mPost.id);
        mViewHolder.icon.setText(mPost.icon);




        return convertView;
    }

    private static class ViewHolder{


        TextView description;
        TextView main;
        TextView id;
        TextView icon;




    }
}
