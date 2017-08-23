package main.java.org.uniandes.csapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.org.uniandes.csapp.file.AppFile;
import main.java.org.uniandes.csapp.object.App;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CSApp {

	public static void main(String[] args) throws IOException {
            
            // Obtener Categoria
            try{
                //Inicia prompt
            System.out.println("--------Crawler/Scrapper for the PlayStore--------");
            System.out.println(" ");
            System.out.println("Select one of the categories listed below:");
            System.out.println(" ");
                Document doc = Jsoup.connect("https://play.google.com/store/apps").timeout(0).get();
                ArrayList<String> categorias = new ArrayList<>();
                Elements anchors = doc.getElementsByClass("child-submenu-link");
                String s_categorias = "";
                int i = 0;
                for (Element element : anchors){
                    String categoria = element.attr("href").replace("/store/apps/category/", "");
                    categorias.add(categoria);
                    s_categorias = "["+i+"]"+categoria;
                    System.out.println(s_categorias);
                    i++;
                }
                        
            if (s_categorias.equals("")) {
                System.out.println("There are no categories"
                    + "... verify your Internet connection");
                System.exit(0);
            }
                    
            Scanner in = new Scanner(System.in);
            int index = in.nextInt();
            String categoria_sel = categorias.get(index);
            System.out.println("Selected: " + categoria_sel);
            
            System.out.println("Select an option:");
            String[] top = new String[2];
            top[0] = "topselling_paid";
            top[1] = "topselling_free";
            System.out.println("[0]"+top[0]);            
            System.out.println("[1]"+top[1]);
            
            int index_sel = in.nextInt();
            String top_sel = top[index_sel];
            System.out.println("Selected: " + top_sel);
            
            String consulta = "https://play.google.com/store/apps/"
                  + "category/"+categoria_sel+"/collection/"+top_sel;
            System.out.println("Searching: "+consulta);
          Document docC = Jsoup.connect(consulta).timeout(0).get();
          Document detailDoc = null;
          HashSet<String> hrefs = new HashSet<>();
          String href = null;
          Elements anchorsSel = docC.getElementsByClass("card-click-target");
          

          for (Element element: anchorsSel){
              href = "https://play.google.com" + element.attr("href");
              hrefs.add(href);
          }

          String description = null;
          String name = null;
          int numRatings = 0;
          double raitingProm = 0.0;
          
          HashSet<App> apps = new HashSet<>();
          HashSet<App> apps45 = new HashSet<>();
          HashSet<App> apps34 = new HashSet<>();
          HashSet<App> apps23 = new HashSet<>();
          HashSet<App> apps02 = new HashSet<>();

          String recentC = null;
          String reviewC= null;

          
          for(String url: hrefs){
              System.out.println(url+"\n");
              HashSet<String> reviews = new HashSet<>();
              HashSet<String> recentChanges = new HashSet<>();
              int ratings5 = 0;
              int ratings4 = 0;
              detailDoc = Jsoup.connect(url).timeout(0).get();
              name = detailDoc.select("[class=\"id-app-title\"]").text();
              if (!detailDoc.select("[class=\"rating-count\"]").text().equals(""))
                numRatings = Integer.parseInt(detailDoc.select("[class=\"rating-count\"]").text()
                        .replace(".", "").replace(",", ""));
              if (!detailDoc.select("[class=\"score\"]").text().equals(""))
                raitingProm = Double.parseDouble(detailDoc.select("[class=\"score\"]").text().replace(",", "."));
              description = detailDoc.select("[itemprop='description']").text();
              
              Elements recentChangeE = detailDoc.select("[class=\"recent-change\"]");
              for (Element element: recentChangeE){
              recentC = element.text();
              recentChanges.add(recentC);
              }
              
              Elements reviewsE = detailDoc.select("[class=\"review-text\"]");
              for (Element element: reviewsE){
              reviewC = element.text();
              reviews.add(reviewC);
              }
              if (!detailDoc.select("[class=\"rating-bar-container five\"]").text().equals(""))
                ratings5 = Integer.parseInt(detailDoc.select("[class=\"rating-bar-container five\"]").text().replace(",", "").split(" ")[1]);
              if (!detailDoc.select("[class=\"rating-bar-container four\"]").text().equals(""))
                ratings4 = Integer.parseInt(detailDoc.select("[class=\"rating-bar-container four\"]").text().replace(",", "").split(" ")[1]);
              
              
              App app = new App(name, url, description, numRatings, raitingProm,
                      recentChanges, reviews, ratings5, ratings4);
              apps.add(app);
              
              
              //Analisis de texto
              //División de apps por rating
              
              if (raitingProm > 4){
                  apps45.add(app);
              }else if(raitingProm > 3){
                  apps34.add(app);                  
              }else if(raitingProm > 2){
                  apps23.add(app);                  
              }else{
                  apps02.add(app);                  
              }
              
              System.out.println(app.toString()+"\n");
          }       
          
          AppFile file = new AppFile("PlayStore"+categoria_sel+top_sel, apps,
                  apps45, apps34, apps23, apps02);
          
          System.exit(0);
            
          } catch (IOException ex) {
               Logger.getLogger(CSApp.class.getName()).log(Level.SEVERE,
                        null, ex);
         }
           
                    
    }

	

}
