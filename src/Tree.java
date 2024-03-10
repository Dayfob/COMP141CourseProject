
public class Tree {
    public Token token;
    public Tree leftSubtree;
    public Tree middleSubtree;
    public Tree rightSubtree;

    public Tree() {

    }

    public Tree(Token token, Tree leftSubtree, Tree middleSubtree, Tree rightSubtree) {
        this.token = token;
        this.leftSubtree = leftSubtree;
        this.middleSubtree = middleSubtree;
        this.rightSubtree = rightSubtree;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Tree getLeftSubtree() {
        return leftSubtree;
    }

    public void setLeftSubtree(Tree leftSubtree) {
        this.leftSubtree = leftSubtree;
    }

    public Tree getMiddleSubtree() {
        return middleSubtree;
    }

    public void setMiddleSubtree(Tree middleSubtree) {
        this.middleSubtree = middleSubtree;
    }

    public Tree getRightSubtree() {
        return rightSubtree;
    }

    public void setRightSubtree(Tree rightSubtree) {
        this.rightSubtree = rightSubtree;
    }
}