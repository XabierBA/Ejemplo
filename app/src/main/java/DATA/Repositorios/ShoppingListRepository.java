package DATA.Repositorios;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import DATA.Class.ShoppingList;
import DATA.DAO.ShoppingListDao;
import DATA.Database.Database;

public class ShoppingListRepository {
    private final LiveData<List<ShoppingList>> mShoppingLists;

    private final ShoppingListDao mShoppingListDao;

    public ShoppingListRepository(Context context) {
        Database db = Database.getInstance(context);
        mShoppingListDao = db.shoppingListDao();
        mShoppingLists = mShoppingListDao.getAll();
    }

    public LiveData<List<ShoppingList>> getAllShoppingLists() {
        return mShoppingLists;
    }

    public void insert(ShoppingList shoppingList) {
        Database.dbExecutor.execute(
                () -> mShoppingListDao.insert(shoppingList)
        );
    }
}
