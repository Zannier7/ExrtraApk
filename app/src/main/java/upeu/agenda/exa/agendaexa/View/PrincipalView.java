package upeu.agenda.exa.agendaexa.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import upeu.agenda.exa.agendaexa.Entidad.Contactos;
import upeu.agenda.exa.agendaexa.Model.ContactosAdapter;
import upeu.agenda.exa.agendaexa.Model.ContactosDAO;
import upeu.agenda.exa.agendaexa.R;

public class PrincipalView extends AppCompatActivity {

    private FloatingActionButton add;
    private Button delete;
    private Button edit;
    private Button call;
    private int idcontact;
    private int idposi;
    private EditText search;

    ArrayList<Contactos> contactlist;
    RecyclerView recyclerView;
    ContactosAdapter conAdapter;
    ContactosDAO contactosDAO;

    private static int CALL = 101;
    public PrincipalView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_view);

        add = (FloatingActionButton) findViewById(R.id.add);

        contactosDAO = new ContactosDAO(this);
        contactlist = contactosDAO.listarContactos();
        conAdapter = new ContactosAdapter(this, contactlist);
        delete = (Button) findViewById(R.id.delete);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerproduct);
        search = (EditText)findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString().trim());
            }
        });


        recyclerView.setAdapter(conAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));




        conAdapter.setOnItemClickListener(new ContactosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                idposi = position;
                int id = contactlist.get(idposi).getIdcontact();
                String na = contactlist.get(idposi).getName();
                Log.d("no", "porque " + na);
                Intent intent = new Intent(getApplicationContext(), deleteContact.class);
                intent.putExtra("idcontacto", id);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                idposi = position;
                int ids = contactlist.get(idposi).getIdcontact();
                Intent intent = new Intent(getApplicationContext(), updateContact.class);
                intent.putExtra("contact", ids);
                startActivity(intent);
            }

            @Override
            public void onCallItem(int position) {
                idposi = position;
                int ids = contactlist.get(idposi).getIdcontact();
                final Contactos model = contactosDAO.buscarContact(ids);
                String caller = model.getName();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + caller));
                if (ActivityCompat.checkSelfPermission(PrincipalView.this, Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PrincipalView.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            CALL);
                    return;
                }
                startActivity(intent);
            }
        });
        idcontact = getIntent().getIntExtra("USUARIO_ID",0);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),addContactView.class);
                startActivity(intent);
            }
        });




    }

    private void filter(String trim) {
        ArrayList<Contactos> filteredList = new ArrayList<>();
        for (Contactos item : contactlist){
            if (item.getNumber().toLowerCase().contains(trim.toLowerCase())){
                filteredList.add(item);
            }
        }
        conAdapter.filterList(filteredList);
    }

    public void deleteContact(View view) {

    }

    public void editContact(View view) {

    }


    public void callContact(View view) {
    }
}
