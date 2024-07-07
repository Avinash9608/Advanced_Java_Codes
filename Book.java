/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.examples;

/**
 *
 * @author user
 */
public class Book {

    @Override
    public String toString() {
        return "Book{" + "bookName=" + bookName + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice + '}';
    }

    public Book(String bookName, double oldPrice, double newPrice) {
        this.bookName = bookName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
      private String bookName;
    private double oldPrice;
    private double newPrice;
}
