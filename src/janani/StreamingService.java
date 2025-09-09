package janani;

import java.util.ArrayList;
import java.util.List;

//-------------------- STREAMING SERVICE --------------------
class StreamingService {
private List<User> users;
private List<Plan> plans;
private List<Content> catalog;

public StreamingService() {
   users = new ArrayList<>();
   plans = new ArrayList<>();
   catalog = new ArrayList<>();
}

// Manage Users, Plans, Content
public void addUser(User user) { users.add(user); }
public void addPlan(Plan plan) { plans.add(plan); }
public void addContent(Content content) { catalog.add(content); }

// Overloaded Recommendation methods
public void recommend(String genre) {
   System.out.println("Recommended by Genre (" + genre + "):");
   for (Content c : catalog) {
       if (c.getGenre().equalsIgnoreCase(genre)) System.out.println("  - " + c.details());
   }
}

public void recommend(double minRating) {
   System.out.println("Recommended by Rating > " + minRating + ":");
   for (Content c : catalog) {
       if (c.getRating() >= minRating) System.out.println("  - " + c.details());
   }
}

public void recommend(int minViews, boolean byPopularity) {
   if (byPopularity) {
       System.out.println("Recommended by Popularity (Views > " + minViews + "):");
       for (Content c : catalog) {
           if (c.getViews() >= minViews) System.out.println("  - " + c.details());
       }
   }
}

// Reports
public void totalRevenue() {
   double revenue = 0;
   for (User u : users) revenue += u.calculateBill();
   System.out.println("Total Monthly Revenue: " + revenue);
}

public void topWatchedContent() {
   catalog.sort((a, b) -> b.getViews() - a.getViews());
   System.out.println("Top Watched Content:");
   for (int i = 0; i < Math.min(3, catalog.size()); i++) {
       System.out.println("  - " + catalog.get(i).getTitle() + " (Views: " + catalog.get(i).getViews() + ")");
   }
}
}


