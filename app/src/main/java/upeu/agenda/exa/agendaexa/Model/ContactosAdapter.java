package upeu.agenda.exa.agendaexa.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import upeu.agenda.exa.agendaexa.Entidad.Contactos;
import upeu.agenda.exa.agendaexa.R;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Contactos> pData;
    private OnItemClickListener mListener;

    public ContactosAdapter(Context context, ArrayList<Contactos> pData) {
        this.context = context;
        this.pData = pData;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onCallItem(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.agencarview,parent,false);
        return new MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(pData.get(position).getName());
        holder.number.setText(pData.get(position).getNumber());
    }



    @Override
    public int getItemCount() {
        return pData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        TextView number;
        Button delete;
        Button edit;
        Button call;
        public MyViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
            delete = (Button)itemView.findViewById(R.id.delete);
            edit = (Button)itemView.findViewById(R.id.edit);
            call = (Button)itemView.findViewById(R.id.call);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        };
                    }
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        };
                    }
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onCallItem(position);
                        };
                    }
                }
            });


        }
    }
    public void filterList(ArrayList<Contactos> filteredList){
        pData = filteredList;
        notifyDataSetChanged();
    }
}
