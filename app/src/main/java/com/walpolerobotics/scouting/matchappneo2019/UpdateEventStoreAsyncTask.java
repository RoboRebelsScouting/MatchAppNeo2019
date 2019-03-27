package com.walpolerobotics.scouting.matchappneo2019;

import android.content.Context;
import android.os.AsyncTask;

import com.walpolerobotics.scouting.matchappneo2019.room.AppDatabase;
import com.walpolerobotics.scouting.matchappneo2019.room.Event;
import com.walpolerobotics.scouting.matchappneo2019.room.EventDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UpdateEventStoreAsyncTask extends AsyncTask<Object, Double, Boolean> {

    private static final String LIST_EVENTS_ENDPOINT = "https://www.thebluealliance.com/api/v3/events/2019/simple";

    @Override
    protected Boolean doInBackground(Object[] objects) {
        try {
            URL url = new URL(LIST_EVENTS_ENDPOINT);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-TBA-Auth-Key", "");

            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder data = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                data.append(line).append('\n');
            }

            Context context = (Context) objects[0];
            JSONArray parsedData = new JSONArray(data.toString());
            EventDao eventDao = AppDatabase.getDatabaseInstance(context).eventDao();
            for (int i = 0; i < parsedData.length(); i++) {
                JSONObject obj = parsedData.getJSONObject(i);

                Event event = new Event();
                event.key = obj.getString("key");
                event.readableName = obj.getString("name");

                eventDao.insertAll(event);
            }

            return true;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {

        }
    }
}
