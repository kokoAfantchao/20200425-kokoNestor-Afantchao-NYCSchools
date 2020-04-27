package com.push.nycschools.ui.main.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.push.nycschools.R;
import com.push.nycschools.model.School;

import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class );
        // TODO: Use the ViewModel

    }


    private final  class MainLDObsever implements Observer<List<School>>{

        @Override
        public void onChanged(List<School> schools) {

        }
    }


    private  static   class SchoolsAdapter  extends RecyclerView.Adapter<SchoolsAdapter.SchoolViewItem>{
        @NonNull
        @Override
        public SchoolViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull SchoolViewItem holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        private  class SchoolViewItem extends  RecyclerView.ViewHolder  {
            public SchoolViewItem(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


}
