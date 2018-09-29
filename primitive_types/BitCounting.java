// Facts
// Take the decimal 5. In binary, 5 is 101
// Width of integer in Java is 32 bits
// Hence, decimal 5 is represented in memory as 0000 0000 0000 0101
// 1 in binary is 0000 0000 0000 0001
// When we bitwise AND 1 and 5, we get 0000 0000 0000 0001 = 1
// >>> is Java's unsigned right shift operator. Unsign meaning it alway fills the trailing positions after shift to 0.

// Algorithm is O(n)
class BitCounting {
  public static short countBits(int x) {
    short numBits = 0;
    while (x != 0) {
      numBits += (x & 1);
      x >>>= 1;
    }
    return numBits;
  }

  public static void main(String[] args) {
    System.out.println(BitCounting.countBits(5));
    System.out.println(BitCounting.countBits(10));
    System.out.println(BitCounting.countBits(1));
    System.out.println(BitCounting.countBits(0));
  }
}
