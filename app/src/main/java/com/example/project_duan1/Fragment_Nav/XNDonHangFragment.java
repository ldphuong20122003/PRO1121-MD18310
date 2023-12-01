package com.example.project_duan1.Fragment_Nav;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_duan1.Adapter.DonHangAdapter;
import com.example.project_duan1.DTO.Bill;
import com.example.project_duan1.DTO.GioHang;
import com.example.project_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link XNDonHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class XNDonHangFragment extends Fragment {
    RecyclerView rec_xndh;
    List<Bill> billList;
    DonHangAdapter adapter;



    public XNDonHangFragment() {
        // Required empty public constructor
    }


    public static XNDonHangFragment newInstance() {
        XNDonHangFragment fragment = new XNDonHangFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_x_n_don_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rec_xndh=view.findViewById(R.id.rec_xndh);
        billList= new ArrayList<>();
        adapter= new DonHangAdapter(getContext(),billList);
        rec_xndh.setAdapter(adapter);
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                billList.clear(); // Xóa danh sách cũ trước khi thêm dữ liệu mới

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Bill gioHangItem = snapshot.getValue(Bill.class);
                    billList.add(gioHangItem);
                }

                adapter.notifyDataSetChanged(); // Cập nhật RecyclerView khi có thay đổi
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }
}