package analyses;

import java.util.List;

public class TP4 {

	public static void main(String[] args) {

		// testAnalyseSyntaxique("3*(10+1)");
		//testAnalyseSyntaxique("3+2*(1+2)");
		// testAnalyseSyntaxique("3 * 3 + 7 * 7 + 4");
		testAnalyseLexicale("Couloir ligne,3,(6;8) colonne,5,(8;9)end");
		//testAnalyseSyntaxique("Couloir ligne,3,(6;8) colonne,5,(8;9)end");

	}

	/*
	effectue l'analyse lexicale de la chaine entree,
	affiche la liste des tokens reconnus
	 */
	private static void testAnalyseLexicale(String entree) {
		System.out.println("test analyse lexicale"); 
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			for (Token t : tokens) {
				System.out.println(t);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
	}

	/*
	effectue l'analyse lexicale et syntaxique de la chaine entree
	 */
	private static void testAnalyseSyntaxique(String entree) {
		System.out.println("test analyse syntaxique");
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			Integer res = new AnalyseSyntaxique().analyse(tokens);
			System.out.println("La valeur de l'expression " + entree + " est " + res);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
	}

}
