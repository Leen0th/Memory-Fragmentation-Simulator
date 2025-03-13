import java.util.Scanner;

// Class to represent a memory block
class MemoryBlock {
    int blockSize;
    int startAddress;
    int endAddress;
    String status; // "allocated" or "free"
    String processID; // "Null" if free
    int internalFragmentation;

    // Constructor to initialize a memory block
    public MemoryBlock(int startAddress, int blockSize) {
        this.startAddress = startAddress;
        this.blockSize = blockSize;
        this.endAddress = startAddress + blockSize - 1;
        this.status = "free";
        this.processID = "Null";
        this.internalFragmentation = 0;
    }

    // Display block information
    public void printBlock() {
        System.out.printf("Block: Start=%d, End=%d, Size=%d KB, Status=%s, ProcessID=%s, Internal Fragmentation=%d KB\n",
                startAddress, endAddress, blockSize, status, processID, internalFragmentation);
    }
}

public class MemoryInitialization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the number of memory blocks
        System.out.print("Enter the number of memory blocks (M): ");
        int M = scanner.nextInt();
        MemoryBlock[] memoryBlocks = new MemoryBlock[M];

        // Initialize memory blocks
        int currentStartAddress = 0;
        for (int i = 0; i < M; i++) {
            System.out.print("Enter size of block " + (i + 1) + " in KB: ");
            int blockSize = scanner.nextInt();
            memoryBlocks[i] = new MemoryBlock(currentStartAddress, blockSize);
            currentStartAddress += blockSize; // Update start address for next block
        }

        // Display initialized memory blocks
        System.out.println("\nMemory Blocks Initialized:");
        System.out.println("-----------------------------------------");
        System.out.println("Block#   Start   End   Size(KB)   Status   ");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < M; i++) {
            System.out.printf("%-8d %-7d %-8d %-7d %-8s \n",
                    i, memoryBlocks[i].startAddress, memoryBlocks[i].endAddress,
                    memoryBlocks[i].blockSize, memoryBlocks[i].status);
        }
        System.out.println("-----------------------------------------");

        scanner.close();
    }
}
