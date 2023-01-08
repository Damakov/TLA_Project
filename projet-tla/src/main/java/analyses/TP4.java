package analyses;

import java.util.List;

public class TP4 {

	public static void main(String[] args) {
			LireFichierTxt a = new LireFichierTxt();
			String path = "C://Users/Sewraj/Documents/tlaTest.txt" ;
			ArrayList<ArrayList<String>> arr = a.lireFichier(path);
			String entree = concatToString(arr);
			System.out.println(entree);
			

	}

	/*
	effectue l'analyse lexicale de la chaine entree,
	affiche la liste des tokens reconnus
	 */
	public static String concatToString(ArrayList<ArrayList<String>> allElements) {
		ArrayList<String> concat = new ArrayList<String>();
		concat.addAll(allElements.get(0));
		concat.addAll(allElements.get(1));
		concat.addAll(allElements.get(2));
		concat.addAll(allElements.get(3));
		
		return concat.toString();
	      }
	
	
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
			String res = new AnalyseSyntaxique().analyse(tokens);
			System.out.println("La valeur de l'expression " + entree + " est " + res);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
	}

}
