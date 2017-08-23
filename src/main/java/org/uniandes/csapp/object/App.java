/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.org.uniandes.csapp.object;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Daniel
 */
public class App {
    String name, url, description;
    int numeroRatings;
    double raitingProm;
    HashSet<String> recentChange;    
    HashSet<String> reviews;
    int raitings5;
    int raitings4;    

    public App(String name, String url, String description, int numeroRatings, double raitingProm, HashSet<String> recentChange, HashSet<String> reviews, int raitings5, int raitings4) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.numeroRatings = numeroRatings;
        this.raitingProm = raitingProm;
        this.recentChange = recentChange;
        this.reviews = reviews;
        this.raitings5 = raitings5;
        this.raitings4 = raitings4;
    }

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumeroRatings() {
        return numeroRatings;
    }

    public void setNumeroRatings(int numeroRatings) {
        this.numeroRatings = numeroRatings;
    }

    public double getRaitingProm() {
        return raitingProm;
    }

    public void setRaitingProm(double raitingProm) {
        this.raitingProm = raitingProm;
    }

    public HashSet<String> getRecentChange() {
        return recentChange;
    }

    public void setRecentChange(HashSet<String> recentChange) {
        this.recentChange = recentChange;
    }

    public HashSet<String> getReviews() {
        return reviews;
    }

    public void setReviews(HashSet<String> reviews) {
        this.reviews = reviews;
    }

    public int getRaitings5() {
        return raitings5;
    }

    public void setRaitings5(int raitings5) {
        this.raitings5 = raitings5;
    }

    public int getRaitings4() {
        return raitings4;
    }

    public void setRaitings4(int raitings4) {
        this.raitings4 = raitings4;
    }
    
    
  @Override
    public String toString() {
        return "App{" + "name=" + name + ", url=" + url + ", description=" + description + ", numeroRatings=" + numeroRatings + ", raitingProm=" + raitingProm + ", recentChange=" + recentChange + ", reviews=" + reviews + ", raitings5=" + raitings5 + ", raitings4=" + raitings4 + '}';
    }

}
