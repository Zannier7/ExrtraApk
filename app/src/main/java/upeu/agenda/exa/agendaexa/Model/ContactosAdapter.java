package upeu.agenda.exa.agendaexa.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import upeu.agenda.exa.agendaexa.Entidad.Contactos;
import upeu.agenda.exa.agendaexa.R;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Contactos> pData;

    public ContactosAdapter(Context context, ArrayList<Contactos> pData) {
        this.context = context;
        this.pData = pData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.agencarview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(pData.get(position).getName());
        holder.number.setText(Integer.toString(pData.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return pData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        TextView number;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);

        }
    }
}
