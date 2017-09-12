/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author exchange
 */
public class Card extends StackPane{
    
    private boolean flipped;
    private boolean matched;
    private boolean disabled;
    private String path;
    private Image image;
    private ImageView imageView;
    private int row;
    private int col;
    private int numRows;
    private int numCols;
    
    public Card() {
        flipped = false;
        matched = false;
        disabled = true;
        path = "";
        image = null;
        imageView = new ImageView();
        row = 0;
        col = 0;
        numRows = 0;
        numCols = 0;
        this.setStyle("-fx-background-color: WHITE");
    }
    
    public Card(String Path) {
        flipped = false;
        matched = false;
        disabled = true;
        path = "";
        imageView = new ImageView();
        setPath(path);
        Image image;
        row = 0;
        col = 0;
        numRows = 0;
        numCols = 0;
        this.setStyle("-fx-background-color: GREEN");
        setPath(path);
    }

    /**
     * @return the flipped
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * @param flipped the flipped to set
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /**
     * @return the matched
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * @param matched the matched to set
     */
    public void setMatched(boolean matched) {
        matched = true;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        try{
            this.path = path;
            FileInputStream stream = new FileInputStream(path);
            setImage(new Image(stream));
    }
    catch(java.io.FileNotFoundException e){
        System.out.println("File not found");
        System.exit(-1);
    }
}

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }

    /**
     * @return the imageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * @param imageView the imageView to set
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int rows) {
        this.row = rows;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(int cols) {
        this.col = cols;
    }

    /**
     * @return the numRows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * @param numRows the numRows to set
     */
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    /**
     * @return the numCols
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * @param numCols the numCols to set
     */
    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
    
    public void setCardAndImageSize(int width, int height){
        this.setPrefSize(width,height);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
    
    public void setGridPos(int r, int c) {
        r = row;
        c = col;
    }
    
    public void setGridSize(int nr, int nc){
        nr = numRows;
        nc = numCols;
    }
    
    public void flipCard(){
        getChildren().clear();
        if(flipped == true){
            getChildren().add(imageView);
        }
    }
    
    public boolean isCardDisabled(){
        return disabled;
    }
    
    public void setCardDisabled(boolean disabled){
        this.disabled = disabled;
        setDisabled(disabled);
        if(disabled == true){
            setStyle("-fx-background-color: yellow");
        }else{
            setStyle("-fx-background-color: blue");
        }
            
    }
    
}
