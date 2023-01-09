package analyses;

import java.util.List;

public class AnalyseSyntaxique {

	private int pos;
	private List<Token> tokens;
	private int niveauIndentation;

	/*
	effectue l'analyse syntaxique à partir de la liste de tokens
	et retourne le noeud racine de l'arbre syntaxique abstrait
	 */
	public String analyse(List<Token> tokens) throws Exception {
		this.pos = 0;
		this.tokens = tokens;
		this.niveauIndentation = 0;
		String res = S();
		if (pos != tokens.size()-1) {
			System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
			throw new IncompleteParsingException();
		}
		return res;
	}



	private String S() throws UnexpectedTokenException {
		if (getTypeDeToken() == TypeDeToken.Fin) {
			printToken("Fin");
			return "Fin \n";
		}
		if (getTypeDeToken() == TypeDeToken.Couloir){
			niveauIndentation++;
			printToken("Couloir");
			niveauIndentation++;
			String c = C1();
			niveauIndentation--;
			return "Couloir \n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.Trappe){
			niveauIndentation++;
			printToken("Trappe");
			niveauIndentation++;
			String c = T1();
			niveauIndentation--;
			return "Trappe \n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.Fantome){
			niveauIndentation++;
			printToken("Fantome");
			niveauIndentation++;
			String c = F1();
			niveauIndentation--;
			return "Fantome \n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.Commutateur){
			niveauIndentation++;
			printToken("Commutateur");
			niveauIndentation++;
			String c = K1();
			niveauIndentation--;
			return "Commutateur \n"+c;
		}
		throw new UnexpectedTokenException("Vous avez oublié de finir par Fin ou de commencer par Couloir, Trappe, Fantome ou Commutateur.");	
	}

// -------------------------Couloir-----------------------------------
	
	private String C1() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.end){
			printToken("end");
			niveauIndentation++;
			pos++;
			String c = S();
			niveauIndentation--;
			return "end\n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.ligne){
			printToken("ligne");
			niveauIndentation++;
			String c = C2();
			niveauIndentation--;
			return "ligne "+c;
		} 
		if (getTypeDeToken() == TypeDeToken.colonne){
			printToken("colonne");
			niveauIndentation++;
			String c = C2();
			niveauIndentation--;
			return "colonne "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe, apres un Couloir il devrait y avoir une ligne, une colonne ou un end.");
	}
	
	private String  C2() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.slash){
			printToken("/");
			niveauIndentation++;
			String c = C3();
			niveauIndentation--;
			return "/ "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe, apres une ligne ou une colonne il devrait y avoir une virgule.");
	}
	
	private String  C3() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = C4();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}
	
	private String  C4() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.slash){
			printToken("/");
			niveauIndentation++;
			String c = C5();
			niveauIndentation--;
			return "/ "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}
	
	private String  C5() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = C6();
			niveauIndentation--;
			return "( "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}
	
	private String  C6() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = C7();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}
	
	private String  C7() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = C8();
			niveauIndentation--;
			return "; "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}
	
	private String  C8()throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = C9();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}
	
	private String C9() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = C1();
			niveauIndentation--;
			return ")\n"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Couloir.");
	}

	// -------------------------Trappe-----------------------------------	
	
	
	private String  T1() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.end){
			printToken("end");
			niveauIndentation++;
			pos++;
			String c = S();
			niveauIndentation--;
			return "end\n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.entre){
			printToken("entre");
			niveauIndentation++;
			String c = T2();
			niveauIndentation--;
			return "entre "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T2() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = T3();
			niveauIndentation--;
			return "( "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T3() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = T4();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T4() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = T5();
			niveauIndentation--;
			return "; "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T5() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = T6();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T6() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = T13();
			niveauIndentation--;
			return ")"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	private String  T13() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.slash){
			printToken("/");
			niveauIndentation++;
			String c = T7();
			niveauIndentation--;
			return "/"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T7() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.sortie){
			printToken("sortie");
			niveauIndentation++;
			String c = T8();
			niveauIndentation--;
			return "sortie "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T8() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = T9();
			niveauIndentation--;
			return "( "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T9() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = T10();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T10() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = T11();
			niveauIndentation--;
			return "; "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String  T11() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = T12();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	private String T12() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = T1();
			niveauIndentation--;
			return ")\n"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Trappe.");
	}
	
	// -------------------------Fantome-----------------------------------	
	
	private String F1() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.end){
			printToken("end");
			niveauIndentation++;
			pos++;
			String c = S();
			niveauIndentation--;
			return "end\n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.spawn){
			printToken("spawn");
			niveauIndentation++;
			String c = F2();
			niveauIndentation--;
			return "spawn "+c;
		}throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F2() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = F3();
			niveauIndentation--;
			return "( "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F3() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = F4();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F4() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = F5();
			niveauIndentation--;
			return "; "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F5() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = F6();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F6() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = F7();
			niveauIndentation--;
			return ") "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F7() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.slash){
			printToken("/");
			niveauIndentation++;
			String c = F8();
			niveauIndentation--;
			return "/ "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F8() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.deplacement){
			printToken("deplacement");
			niveauIndentation++;
			String c = F9();
			niveauIndentation--;
			return "deplacement "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F9() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = F10();
			niveauIndentation--;
			return "("+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F10() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.gauche){
			printToken("gauche");
			niveauIndentation++;
			String c = F11();
			niveauIndentation--;
			return "gauche "+c;
		}
		if (getTypeDeToken() == TypeDeToken.droite){
			printToken("droite");
			niveauIndentation++;
			String c = F11();
			niveauIndentation--;
			return "droite "+c;
		}
		if (getTypeDeToken() == TypeDeToken.haut){
			printToken("haut");
			niveauIndentation++;
			String c = F11();
			niveauIndentation--;
			return "haut "+c;
		}
		if (getTypeDeToken() == TypeDeToken.bas){
			printToken("bas");
			niveauIndentation++;
			String c = F11();
			niveauIndentation--;
			return "bas "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F11() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.equal){
			printToken("=");
			niveauIndentation++;
			String c = F12();
			niveauIndentation--;
			return "= "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F12() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = F13();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
	private String F13() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = F10();
			niveauIndentation--;
			return ";"+c;
		}
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = F1();
			niveauIndentation--;
			return ")\n"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Fantome.");
	}
	
