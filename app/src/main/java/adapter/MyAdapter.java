package adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmarket.R;

import java.util.List;

import bean.App;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<App> apps;
    private Context context;

    public MyAdapter(List<App> apps, Context context){
        this.apps = apps;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        App app = apps.get(position);
        String appimg = app.getAppimg();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int picId = context.getResources().getIdentifier(appimg, "drawable", applicationInfo.packageName);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), picId);
        holder.ivApp.setImageBitmap(bitmap);
        holder.tvName.setText(app.getAppName());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView ivApp;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivApp = itemView.findViewById(R.id.iv_app);
            tvName = itemView.findViewById(R.id.tv_app_name);
        }
    }

}
