package com.example.vance.datenight;

/**
 * Created by Vance on 7/23/2017.
 */

public class Location {
    private int id;
    private String name;
    private String address;
    private String phone;
    private int visited;
    private int userLiked;
    private String dateVisited;
    private String url;
    private int deleted;


    //Constructors
    public Location(){
        super();
    }

    public Location(int id, String name, String address, String phone, String url){
        this.id=id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.url = url;
        visited = 0;
        userLiked = 3;
        dateVisited = "";
        deleted = 0;
    }


    //Get Data
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone() {return phone;}

    public int getVisited(){
        return visited;
    }

    public String getDateVisited(){
        return dateVisited;
    }

    public String getUrl(){return url;}

    public int getUserLiked() {return userLiked;}

    public int getDeleted() { return deleted; }


    //Set Data
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhone(String phone) {this.phone =phone;}

    public void setVisited(int visited){
        this.visited = visited;
    }

    public void setDateVisited(String dateVisited){ this.dateVisited = dateVisited; }

    public void setUrl(String url){this.url=url;}

    public void setUserLiked(int userLiked) {this.userLiked = userLiked;}

    public void setDeleted(int deleted){ this.deleted = deleted;}

}
