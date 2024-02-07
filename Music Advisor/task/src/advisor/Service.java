package advisor;

import api.*;

import java.util.Scanner;

public class Service {
    Scanner scanner = new Scanner(System.in);
    boolean isAuthorized = false;

    MusicApi api = null;

    public void start() {

        while (true) {

            String input = scanner.nextLine();

            switch (input) {

                case "auth":
                    setAuthorization();
                    break;

                case "featured":
                    getFeatured(isAuthorized);
                    break;

                case "new":
                    getReleased(isAuthorized);
                    break;

                case "categories":
                    getCategories(isAuthorized);
                    break;

                case "next":
                    api.next();
                    break;

                case "prev":
                    api.prev();
                    break;

                case "exit":
                    System.out.println("---GOODBYE!---");
                    return;

                default:

                    if (input.matches("playlists .+")) {

                        String categoriesName = input.substring(10);
                        getPlaylists(isAuthorized, categoriesName);

                    }
            }
        }
    }

    public void setAuthorization() {
        Authentication auth = new Authentication();
        auth.getAccessCode();
        auth.getAccessToken();
        this.isAuthorized = true;
    }

    public void getReleased(boolean auth) {
        if (auth) {
            api = new NewReleases();
            api.obtain();
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void getFeatured(boolean auth) {
        if (auth) {
            api = new FeaturedPlaylists();
            api.obtain();
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void getCategories(boolean auth) {
        if (auth) {
           api = new Category();
           api.obtain();
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    public void getPlaylists(boolean auth, String categoriesName) {
        if (auth) {
            api = new CategorizedPlaylists(categoriesName);
            api.obtain();
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}