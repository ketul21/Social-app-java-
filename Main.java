package io.itpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SocialApp socialApp = new SocialApp();

        // Add users
        socialApp.addUser("Ketul", "ketul.jpg");
        socialApp.addUser("Stavan", "stavan.jpg");
        socialApp.addUser("Nirvi", "nirvi.jpg");
        socialApp.addUser("Shruti", "shruti.jpg");
        socialApp.addUser("Aarav", "aarav.jpg");
        socialApp.addUser("Maully", "maully.jpg");
        socialApp.addUser("Utsavi", "utsavi.jpg");

        // Create friendships
        socialApp.addFriendship("Ketul", "Shruti");
        socialApp.addFriendship("Ketul", "Nirvi");
        socialApp.addFriendship("Ketul", "Maully");
        socialApp.addFriendship("Stavan", "Nirvi");
        socialApp.addFriendship("Nirvi", "Utsavi");
        socialApp.addFriendship("Stavan", "Aarav");
        socialApp.addFriendship("Aarav", "Ketul");
        socialApp.addFriendship("Aarav", "Shruti");
        socialApp.addFriendship("Maully","Utsavi");





        // Get friends of a user
        List<Node> friends = socialApp.getFriends("Ketul");
        System.out.println("Friends of Ketul:");
        for (Node friend : friends) {
            System.out.println("Name: " + friend.getName() + ", Profile Picture: " + friend.getProfilePicture());
        }

        // Calculate distance between specific users
        String startUser = "Shruti";
        String targetUser = "Ketul";
        int distance = socialApp.getRelationshipDistance(startUser, targetUser);
        if (distance != -1) {
            System.out.println("Distance between " + startUser + " and " + targetUser + ": " + distance);
        } else {
            System.out.println("No path found between " + startUser + " and " + targetUser);
        }

        calculateAllDistances(socialApp);
    }
    private static void calculateAllDistances(SocialApp socialApp) {
        List<Node> users = new ArrayList<>(socialApp.getUsers());
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                Node user1 = users.get(i);
                Node user2 = users.get(j);

                int distance = socialApp.getRelationshipDistance(user1.getName(), user2.getName());

                if (distance != -1) {
                    System.out.println("Distance between " + user1.getName() + " and " + user2.getName() + ": " + distance);
                } else {
                    System.out.println("No path found between " + user1.getName() + " and " + user2.getName());
                }
            }
               }

    }
}
