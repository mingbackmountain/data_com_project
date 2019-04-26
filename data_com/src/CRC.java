//Thanakorn Pasangthien 6088109
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CRC {
	//helper function for crc
		static int xor(int a, int b) {
			if(a == b) {
				return 0;
			}
			return 1;
		}
		
		static int[] divide(int old_data[],int divisor[]) {
			int remainder[];
			int data[] = new int[old_data.length+divisor.length];
			System.arraycopy(old_data, 0, data, 0, old_data.length);
			
			remainder = new int[divisor.length];
			System.arraycopy(data, 0, remainder, 0, divisor.length);
			
			for(int i = 0; i<old_data.length; i++) {
			
				if(remainder[0] == 1) {
					for(int j=1; j<divisor.length;j++) {
						remainder[j-1] = xor(remainder[j],divisor[j]);
						
					}
				}else {
					for(int j=1; j<divisor.length;j++) {
						remainder[j-1] = xor(remainder[j],0);
					}
				}
				remainder[divisor.length-1] = data[i+divisor.length];
			}
			return remainder;
		}
		
		
		
		public static int[] CRC_gen(ArrayList<Integer> dataword, int divisor[]) {
			ArrayList<Integer> input_data = (ArrayList<Integer>) dataword.clone();
			
			int data[] = new int[input_data.size()];
			for(int i = 0;i<input_data.size();i++) {
				data[i] = input_data.get(i);
			}
			 
			int remainder [] = divide(data, divisor);
			
			int[] sent_data = new int[data.length + remainder.length -1];
			System.arraycopy(data, 0, sent_data, 0, data.length);
			System.arraycopy(remainder, 0, sent_data, data.length, remainder.length - 1);
			return sent_data;
		}
		
		public static void CRC_checker(int[] dataword, int[] divisor) {
			int remainder [] = divide(dataword,divisor);
			for(int i=0;i<remainder.length;i++) {
				if(remainder[i] != 0) {
					System.out.println("Error for this dataword\n");
					return;
				}
			}
			System.out.println("No Error\n");
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
			
			//CRC
			System.out.println("CRC CASE");
			System.out.println("==============================");
			int [] divisor = {1,0,0,0,0,0,1,1,1};
			int[] sender = CRC_gen(Input_data,divisor);
			System.out.println("original data = "+Input_data.toString());
			System.out.print("sender = ");
			for(int i =0;i<sender.length;i++) {
				System.out.print(sender[i]);
			}
			
			System.out.println();
			CRC_checker(sender, divisor);
			
			int[] sender_corrupted = {0,0,1,1,0,1,1,0,1,0,1,1,1,0,1,0}; 
			System.out.print("sender_corrupted = ");
			for(int i =0;i<sender_corrupted.length;i++) {
				System.out.print(sender_corrupted[i]);
			}
			System.out.println();
			CRC_checker(sender_corrupted, divisor);
		}
}
