
import java.io.*;
import java.util.*;

public class GrammarSolver {

    Map<String,List<String>> grammarMap = new TreeMap<>();
    List<String> nonterminals = new ArrayList<>();
    List<String> terminals = new ArrayList<>();
    List<String> original;


    public GrammarSolver(List<String> RULES) {
        if(RULES.size() == 0 || RULES == null)
            throw new IllegalArgumentException();

        int index;
        String symbol;

        for(int i = 0; i< RULES.size(); i++) {
            index = 0;
            symbol = "";

            while(RULES.get(i).charAt(index) != ':') {
                symbol += RULES.get(i).charAt(index);
                index++;
            }
            nonterminals.add(symbol.trim());
        }


        original = RULES;
        String line;
        int indx;

        for(int i = 0 ; i < original.size(); i++) {
            indx = 0;
            line = original.get(i);

            while(line.charAt(indx) != '=') {
                indx++;
            }
            terminals.add(line.substring(indx + 1));
        }

        convertGrammarMap();
    }


    public void convertGrammarMap() {
        String rule;

        for(int i = 0; i < nonterminals.size(); i++) {

            String symbol = nonterminals.get(i);
            String[] lineArr = terminals.get(i).split("[|]");

            String line = key(lineArr);


            for(int j = 0; j < lineArr.length; j++) {
                rule = lineArr[j].trim();

                if(grammarMap.containsKey(symbol))
                    grammarMap.get(symbol).add(rule);

                else {
                    List<String> rules = new ArrayList<>();
                    rules.add(rule);
                    grammarMap.put(symbol, rules);
                }
            }
        }
        System.out.println("MAP!!!!! " + grammarMap);
    }


    public String key (String[] line) {
        String returnString = "";
        for(int j = 0; j < line.length; j++)
            returnString+=line[j]+" ";
        return returnString.trim();
    }


    public boolean contains(String Symbol) {
        if(Symbol.length() == 0 || Symbol == null)
            throw new IllegalArgumentException("Not a proper symbol");
        return nonterminals.contains(Symbol);
    }


    public Set <String> getSymbols() {
        Set<String> nonTerminalSet = new TreeSet<>(nonterminals);
        return nonTerminalSet;
    }


    public String generate (String Symbol) {
        if(Symbol == null || Symbol.length() == 0)
            throw new IllegalArgumentException("Symbol cannot be null or empty!!");

        if(!grammarMap.containsKey(Symbol))
            return Symbol;

        Random rand = new Random();
        int size = grammarMap.get(Symbol).size();

        String rule = grammarMap.get(Symbol).get(rand.nextInt(size)+0);

        String[] rulesSplit = rule.split("[ \t]+");
        String sentence = "";

        for (int i = 0; i < rulesSplit.length; i++)
            sentence += " "+ generate(rulesSplit[i]) ;

        return sentence.trim();
    }




}