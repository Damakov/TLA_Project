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

	/*

	Traite la dérivation du symbole non-terminal S

	S -> A S'

	 */

	private Integer S() throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal /*||
				getTypeDeToken() == TypeDeToken.leftPar*/) {

			// production S -> A S'

			niveauIndentation++;
			Integer a = A();
			niveauIndentation--;
			return S_prime(a);
		}
		throw new UnexpectedTokenException("intVal ou ( attendu");
	}

	/*

	Traite la dérivation du symbole non-terminal S'

	S' -> + S | epsilon

	 */

	private Integer S_prime(Integer i) throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.intVal/*add*/) {

			// production S' -> + S

			Token t = lireToken();
			printToken("+");
			niveauIndentation++;
			Integer s = S();
			niveauIndentation--;
			return i + s;
		}

		if (getTypeDeToken() == TypeDeToken.parfermante/*rightPar*/ ||
				finAtteinte()) {

			// production S' -> epsilon

			return i;
		}

		throw new UnexpectedTokenException("+ ou ) attendu");
	}

	/*

	Traite la dérivation du symbole non-terminal A

	A -> ( S ) A' | intVal A'

	 */

	private Integer A() throws UnexpectedTokenException {

		if (getTypeDeToken() == TypeDeToken.parouvrante/*leftPar*/) {

			// production A -> ( S ) A'

			lireToken();
			niveauIndentation++;
			Integer s = S();
			niveauIndentation--;

			if (getTypeDeToken() == TypeDeToken.parfermante/*rightPar*/) {
				lireToken();
				return A_prime(s);
			}
			throw new UnexpectedTokenException(") attendu");
		}

		if (getTypeDeToken() == TypeDeToken.intVal) {

			// production A -> intVal A'

			Token t = lireToken();
			niveauIndentation++;
			printToken(t.getValeur());
			niveauIndentation--;
			Integer i = Integer.valueOf(t.getValeur());
			return A_prime(i);
		}

		throw new UnexpectedTokenException("intVal ou ( attendu");
	}

	/*

	Traite la dérivation du symbole non-terminal A'

	A' -> * A | epsilon

	 */

	private Integer A_prime(Integer i) throws UnexpectedTokenException {
		if (getTypeDeToken() == TypeDeToken.intVal/*multiply*/) {

			// production A' -> * A

			Token t = lireToken();
			printToken("*");
			niveauIndentation++;
			Integer a = A();
			niveauIndentation--;
			return i * a;
		}

		if (getTypeDeToken() == TypeDeToken.intVal/*add*/ ||
				getTypeDeToken() == TypeDeToken.intVal/*rightPar*/ ||
				finAtteinte()) {

			// production A' -> epsilon

			return i;
		}
		throw new UnexpectedTokenException("*, +, ) ou fin d'entrée attendu");

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
