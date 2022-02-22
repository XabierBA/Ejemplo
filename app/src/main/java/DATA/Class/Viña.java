package DATA.Class;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "viñas")
public class Viña {
    @PrimaryKey(autoGenerate = true) public int ID_Viña;

    @NonNull
    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "ncepas")
    public int n_cepas;

    @ColumnInfo(name = "extension")
    public int extensión;

    @ColumnInfo(name = "variedad")
    public String variedad;

    public Viña(@NonNull int viña, @NonNull String nom, int ncp, int ext, String var){
        nombre = nom;
        n_cepas = ncp;
        extensión = ext;
        variedad = var;
        ID_Viña = viña;
    }

    public int getID_Viña() {
        return ID_Viña;
    }

    public String getNombre() {
        return nombre;
    }

    public int getN_cepas() {
        return n_cepas;
    }

    public int getExtensión() {
        return extensión;
    }

    public String getVariedad() {
        return variedad;
    }


}
