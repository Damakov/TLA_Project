package analyses;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLexicale {

	/*
	Table de transition de l'analyse lexicale
	 */
	private static Integer TRANSITIONS[][] = {
			//           espace   ,    (    )    ;    =  chiffre  lettre
			/*  0 */    {	  0, 101, 102, 103, 104, 105,    1,    2},
			/*  1 */    {	106, 106, 106, 106, 106, null,    1, null}, //Si c'est un chiffre
			/*  2 */    {	107, 107, 107, null, null, 107, null,    2} //Si c'est une lettre

			// 101 acceptation d'un ,
			// 102 acceptation d'un (
			// 103 acceptation d'un )
			// 104 acceptation d'un ;
			// 105 acceptation d'un =
			// 106 acceptation d'un entier                   (retourArriere)
			// 107 acceptation d'un identifiant ou mot clé   (retourArriere)

	};

	private String entree;
	private int pos;

	private static final int ETAT_INITIAL = 0;

	/*
	effectue l'analyse lexicale et retourne une liste de Token
	 */
	public List<Token> analyse(String entree) throws Exception {

		this.entree=entree;
		pos = 0;

		List<Token> tokens = new ArrayList<>();
		/* copie des symboles en entrée
		- permet de distinguer les mots-clés des identifiants
		- permet de conserver une copie des valeurs particulières des tokens de type ident et intval
		 */
		String buf = "";

		Integer etat = ETAT_INITIAL;

		Character c;
		do {
			c = lireCaractere();
			Integer e = TRANSITIONS[etat][indiceSymbole(c)];
			if (e == null) {
				System.out.println("pas de transition depuis état " + etat + " avec symbole " + c);
				throw new LexicalErrorException("pas de transition depuis état " + etat + " avec symbole " + c);
			}
			// cas particulier lorsqu'un état d'acceptation est atteint
			if (e >= 100) {
				if (e == 101) {
					tokens.add(new Token(TypeDeToken.comma));
				} else if (e == 102) {
					tokens.add(new Token(TypeDeToken.leftPar));
				} else if (e == 103) {
					tokens.add(new Token(TypeDeToken.rightPar));
				} else if (e == 104) {
					tokens.add(new Token(TypeDeToken.semiColon));
				} else if (e == 105) {
					tokens.add(new Token(TypeDeToken.equal));
				} else if (e == 106) {
					tokens.add(new Token(TypeDeToken.intVal, buf));
					retourArriere();
				} else if (e == 107) {
					//Maintenant qu'on sait que c'est un texte, regarde tout les Tokens de texte pour mettre le bon
					
					switch(buf) {
					case "Fin":
					case "fin":
						tokens.add(new Token(TypeDeToken.Fin));
						retourArriere();
						break;
					case "Couloir":
					case "Couloirs":
					case "couloir":
					case "couloirs":
					case "c":
					case "C":
						tokens.add(new Token(TypeDeToken.Couloir));
						retourArriere();
						break;
					case "Trappe":
					case "Trappes":
					case "trappe":
					case "trappes":
					case "T":
					case "t":
						tokens.add(new Token(TypeDeToken.Trappe));
						retourArriere();
						break;
					case "Fantome":
					case "Fantomes":
					case "fantomes":
					case "fantome":
					case "Fantôme":
					case "Fantômes":
					case "fantômes":
					case "fantôme":
					case "F":
					case "f":
						tokens.add(new Token(TypeDeToken.Fantome));
						retourArriere();
						break;
					case "Commutateur":
					case "Commutateurs":
					case "commutateurs":
					case "commutateur":
						tokens.add(new Token(TypeDeToken.Commutateur));
						retourArriere();
						break;
					case "end":
					case "End":
					case "e":
						tokens.add(new Token(TypeDeToken.end));
						retourArriere();
						break;
					case "ligne":
					case "lignes":
					case "Lignes":
					case "Ligne":
					case "L":
					case "l":
						tokens.add(new Token(TypeDeToken.ligne));
						retourArriere();
						break;
					case "colonne":
					case "colonnes":
					case "Colonne":
					case "Colonnes":
						tokens.add(new Token(TypeDeToken.colonne));
						retourArriere();
						break;
					case "entre":
					case "Entre":
						tokens.add(new Token(TypeDeToken.entre));
						retourArriere();
						break;
					case "sortie":
					case "sorties":
					case "Sorties":
					case "Sortie":
						tokens.add(new Token(TypeDeToken.sortie));
						retourArriere();
						break;
					case "spawn":
					case "Spawn":
					case "S":
					case "s":
						tokens.add(new Token(TypeDeToken.spawn));
						retourArriere();
						break;
					case "deplacement":
					case "deplacements":
					case "Deplacement":
					case "Deplacements":
						tokens.add(new Token(TypeDeToken.deplacement));
						retourArriere();
						break;
					case "gauche":
					case "Gauche":
					case "G":
					case "g":
						tokens.add(new Token(TypeDeToken.gauche));
						retourArriere();
						break;
					case "droite":
					case "D":
					case "d":
					case "Droite":
						tokens.add(new Token(TypeDeToken.droite));
						retourArriere();
						break;
					case "haut":
					case "Haut":
					case "H":
					case "h":
						tokens.add(new Token(TypeDeToken.haut));
						retourArriere();
						break;
					case "bas":
					case "B":
					case "b":
					case "Bas":
						tokens.add(new Token(TypeDeToken.bas));
						retourArriere();
						break;
					case "levier":
					case "Levier":
					case "leviers":
					case "Leviers":
						tokens.add(new Token(TypeDeToken.levier));
						retourArriere();
						break;
					case "porte":
					case "portes":
					case "Porte":
					case "Portes":
					case "P":
					case "p":
						tokens.add(new Token(TypeDeToken.porte));
						retourArriere();
						break;
					default:
						System.out.println("Le texte '"+buf+"' n'est pas reconnu dans ceux possible, merci de vérifier avant de réessayer.");
						throw new IllegalCharacterException(c.toString());
					}
				}
				// un état d'acceptation ayant été atteint, retourne à l'état 0
				etat = 0;
				// reinitialise buf
				buf = "";
			} else {
				// enregistre le nouvel état
				etat = e;
				// ajoute le symbole qui vient d'être examiné à buf
				// sauf s'il s'agit un espace ou assimilé
				if (etat>0) buf = buf + c;
			}

		} while (c != null);

		return tokens;
	}

	private Character lireCaractere() {
		Character c;
		try {
			c = entree.charAt(pos);
			pos = pos + 1;
		} catch (StringIndexOutOfBoundsException ex) {
			c = null;
		}
		return c;
	}

	private void retourArriere() {
		pos = pos - 1;
	}

	/*
	Pour chaque symbole terminal acceptable en entrée de l'analyse syntaxique
	retourne un indice identifiant soit un symbole, soit une classe de symbole :
	 */
	private static int indiceSymbole(Character c) throws IllegalCharacterException {
		if (c == null) return 0;
		if (Character.isWhitespace(c)) return 0;
		if (c == ',') return 1;
		if (c == '(') return 2;
		if (c == ')') return 3;
		if (c == ';') return 4;
		if (c == '=') return 5;
		if (Character.isDigit(c)) return 6;
		if (Character.isLetter(c)) return 7;
		System.out.println("Symbole inconnu : " + c);
		throw new IllegalCharacterException(c.toString());
	}

}
