package dientcph27512.fpoly.asm_mob201_dientcph27512.QuanLiTaiKhoan;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.User;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Database.DatabaseUser;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DoiMatService;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class DoiMatKhauFragment extends Fragment {private EditText passCu;
    private EditText passMoi1;
    private ImageView showPassMoi2;
    private EditText passMoi2;
    private ImageView showPass2;
    private Button dangKi;
    private MainActivity mainActivity;
    private List<User> list;
    private TextView ten;
    private DoiMatService doiMatService;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DoiMatService.DoiMatKhauBinder binder = (DoiMatService.DoiMatKhauBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Đổi Mật Khẩu");
        passCu = (EditText) view.findViewById(R.id.passCu);
        passMoi1 = (EditText) view.findViewById(R.id.passMoi1);
        showPassMoi2 = (ImageView) view.findViewById(R.id.showPassMoi2);
        passMoi2 = (EditText) view.findViewById(R.id.passMoi2);
        showPass2 = (ImageView) view.findViewById(R.id.showPass2);
        dangKi = (Button) view.findViewById(R.id.doimatkhau);
        mainActivity = (MainActivity) getActivity();
        ten = view.findViewById(R.id.tenAcount);
        String user = mainActivity.getTenAcount();
        ten.setText(user);
        doiMatService = new DoiMatService();
        Intent intent = new Intent(getActivity(),DoiMatService.class);
        getActivity().bindService(intent,connection, Context.BIND_AUTO_CREATE);
        showPass();
        dangKi.setOnClickListener(v -> {
//            String pass = passCu.getText().toString();
            if(!"".equals(passCu.getText().toString()) && passMoi1.getText().toString().equals(passMoi2.getText().toString())){
                boolean check = doiMatService.doiMatKhau(user,passCu.getText().toString(),passMoi1.getText().toString(),getActivity());
                if(check == true){
                    Toast.makeText(getActivity(), "Đã Đổi Mật Khẩu", Toast.LENGTH_SHORT).show();
                    passCu.setText("");
                    passMoi1.setText("");
                    passMoi2.setText("");
                }else{
                    Toast.makeText(getActivity(), "Đổi Không Thành Công", Toast.LENGTH_SHORT).show();
                    passCu.setText("");
                    passMoi1.setText("");
                    passMoi2.setText("");
                }
            }else {
                Toast.makeText(getActivity(), "Đổi Không Thành Công", Toast.LENGTH_SHORT).show();
                passCu.setText("");
                passMoi1.setText("");
                passMoi2.setText("");
            }
//            list = DatabaseUser.getInstance(getActivity()).userDAO().checkLogin(user,pass);
//            if(list.size() == 0){
//                Toast.makeText(getActivity(), "Đổi Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
//            }else{
//                if(passMoi1.getText().toString().equals(passMoi2.getText().toString())){
//                    User user1 = list.get(0);
//                    user1.setPassWord(passMoi1.getText().toString());
//                    DatabaseUser.getInstance(getActivity()).userDAO().update(user1);
//                    Toast.makeText(getActivity(), "Đã Đổi Mật Khẩu", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getActivity(), "Đổi Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
//                }
//            }
        });
    }
    public void showPass() {
        showPassMoi2.setOnClickListener(v -> {
            if (passMoi1.getInputType() == 129) {
                passMoi1.setInputType(1);
            } else {
                passMoi1.setInputType(129);
            }
        });
        showPass2.setOnClickListener(v -> {
            if (passMoi2.getInputType() == 129) {
                passMoi2.setInputType(1);
            } else {
                passMoi2.setInputType(129);
            }
        });
    }
}