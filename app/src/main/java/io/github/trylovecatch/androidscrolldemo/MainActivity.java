package io.github.trylovecatch.androidscrolldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mLstView;
    private MyAdapter mAdp;

    private String[] mDatas = {"layout()", "offsetLeftAndRight()&offsetTopAndBottom()",
            "LayoutParams", "scrollTo&scrollBy", "Scroller", "属性动画", "ViewDragHelper", "translationX&translationY"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mLstView = (RecyclerView)findViewById(R.id.main_lst);
        LinearLayoutManager tLinearLayoutManager = new LinearLayoutManager(this);
        tLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLstView.setLayoutManager(tLinearLayoutManager);
        mAdp = new MyAdapter();
        mLstView.setAdapter(mAdp);
    }

    private class MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder();
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyViewHolder tHolder = (MyViewHolder)holder;
            tHolder.bindData(mDatas[position]);
            tHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Class tClass = null;
                    switch (position){
                        case 0:
                            tClass = Activity01.class;
                            break;
                        case 1:
                            tClass = Activity02.class;
                            break;
                        case 2:
                            tClass = Activity03.class;
                            break;
                        case 3:
                            tClass = Activity04.class;
                            break;
                        case 4:
                            tClass = Activity05.class;
                            break;
                        case 5:
                            break;
                        case 6:
                            tClass = Activity07.class;
                            break;
                        case 7:
                            tClass = Activity08.class;
                            break;
                        default:
                            return;
                    }
                    if(tClass!=null) {
                        Intent tIntent = new Intent(MainActivity.this, tClass);
                        startActivity(tIntent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.length;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxt;

        public MyViewHolder() {
            super(LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null));
            initView();
        }

        public void bindData(String pTxt){
            mTxt.setText(pTxt);
        }


        private void initView(){
            mTxt = (TextView) itemView.findViewById(R.id.item_txt);
        }
    }
}
