package tla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import analyses.AnalyseLexicale;
import analyses.AnalyseSyntaxique;
import analyses.Token;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public String getClass(File file) {
    	return removeExtension(file.getName());
    }
    public static String removeExtension(String file){
    	  return file.replaceFirst("[.][^.]+$", "");
    	}
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	
    	GridPane menuPane = new GridPane();
    	ImageView imageView = new ImageView(LibrairieImages.imgJoueurGrand);
    	menuPane.add(imageView, 1, 0, 1, 5);
    	
    	Scene scene = new Scene(menuPane);
        BorderPane borderPane = new BorderPane();
        Plateau plateau = new Plateau(borderPane);
    
        
    //Recuperation de tout les fichiers
		String path = "src/main/resources/level";
		File folder = new File(path);
			
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		if(listOfFiles.length<1) {
			menuPane.add(new Label("Vous n'avez aucun fichier texte"),0,0);
		}
		//Le nom de tout les fichiers txt dans le repertoire
		ArrayList<String> txtFiles=new ArrayList<>();
		for(File file:listOfFiles) {
			if(file.isFile()) {
				txtFiles.add(file.getName());
			}
			String c=getClass(file);System.out.println(c);
		}
		
		int nbFichier=0;
		//Recupere en String le contenu du fichier et faire les analyses
		for(String name:txtFiles) {
			//nom du fichier sans .txt
			String[] nom = name.split("\\.");
			String nom1=nom[0];
			//File file = new File("./src/main/java/tla/"+name+".java");
			
			nbFichier++;
			System.out.println("\n\n\n\nFichier numero "+nbFichier+" au nom "+name);
			StringBuilder sb = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new FileReader(path+"/"+name))) {
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
			        sb.append(System.lineSeparator());
			      }
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			String text = sb.toString();
			
			//fait les analyses et envoit le resultat dans la lecture de fichier pour obtenir des Listes des éléments
			ArrayList<ArrayList<String>>a=LireFichierTxt.lireFichier(testAnalyseSyntaxique(text));
			//pour afficher les listes pour voir le résultat
			//for(ArrayList<String> b:a) {for(String c:b) {System.out.println(c);}}
			creationNiveau.creerNiveau(a,nom1);
			
			Button btnNiveau=new Button(nom1);
			menuPane.add(btnNiveau, 0, nbFichier);
			try {
			btnNiveau.setOnAction(event -> {
	            scene.setRoot(borderPane);
	            plateau.setNiveau(new Niveau1());
	            plateau.start();
	            primaryStage.sizeToScene();
	        });
		}catch(Exception e) {
			
		}
			}
		
       
		primaryStage.setScene(scene);
        primaryStage.show();


        // gestion du clavier

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Q) {
                // touche q : quitte le jeu et affiche le menu principal
                plateau.stop();
                scene.setRoot(menuPane);
                primaryStage.sizeToScene();
            }
            if (event.getCode() == KeyCode.R) {
                // touche r : redémarre le niveau en cours
                plateau.start();
            }

            if (event.getCode() == KeyCode.LEFT) {
                plateau.deplGauche();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                plateau.deplDroite();
            }
            if (event.getCode() == KeyCode.UP) {
                plateau.deplHaut();
            }
            if (event.getCode() == KeyCode.DOWN) {
                plateau.deplBas();
            }
        });
    }
    
    
    public static String testAnalyseSyntaxique(String entree) {
		System.out.println("Test analyse syntaxique qui comprend l'analyse lexicale: \n");
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			System.out.println("Pas d'erreur lors de l'analyse lexicale \n");
			String res = new AnalyseSyntaxique().analyse(tokens);
			//System.out.println(entree+"\n"+res);
			//System.out.println(entree + "\n" + res);//pour voir la tranformation entre le texte de base et en token
			System.out.println("Pas d'erreur lors de l'analyse syntaxique\n");
			return res;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return null;
	}
}