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
	public Integer analyse(List<Token> tokens) throws Exception {
		this.pos = 0;
		this.tokens = tokens;
		this.niveauIndentation = 0;
		Integer res = S();
		if (pos != tokens.size()) {
			System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
			throw new IncompleteParsingException();
		}
		return res;
	}



	private Integer S() throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.Fin) {
			
			return 0;
		}
		if (getTypeDeToken() == TypeDeToken.Couloir){ 
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer i = Integer.valueOf(t.getValeur());
			return C0(i);
			} 
		if (getTypeDeToken() == TypeDeToken.Trappe){
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer i = Integer.valueOf(t.getValeur());
			return T0(i);
				} 
		if (getTypeDeToken() == TypeDeToken.Fantome){
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer i = Integer.valueOf(t.getValeur());
			return F0(i);
				}
		if (getTypeDeToken() == TypeDeToken.Commutateur) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer i = Integer.valueOf(t.getValeur());
			return K0(i); 

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}

// -------------------------Couloir-----------------------------------
	private Integer C0(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.ident) {
			Token t = lireToken();
			printToken("\n");
			niveauIndentation++;
			Integer c = C1(i);
			niveauIndentation--;
			return c;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C1(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.end||
				getTypeDeToken() == TypeDeToken.ligne ||
				getTypeDeToken() == TypeDeToken.colonne) {

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C2(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.comma) {

			Token t = lireToken();
			printToken(",");
			niveauIndentation++;
			Integer c = C3(i);
			niveauIndentation--;
			return c;


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C3(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());
			
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C4(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.comma) {
			Token t = lireToken();
			printToken(",");
			niveauIndentation++;
			Integer c = C5(i);
			niveauIndentation--;
			return c;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C5(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {
			Token t = lireToken();
			niveauIndentation++;
			Integer c = C6(i);
			niveauIndentation--;
			return c;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C6(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C7(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {
			Token t = lireToken();
			printToken(";");
			niveauIndentation++;
			Integer c = C8(i);
			niveauIndentation--;
			return c;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C8(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer C9(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.rightPar) {
			Token t = lireToken();
			printToken(")");
			niveauIndentation++;
			Integer c = C0(i);
			niveauIndentation--;
			return c;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}

	// -------------------------Trappe-----------------------------------	
	private Integer T0(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.ident) {
			Token t = lireToken();
			printToken("/n");
			niveauIndentation++;
			Integer T = T1(i);
			niveauIndentation--;
			return T;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T1(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.entre) {
   

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T2(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {
			Token t = lireToken();
			niveauIndentation++;
			Integer T = T2(i);
			niveauIndentation--;
			return T;
			
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	private Integer T3(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T4(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {
			Token t = lireToken();
			printToken(";");
			niveauIndentation++;
			Integer T = T5(i);
			niveauIndentation--;
			return T;
         
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T5(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T6(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.rightPar) {
			Token t = lireToken();
			printToken(")");
			niveauIndentation++;
			Integer T = T7(i);
			niveauIndentation--;
			return T;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T7(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.sortie) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T8(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {
			Token t = lireToken();
			
			niveauIndentation++;
			Integer T = T9(i);
			niveauIndentation--;
			return T;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T9(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T10(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {
			Token t = lireToken();
			printToken(";");
			niveauIndentation++;
			Integer T = T9(i);
			niveauIndentation--;
			return T;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T11(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer T12(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.rightPar) {
			Token t = lireToken();
			printToken(")");
			niveauIndentation++;
			Integer T = T0(i);
			niveauIndentation--;
			return T;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	// -------------------------Fantome-----------------------------------	
	
	private Integer F0(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.ident) {
			Token t = lireToken();
			printToken("/n");
			niveauIndentation++;
			Integer F = F1(1);
			niveauIndentation--;
			return F;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F1(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.end || getTypeDeToken() == TypeDeToken.spawn) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F2(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {
			Token t = lireToken();
			
			niveauIndentation++;
			Integer F = F3(i);
			niveauIndentation--;
			return F;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F3(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F4(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F5(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F6(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.rightPar) {
			Token t = lireToken();
			printToken(")");
			niveauIndentation++;
			Integer F = F7(i);
			niveauIndentation--;
			return F;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F7(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.comma) {
			Token t = lireToken();
			printToken(",");
			niveauIndentation++;
			Integer F = F8(i);
			niveauIndentation--;
			return F;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F8(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.deplacement) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F9(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F10(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.gauche || getTypeDeToken() == TypeDeToken.droite || getTypeDeToken() == TypeDeToken.haut || getTypeDeToken() == TypeDeToken.bas) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F11(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.equal) {
			Token t = lireToken();
			printToken("=");
			niveauIndentation++;
			Integer F = F3(i);
			niveauIndentation--;
			return F;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F12(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer F13(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {
			Token t = lireToken();
			printToken(";");
			niveauIndentation++;
			Integer F = F10(i);
			niveauIndentation--;
			return F;

		}
		
		if (getTypeDeToken() == TypeDeToken.rightPar) {
			Token t = lireToken();
			printToken(")");
			niveauIndentation++;
			Integer F = F0(i);
			niveauIndentation--;
			return F;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
// -------------------------K-----------------------------------
	private Integer K0(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.ident) {
			Token t = lireToken();
			printToken("/n");
			niveauIndentation++;
			Integer k = K0(i);
			niveauIndentation--;
			return k;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K1(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.end) {
            

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K2(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {
			Token t = lireToken();
			niveauIndentation++;
			Integer k = K3(i);
			niveauIndentation--;
			return k;


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K3(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K4(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {
			Token t = lireToken();
			printToken(";");
			niveauIndentation++;
			Integer k = K5(i);
			niveauIndentation--;
			return k;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K5(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K6(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.rightPar) {

 
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K7(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.porte) {
			Token t = lireToken();
			niveauIndentation++;
			Integer k = K7(i);
			niveauIndentation--;
			return k;
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K8(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.leftPar) {


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K9(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K10(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.semiColon) {
			Token t = lireToken();
			printToken(";");
			niveauIndentation++;
			Integer k = K11(i);
			niveauIndentation--;
			return k;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K11(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal) {
			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer j = Integer.valueOf(t.getValeur());


		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	private Integer K12(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.rightPar) {
			Token t = lireToken();
			printToken(")");
			niveauIndentation++;
			Integer k = K0(i);
			niveauIndentation--;
			return k;

		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}
	
	/*

	méthodes utilitaires

	 */

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