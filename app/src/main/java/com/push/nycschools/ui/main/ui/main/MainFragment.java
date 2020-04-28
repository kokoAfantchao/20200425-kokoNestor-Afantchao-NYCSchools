package com.push.nycschools.ui.main.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.push.nycschools.R;
import com.push.nycschools.model.School;

import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private SchoolsAdapter schoolsAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.schools_recycleView);
        schoolsAdapter = new SchoolsAdapter();
        recyclerView.setAdapter(schoolsAdapter);
        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class );
        // TODO: Use the ViewModel
        mViewModel.getAllSchool().observe( getViewLifecycleOwner(),  new MainLDObsever());
    }


    private final  class MainLDObsever implements Observer<List<School>>{

        @Override
        public void onChanged(List<School> schools) {
            schoolsAdapter.swapData(schools);

        }
    }


    private  static   class SchoolsAdapter  extends RecyclerView.Adapter<SchoolsAdapter.SchoolViewItem>{

        private List<School> schools;

        public  SchoolsAdapter (){
        }

        public  SchoolsAdapter ( List<School> schools){
            this.schools  = schools;
        }
        @NonNull
        @Override
        public SchoolViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);
            SchoolViewItem viewItem = new SchoolViewItem(rootView);

            return viewItem;
        }

        @Override
        public void onBindViewHolder(@NonNull SchoolViewItem holder, int position) {
            School school = schools.get(position);
            holder.name.setText(school.getSchool_name());
            holder.address.setText( school.getLocation());
        }



        public  void swapData(List<School> schoolsList ){
            if( schools != null) {
                this.schools = schoolsList ;
                notifyDataSetChanged();
            }
        }
        @Override
        public int getItemCount() {
            if (schools == null || schools.isEmpty()) return 0;
            return schools.size();
        }

        private  class SchoolViewItem extends  RecyclerView.ViewHolder  {
            TextView name;
            TextView address;
            public SchoolViewItem(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.school_name_tv);
                address = itemView.findViewById(R.id.school_address_tv);
            }
        }
    }


}
