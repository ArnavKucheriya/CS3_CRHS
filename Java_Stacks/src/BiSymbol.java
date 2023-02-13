import java.util.*;

public class BiSymbol {
    private char open;
    private char closed;

    public BiSymbol(char open, char closed) {
        this.open = open;
        this.closed = closed;
    }

    public Character getOpen() {
        return open;
    }

    public Character getClosed() {
        return closed;
    }

    public char getOther(char contained) {
        if (!isContained(contained))
            throw new IllegalStateException("Character must be contained.");
        else if (contained == open)
            return closed;
        return open;
    }

    public boolean isContained(char character) {
        return character == open || character == closed;
    }

    public boolean isOpen(char contained) {
        return contained == open ? true : false;
    }

    public boolean isClosed(char contained) {
        return !isOpen(contained);
    }

    public Set<Character> getSymbols() {
        return new HashSet<Character>(List.of(
                open, closed
        ));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        BiSymbol other = (BiSymbol) obj;
        return this.getSymbols().equals(other.getSymbols());
    }

    @Override
    public int hashCode() {
        return open + closed;
    }
}