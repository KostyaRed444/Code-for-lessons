package interpreter;

import java.util.HashMap;

public class Interpreter implements NodeVisitor {
    private Parser parser;
    private static HashMap <String, Float> ID = new HashMap<>();
    public Interpreter(Parser parser){
        this.parser = parser;
    }

    @Override
    public void visit(Node node)throws Exception {
        Body body = (Body) node;
        boolean next = true;
        Node action = body.getNext();
        while(next!=false) {
            if (action.getClass() == Writer.class){
                visitWriter(action);
            }
            else if(action.getClass() == Body.class){
                visit(action);
            }
            else if(action.getClass() == End.class){
                return;
            }
            else
            {
                visitCalc(action);
            }
            action = body.getNext();
            next = action != null;
        }


    }

    public void visitWriter(Node node)throws Exception {
        Writer writer = (Writer) node;
        if (node.getClass().equals(Writer.class)){
            float val = visitCalc(writer.getExpr());
            ID.put(writer.getID().toString(), val);
        }
    }

    public float visitCalc(Node node) throws Exception {
        if (node.getClass().equals(BinOp.class)){
            return visitBinOp(node);
        }
        else if (node.getClass().equals(Number.class)){
            return visitNumber(node);
        }
        else if(node.getClass().equals(Variable.class))
        {
            if (!ID.containsKey(((Variable)node).getVariable().getValue().toString()))
            {
                throw new Exception("Unknown variable!");
            }
            return ID.get(((Variable)node).getVariable().getValue().toString());
        }
        else if (node.getClass().equals(UnaryOp.class)){
            return visitUnaryOp(node);
        }
        throw new Exception("Interpreter error!");
    }

    public float visitBinOp(Node node) throws Exception {
        BinOp binop = (BinOp) node;
        if (binop.getOp().getType().equals(TokenType.PLUS)){
            return visitCalc(binop.getLeft()) + visitCalc(binop.getRight());
        }
        else if (binop.getOp().getType().equals(TokenType.MINUS)){
            return visitCalc(binop.getLeft()) - visitCalc(binop.getRight());
        }
        else if (binop.getOp().getType().equals(TokenType.DIV)){
            return visitCalc(binop.getLeft()) / visitCalc(binop.getRight());
        }
        else if (binop.getOp().getType().equals(TokenType.MUL)){
            return visitCalc(binop.getLeft()) * visitCalc(binop.getRight());
        }
        throw new Exception("BinOp error");
    }

    public float visitNumber(Node node){
        Number number = (Number) node;
        return Float.parseFloat(number.getToken().getValue());
    }

    public float visitUnaryOp(Node node) throws Exception {
        UnaryOp op = (UnaryOp) node;
        if (op.getToken().getType().equals(TokenType.PLUS)){
            return +visitCalc(op.getExpr());
        }
        else if (op.getToken().getType().equals(TokenType.MINUS)){
            return -visitCalc(op.getExpr());
        }
        throw new Exception("UnaryOp error!");
    }

    public void interpret() throws Exception {
        Node tree = parser.parse();
        visit(tree);
    }

    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer("BEGIN\nBEGIN\n\n v =" + " 6+6; END;\nd = 2+3; a = v+5; END.");
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        interpreter.interpret();

        System.out.println("v= "+ ID.get("v"));
        System.out.println("d= "+ ID.get("d"));
        System.out.println("a= "+ ID.get("a"));

    }


}
