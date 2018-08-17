package com.example.epubreader;

public class urlHelper {
    String BasePath = "http://workshop.creatorslane.org/Wesley/Books/";
    public String ToBook(String BookName, String Language)
    {
        return BasePath + BookName + "/" + Language + "/book.epub";
    }

    public String ToCoverArt(String BookName, String Language)
    {
        return BasePath + BookName + "/" + Language + "/.cover.png";
    }

 /*   public String ToGetBooks()
    {
        return BasePath + "dir.php";
    }
    */
}
