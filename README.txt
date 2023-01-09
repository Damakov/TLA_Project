PROJET UNIVERSITAIRE LICENCE 3EME ANNÉE - THEORIE DES LANGAGES ET AUTOMATES - Professeur Mr.Bourreau

~ Le jeu a été créé par Mr.Bourreau, notre projet étant de créer notre propre langage permettant de créer des niveaux de ce jeu ~

Membres du groupe :
Sean Fauvel
Hreshvik Sewraj
Martin Guillon
Antoine Coestesquis



Pour la création de nouveaux niveaux, on doit stocker le fichier texte dans le dossier de ressources où se trouve les images du jeu.
Afin de creer le niveau notre code va lister les fichiers textes présents dans ressources et faire en sorte d'appliquer les analyses sur chacun des fichiers.
Si analyses positives, alors on va ensuite appliquer la fonction créationNiveau qui permet de transformer le fichier texte en class Java afin de paramétrer un niveau en Java qui va fonctionner comme les anciens niveaux.

Ce qu'on a validé :

- Lecture et sauvegarde du fichier texte d'un niveau que le joueur a écrit avec notre langage.
- Analyse Lexicale et Syntaxique qui doivent être validés pour pouvoir continuer.
- Creation du niveau qui permet de reprendre les éléments de la lecture du fichier pour 
pouvoir écrire automatiquement un fichier java permettant ainsi de faire fonctionner le niveau.
- Main qui permet de relier tout ceci et faire fonctionner le fichier java en faisant appel aux fichiers du jeu de Mr Bourreau.

-> N'importe quel niveau peut maintenant être créé via notre langage.



Ce que nous n'avons pas fini :

- Sortie du niveau pas réalisée (elle sera en bas a droite d'office)
- Colonne ne fonctionne pas : seulement possible d'enlever des murs (des #) par LIGNE donc ligne/2/(5;10) soit à la ligne 2 on enlève du 5ème # compris au 10ème # compris pour mettre des vides.
- Pas de sens exigé pour rentrer dans la trappe, mais il faut aller à droite pour se téléporter.
- Quand nous avions avancé au fur et à mesure, on s'est rendu compte que certaines choses pouvaient être plus optimisé et facile en les changeant.
Par exemple, on a remplacé les "," par des "/" car pour l'exemple : [levier(5;14)/porte(1;3), levier(11;6)/porte(10;13)]
c'est plus simple pour split avec des "/" chaque élément et les arrayLists séparent automatiquement par des "," entre chaque.
Mais de ce fait il fallait revenir sur la table syntaxique et les analyses.


Travail dérivé d'un projet de Maxime Bourreau, réalisé dans le cadre d'un module de théorie des langages, à l'université de Tours. Les fichiers
d'images proviennent du site www.kenney.nl.
