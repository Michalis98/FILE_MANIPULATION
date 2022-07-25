package tuc.main2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Random;

public class Methods {

	private int offset;
	int[] array1000 = new int[10000];
	int[] array90001 = new int[3];
	private int number;
	Random random = new Random();
	String[] myString = { "mixalis1", "mixalis2", "mixalis3", "mixalis4", "mixalis5", "mixalis6", "mixalis7","mixalis8", "mixalis9", "mixalis10", "mixalis11" };
	
    void makeTheFile() throws IOException{
		
		int number = 0;
		int counter = 0;
		int offset = 0;
		for (int i = 0; i < 100; i++) {

			int[] array = new int[1000];
			{

				for (int i1 = 0; i1 < 1000; i1++) {
					number = random.nextInt(350000);
					array[i1] = number;
				}

			}

			
				writeIntArrayToDisk("mixalis", offset, array);
			

			offset = offset + 1000;
			counter++;

		}

		ToCsv.main("mixalis");

		System.out.println("The buffers accesed the disk : " + counter + " times for the 1st subquery");

	}
	
	void SplitFilein10files() throws IOException {
		// Second part of the exercise (sort the array in 10 files)

				// counter for accessing the disk
				
				int accesesToTheDisk = 0;
				int counter1 = 0;
				int counter2 = 0;
				int offset2 = 0;
				int offset3 = 0;
				int[] pinakas10 = new int[1000];
				
				

				for (int j12 = 0; j12 < 10; j12++) {
					// bazoume ta 1000 int ston array2
					for (int i = 0; i < 10; i++) {
						pinakas10 = readIntArrayFromDisk("mixalis", offset2, 1000);
						offset2 = offset2 + 1000;
						accesesToTheDisk++;
						// bazoyme 1000 ints ston allo pinaka 10000thesewn
						for (int k = 0; k < pinakas10.length; k++) {
							array1000[counter1] = pinakas10[k];
							counter1++;
						}
					}

					// exoume enan pinaka 10k thesewn
					Arrays.sort(array1000);
					for (int ik = 0; ik < 10; ik++) {
						// grafoume ston array2 1000 stixia

						for (int k2 = 0; k2 < pinakas10.length; k2++) {
							pinakas10[k2] = array1000[counter2];
							counter2++;
						}

						// grafoume ton array2 sto arxio me offset
						writeIntArrayToDisk(myString[j12], offset3, pinakas10);
						accesesToTheDisk++;
						offset3 = offset3 + 1000;
					}
					counter2 = 0;
					counter1 = 0;

					offset3 = 0;
				}

				System.out.println("The buffers accesed the disk : " + accesesToTheDisk + " times to make the 10 files");
	}

	void Merge10files() throws IOException {
	// merge the 10 files we created in the disk
				int offset = 0;
				//counterloop=thimame kathe fora se pio stixio tou pinaka ime alla meta to ksanamidenizw
				int[] counterloop = new int[10];
				//counterloop=thimame kathe fora se pio stixio tou pinaka ime alla den to midenizw gia na kserw an ta diabassa ola apo to arxio
				int[] counterloop2 = new int[10];
				//extra buffer
				int[] pinakas10 = new int[1000];
				//10 pinakes me 1000 thesis (10 buffers)
				int[][] arrays = new int[1000][10];
				//array poy gemizw me arithmous kai kanw sorting
				int[] array10 = new int[10];

				// gemizw ta arrays me arithmous gia 1i fora
				for (int i = 0; i < 10; i++) {
					arrays[i] = readIntArrayFromDisk(myString[i], 0, 1000);

				}

				for (int i = 0; i < 10; i++) {
					array10[i] = arrays[i][0];
				}

				int n = 0;
				int location = 0;
				for (int k2 = 0; k2 < 100; k2++) {
					int k = 0;
					while (k != 999) {
						int small = array10[0];
						int index3 = 0;
						for (int i = 0; i < 10; i++) {
							if (array10[i] < small) {
								small = array10[i];
								index3 = i;
							}
						}

						pinakas10[k] = small;
						n = counterloop[index3];
						n++;
						array10[index3] = arrays[index3][n];
						counterloop[index3] = n;

						if (n == 999) {
							location = counterloop[index3] + counterloop2[index3];
							counterloop2[index3] = counterloop[index3] + counterloop2[index3];
							arrays[index3] = readIntArrayFromDisk(myString[index3], location, 1000);
							counterloop[index3] = 0;
						}

						k++;

					}
					writeIntArrayToDisk("file", offset, pinakas10);
					offset = offset + 1000;

				}
				ToCsv.main("file");
	}

