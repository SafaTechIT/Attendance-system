package com.example.attendancesystem.database;

import com.example.attendancesystem.model.Event;

import java.sql.*;
import java.util.ArrayList;

public class EventsDriver extends Driver {
    private final ArrayList<Event> events;

    public EventsDriver() {
        events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        getAllObjects(this.getStatement());
        return events;
    }

    public Event getEvent(int id) {
        return (Event) getObjects(this.getStatement(), id);
    }

    //  TODO get event from database
    @Override
    protected void getAllObjects(Statement st) {
        try {
            connector();
            ResultSet rs = st.executeQuery("select * from events");
            while (rs.next()) {
                Event event = getEventDate(rs);
                events.add(event);
            }
            rs.close();
            closeAnything();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected Object getObjects(Statement st, int id) {
        try {
            connector();
            String sql = "SELECT * FROM events WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            Event result = getEventDate(rs);
            rs.close();
            closeAnything();
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private Event getEventDate(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            Date date = Date.valueOf(rs.getString("date"));
            ArrayList<String> members;
            Event event = new Event(title, date);
            members = memberExtraction(rs.getString("members"));
            event.setMembers(members);
            event.setId(id);
            return event;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<String> memberExtraction(String str) {
        ArrayList<String> members = new ArrayList<>();
        if (str != null) {
            str = str.substring(0, str.length() - 1);
            str = str.substring(1);
            String[] newStr = str.split(", ");
            for (String ar : newStr) {
                ar = ar.replace("\"", "");
                members.add(ar);
            }
        }
        return members;
    }

    @Override
    public boolean insert(Object obj) {
        Event event = (Event) obj;
        String title = event.getTitle();
        Date date = event.getDate();
        String sql = "INSERT INTO events (title, date) VALUES (? , ?)";
        try {
            connector();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setDate(2, date);
            ps.executeUpdate();
            closeAnything();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // TODO update event to database
    public boolean update(int id, Object obj) {
        ArrayList<String> members = ((Event) obj).getMembers();
        String sql = "UPDATE events SET members=?  WHERE id=?";
        try {
            connector();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, correctiveMembers(members));
            ps.setInt(2, id);
            ps.executeUpdate();
            closeAnything();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String correctiveMembers(ArrayList<String> members) {
        ArrayList<String> result = new ArrayList<>();
        for (String member : members) {
            String str = "\"" + member + "\"";
            result.add(str);
        }
        return result.toString();
    }

    //  TODO delete event from database
    public boolean delete(int id) {
        String sql = "DELETE FROM events WHERE id=?";
        return deleteItem(sql, id);
    }
}
