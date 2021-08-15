package bool;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BooleanEvaluator {

    public static boolean evaluate(BooleanNode expression) {
        // Return the expression evaluated
        return expression.getBooleanValue();
    }

    public static void recursion(BooleanNode node, StringBuilder sb) {
        if (node.getName().equals("OR") || node.getName().equals("NOT") || node.getName().equals("AND")) {
            sb.append("(");
            sb.append(node.getName()).append(" ");
            if (node.getLeft() != null) {
                recursion(node.getLeft(), sb);
            }
            if (node.getRight() != null) {
                recursion(node.getRight(), sb);
            }
            sb.append(")");
        } else {
            sb.append(node.getName()).append(" ");
            if (node.getLeft() != null) {
                recursion(node.getRight(), sb);
            }
            if (node.getRight() != null) {
                recursion(node.getLeft(), sb);
            }
        }
    }

    public static String prettyPrint(BooleanNode expression) {
        StringBuilder sb = new StringBuilder();
        recursion(expression, sb);
        sb.deleteCharAt(sb.lastIndexOf(" "));
        return sb.toString();
        // Pretty print the expression
        // StringBuilder sb = new StringBuilder();
        // Queue<BooleanNode> queue = new LinkedList<>();
        // queue.offer(expression);
        // while (!queue.isEmpty()) {
        //     int size = queue.size();
        //     while (size > 0) {
        //         BooleanNode node = queue.poll();
        //         sb.append(" ").append(node.getName()).append(" ");
        //         if (node.getLeft() != null) {
        //             queue.offer(node.getLeft());
        //         }
        //         if (node.getRight() != null) {
        //             queue.offer(node.getRight());
        //         }
        //         size--;
        //     }
        // }
    }

    public static void main(String[] args) {
        BooleanNode booleanNode = new BooleanAnd(new BooleanLeaf(true), new BooleanLeaf(false));
        System.out.println(BooleanEvaluator.evaluate(booleanNode));
        System.out.println(prettyPrint(booleanNode));

        booleanNode = new BooleanOr(new BooleanLeaf(false), new BooleanNot(new BooleanLeaf(false), null));
        System.out.println(BooleanEvaluator.evaluate(booleanNode));
        System.out.println(prettyPrint(booleanNode));

        booleanNode = new BooleanOr(
                new BooleanLeaf(true), new BooleanNot(
                new BooleanAnd(
                        new BooleanLeaf(false), new BooleanOr(
                        new BooleanLeaf(true), new BooleanLeaf(false))), null)
        );
        System.out.println(BooleanEvaluator.evaluate(booleanNode));
        System.out.println(prettyPrint(booleanNode));
    }


}