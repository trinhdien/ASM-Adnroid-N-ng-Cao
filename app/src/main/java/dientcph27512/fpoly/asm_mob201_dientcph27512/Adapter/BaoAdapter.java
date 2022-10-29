package dientcph27512.fpoly.asm_mob201_dientcph27512.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.BaiBao;
import dientcph27512.fpoly.asm_mob201_dientcph27512.R;
import dientcph27512.fpoly.asm_mob201_dientcph27512.UI.BaiBaoActivity;

public class BaoAdapter extends RecyclerView.Adapter<BaoAdapter.DocBaoViewHoler> {
    private List<BaiBao> list;
    private Context context;

    public void setList(List<BaiBao> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public BaoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DocBaoViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bao, parent, false);
        return new DocBaoViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocBaoViewHoler holder, int position) {
        BaiBao baiBao = list.get(position);
        if(baiBao == null){
            return;
        }
        holder.title.setText(baiBao.getName());
        holder.item.setOnClickListener(v -> {
            Intent intent = new Intent(context, BaiBaoActivity.class);
            intent.putExtra("link",baiBao.getLink());
            intent.putExtra("tieude",baiBao.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class DocBaoViewHoler extends RecyclerView.ViewHolder {
        private CardView item;
        private TextView title;
        public DocBaoViewHoler(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            item = (CardView) itemView.findViewById(R.id.item);

        }
    }
}
