//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Movie Tree
// Course: CS 300 Spring 2022
//
// Author: Johnny Palumbo
// Email: jdpalumbo2@wisc.edu
// Lecturer: Michelle Jensen
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: na
// Partner Email: na
// Partner Lecturer's Name: na
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////

// Add javadoc style class header here
// File Header comes here
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * MovieTree.
 *
 */

public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one movie and then check that the
   * addMovie() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another movie which is smaller
   * that the movie at the root, (4) Try adding a third movie which is greater than the one at the
   * root, (5) Try adding at least two further movies such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * movie with respect to year, rating, and then name. (6) Try adding a movie already stored in the
   * tree. Make sure that the addMovie() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    // test (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that
    // its string representation is an empty string "".
    MovieTree tree = new MovieTree();

    if (!tree.isEmpty() || tree.size() != 0 || tree.toString() != "") {
      System.out.println("testAddMovieToStringSize 1) Error");
      return false;
    }

    // test (2) try adding one movie and then check that the addMovie() method call returns true,
    // the tree is not empty, its size is 1, and the .toString() called on the tree returns the
    // expected output.
    if (!tree.addMovie(new Movie(2002, 9.8, "Johnny's Life")) || tree.isEmpty() || tree.size() != 1
        || !tree.toString().equals("[(Year: 2002) (Rate: 9.8) (Name: Johnny's Life)]")) {
      System.out.println("testAddMovieToStringSize 2) Error, expected: "
          + "[(Year: 2002) (Rate: 9.8) (Name: Johnny's Life)]" + ", actual: " + tree.toString());
      return false;
    }

    // test (3) Try adding another movie which is smaller that the movie at the root,
    if (!tree.addMovie(new Movie(1996, 6.9, "A Bug's Life")) || tree.isEmpty()
        || tree.size() != 2) {
      System.out.println("testAddMovieToStringSize 3) Error, counldn't add correctly");
      return false;
    }


    // test (4) Try adding a third movie which is greater than the one at the root,
    if (!tree.addMovie(new Movie(2014, 9.5, "Avatar")) || tree.isEmpty() || tree.size() != 3) {
      System.out.println("testAddMovieToStringSize 4) Error, counldn't add correctly");
      return false;
    }

    // test (5) Try adding at least two further movies such that one must be added at the left
    // subtree, and the other at the right subtree. For all the above scenarios, and more, double
    // check each time that size() method returns the expected value, the add method call returns
    // true, and that the .toString() method returns the expected string representation of the
    // contents of the binary search tree in an increasing order from the smallest to the greatest
    // movie with respect to year, rating, and then name.
    if (!tree.addMovie(new Movie(1998, 7.0, "Karate Kid")) || tree.isEmpty() || tree.size() != 4) {
      System.out.println("testAddMovieToStringSize 5a) Error, counldn't add correctly");
      return false;
    }
    if (!tree.addMovie(new Movie(2022, 3.6, "Nope")) || tree.isEmpty() || tree.size() != 5) {
      System.out.println("testAddMovieToStringSize 5b) Error, counldn't add correctly");
      return false;
    }

    String expected = "[(Year: 1996) (Rate: 6.9) (Name: A Bug's Life)]\n"
        + "[(Year: 1998) (Rate: 7.0) (Name: Karate Kid)]\n"
        + "[(Year: 2002) (Rate: 9.8) (Name: Johnny's Life)]\n"
        + "[(Year: 2014) (Rate: 9.5) (Name: Avatar)]\n" + "[(Year: 2022) (Rate: 3.6) (Name: Nope)]";
    String actual = tree.toString();

    if (!actual.equals(expected)) {
      System.out.println(
          "testAddMovieToStringSize 5c) Error, expected: " + expected + ", actual: " + actual);
      return false;
    }

    // test (6) Try adding a movie already stored in the tree. Make sure that the addMovie()
    // method call returned false, and that the size of the tree did not change.
    if (tree.addMovie(new Movie(1998, 7.0, "Karate Kid")) || tree.isEmpty() || tree.size() != 5) {
      System.out.println("testAddMovieToStringSize 6) Error, duplicate movie added");
      return false;
    }


    return true;
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new MovieTree. Then, check that
   * calling the contains() method on an empty MovieTree returns false. (2) Consider a MovieTree of
   * height 3 which contains at least 5 movies. Then, try to call contains() method to search for
   * the movie having a match at the root of the tree. (3) Then, search for a movie at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    // test (1) Create a new MovieTree. Then, check that calling the contains() method on an empty
    // MovieTree returns false.
    MovieTree tree = new MovieTree();
    if (!tree.isEmpty()) {
      System.out.println("testContains 1) Error, tree not empty at start");
      return false;
    }

    // test (2) Consider a MovieTree of height 3 which contains at least 5 movies. Then, try to
    // call contains() method to search for the movie having a match at the root of the tree.
    tree.addMovie(new Movie(2002, 9.8, "Johnny's Life"));
    tree.addMovie(new Movie(1996, 6.9, "A Bug's Life"));
    tree.addMovie(new Movie(2014, 9.5, "Avatar"));
    tree.addMovie(new Movie(1998, 7.0, "Karate Kid"));
    tree.addMovie(new Movie(2022, 3.6, "Nope"));

    if (!tree.contains(2002, 9.8, "Johnny's Life")) {
      System.out.println("testContains 2) Error, no match found for root");
      return false;
    }

    // test (3) Then, search for a movie at the right and left subtrees at different levels
    // considering successful and unsuccessful search operations. Make sure that the contains()
    // method returns the expected output for every method call.
    if (tree.contains(2002, 9.4, "Johnny's Life")) {
      System.out.println("testContains 3a) Error, false match found for root");
      return false;
    }

    if (!tree.contains(1996, 6.9, "A Bug's Life") || !tree.contains(2014, 9.5, "Avatar")) {
      System.out.println("testContains 3b) Error, no match found for depth 1 node");
      return false;
    }

    if (!tree.contains(1998, 7.0, "Karate Kid") || !tree.contains(2022, 3.6, "Nope")) {
      System.out.println("testContains 3c) Error, no match found for depth 2 node");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty movie tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * MovieTree with the following structure for instance, is 4.
   *
   * (*) / \ (*) (*) \ / \ (*) (*) (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // test (1) ensures that the height of an empty movie tree is zero.
    MovieTree tree = new MovieTree();
    if (tree.height() != 0) {
      System.out.println("testHeight 1) Error, tree height not 0 at start");
      return false;
    }

    // test (2) ensures that the height of a tree which consists of only one node is 1.
    tree.addMovie(new Movie(2002, 9.8, "Johnny's Life"));
    if (tree.height() != 1) {
      System.out.println("testHeight 2) Error, tree height not 1 after 1 add");
      return false;
    }

    // test (3) ensures that the height of a MovieTree with the following structure for instance,
    // is 4. See above
    tree.addMovie(new Movie(1996, 6.9, "A Bug's Life"));
    tree.addMovie(new Movie(2014, 9.5, "Avatar"));
    tree.addMovie(new Movie(1998, 7.0, "Karate Kid"));

    if (tree.height() != 3) {
      System.out.println("testHeight 3) Error, tree height not 3");
      return false;
    }

    tree.addMovie(new Movie(1998, 7.9, "Karate Kid"));

    if (tree.height() != 4) {
      System.out.println("testHeight 3) Error, tree height not 4");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {
    // test (1) creates a BST and then calls getBestMovie to ensure it returns the correct movie
    try {
      MovieTree tree = new MovieTree();

      tree.addMovie(new Movie(2002, 9.8, "Johnny's Life"));
      tree.addMovie(new Movie(1996, 6.9, "A Bug's Life"));
      tree.addMovie(new Movie(2014, 9.5, "Avatar"));
      tree.addMovie(new Movie(1998, 7.0, "Karate Kid"));
      tree.addMovie(new Movie(2022, 3.6, "Nope"));

      if (!tree.getBestMovie().toString().equals("[(Year: 2022) (Rate: 3.6) (Name: Nope)]")) {
        System.out.println("testGetBestMovie 1) Error, best movie not chosen");
        return false;
      }
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the MovieTree.lookup() method returns an array
   * list which contains all the movies satisfying the search criteria of year and rating, when
   * called on a non empty movie tree with one match, and two matches and more. Vary your search
   * criteria such that the lookup() method must check in left and right subtrees. (3) Ensures that
   * the MovieTree.lookup() method throws a NoSuchElementException when called on a non-empty movie
   * tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    // test (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException when
    // called on an empty tree.
    MovieTree tree = new MovieTree();

    try {
      tree.lookup(2002, 7.0);
      System.out.println("testLookup 1) Error, Exception not thrown");
      return false;
    } catch (NoSuchElementException e) {
    }

    // test (2) Ensures that the MovieTree.lookup() method returns an array list which contains
    // all the movies satisfying the search criteria of year and rating, when called on a non
    // empty movie tree with one match, and two matches and more. Vary your search criteria such
    // that the lookup() method must check in left and right subtrees.
    tree.addMovie(new Movie(2002, 8.8, "Johnny's Life"));
    tree.addMovie(new Movie(1998, 6.9, "A Bug's Life"));
    tree.addMovie(new Movie(2002, 9.5, "Avatar"));
    tree.addMovie(new Movie(1998, 7.0, "Karate Kid"));
    tree.addMovie(new Movie(2002, 3.6, "Nope"));

    System.out.println(tree);
    if (!tree.lookup(1998, 7.0).get(0).toString()
        .equals("[(Year: 1998) (Rate: 7.0) (Name: Karate Kid)]")) {
      System.out.println("testLookup 2) Error, Movie not returned/incorrect movie returned");
      return false;
    }

    // test (3) Ensures that the MovieTree.lookup() method throws a NoSuchElementException when
    // called on a non-empty movie tree with no search results found.

    if (!tree.lookup(2002, 2.0).get(2).toString()
        .equals("[(Year: 2002) (Rate: 9.5) (Name: Avatar)]")
        || !tree.lookup(2002, 2.0).get(1).toString()
            .equals("[(Year: 2002) (Rate: 3.6) (Name: Nope)]")) {
      System.out.println("testLookup 3) Error, Movie not returned/incorrect movie returned");
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    if (!testAddMovieToStringSize()) {
    } else if (!testContains()) {
    } else if (!testHeight()) {
    } else if (!testGetBestMovie()) {
    } else if (!testLookup()) {
    } else
      System.out.println("All tests passed");
    

  }

}
