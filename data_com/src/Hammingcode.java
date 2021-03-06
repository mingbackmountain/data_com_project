//Thanakorn Pasangthien 6088109
public class Hammingcode {

	//helper function for hammingCode
	static int getParity(int list[],int power) {
		int parity = 0;
		for(int i=0; i<list.length;i++) {
			if(list[i] != 2) {
				int k = i+1;
				String s = Integer.toBinaryString(k);
				int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
				if(x == 1) {
					if(list[i] == 1) {
						parity = (parity+1)%2;
					}
				}
			}
		}
		
		
		return parity;
	}
	
	public static int[]  HammingCode_gen(int[] dataword) {
		int[] hamming_list;
		int i=0,parity_count =0,j=0,k=0;
		while(i<dataword.length) {
			if(Math.pow(2, parity_count) == i+parity_count+1) {
				parity_count++;
			}else {
				i++;
			}
		}
		hamming_list = new int[dataword.length + parity_count];
		
		for(i=1; i<hamming_list.length;i++) {
			if(Math.pow(2, j) == i) {
				hamming_list[i-1] = 2;
				j++;
			}
			else {
				hamming_list[k+j] = dataword[k++];
			}
		}
		for(i=0;i<parity_count;i++) {
			hamming_list[((int)Math.pow(2, i))-1] = getParity(hamming_list,i);
			
		}
		return hamming_list;
	}
	
	public static void HammingCode_checker(int[] dataword, int parity_count) {
		int power;
		int parity[] = new int[parity_count];
		
		String syndrome = new String();
		
		for(power=0;power<parity_count;power++) {
			for(int i=0; i<dataword.length;i++) {
				int k = i+1;
				String s = Integer.toBinaryString(k);
				int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
				
				if(bit==1) {
					if(dataword[i] == 1) {
						parity[power] = (parity[power]+1)%2;
					}
				}
			}
			syndrome = parity[power] + syndrome;
		}
		
		int error_location = Integer.parseInt(syndrome,2);
		if(error_location != 0) {
			System.out.println("Error is at location: "+error_location);
			dataword[error_location -1] = (dataword[error_location-1]+1)%2;
		}else {
			System.out.println("There is no error in the received data\n");
		}
	}
	
	public static void main(String[] args) {
		//Hamming Code
				System.out.println("HammingCode CASE");
				System.out.println("==============================");
				int[] raw_input = {1, 0, 1, 0, 0, 0, 0, 0};
				
				int[] sender_hamming = HammingCode_gen(raw_input);
				System.out.print("hamming_sender = ");
				for(int i =0;i<sender_hamming.length;i++) {
					System.out.print(sender_hamming[i]);
				}
				System.out.println();
				HammingCode_checker(sender_hamming, 4);
				int[] sender_hamming_corrupted = {1,0,1,1,0,1,0,1,0,0,0,0};
				System.out.print("hamming_sender_corrupted = ");
				for(int i =0;i<sender_hamming_corrupted.length;i++) {
					System.out.print(sender_hamming_corrupted[i]);
				}
				System.out.println();
				HammingCode_checker(sender_hamming_corrupted, 4);
	}
}
