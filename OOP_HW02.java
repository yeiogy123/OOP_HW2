import java.lang.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class OOP_HW02 {
    static Boolean KEYBOARD_DEBUG =  true;
    static Boolean FILE_DEBUG = false;
    static Scanner System_scan = new Scanner(System.in);
    public static int judgeCoe(String input)
    {
        for(int i = 0 ; i < input.length() ; i++)
            if(input.charAt(i)!= '\0' && input.charAt(i) != '.' && !Character.isDigit(input.charAt(i))
                    && input.charAt(i) != 'E' && input.charAt(i) != 'e' && input.charAt(i) != '-')
                return 0;
            else if(input.charAt(i)=='\0')
                return 1;
        return 1;

    }
    public static  int judgePow(String input)
    {
        for(int i = 0 ; i < input.length();i++)
            if(input.charAt(i)!='\0'&&!Character.isDigit(input.charAt(i)))
                return 0;
            else if(input.charAt(i)== '\0')
                return 1;
        return 1;
    }
    public static long highestPower(float coff[])
    {
        return (long)coff[0];
    }
    public static void  display(int  highestPow, float coff[])
    {
        for(int current = 1 ; current <= highestPow+1; current++)
        {
            if(coff[current]!=0)
            {
                System.out.print(coff[current]);
                for (int x = 1; x < current; x++)
                {
                    System.out.print("x");
                    if (x + 1 != current)
                        System.out.print("*");
                }
                if (current <= highestPow && coff[current+1]!=0)
                    System.out.print(" + ");
            }
        }
        System.out.print("\n");
    }
    public static void derivative(int highestPow, float coff[], float dev_coff[])
    {

        int dev_highestPow = highestPow;
        for(int i = highestPow ; i >= 1 ; i--)
        {
            dev_coff[dev_highestPow] = i * coff[i+1];
            dev_highestPow--;
        }

    }
    public static float compute(long highestPow, float coff[], float x)
    {
        float total = coff[1];
        for(int i = 2 ; i <= highestPow+1 ; i++)
        {
            float xTotal ;
            xTotal = (float)Math.pow(x,i-1);
            total = coff[i]*xTotal + total ;
        }
        return total ;
    }
    public static void search_file(FileReader FileInput) throws IOException {
        String buffer = null;
        FileInput = new FileReader("polydata.txt");
        BufferedReader bf = new BufferedReader(FileInput);
        while ((buffer = bf.readLine())!=null)
        {
            float target;
            System.out.print("please input x variable of the function : ");
            target =System_scan.nextFloat();
            System.out.print("your x equals to "+target+".\n");
            Boolean power = false;
            Boolean status = true;

            int currentIndex = 0;
            float coefficient;
            float [] poly = new float[7];
            float []dev_coff = new float[6];
            String [] tmp = buffer.split("\\s+");
            float temp;
	    while (currentIndex < tmp.length )
            {
                if (!power)
                {
                    if (judgePow(tmp[currentIndex])==0)
                    {
                        System.out.print("The highest number must be integer\n");
                        status = false;
                        break;
                    }
                    else
                    {
                        power = true;
                        temp = Integer.parseInt(tmp[currentIndex]);
                        poly[currentIndex++] = (float) temp;
                    }
                }
                else
                {
                    if (judgeCoe(tmp[currentIndex])==0)
                    {
                        status = false;
                        System.out.print("Each of the data in the file must be number.\n");
                        System.out.print("your input ["+currentIndex+"] is ["+tmp[currentIndex]+"].\n");
                        System.out.print("so please input the correct number in the file.\n");
                        break;
                    }
                    coefficient = Float.parseFloat(tmp[currentIndex]);
                    poly[currentIndex++] = coefficient;
                }
            }
            if (!status)
                continue;
            if ((currentIndex - 1) != (highestPower(poly) + 1))
            {
                System.out.print("The input of the coefficient are not sufficient\n");
                System.exit(2);
            } else {
                System.out.print("f(x) = ");
                display(((int)highestPower(poly)), poly);
                dev_coff[0] = (highestPower(poly) - 1);
                derivative(((int)highestPower(poly)), poly, dev_coff);
                System.out.print("f'(x) = ");
                display(((int)highestPower(dev_coff)), dev_coff);
                System.out.printf("f(%f) = %.3f\n",target,compute(highestPower(poly), poly, target));
                System.out.printf("f'(%f) = %.3f\n",target,compute(highestPower(dev_coff), dev_coff, target));
            }
        }
    }
    public static void Key_board() {
        {
            while (true) {

                String inputX ;
                System.out.print("please input x variable of the function or type [exit] to shut down \n");
                inputX = System_scan.next();
                if (judgePow(inputX)==0 && inputX.compareTo("exit") != 0) {
                    System.out.print("your x input is wrong,please input the number\n");
                    continue;
                } else if (inputX.compareTo("exit") == 0) {
                    System.out.print("program shut down.\n");
                    System.exit(3);

                } else
                    System.out.print("your x equals to "+inputX+".\n");
                float target = Float.parseFloat(inputX);
                System.out.print("please input the highest power in the function :\n");
                float coefficient;
                float []poly = new float[7];
                float []dev_coff = new float[6];
                String tmp;
                tmp = System_scan.next();
                int count = 0;
                Boolean status = true;
                if (judgePow(tmp) == 0) {
                    System.out.print("The highest number must be integer\n");
                    status = false;
                    continue;
                } else
                    poly[0] = Integer.parseInt(tmp);
                System.out.print("please input the each of the coefficient in the function\n");
                int currentIndex = 1;
                while (count <= poly[0]) {
                    String input;
                    input = System_scan.next();
                    if (judgeCoe(input) == 0) {
                        status = false;
                        System.out.print("Each of the data in the file must be number.\n");
                        System.out.print("your input ["+currentIndex+"] is ["+input+"].\n");
                        System.out.print("so please input the correct number in the file.\n");
                        break;
                    }
                    coefficient = Float.parseFloat(input);
                    poly[currentIndex] = coefficient;
                    currentIndex++;
                    count++;
                }
                if (status == false)
                    continue;
                if (currentIndex - 1 != highestPower(poly) + 1) {
                    System.out.print("The input of the coefficient are not sufficient\n");
                    System.exit(2);
                } else {
                    System.out.print("f(x) = ");
                    display((int)highestPower(poly), poly);
                    dev_coff[0] = highestPower(poly) - 1;
                    derivative((int)highestPower(poly), poly, dev_coff);
                    System.out.print("f'(x) = ");
                    display((int)highestPower(dev_coff), dev_coff);
                    float result = compute((highestPower(poly)), poly, target);
                    System.out.printf("f(%f) = %.3f\n",target,result);
                    System.out.printf("f'(%f) = %.3f\n",target,compute(highestPower(dev_coff), dev_coff, target));
                }
            }
        }
    }
    public static void main(String argv[])throws IOException{
        String FileNameIn = "polydata.txt";
        FileReader FileInput = new FileReader(FileNameIn);
        int num;
        Boolean mode;
        System.out.print("please input 1 which the data scan by keyboard or 0 which"+
                "scan the data from the file to run the program : ");
        num = System_scan.nextInt();
        if (num == 1)
            mode = KEYBOARD_DEBUG;
        else
            mode = FILE_DEBUG;
        if (mode) {
            Key_board();
        } else {
                search_file(FileInput);
                System.out.print("the program is completed. \n");
        }
    }
}
