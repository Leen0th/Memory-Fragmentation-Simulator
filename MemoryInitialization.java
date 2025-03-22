import java.util.Scanner;

// Class to represent a memory block
class MemoryBlock {
    int blockSize;
    int startAddress;
    int endAddress;
    String status; // "allocated" or "free"
    String processID; // "Null" if free"
    int internalFragmentation; // Internal Fragmentation

    // Constructor to initialize a memory block
    public MemoryBlock(int startAddress, int blockSize) {
        this.startAddress = startAddress;
        this.blockSize = blockSize;
        this.endAddress = startAddress + blockSize - 1;
        this.status = "free";
        this.processID = "Null";
        this.internalFragmentation = 0;
    }

    // Free the block
    public void deallocate() {
        this.status = "free";
        this.processID = "Null";
        this.internalFragmentation = 0;
    }

    // Display block information
    public void printBlock(int index) {
        System.out.printf("%-8d %-7d %-8d %-7d %-8s %-8s %-5d\n",
                index, startAddress, endAddress, blockSize, status, processID, internalFragmentation);
    }
}

// Class containing memory allocation algorithms
class Algorithms {
    // First-Fit 
    public static boolean firstFit(MemoryBlock[] memoryBlocks, String processID, int processSize, int strategy) {
        for (MemoryBlock block : memoryBlocks) {
            if (block.status.equals("free") && block.blockSize >= processSize) {
                block.status = "allocated";
                block.processID = processID;

            // Call fragmentation calculation method
                calculateFragmentation(block, strategy, processSize);

                System.out.printf("%s Allocated at address %d, and the internal fragmentation is %d\n",
                        processID, block.startAddress, block.internalFragmentation);
                return true; 
            }
        }
        return false; 
    }
 // Best-Fit Algorithm
    public static boolean bestFit(MemoryBlock[] memoryBlocks, String processID, int processSize, int strategy) {
        MemoryBlock bestBlock = null;
        int minSize = Integer.MAX_VALUE;
        for (MemoryBlock block : memoryBlocks) {
            if (block.status.equals("free") && block.blockSize >= processSize && block.blockSize < minSize) {
                bestBlock = block;
                minSize = block.blockSize;
            }
        }
        if (bestBlock != null) {
            bestBlock.status = "allocated";
            bestBlock.processID = processID;
	 // Call fragmentation calculation method
            calculateFragmentation(bestBlock, strategy, processSize);
            System.out.printf("%s Allocated at address %d, and the internal fragmentation is %d\n",
                    processID, bestBlock.startAddress, bestBlock.internalFragmentation);
            return true;
        }
        return false;
    }

    // TODO: Implement Worst-Fit Algorithm
    // public static boolean worstFit(MemoryBlock[] memoryBlocks, String processID, int processSize, int strategy) { }

    // calculate internal fragmentation
    public static void calculateFragmentation(MemoryBlock block, int strategy, int processSize) {
        switch (strategy) {
            case 1: // First-Fit
            case 2: // Best-Fit
            case 3: // Worst-Fit
               
        }
    }
}

   import java.util.Scanner;

public class MemoryInitializatio {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Step A: Ask for the number of memory blocks
	        System.out.print("Enter the number of memory blocks (M): ");
	        int M = scanner.nextInt();
	        MemoryBlock[] memoryBlocks = new MemoryBlock[M];

	        // Step A: Ask for the size of each block
	        int currentStartAddress = 0;
	        for (int i = 0; i < M; i++) {
	            System.out.print("Enter size of block " + (i + 1) + " in KB: ");
	            int blockSize = scanner.nextInt();
	            memoryBlocks[i] = new MemoryBlock(currentStartAddress, blockSize);
	            currentStartAddress += blockSize; // Update start address for next block
	        }

	        // Step B: Ask the user to enter the allocation strategy
	        System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
	        int strategy = scanner.nextInt();

	        // Display initialized memory blocks
	        System.out.println("Memory Blocks Initialized:");
            System.out.println("=========================================");
	        System.out.println("Block#   Start   End   Size(KB)   Status ");
            System.out.println("=========================================");
	        for (int i = 0; i < M; i++) {
	            System.out.printf("%-8d %-7d %-8d %-7d %-8s \n",
	                    i, memoryBlocks[i].startAddress, memoryBlocks[i].endAddress,
	                    memoryBlocks[i].blockSize, memoryBlocks[i].status);
	        }
            System.out.println("=========================================");

	        // Step C: Display menu and handle user choices
	        while (true) {
	            System.out.println("1) Allocate memory blocks");
	            System.out.println("2) Deallocate memory blocks");
	            System.out.println("3) Print report about the current state of memory and internal Fragmentation");
	            System.out.println("4) Exit");
	            System.out.println("=========================================");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    // Memory allocation request 
	                    System.out.print("Enter process ID: ");
	                    String processID = scanner.next();
	                    System.out.print("Enter process size in KB: ");
	                    int processSize = scanner.nextInt();

	                    boolean allocated = false;
	                    if (strategy == 1) {
	                        allocated = Algorithms.firstFit(memoryBlocks, processID, processSize, strategy);
	                    } else if (strategy == 2) {
                           allocated = Algorithms.BestFit(memoryBlocks, processID, processSize, strategy);
	                    } else if (strategy == 3) {	                       
	                        allocated = Algorithms.worstFit(memoryBlocks, processID, processSize, strategy);
	                    }

	                    if (!allocated) {
	                        System.out.println("Error: No sufficient memory available for process " + processID);
	                    }
	                    System.out.println("=========================================");

	                    break;

	                case 2:
	                    // Deallocate memory block
	                    System.out.print("Enter process ID to deallocate: ");
	                    String deallocateID = scanner.next();
	                    boolean found = false;

	                    for (MemoryBlock block : memoryBlocks) {
	                        if (block.status.equals("allocated") && block.processID.equals(deallocateID)) {
	                            block.deallocate();
	                            System.out.println("Process " + deallocateID + " deallocated.");
	                            found = true;
	                            break;
	                        }
	                    }

	                    if (!found) {
	                        System.out.println("Error: Process ID not found.");
	                    }
	                    break;

	                case 3:
	                    // Print memory report using printBlock()
	                    System.out.println("Memory Blocks Status:");
	                    System.out.println("============================================================================");
	                    System.out.println("Block#   Size   Start-End    Status      ProcessID   InternalFragmentation");
	                    System.out.println("============================================================================");
	                    for (int i = 0; i < M; i++) {
	                        memoryBlocks[i].printBlock(i);
	                    }
	                    System.out.println("============================================================================");
	                    break;

	                case 4:
	                    // Exit the program
	                    System.out.println("Exiting program...");
	                    scanner.close();
	                    return;

	                default:
	                    System.out.println("Invalid choice! Please enter a valid option.");
	            }
	         }
	        }
	 }
