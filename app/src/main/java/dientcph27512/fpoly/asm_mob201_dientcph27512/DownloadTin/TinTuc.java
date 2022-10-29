package dientcph27512.fpoly.asm_mob201_dientcph27512.DownloadTin;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter.BaoAdapter;
import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.BaiBao;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.TinTucLoader.TinTucLoader;

public class TinTuc extends AsyncTask<String,Void, List<BaiBao>> {
    Activity activity = null;

    public TinTuc(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected List<BaiBao> doInBackground(String... strings) {
        TinTucLoader tinTucLoader = new TinTucLoader();
        List<BaiBao> list = new ArrayList<>();
        String urlRSS = strings[0];
        try {
            URL url = new URL(urlRSS);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == 200){
                InputStream inputStream = httpURLConnection.getInputStream();
                list = tinTucLoader.getList(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<BaiBao> baiBaos) {
        super.onPostExecute(baiBaos);
        BaoAdapter baoAdapter = new BaoAdapter(activity);
        baoAdapter.setList(baiBaos);
        RecyclerView recyclerView2 = (RecyclerView) activity.findViewById(R.id.recyclerView2);
        LinearLayoutManager manager = new LinearLayoutManager(activity,RecyclerView.VERTICAL,false);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setAdapter(baoAdapter);
    }
}
