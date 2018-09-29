import java.util.HashMap;
import java.util.Map;

// Parity checks are used to detect single bit errors in data storage and communication

// ^ is Java's XOR operator
// 0000 ^ 0001 = 0001
// 0001 ^ 0001 = 0000
// So, XOR is used here to alternate between 1 and 0 decimal numbers

class WordParity {
  // Algorithm is O(n)
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= (x & 1);
      x >>>= 1;
    }
    return result;
  }

  // Optimization
  // Idea: Cache parity of every 64 bit integer
  // However, we will need 2^64 bits of storage, which is too big.
  // But because parity computation is associative, we can split the
  // 64 bit into four 16 bit subwords. Compute the parity of each subword,
  // and then compute the parity of the four subresults.
  // Time complexity is O(n/L)
  //    where n = word size, and L is width of words for the cache
  //    => for a 64 bit word, and a word size of 16 for caching,
  //    the time complexity is 64/16 = 4

  // Example: decimal 21990232 is 0001 0100 1111 1000 1011 0101 1000 binary

  // Lookup table for 16-bit words
  public static short[] precomputedParity;

  static {
    precomputedParity = new short[1 << 16];
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedParity[i] = WordParity.parity(i);
    }
  }

  public static short parityCache(long x) {
    final int WORD_SIZE = 16;
    final int BIT_MASK = 0xFFFF; // 1111 1111 1111 1111 (binary)

    return (short) (
      precomputedParity[(int) ((x >>> (3 * WORD_SIZE)) & BIT_MASK)]
      ^ precomputedParity[(int) ((x >>> (2 * WORD_SIZE)) & BIT_MASK)]
      ^ precomputedParity[(int) ((x >>> WORD_SIZE) & BIT_MASK)]
      ^ precomputedParity[(int) (x & BIT_MASK)]);
  }

  // Another approach
  // XOR of a group of bits is its parity. We can exploit this fact to
  // use the CPU's word-level XOR instruction to process multiple bits
  // at a time.
  // Time complexity is O(logn) where n is the word size
  public static short parityXOR(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (short) (x & 0x1);
  }

  public static void main(String[] args) {
    System.out.println(WordParity.parity(5));
    System.out.println(WordParity.parity(10));
    System.out.println(WordParity.parity(1));
    System.out.println(WordParity.parity(0));
    System.out.println(WordParity.parity(21990232L));

    System.out.println("");

    System.out.println(WordParity.parityCache(5));
    System.out.println(WordParity.parityCache(10));
    System.out.println(WordParity.parityCache(1));
    System.out.println(WordParity.parityCache(0));
    System.out.println(WordParity.parityCache(21990232L));



    // Play
    System.out.println("Play");
    System.out.println(Long.toBinaryString(21990232L));
    System.out.println(Long.toBinaryString(21990232L >>> 16)); // 0001 0100 1111
    System.out.println(Long.toBinaryString((21990232L >>> 16) & 0xFFFF )); // 0001 0100 1111
    System.out.println((int)((21990232L >>> 16))); // 335
    System.out.println((int)((21990232L >>> 16) & 0xFFFF)); // 335

    System.out.println(WordParity.precomputedParity.length);
    System.out.println(Integer.toBinaryString(1 << 16));
    System.out.println(((Object) 1).getClass().getName());
  }
}
