package com.example.googlemap.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.googlemap.DisplayInformationActivity;
import com.example.googlemap.R;
import com.example.googlemap.adapter.ListViewAdapter;
import com.example.googlemap.model.School;

import java.util.ArrayList;

public class LocationFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<School> schoolArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        schoolArrayList = (ArrayList<School>) getArguments().getSerializable("arrayList");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        listView = view.findViewById(R.id.lv_locations);

        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), R.layout.list_view_items, schoolArrayList);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        School school = (School) listView.getItemAtPosition(i);
        Intent intent = new Intent(getActivity(),DisplayInformationActivity.class);
        intent.putExtra("item", (Parcelable) school);
        startActivity(intent);
    }
}
