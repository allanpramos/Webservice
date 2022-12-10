import spark.Request;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.regex.Pattern;
import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace()); // print all exceptions
        staticFiles.location("/public");

        port(8081);
        get("/calcular/:n1/:op/:n2", (req, res) -> calculadora(req));
    }

    private static String calculadora(Request req) throws IOException {
        String n1 = req.params(":n1");
        String op = req.params(":op");
        String n2 = req.params(":n2");
        op = substiruirOperador(op);
        String expressaoChegada = n1+op+n2;

        if (verificaExpressao(expressaoChegada) >= 1) {
            return "ERRO! Expressão incorreta. Tente NOVAMENTE!!";
        }else if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals(":")){
            Socket sS = new Socket("127.0.0.1", 9999);
            InputStream iS = sS.getInputStream();
            OutputStream oS = sS.getOutputStream();

            String expressaoS = null;

            byte[] line = (n1+op+n2).getBytes();
            System.out.println("\nO que chegou aqui: " + n1 + op + n2);

            expressaoS = new String(line);
            oS.write(line);

            Arrays.fill(line, (byte) 0); //Zera todas as posições do vetor

            iS.read(line);
            String strS = new String(line);
            sS.close();

            System.out.println("Resposta: " + strS.trim());
            return "Resposta: " + expressaoS.trim() + " = " + strS.trim();

        }else{
            Socket sC = new Socket("127.0.0.1", 12345);
            InputStream iC = sC.getInputStream();
            OutputStream oC = sC.getOutputStream();
            if(op.equals("$")){op = "rad";}
            String expressaoC = null;


            byte[] line = new byte[10000];
            line = (n1+op+n2).getBytes();

            System.out.println("\nO que chegou aqui: " + n1 + op + n2);

            expressaoC = new String(line);
            oC.write(line);

            Arrays.fill(line, (byte) 0); //Zera todas as posições do vetor

            iC.read(line);

            String strC = new String(line);
            sC.close();

            System.out.println("Resposta: " + strC.trim());
            return "Resposta: " + expressaoC.trim() + " = " + strC.trim();

        }
    }

    public static int verificaExpressao(String expressao) {
        int verifica = 0;
        int qtdOperadores = 0;
        String[] separaSrting = expressao.split("(?!^)");
        int tamanhoExpresao = separaSrting.length;

        for(int i = 0; i < tamanhoExpresao; ++i) {
            if (Pattern.matches("[^0-9+*:%^$-]", separaSrting[i])) {
                ++verifica;
            }

            if (Pattern.matches("[+*:%^$-]", separaSrting[i])) {
                ++qtdOperadores;
            }
        }

        if (qtdOperadores == 0 || qtdOperadores >= 2) {
            ++verifica;
        }

        if (Pattern.matches("[+*:%^$-]", separaSrting[tamanhoExpresao - 1]) || Pattern.matches("[+*:%^$-]", separaSrting[0])) {
            ++verifica;
        }

        return verifica;
    }

    public static String substiruirOperador(String operador) {
        if(operador.equals("por")){
            operador = "%";
        }
        if(operador.equals("pot")){
            operador = "^";
        }
        if(operador.equals("rad")){
            operador = "$";
        }
        return operador;
    }

}
