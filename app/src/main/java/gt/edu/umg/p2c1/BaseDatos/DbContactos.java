package gt.edu.umg.p2c1.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbContactos extends DbHelper{
    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //este metodo inserta un contacto en la tabla t_contactos

    public long insertaContacto(String nombre, String telefono, String email) {
        try{
            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                return -1;
            }
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("email", email);

            long id = db.insert(DbHelper.TABLE_CONTACTOS, null, values);
            return id;
        } catch (Exception e) {
            return -1;
        }
    }
}
