//? A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class SyntaxChecker {
	private String exp;
	private String state;
	private Stack<Character> symbols;
	private final Set<BiSymbol> boundedSymbols = new HashSet<>(List.of(
			new BiSymbol('(', ')'),
			new BiSymbol('[', ']'),
			new BiSymbol('{', '}'),
			new BiSymbol('<', '>')));

	public SyntaxChecker() {
		symbols = new Stack<>();
	}

	public SyntaxChecker(String s) {
		this();
		setExpression(s);
		state = checkExpression() ? "valid" : "invalidad";
	}

	public void setExpression(String s) {
		symbols = new Stack<>();
		this.exp = s;
		exp.chars().forEach(c -> {
			Character ch = Character.valueOf((char) c);
			symbols.add(ch);
		});
	}

	public BiSymbol getBiSymbol(char contained) {
		for (BiSymbol bisym : boundedSymbols) {
			if (bisym.isContained(contained))
				return bisym;
		}
		return null;
	}

	public boolean checkExpression() {
		Stack<Character> checker = new Stack<>();
		for (Character c : symbols) {
			BiSymbol s = getBiSymbol(c);
			if (s == null)
				continue;
			else if (s.isOpen(c))
				checker.push(c);
			else if (checker.isEmpty())
				return false;
			else if (!s.isContained(checker.peek()))
				return false;
			else
				checker.pop();

		}
		return checker.isEmpty();
	}

	@Override
	public String toString() {
		return exp + " is " + state;
	}
}