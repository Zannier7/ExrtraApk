package upeu.agenda.exa.agendaexa.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import upeu.agenda.exa.agendaexa.Entidad.Contactos;
import upeu.agenda.exa.agendaexa.Model.ContactosAdapter;
import upeu.agenda.exa.agendaexa.Model.ContactosDAO;
import upeu.agenda.exa.agendaexa.R;

public class deleteContact extends Activity {
    ArrayList<Contactos> contactlist;
    RecyclerView recyclerView;
    ContactosAdapter conAdapter;
    ContactosDAO contactosDAO;
    private int idposi;

    private Button yes;
    private Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_contact);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Bundle parametros = this.getIntent().getExtras();
        final int datos = parametros.getInt("idcontacto");

        contactosDAO = new ContactosDAO(this);
        contactlist = contactosDAO.listarContactos();

        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactosDAO.deleteContacto(datos);
                Intent intent = new Intent(getApplicationContext(),PrincipalView.class);
                startActivityForResult(intent,0);
                Toast.makeText(deleteContact.this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PrincipalView.class);
                startActivityForResult(intent,0);
            }
        });
    }
}
