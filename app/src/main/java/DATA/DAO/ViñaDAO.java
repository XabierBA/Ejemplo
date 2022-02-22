package DATA.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;



import java.util.List;

import DATA.Class.Viña;

public interface ViñaDAO {

    @Query("SELECT * FROM viñas")
    LiveData<List<Viña>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Viña viña);


}
