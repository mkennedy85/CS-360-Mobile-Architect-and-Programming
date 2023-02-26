package com.snhu.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class EventListActivity extends AppCompatActivity {

    // List of all inventory items
    private List<Event> mEventList;

    // Instance of the app database
    EventDatabase eventDatabase;

    // View elements
    RecyclerView eventListView;
    TextView emptyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        // Initialize the database and fetch all inventory items
        eventDatabase = EventDatabase.getInstance(getApplicationContext());
        mEventList = eventDatabase.getEvents();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        eventListView = findViewById(R.id.eventListView);
        eventListView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(eventListView.getContext(),
                layoutManager.getOrientation());
        eventListView.addItemDecoration(dividerItemDecoration);

        // Find the view when there are no items
        emptyListView = findViewById(R.id.emptyListView);

        // Send items to recycler view
        EventAdapter adapter = new EventAdapter(mEventList, this, eventDatabase);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkListIsEmpty();
            }
        });

        findViewById(R.id.addEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addEvent();
                adapter.notifyItemInserted(mEventList.size() - 1);
            }
        });

        eventListView.setAdapter(adapter);

        // Check to see if the list is empty - showing the appropriate child view
        checkListIsEmpty();
    }

    public void checkListIsEmpty() {
        if (mEventList.isEmpty()) {
            eventListView.setVisibility(View.GONE);
            emptyListView.setVisibility(View.VISIBLE);
        } else {
            eventListView.setVisibility(View.VISIBLE);
            emptyListView.setVisibility(View.GONE);
        }
    }

    public void settings(View view) {
        Intent intent = new Intent(getApplicationContext(), NotificationSettingsActivity.class);
        startActivity(intent);
    }
}
