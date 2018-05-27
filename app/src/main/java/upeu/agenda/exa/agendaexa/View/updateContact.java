package upeu.agenda.exa.agendaexa.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import upeu.agenda.exa.agendaexa.Entidad.Contactos;
import upeu.agenda.exa.agendaexa.Model.ContactosDAO;
import upeu.agenda.exa.agendaexa.R;

public class updateContact extends Activity {

    private Button update;
    private EditText nombre;
    private EditText numero;
    private ContactosDAO contactosDAO;
    private Contactos contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_contact);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        nombre =(EditText)findViewById(R.id.nombre);
        numero=(EditText)findViewById(R.id.numero);
        update=(Button)findViewById(R.id.update);

        contactos = new Contactos();

        contactosDAO = new ContactosDAO(this);

        Bundle parametros = this.getIntent().getExtras();
        final int datos = parametros.getInt("contact");

        final Contactos model = contactosDAO.buscarContact(datos);


            String namew = model.getName();
            String numberw = model.getNumber();

            nombre.setText(numberw);
            numero.setText(namew);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = nombre.getText().toString().trim();
                final String number = numero.getText().toString().trim();

                contactos = new Contactos();
                contactos.setIdcontact(datos);
                contactos.setName(name);
                contactos.setNumber(number);
                contactosDAO.modificarContactos(contactos);
                Intent intent = new Intent(getApplicationContext(),PrincipalView.class);
                startActivity(intent);

            }
        });



    }
}
