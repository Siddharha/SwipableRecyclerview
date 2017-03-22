package creativelizard.in.swipablerecyclerview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlItems;
    private ArrayList<MyListItem> arrayList;
    private MyListAdapter myListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        setupList();
        loadData();

        onClickAction();
    }

    private void onClickAction() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                myListAdapter.remove(swipedPosition);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);

        itemTouchHelper.attachToRecyclerView(rlItems);


    }

    private void setupList() {
        rlItems.setLayoutManager(layoutManager);
        rlItems.setAdapter(myListAdapter);

        myListAdapter.notifyDataSetChanged();
    }

    private void loadData() {

        for(int i=0;i<10;i++) {
            MyListItem myListItem = new MyListItem();
            myListItem.setName("Sid_"+i);
            myListItem.setPhone("9564751914"+i);
            myListItem.setEmail("siddhartha.maji_"+i+"@gmail.com");


            arrayList.add(myListItem);
        }
    }

    private void initialize() {
        rlItems = (RecyclerView)findViewById(R.id.rlItems);
        arrayList = new ArrayList<>();
        myListAdapter = new MyListAdapter(this, arrayList,R.layout.my_item_cell);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    }

    public void deleteItem(final int position, final MyListItem myListItem) {
        Snackbar snackbar = Snackbar
                .make(rlItems, "ITEM REMOVED", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrayList.add(position, myListItem);
                        myListAdapter.notifyItemInserted(position);
                        rlItems.scrollToPosition(position);
                       // photosToDelete.remove(mPhoto);
                    }
                });
        snackbar.show();
        if (position < 0 || position >= arrayList.size()) {
            return;
        }
        arrayList.remove(position);
        myListAdapter.notifyItemRemoved(position);
    }
}
