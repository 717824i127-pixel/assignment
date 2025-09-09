package janani;

import java.util.*;

//-------------------- PLAN CLASS --------------------
class Plan {
 private int planId;
 private String name;
 private double monthlyPrice;
 private int screens;
 private String quality;

 public Plan(int planId, String name, double monthlyPrice, int screens, String quality) {
     this.planId = planId;
     this.name = name;
     this.monthlyPrice = monthlyPrice;
     this.screens = screens;
     this.quality = quality;
 }

 // Getters
 public int getPlanId() { return planId; }
 public String getName() { return name; }
 public double getMonthlyPrice() { return monthlyPrice; }
 public int getScreens() { return screens; }
 public String getQuality() { return quality; }

 // Methods
 public void showPlanDetails() {
     System.out.println("Plan: " + name + " | Price: " + monthlyPrice +
             " | Screens: " + screens + " | Quality: " + quality);
 }

 public boolean isAffordable(double budget) {
     return budget >= monthlyPrice;
 }

 public void updatePrice(double newPrice) {
     this.monthlyPrice = newPrice;
 }

 public String getPlanSummary() {
     return name + " - " + quality + " @ " + monthlyPrice;
 }
}

//-------------------- CONTENT (BASE CLASS) --------------------
abstract class Content {
 protected int contentId;
 protected String title;
 protected String genre;
 protected double rating;
 protected int views;

 public Content(int contentId, String title, String genre, double rating) {
     this.contentId = contentId;
     this.title = title;
     this.genre = genre;
     this.rating = rating;
     this.views = 0;
 }

 // Encapsulation for rating
 public double getRating() { return rating; }
 public void setRating(double rating) {
     if (rating >= 0 && rating <= 10) this.rating = rating;
 }

 public String getTitle() { return title; }
 public String getGenre() { return genre; }
 public int getViews() { return views; }

 public void incrementViews() { views++; }

 // Abstract Method - Overridden in subclasses
 public abstract void play();

 public String details() {
     return title + " (" + genre + ") - Rating: " + rating;
 }
}

//-------------------- MOVIE CLASS --------------------
class Movie extends Content {
 private int duration; // minutes

 public Movie(int contentId, String title, String genre, double rating, int duration) {
     super(contentId, title, genre, rating);
     this.duration = duration;
 }

 @Override
 public void play() {
     incrementViews();
     System.out.println("▶ Playing Movie: " + title + " [" + duration + " mins]");
 }

 public int getDuration() { return duration; }
 public void setDuration(int duration) { this.duration = duration; }

 public void showMovieInfo() {
     System.out.println("Movie: " + details() + " | Duration: " + duration + " mins");
 }
}

//-------------------- SERIES CLASS --------------------
class Series extends Content {
 private int episodes;

 public Series(int contentId, String title, String genre, double rating, int episodes) {
     super(contentId, title, genre, rating);
     this.episodes = episodes;
 }

 @Override
 public void play() {
     incrementViews();
     System.out.println("▶ Playing Series: " + title + " | Episode 1 of " + episodes);
 }

 public int getEpisodes() { return episodes; }
 public void setEpisodes(int episodes) { this.episodes = episodes; }

 public void showSeriesInfo() {
     System.out.println("Series: " + details() + " | Episodes: " + episodes);
 }
}

//-------------------- USER CLASS --------------------
class User {
 private int userId;
 private String name;
 private String email;
 private Plan activePlan;
 private List<Content> watchlist;
 private List<Content> history;

 public User(int userId, String name, String email) {
     this.userId = userId;
     this.name = name;
     this.email = email;
     this.watchlist = new ArrayList<>();
     this.history = new ArrayList<>();
 }

 public String getName() { return name; }
 public Plan getActivePlan() { return activePlan; }

 // Encapsulation: Controlled plan subscription
 public void subscribe(Plan plan) {
     this.activePlan = plan;
     System.out.println(name + " subscribed to " + plan.getName());
 }

 public void addToWatchlist(Content content) {
     watchlist.add(content);
     System.out.println(content.getTitle() + " added to " + name + "'s watchlist.");
 }

 public void playContent(Content content) {
     content.play();
     history.add(content);
 }

 public void showHistory() {
     System.out.println(name + "'s Watch History:");
     for (Content c : history) {
         System.out.println("  - " + c.getTitle());
     }
 }

 public double calculateBill() {
     return (activePlan != null) ? activePlan.getMonthlyPrice() : 0;
 }
}

