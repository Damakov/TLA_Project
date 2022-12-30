package tla;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	// ça permet de lister les fichiers contenu dans le ressources level et ensuite on boucle sur ces fichiers là
    	// le but c'est ensuite d'appliquer le lire fichier text sur tous les fichiers et de verifier que chaque tableau qui en sort est conforme avec l'anlyse lexical et syntaxique
    	
    	File dir = new File("src/main/ressources/level");
    	File[] liste = dir.listFiles();
    	
    	int i=0;
    	for (File item : liste) {
    		if (item.isFile()) {
    			
    			// ici on doit appeller la fonctions lire fichier text
    			
    			
    			// puis les deux analyses 
    			
    			
    			// ensuite on fait en sorte de reproduire l'interface avec le nom de chaque fichier exemple:
    			
    			// si tout est validé on appel la fonction creationNiveau pour faire le niveau lié au tableau creer dans lire fichier text
    			
    			GridPane menuPane = new GridPane();
    	        Button btnNiveau1 = new Button(item.getName());
    	        menuPane.add(btnNiveau1, 0, i);
    			// ça va peut etre etre un probleme avec le nom du button mais à voir plus tard pas le probleme maintenant
    		}
    		i+=1;
    	}

        // fenêtre principale et panneau de menu

        GridPane menuPane = new GridPane();
        Button btnNiveau1 = new Button("niveau 1");
        menuPane.add(btnNiveau1, 0, 1);
        Button btnNiveau2 = new Button("niveau 2");
        menuPane.add(btnNiveau2, 0, 2);
        Button btnNiveau3 = new Button("niveau 3");
        menuPane.add(btnNiveau3, 0, 3);
        ImageView imageView = new ImageView(LibrairieImages.imgJoueurGrand);
        menuPane.add(imageView, 1, 0, 1, 5);

        Scene scene = new Scene(menuPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        // panneau racine du jeu

        BorderPane borderPane = new BorderPane();

        Plateau plateau = new Plateau(borderPane);

        btnNiveau1.setOnAction(event -> {
            // affiche le panneau racine du jeu (à la place du panneau de menu)
            scene.setRoot(borderPane);

            // affecte un object correspondant au niveau choisi
            plateau.setNiveau(new Niveau1());

            // démarre le jeu
            plateau.start();

            // ajuste la taille de la fenêtre
            primaryStage.sizeToScene();
        });

        btnNiveau2.setOnAction(event -> {
            scene.setRoot(borderPane);
            plateau.setNiveau(new Niveau2());
            plateau.start();
            primaryStage.sizeToScene();
        });

        btnNiveau3.setOnAction(event -> {
            scene.setRoot(borderPane);
            plateau.setNiveau(new Niveau3());
            plateau.start();
            primaryStage.sizeToScene();
        });

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
}
