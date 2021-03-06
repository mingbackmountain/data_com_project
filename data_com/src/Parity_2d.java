//Thanakorn Pasangthien 6088109
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Parity_2d {
	public static void main(String[]args)
    {
        System.out.println("---------- 2D Parity Check----------");
        System.out.println("Sender");
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of block: ");
        int n = sc.nextInt();
        String data = sc.nextLine();
        System.out.print("Input length of each block: ");
        int length = sc.nextInt();
        data = sc.nextLine();
        String[] input = new String[n];
        int count = 0;
        while(count < n)
        {
            System.out.print("Block "+(count+1)+": ");
            input[count] = sc.nextLine();
            if(input[count].length() != length)
            {
               System.out.println("Wrong input!! you must input "+length+" bits");
               System.exit(0);
            }
            count++;
        }
        
        String sender_end[] = parity_2d_gen(input);
        
       
        System.out.println();
        System.out.println("CHECK DATA");
        System.out.println("Received codeword: ");
        String[] reciver_end = new String[n];
        count = 0;
        while(count < n)
        {
            System.out.print("Block "+(count+1)+": ");
            reciver_end[count] = sc.nextLine();
            if(reciver_end[count].length() != length+1)
            {
               System.out.println("Wrong input!! you must input "+(length+1)+" bits");
               System.exit(0);
            }
            reciver_end[count] = reciver_end[count].substring(0,reciver_end[count].length()-1);
            count++;
        }
        parity_2d_checker(sender_end,reciver_end);
    }
    
    public static String[] parity_2d_gen(String[] dataWord)
    {
        int count = 0;
        char parity_rowbit[] = new char[dataWord.length];
        while(count<dataWord.length)
        {
        	parity_rowbit[count] = dataWord[count].charAt(0);
            for(int i = 1 ; i < dataWord[count].length() ; i++)
            {
                if(parity_rowbit[count] == dataWord[count].charAt(i))
                {
                	parity_rowbit[count] = '0';
                }
                else
                {
                	parity_rowbit[count] = '1';
                }
            }
            dataWord[count] += parity_rowbit[count];
            count++;
        }
        
        System.out.print("Row parities: ");
        String rowbit = "";
        for(int i = 0 ; i<parity_rowbit.length ; i++)
        {
            rowbit += parity_rowbit[i];
            System.out.print(parity_rowbit[i]);
        }
        System.out.println();
        
        count = 0;
        char parity_rowbit_col[] = new char[dataWord[0].length()];
        String colbit = "";
        while(count<dataWord[0].length())
        {
        	parity_rowbit_col[count] = dataWord[0].charAt(count);
            for(int i = 1 ; i < dataWord.length ; i++)
            {
                //System.out.println(dataWord[i].charAt(count));
                if(parity_rowbit_col[count] == dataWord[i].charAt(count))
                {
                	parity_rowbit_col[count] = '0';
                }
                else
                {
                	parity_rowbit_col[count] = '1';
                }
            }
            count++;
        }
        
        System.out.print("Column parities: ");
        for(int i = 0 ; i<parity_rowbit_col.length ; i++)
        {
            colbit += parity_rowbit_col[i];
            System.out.print(parity_rowbit_col[i]);
        }
        System.out.println();
        
        String parity_code[] = new String[dataWord.length+1];
        String singleword = "";
        
        for(int i = 0 ; i<parity_code.length ; i++)
        {
            if( i == dataWord.length)
            {
            	parity_code[i] = colbit;
            }
            else
            {
            	parity_code[i] = dataWord[i]; 
            }
        }
        
        System.out.print("Codeword:\n");
        for(int i=0; i<parity_code.length;i++) {
        	System.out.println(parity_code[i]+" ");
        	singleword += parity_code[i];
        }
        
        String[] parity_gen = new String[3];
        for(int i = 0 ; i<3 ; i++)
        {
            switch (i) {
                case 0:
                	parity_gen [i] = singleword;
                    break;
                case 1:
                	parity_gen [i] = rowbit;
                    break;
                default:
                	parity_gen[i] = colbit;
                    break;
            }
        }
        return parity_gen;
    }
    
    public static void parity_2d_checker(String[] sender, String[] reciver)
    {
        int count = 0;
        char parity_rowbit[] = new char[reciver.length];
        while(count<reciver.length)
        {
        	parity_rowbit[count] = reciver[count].charAt(0);
            for(int i = 1 ; i < reciver[count].length() ; i++)
            {
                if(parity_rowbit[count] == reciver[count].charAt(i))
                {
                	parity_rowbit[count] = '0';
                }
                else
                {
                	parity_rowbit[count] = '1';
                }
            }
            reciver[count] += parity_rowbit[count];
            count++;
        }
        System.out.print("Row parities: ");
        String rowbit = "";
        for(int i = 0 ; i<parity_rowbit.length ; i++)
        {
        	rowbit += parity_rowbit[i];
            System.out.print(parity_rowbit[i]);
        }
        System.out.println();
        
        count = 0;
        char parity_colbit[] = new char[reciver[0].length()];
        String colbit = "";
        while(count<reciver[0].length())
        {
        	parity_colbit[count] = reciver[0].charAt(count);
            for(int i = 1 ; i < reciver.length ; i++)
            {
                if(parity_colbit[count] == reciver[i].charAt(count))
                {
                	parity_colbit[count] = '0';
                }
                else
                {
                	parity_colbit[count] = '1';
                }
            }
            count++;
        }
        System.out.print("Column parities: ");
        for(int i = 0 ; i<parity_colbit.length ; i++)
        {
            colbit += parity_colbit[i];
            System.out.print(parity_colbit[i]);
        }
        System.out.println();
        
        if(sender[1].equals(rowbit) && sender[2].equals(colbit))
        {
            System.out.println("No error");
        }
        else
        {
            System.out.println("Error");
        }
    }
}