	void LinearSearch () throws IOException {
		Random random = new Random();
		
		//olous aftous tou counter tous xrisimopio gia na kserw se pia thesi tou file ime posous arithmous
		//exw diabasi posous brika epiximena apotiximena kai exw 2 apo to kathena epidi kapious tous midenizw
		//kapia fora gia na ksanarxizi apo tin arxi
		
		int counter6 = 0;
		int counter7 = 0;
		int j = 0;
		int counter1 = 0;
		int counter4 = 0;
		int counter5 = 0;
		int c1 = 0;
		int c2 = 0;
		
		//o random number pou tha anazitisw
		int number=0;
		
		// pinakas gia na balw mesa tous random arithmous mou
		int[] array9 = new int[100000];

		for (int i1 = 0; i1 < 999; i1++) {
			number = random.nextInt(350000);
			array9[i1] = number;
		}

		// poses fores kanw to loop
		int select = 0;

		while (counter4 != 20) {
			counter1 = 0;
			array1000 = readIntArrayFromDisk("file", 0, 1000);

			offset = 1000;
			j = 0;

			int i = array9[select];

			for (int i7 = 0; i7 < 100; i7++) {

				for (int i2 = 0; i2 < 1000; i2++) {
					int n65 = array1000[i2];
					if (i == n65) {

						j = 1;
						if (counter4 < 20)
							counter4++;
						c1++;
						i2 = 1111;
					}
				}

				if ((j != 1) && (i7 == 99)) {
					j = 2;
					if (counter5 < 20)
						counter5++;
					c2++;
				}

				if ((j != 1) && (j != 2))

				array1000 = readIntArrayFromDisk("file", offset, 1000);
				counter1++;
				offset = offset + 1000;

				if (j == 1) {
					if (c1 < 21) {
						counter6 = counter1 + counter6;

					}
					i7 = 123;
				} else if (j == 2) {
					if (c2 < 21) {
						counter7 = counter1 + counter7;

					}

					i7 = 123;
				}

			}

			select++;
		}
		System.out.println("\n");
		System.out.println("LINEAR SEARCH RESULTS: \n  ");
		System.out.println("NUMBERS FOUND                             : " + counter4);
		System.out.println("NUMBERS NOT FOUND                         : " + counter5);
		System.out.println("Average of accesses at disk for FOUND     : " + counter6 / 20);
		System.out.println("Average of accesses at disk for NOT FOUND : " + counter7 / 20);
		System.out.println("\n");
		System.out.println("BINARY SEARCH RESULTS :\n");

	}

	void BinarySearch() {
		int i = 0;
		int[] array9 = new int[10000];
		
		for (int i1 = 0; i1 < 9999; i1++) {
			number = random.nextInt(350000);
			array9[i1] = number;
		}

		int counter4 = 0;
		int counter5 = 0;
		int counter6 = 0;
		int counter7 = 0;
		

		i = 0;

		while ((counter4 != 20) || (counter5 != 20)) {

			array90001 = binarySearch(array9[i]);

			if (array90001[0] == 2) {
				if (counter4 < 20) {
					counter4++;
					counter6 = counter6 + array90001[1];
				}
			}

			if (array90001[0] == -1) {
				if (counter5 < 20) {
					counter5++;
					counter7 = counter7 + array90001[2];
				}
			}

			i++;
		}

		System.out.println("NUMBERS FOUND                             :" + counter4);
		System.out.println("NUMBERS NOT FOUND                         :" + counter5);
		System.out.println("Average of accesses at disk for FOUND     :" + counter6 / 20);
		System.out.println("Average of accesses at disk for NOT FOUND :" + counter7 / 20);

	}
	
	
	
	
	public  int[] readIntArrayFromDisk(String filename, long intOffset, int numberOfIntsToRead)
				throws IOException {
			RandomAccessFile randomAccessFile = null;
			byte[] byteBuffer = new byte[numberOfIntsToRead * 4];
			@SuppressWarnings("unused")
			int bytesRead;
			try {
				randomAccessFile = new RandomAccessFile(filename, "r");
				randomAccessFile.seek(intOffset * 4);
				bytesRead = randomAccessFile.read(byteBuffer, 0, byteBuffer.length); // 0 is the offset in array byteBuffer
				randomAccessFile.close();
				randomAccessFile = null;
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			} finally {
				if (randomAccessFile != null) {
					try {
						randomAccessFile.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			int[] intArray = new int[numberOfIntsToRead];

			Utils.bytesToIntArray(byteBuffer, intArray);
			// intArray contains now the read bytes in byteBuffer as integers
			// does not handle cases where bytesRead is less than the number of intended
			// reads

			return intArray;
		}

	public  void writeIntArrayToDisk(String filename, long intOffset, int[] intArray) throws IOException {
			RandomAccessFile randomAccessFile = null;
			byte[] byteBuffer = intArrayToBytes(intArray);

			try {
				randomAccessFile = new RandomAccessFile(filename, "rw");
				randomAccessFile.seek(intOffset * 4);
				randomAccessFile.write(byteBuffer);
				randomAccessFile.close();
				randomAccessFile = null;
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			} finally {
				if (randomAccessFile != null) {
					try {
						randomAccessFile.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	public  byte[] intArrayToBytes(final int[] intArray) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length * 4);
			IntBuffer intBuffer = byteBuffer.asIntBuffer();
			intBuffer.put(intArray);
			byte[] byteArray = byteBuffer.array();
			return byteArray;
		}

	public  int[] removeElement(int[] original, int element) {
			int[] n = new int[original.length - 1];
			System.arraycopy(original, 0, n, 0, element);
			System.arraycopy(original, element + 1, n, element, original.length - element - 1);
			return n;
		}

	public  int[] binarySearch(int x) {
			//buffer
			int[] array = new int[1000];
			// krataw mesa tous 3 pointers mou
			int[] array2 = new int[3];
			//counter
			int c = 0;

			
			
			int l = 0, r = 1000 - 1;
			while (l <= r) {
				int m = l + (r - l) / 2;
				try {
					array = readIntArrayFromDisk("file", 100 * m, 1000);
					c++;
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error opening file");
				}
				// Check if x is present at mid
				for (int i = 0; i < 1000; i++) {
					if (array[i] == x) {
						array2[0] = 2;
						array2[1] = c;
						array2[2] = 0;
						return array2;
					}
				}
				// If x greater, ignore left half
				if (array[m] < x)
					l = m + 1;

				// If x is smaller, ignore right half
				else
					r = m - 1;
			}

			// if we reach here, then element was
			// not present
			array2[0] = -1;
			array2[2] = c;
			array2[1] = 0;
			return array2;
		}
		
}


	

