package com.example.weeklyschedule;

/**
 * @author Zachary Eubanks-Wilson
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class noteList {
    private ArrayList<Note> notes = new ArrayList<>();
    File file = new File("Notes");
    PrintWriter writer;
    FileWriter fw;
    BufferedWriter bw;

    noteList(ArrayList notes) throws FileNotFoundException, IOException{
        setNotes(notes);
    }
    //set notes
    public void setNotes(ArrayList notes) throws FileNotFoundException, IOException{
        this.notes = notes;
        this.writer = new PrintWriter(file);
        this.fw = new FileWriter(file, true);
        this.bw = new BufferedWriter(fw);
        for(int i=0;i<notes.size();i++){
            writer.println(notes.get(i).toString());
        }
        writer.close();
    }
    //get notes
    public ArrayList getNotes(){
        return notes;
    }
    //add method
    public void addNote(Note note){
        notes.add(note);
    }
    //remove method
    public void removeNote(Note note){
        notes.remove(note);
    }
    //edit method
    public void editNote(Note note){
        for(int i=0;i<notes.size();i++){
            if(notes.get(i)==note){
                //Where the textfield should be inputted
            }
        }
    }
}