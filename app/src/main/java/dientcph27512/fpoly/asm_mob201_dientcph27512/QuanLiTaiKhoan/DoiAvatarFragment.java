package dientcph27512.fpoly.asm_mob201_dientcph27512.QuanLiTaiKhoan;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DoiAvatarService;
import dientcph27512.fpoly.asm_mob201_dientcph27512.Services.DoiMatService;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.MainActivity;

public class DoiAvatarFragment extends Fragment {
    private ImageView imgAvt;
    private TextView tenAcount;
    private Button chonAvatar;
    private MainActivity mainActivity;
    private DoiAvatarService doiAvatarService;
    private int img = 0;
    private String currentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DoiAvatarService.DoiAvatarBinder binder = (DoiAvatarService.DoiAvatarBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dang_nhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Đổi Avatar");
        imgAvt = (ImageView) view.findViewById(R.id.imgAvt);
        tenAcount = (TextView) view.findViewById(R.id.ten_acount);
        chonAvatar = (Button) view.findViewById(R.id.chon_avatar);
        mainActivity = (MainActivity) getActivity();
        String user = mainActivity.getTenAcount();
        tenAcount.setText(user);
        doiAvatarService = new DoiAvatarService();
        Intent intent = new Intent(getActivity(), DoiAvatarService.class);
        getActivity().bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        setPicBD();
        chonAvatar.setOnClickListener(v -> {
            openCamera();
        });
    }

    public void openCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "dientcph27512.fpoly.asm_mob201_dientcph27512.QuanLiTaiKhoan.fileprovider",
                        photoFile);


                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        Log.d("zzzzz", "createImageFile: " + currentPhotoPath);
        return image;
    }

    private void setPic() {
        int targetW = imgAvt.getWidth();
        int targetH = imgAvt.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imgAvt.setImageBitmap(bitmap);
    }
    private void setPicBD() {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mainActivity.getAvt(), bmOptions);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mainActivity.getAvt(), bmOptions);
        imgAvt.setImageBitmap(bitmap);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
            doiAvatarService = new DoiAvatarService();
            doiAvatarService.doiAvatar(mainActivity.getTenAcount(),currentPhotoPath,getActivity());
            mainActivity.setPic(currentPhotoPath);
            Toast.makeText(getActivity(), "Đổi Thành Công", Toast.LENGTH_SHORT).show();
        }
    }
}