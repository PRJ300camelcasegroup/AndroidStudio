package com.example.user.shanesandapp;

public class People implements Comparable<People>{
    int PlayerID;
    String Name;
    public People(String name, int playerID){
        this.Name = name;
        this.PlayerID = playerID;
    }
    public int compareTo(People o){
       if (this.PlayerID==o.PlayerID){
           return 0;
       }
       else if (this.PlayerID>o.PlayerID){
           return 1;
       }
       else
       return -1;
    }
}
