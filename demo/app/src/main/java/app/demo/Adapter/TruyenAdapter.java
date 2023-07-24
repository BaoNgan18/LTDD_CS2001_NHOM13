package app.demo.Adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.crypto.ShortBufferException;

import app.demo.R;
import app.demo.model.TheLoai;
import app.demo.model.Truyen;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.TruyenViewHolder> {
    public TruyenAdapter(List<Truyen> truyenList) {
        this.truyenList = truyenList;
    }

    List<Truyen> truyenList;
    @NonNull
    @Override
    public TruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_truyen, parent, false);
        return new TruyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenViewHolder holder, int position) {
        holder.idTruyen.setText(truyenList.get(position).getId()+"");
        holder.tenTruyen.setText(truyenList.get(position).getTenTruyen());
        holder.tenTacGia.setText(truyenList.get(position).getNguoiDung().getUserName());

        StringBuilder sb = new StringBuilder();
        truyenList.get(position).getDsTheLoai().forEach( t -> sb.append(t.getTenTheLoai()+", "));
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        holder.dsTheLoai.setText(sb.toString());

    }

    @Override
    public int getItemCount() {
        return truyenList.size();
    }

    public static class TruyenViewHolder extends  RecyclerView.ViewHolder{
        TextView idTruyen,tenTruyen, tenTacGia, dsTheLoai;



        public TruyenViewHolder(@NonNull View itemView) {
            super(itemView);
            idTruyen = itemView.findViewById(R.id.tv_id);
            tenTruyen = itemView.findViewById(R.id.tv_tentruyen);
            dsTheLoai = itemView.findViewById(R.id.tv_theloai);
            tenTacGia = itemView.findViewById(R.id.tv_tentacgia);


        }

    }
}
