package com.example.guesstheword.logic;

import android.graphics.Color;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.guesstheword.ui.game.MessageNotificationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class extract from the API of "https://random-word-api.herokuapp.com/word" 10 random
 * words in the language given.
 */
public class WordGenerator {
    private ArrayList<String> words;
    private int numberOfWords = 10;
    private String language;
    private String url = "https://random-word-api.herokuapp.com/word";
    private LinkedList<ChatMessage> chat;

    public WordGenerator(Room room, LinkedList<ChatMessage> chat) {
        language = room.getLanguage().toString();
        url = url + "?number=" + numberOfWords + "&lang=" + language;
        this.chat = chat;
    }

    public void extractWordsFromInternet() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("word");
                    words = new ArrayList<>(jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++)
                        words.set(i, jsonArray.getString(i));
                } catch (JSONException exc) {
                    chat.add(new MessageNotificationView("Can't extract random words " +
                            "for the chooser: " + exc.toString(), Color.RED));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                chat.add(new MessageNotificationView("Can't extract random words " +
                        "for the chooser: " + error.toString(), Color.RED));
            }
        });
    }

    public String getUrl() {
        return url;
    }

    public List<String> getWords() {
        return words;
    }
}
