package upeu.agenda.exa.agendaexa.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static upeu.agenda.exa.agendaexa.Database.MyDB.Contacto.TABLA_CONTACTOS;

public class MyDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Agenda";
    public static final int DATABASE_VERSION = 1;

    public MyDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Contactos(idcontact integer primary key autoincrement, "
                +"number text not null, name text not null)");
        db.execSQL("insert into Contactos(number,name) values('980537794', 'Zannier Vargas')");
        db.execSQL("insert into Contactos(number,name) values('980537794', 'Deyvis García')");
        db.execSQL("insert into Contactos(number,name) values('980532124', 'Arnold Morales')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTACTOS);
        onCreate(db);
    }

    public static class Contacto{
        public static final String TABLA_CONTACTOS="Contactos";
        public static final String COLUM_ID="idcontact";
        public static final String COLUM_NUMBER="number";
        public static final String COLUM_NAME="name";
        public static final String[] COLUMNAS = new String[]{COLUM_ID,COLUM_NUMBER,COLUM_NAME};

    }
}
