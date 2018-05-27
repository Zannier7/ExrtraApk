package upeu.agenda.exa.agendaexa.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import upeu.agenda.exa.agendaexa.Database.MyDB;
import upeu.agenda.exa.agendaexa.Entidad.Contactos;
import upeu.agenda.exa.agendaexa.Model.ContactosDAO;
import upeu.agenda.exa.agendaexa.R;

public class addContactView extends Activity {

    MyDB bd;
    private  EditText name2;
    private EditText number2;
    private Button save2;
    private ProgressDialog mProgress;
    private ContactosDAO contactosDAO;
    private Contactos contactos;
    private int idcontact;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addcontact);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        mProgress = new ProgressDialog(this);

        contactosDAO = new ContactosDAO(this);

        idcontact = getIntent().getIntExtra("USUARIO_ID",0);

        name2 = (EditText) findViewById(R.id.names);
        number2 = (EditText) findViewById(R.id.numbers);
        save2 = (Button) findViewById(R.id.saves);

        if (idcontact>0){
            Contactos model = contactosDAO.buscarContact(idcontact);
            name2.setText(model.getName());
            number2.setText(model.getNumber());
        }



       save2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               enviar();
           }
       });

    }

    public void enviar(){
        final String namess = name2.getText().toString().trim();
        final String numberss = number2.getText().toString().trim();

        contactos = new Contactos();
        contactos.setName(namess);
        contactos.setNumber(numberss);

        if (idcontact>0){

            contactos.setIdcontact(idcontact);
        }
        mProgress.setMessage("Agregando contactos...");
        mProgress.show();

        long resultado = contactosDAO.modificarContactos(contactos);
        limpiarcampos();
        confirmacion();

        finish();
        startActivity(new Intent(this, PrincipalView.class));
    }
    public void limpiarcampos(){

        name2.setText("");
        number2.setText("");

    }

    public void confirmacion(){

        mProgress.dismiss();

    }
}
