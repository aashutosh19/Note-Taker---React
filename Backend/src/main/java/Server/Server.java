package Server;

import com.google.gson.Gson;

import java.util.*;
import static spark.Spark.*;
public class Server {

    private static List<String> notes = new ArrayList<>();

    public static void main(String[] args){
        Gson gson = new Gson();
        port(4000);
        path( "/api", () -> {
        get( "/users", (req, res) -> {
            //put more stuff here
                return "Test";
            });
        post("/addNote", (req, res) -> {
            String bodyString = req.body();

//            converts plain Json string to gson
            AddNotesDto notesDto = gson.fromJson(bodyString, AddNotesDto.class);
            notes.add(notesDto.note);
            System.out.println(bodyString);
            System.out.println(notes.size());
            return "OK";
        });
        get("/getAllNotes", (req, res) ->{
            NotesListDto list = new NotesListDto(notes);
            return gson.toJson(list);
        });
        });
    }
}