package DATA.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DATA.Class.ShoppingList;
import DATA.Class.Viña;
import DATA.DAO.ShoppingListDao;
import DATA.DAO.ViñaDAO;

@androidx.room.Database(entities = {ShoppingList.class, Viña.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    // Exposición de DAOs
    public abstract ShoppingListDao shoppingListDao();
    public abstract ViñaDAO viñaDAO();

    private static final String DATABASE_NAME = "shopping-list-db";

    private static Database INSTANCE;

    private static final int THREADS = 4;

    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static Database getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), Database.class,
                            DATABASE_NAME)
                            .addCallback(mRoomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback mRoomCallback;

    static {
        mRoomCallback = new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                dbExecutor.execute(() -> {
                    ShoppingListDao dao = INSTANCE.shoppingListDao();

                    ShoppingList list1 = new ShoppingList("1", "Lista de ejemplo");
                    ShoppingList list2 = new ShoppingList("2", "Banquete de Navidad");

                    dao.insert(list1);
                    dao.insert(list2);
                });
            }
        };
    }
}

