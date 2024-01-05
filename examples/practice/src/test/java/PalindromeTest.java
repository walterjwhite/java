import com.walterjwhite.examples.practice.problems.organize.palindrome.Palindrome;
import com.walterjwhite.examples.practice.problems.organize.palindrome.PalindromeA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeTest {
  protected final Palindrome palindrome = new PalindromeA();

  @Test
  void test_babad() {
    Assertions.assertEquals("bab", palindrome.longestPalindrome("babad"));
  }

  @Test
  void test_cbbd() {
    Assertions.assertEquals("bb", palindrome.longestPalindrome("cbbd"));
  }
}
