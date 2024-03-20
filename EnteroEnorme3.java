package enteroenorme3;

import java.util.Arrays;
import java.util.Scanner;

public class EnteroEnorme3 {
    private int[] digitos; 

    
    public EnteroEnorme3() {
        digitos = new int[40];
    }

   
    public static EnteroEnorme3 parse(String numero) {
        EnteroEnorme3 entero = new EnteroEnorme3();
        int longitud = numero.length();
        for (int i = 0; i < longitud; i++) {
            char digitoChar = numero.charAt(longitud - 1 - i);
            int digitoInt = Character.getNumericValue(digitoChar);
            entero.digitos[39 - i] = digitoInt;
        }
        return entero;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean empezar = false;
        for (int digito : digitos) {
            if (digito != 0 || empezar) {
                empezar = true;
                sb.append(digito);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    
    public EnteroEnorme3 sumar(EnteroEnorme3 otro) {
        EnteroEnorme3 resultado = new EnteroEnorme3();
        int acarreo = 0;
        for (int i = 39; i >= 0; i--) {
            int suma = digitos[i] + otro.digitos[i] + acarreo;
            resultado.digitos[i] = suma % 10;
            acarreo = suma / 10;
        }
        return resultado;
    }

    
    public EnteroEnorme3 restar(EnteroEnorme3 otro) {
        EnteroEnorme3 resultado = new EnteroEnorme3();
        int llevada = 0;
        for (int i = 39; i >= 0; i--) {
            int diferencia = digitos[i] - otro.digitos[i] - llevada;
            if (diferencia < 0) {
                diferencia += 10;
                llevada = 1;
            } else {
                llevada = 0;
            }
            resultado.digitos[i] = diferencia;
        }
        return resultado;
    }

   
    public boolean esIgualA(EnteroEnorme3 otro) {
        return Arrays.equals(this.digitos, otro.digitos);
    }

    
    public boolean noEsIgualA(EnteroEnorme3 otro) {
        return !esIgualA(otro);
    }

    
    public boolean esMayorQue(EnteroEnorme3 otro) {
        for (int i = 0; i < 40; i++) {
            if (this.digitos[i] > otro.digitos[i]) {
                return true;
            } else if (this.digitos[i] < otro.digitos[i]) {
                return false;
            }
        }
        return false; // Son iguales
    }

    
    public boolean esMenorQue(EnteroEnorme3 otro) {
        return !esMayorOIgualA(otro);
    }

    
    public boolean esMayorOIgualA(EnteroEnorme3 otro) {
        return esIgualA(otro) || esMayorQue(otro);
    }

    
    public boolean esCero() {
        for (int digito : digitos) {
            if (digito != 0) {
                return false;
            }
        }
        return true;
    }

    
    public void ingresarDigitos(Scanner scanner) {
        System.out.println("Por favor, ingresa los 40 dígitos del número uno por uno:");
        for (int i = 0; i < 40; i++) {
            System.out.print("Insertar dígito " + (i + 1) + ": ");
            int digito = scanner.nextInt();
            digitos[i] = digito;
        }
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnteroEnorme3 num1 = new EnteroEnorme3();
        num1.ingresarDigitos(scanner);
        System.out.println("El primer número ingresado es: " + num1);

        EnteroEnorme3 num2 = new EnteroEnorme3();
        num2.ingresarDigitos(scanner);
        System.out.println("El segundo número ingresado es: " + num2);

        
        System.out.println("¿num1 es igual a num2? " + num1.esIgualA(num2));
        System.out.println("¿num1 no es igual a num2? " + num1.noEsIgualA(num2));
        System.out.println("¿num1 es mayor que num2? " + num1.esMayorQue(num2));
        System.out.println("¿num1 es menor que num2? " + num1.esMenorQue(num2));
        System.out.println("¿num1 es mayor o igual que num2? " + num1.esMayorOIgualA(num2));

        
        System.out.println("¿num1 es cero? " + num1.esCero());
        System.out.println("¿num2 es cero? " + num2.esCero());
    }
}
