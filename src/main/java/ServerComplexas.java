import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerComplexas {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(12345);
            String str;
            Float num1, num2, resultado = null;

            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                byte[] line = new byte[10000];
                i.read(line);
                str = new String(line);
                str = str.replaceAll("\\s+", "");

                if (str.indexOf("%") >= 0) {
                    String[] str2 = str.split("%");
                    System.out.println("\nÉ porcentagem");
                    num1 = Float.parseFloat(str2[0]);
                    num2 = Float.parseFloat(str2[1]);
                    resultado = Porcentagem_numeros(num1, num2);
                }
                if (str.indexOf("^") >= 0) {
                    String[] str2 = str.split("\\^");
                    System.out.println("\nÉ potenciação");
                    num1 = Float.parseFloat(str2[0]);
                    num2 = Float.parseFloat(str2[1]);
                    resultado = Potenciacao_numeros(num1, num2);
                }
                if (str.indexOf("rad") >= 0) {
                    String[] str2 = str.split("rad");
                    System.out.println("\nÉ radiciação");
                    num1 = Float.parseFloat(str2[0]);
                    num2 = Float.parseFloat(str2[1]);
                    resultado = Radiciacao_numeros(num1, num2);
                }
                str = String.valueOf(resultado);
                o.write(str.getBytes());

                System.out.println("Resultado => " + str);

                str = new String(line);
                c.close();
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }
    public static float Porcentagem_numeros(float x, float y) {
        float porcentagem = y * (x/100);
        return porcentagem;
    }

    public static float Potenciacao_numeros(float x, float y) {
        return (float) Math.pow(x, y);
    }

    public static float Radiciacao_numeros(float x, float y) {
        return (float) Math.pow(x, (1/y));
    }
}