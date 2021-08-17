package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmarket.R;

import java.util.List;

import adapter.MyAdapter;
import bean.App;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppFragment extends Fragment {

    private static final String APP_TYPE = "APP_TYPE";
    private int appType;
    private RecyclerView rv;


    public AppFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppFragment newInstance(int appType) {
        AppFragment fragment = new AppFragment();
        Bundle args = new Bundle();
        args.putInt(APP_TYPE, appType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appType = getArguments().getInt(APP_TYPE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BmobQuery<App> appBmobQuery = new BmobQuery<>();
        appBmobQuery.addWhereEqualTo("appType", appType);
        appBmobQuery.findObjects(new FindListener<App>() {
            @Override
            public void done(List<App> list, BmobException e) {
                if (e == null){
                    MyAdapter myAdapter = new MyAdapter(list, getContext());
                    rv.setAdapter(myAdapter);
                }
            }
        });
    }
}