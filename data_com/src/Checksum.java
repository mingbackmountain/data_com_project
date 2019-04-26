//Thanakorn Pasangthien 6088109

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Checksum {
	public static int Checksum_gen(int[] dataword) {
		int checksum,sum=0,i;
		for(i =0;i<dataword.length;i++) {
			sum += dataword[i];
		}
		checksum = ~sum;
		
		return checksum;
	}
	
	public static int Checksum_checker(int[] dataword, int old_check) {
		int checksum,sum=0,i;
		for( i=0;i<dataword.length;i++) {
			 sum += dataword[i];
		}
		sum += old_check;
		checksum = ~sum;
		return checksum;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> Input_data = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		
		//Generate original data
		System.out.print("Please enter your data size:");
		int size = sc.nextInt();
		System.out.println("==============================\n");
		Random rand = new Random();
		for(int i =0; i<size; i++) {
			int rand_data = rand.nextInt(2);
			Input_data.add(rand_data);
		}
		//Checksum
				System.out.println("Checksum CASE");
				System.out.println("==============================");
				System.out.println(Input_data.toString());
				int[] data = new int[size];
				System.out.println("Please enter input data: ");
				for(int i=0; i<2;i++) {
					data[i] = sc.nextInt();
				}
				
				int checksum_sender = Checksum_gen(data);
				System.out.println("Checksum sender = "+ checksum_sender);
				//If you want the error to occur please unlock line 54
				//data[2] = 1;
				int checksum_receiver = Checksum_checker(data, checksum_sender);
				System.out.println("Checksum receiver = "+ checksum_receiver);
				if(checksum_receiver == 0) {
					System.out.println("No error");
				}else {
					System.out.println("There is an error");
				}
				
				
				
	}
}
