package upeu.agenda.exa.agendaexa.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upeu.agenda.exa.agendaexa.Database.MyDB;
import upeu.agenda.exa.agendaexa.Entidad.Contactos;

public class ContactosDAO {
    private MyDB myDB;
    private SQLiteDatabase database;

    public ContactosDAO(Context context){
        myDB = new MyDB(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = myDB.getWritableDatabase();
        }
        return database;
    }

    public Contactos addContacto(Cursor cursor){
       Contactos contactos = new Contactos(
               cursor.getInt(cursor.getColumnIndex(MyDB.Contacto.COLUM_ID)),
               cursor.getString(cursor.getColumnIndex(MyDB.Contacto.COLUM_NAME)),
               cursor.getString(cursor.getColumnIndex(MyDB.Contacto.COLUM_NUMBER))
       );
       return contactos;
    }

    public ArrayList<Contactos> listarContactos(){
        Cursor cursor = getDatabase().query(MyDB.Contacto.TABLA_CONTACTOS,MyDB.Contacto.COLUMNAS,null,null,null,null,null);
        ArrayList<Contactos> lista = new ArrayList<Contactos>();
        while (cursor.moveToNext()){
            Contactos modelo = addContacto(cursor);
            lista.add(modelo);
        }
        return lista;
    }

    public long updateContacto(Contactos contactos){
        ContentValues values = new ContentValues();
        values.put(MyDB.Contacto.COLUM_NAME,contactos.getName());
        values.put(MyDB.Contacto.COLUM_NUMBER,contactos.getNumber());
        if (contactos.getIdcontact() !=null){
            return database.update(MyDB.Contacto.TABLA_CONTACTOS,values,"idcontact = ?",new String[]{
                    contactos.getIdcontact().toString()
            });
        }
        return getDatabase().insert(MyDB.Contacto.TABLA_CONTACTOS,null,values);
    }

    public boolean deleteContacto(int Idcontact) {
        return getDatabase().delete(MyDB.Contacto.TABLA_CONTACTOS,"idcontact=?", new String[]{
                Integer.toString(Idcontact)
        })>0;
    }

    public Contactos buscarContact(int id){
        Cursor cursor = getDatabase().query(MyDB.Contacto.TABLA_CONTACTOS,
                MyDB.Contacto.COLUMNAS,"idcontact=?", new String[]{Integer.toString(id)},null,null,null);
        if (cursor.moveToNext()){
            Contactos model = addContacto(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public long modificarContactos(Contactos contactos){
        ContentValues values = new ContentValues();
        values.put(MyDB.Contacto.COLUM_NAME,contactos.getName());
        values.put(MyDB.Contacto.COLUM_NUMBER,contactos.getNumber());
        if (contactos.getIdcontact()!=null){
            return database.update(MyDB.Contacto.TABLA_CONTACTOS,values,"idcontact=?", new String[]{contactos.getIdcontact().toString()});
        }
        return getDatabase().insert(MyDB.Contacto.TABLA_CONTACTOS,null,values);
    }

    public void cerrarDB(){
        myDB.close();
        database=null;
    }
}
