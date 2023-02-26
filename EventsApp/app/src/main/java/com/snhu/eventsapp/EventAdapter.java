package com.snhu.eventsapp;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    // Collection of inventory items in this list/adapter
    private List<Event> mEvents;

    // A context that this item adapter is running in
    private Context mCtx;

    // An instance of the app's database
    EventDatabase eventDatabase;

    public EventAdapter(List<Event> events, Context ctx, EventDatabase eventDb) {
        mEvents = events;
        mCtx = ctx;
        eventDatabase = eventDb;
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create an instance of the child view
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
        return new EventHolder(view, eventDatabase);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        // Find the event at the current position and bind its data to the item holder view
        Event event = mEvents.get(position);
        holder.bind(event);
    }

    public void deleteEventView(Event mEvent) {
        int index = mEvents.indexOf(mEvent);
        if (index >= 0) {
            mEvents.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void setEvents(List<Event> events) {
        this.mEvents = events;
    }

    public void addEvent() {
        eventDatabase.addEvent("Party", "today");
        setEvents(eventDatabase.getEvents());
        notifyDataSetChanged();
    }

    class EventHolder extends RecyclerView.ViewHolder {

        // Cached view references
        private Event mEvent;
        private TextView mNameTextView;
        private TextView mDateView;

        // Instance of the database
        EventDatabase eventDatabase;

        public EventHolder(View itemView, EventDatabase eventDb) {
            super(itemView);
            eventDatabase = eventDb;
            mNameTextView = itemView.findViewById(R.id.eventName);
            mDateView = itemView.findViewById(R.id.eventDate);

            itemView.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventDatabase.deleteEvent(mEvent);
                    deleteEventView(mEvent);
                }
            });
        }

        public void bind(Event event) {
            mEvent = event;
            mNameTextView.setText(mEvent.getName());
            mDateView.setText(mEvent.getDate());
        }
    }
}