// -------------------------K-----------------------------------
	private String K1() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.end){
			printToken("end");
			niveauIndentation++;
			pos++;
			String c = S();
			niveauIndentation--;
			return "end\n"+c;
		}
		if (getTypeDeToken() == TypeDeToken.levier){
			printToken("levier");
			niveauIndentation++;
			String c = K2();
			niveauIndentation--;
			return "levier "+c;
		}throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K2() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = K3();
			niveauIndentation--;
			return "( "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K3() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = K4();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K4() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = K5();
			niveauIndentation--;
			return "; "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K5() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = K6();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K6() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = K13();
			niveauIndentation--;
			return ")"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	private String K13() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.slash){
			printToken("/");
			niveauIndentation++;
			String c = K7();
			niveauIndentation--;
			return "/"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K7() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.porte){
			printToken("porte");
			niveauIndentation++;
			String c = K8();
			niveauIndentation--;
			return "porte "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K8() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.leftPar){
			printToken("(");
			niveauIndentation++;
			String c = K9();
			niveauIndentation--;
			return "( "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K9() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = K10();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K10() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.semiColon){
			printToken(";");
			niveauIndentation++;
			String c = K11();
			niveauIndentation--;
			return "; "+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K11() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.intVal){
			String t = getValeurToken();
			printToken(t);
			niveauIndentation++;
			String c = K12();
			niveauIndentation--;
			return t+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}
	
	private String K12() throws UnexpectedTokenException {
		pos++;
		if (getTypeDeToken() == TypeDeToken.rightPar){
			printToken(")");
			niveauIndentation++;
			String c = K1();
			niveauIndentation--;
			return ")\n"+c;
		}
		throw new UnexpectedTokenException("Erreur de syntaxe dans Commutateur.");
	}

	private boolean finAtteinte() {
		return pos >= tokens.size();
	}

	/*
	 * Retourne la classe du prochain token à lire
	 * SANS AVANCER au token suivant
	 */
	private TypeDeToken getTypeDeToken() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			return tokens.get(pos).getTypeDeToken();
		}
	}
	private String getValeurToken() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			return tokens.get(pos).getValeur();
		}
	}

	/*
	 * Retourne le prochain token à lire
	 * ET AVANCE au token suivant
	 */
	private Token lireToken() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			Token t = tokens.get(pos);
			pos++;
			return t;
		}
	}

	/*
	 * Affiche le token t avec un certain niveau d'identation
	 */
	private void printToken(String s) {
		for(int i=0;i<niveauIndentation;i++) {
			System.out.print("      ");
		}
		System.out.println(s);
	}

}