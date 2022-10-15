package com.example.mycloset;

import java.util.List;

public class DataProvider {

    public static List<Content> GREEN_CATEGORY;

    /*public static class Category{
        String uno;
        String due;
        String tre;

        public Category(String uno,String due,String tre) {
            this.uno = uno;
            this.due = due;
            this.tre = tre;
        }
    }
    public static Category GREEN_CATEGORY = new Category("uno", "due", "tre");

    public static Category getContentsByCategory(Category green_category){
        return GREEN_CATEGORY;
    }*/

    public static class Content{
        String nome;
        String tipo;
        String color;

        public Content(String nome,String tipo,String color){
            this.nome = nome;
            this.tipo = tipo;
            this.color = color;
        }

        public int getImage() {
            return R.drawable.ic_home;
        };

        public int getTitle() {
            return R.string.title;
        }
    }

    public static Content uno = new Content("maglia", "t-shirt", "green");
    public Content due = new Content("braghe", "pantaloni", "green");
    public Content tre = new Content("calzini", "calzini", "green");


    public static List<Content> getContentsByCategory(List<Content> greenCategory) {
        greenCategory.add(uno);
        return greenCategory;
    }
}


