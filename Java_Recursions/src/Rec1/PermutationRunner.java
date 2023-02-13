package Rec1;

public class PermutationRunner {
    public static void main(String[] args) {
        Permutation yes = new Permutation("ABC");
        yes.permutation();
        yes.set("abc");
        yes.permutation();
        yes.set("boat");
        yes.permutation();
        yes.set("it");
        yes.permutation();
    }
}
