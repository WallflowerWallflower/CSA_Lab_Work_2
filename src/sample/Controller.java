package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML private RadioButton dec2Bin;
    @FXML private RadioButton dec2Hex;
    @FXML private RadioButton bin2Dec;
    @FXML private RadioButton hex2Dec;
    @FXML private ToggleGroup radioGroup;


    @FXML private TextField outputField;


    @FXML private Button calculateButton;


    @FXML private Button clearButton;


    @FXML private TextField numberField;





    public void radioButtonSelection() {

        if (this.radioGroup.getSelectedToggle().equals(this.dec2Bin))
            outputField.setText(" ");
        numberField.clear();


        if (this.radioGroup.getSelectedToggle().equals(this.dec2Hex))
            outputField.setText(" ");
        numberField.clear();


        if (this.radioGroup.getSelectedToggle().equals(this.bin2Dec))
            outputField.setText(" ");
        numberField.clear();


        if (this.radioGroup.getSelectedToggle().equals(this.hex2Dec))
            outputField.setText(" ");
        numberField.clear();


    }


    public void clearAllButtonClick() {
        radioGroup.selectToggle(null);
        outputField.setText(" ");
        numberField.clear();
    }


    @FXML
    public void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            outputField.setText("Worth a try - Hit \"Convert\" ");
            // calculateConversion();
        } }



    public void calculateConversion() {


        if (this.radioGroup.getSelectedToggle().equals(this.dec2Bin))
            outputField.setText(dec2Bin(Integer.parseInt(numberField.getText()))); // 120 Should output 1111000


        if (this.radioGroup.getSelectedToggle().equals(this.dec2Hex))
            outputField.setText(dec2Hex(Integer.parseInt(numberField.getText()))); // 59305 should output E7A9


        if (this.radioGroup.getSelectedToggle().equals(this.bin2Dec))
            outputField.setText(String.valueOf(bin2Dec(numberField.getText()))); //should output 120


        if (this.radioGroup.getSelectedToggle().equals(this.hex2Dec))
            outputField.setText(String.valueOf(hex2Dec(numberField.getText()))); //should output 59305

    }

    public static String dec2Bin(int value){


        if(value <= 0)
        {
            return "";
        }
        else
        {
            if  (value%2 != 0)
            {
                return dec2Bin(value/2) + '1';
            }
            else
            {
                return  dec2Bin(value/2) + '0';
            }
        }
    }

    public static String dec2Hex(int value) {

        int remainder = value%16;


        if(value <= 0)
        {
            return "";
        }

        else
        {
            if  (remainder >= 10)
            {
                String hexLetter = "Z";
                switch (remainder) {
                    case 10: hexLetter = "A"; break;
                    case 11: hexLetter = "B"; break;
                    case 12: hexLetter = "C"; break;
                    case 13: hexLetter = "D"; break;
                    case 14: hexLetter = "E"; break;
                    case 15: hexLetter = "F"; break;
                }
                return dec2Hex(value/16) + hexLetter;
            }
            else
            {
                return  dec2Hex(value/16) + Integer.toString(remainder);
            }
        }
    }

    public static int bin2Dec(String binaryValue){


        int numberDigits = binaryValue.length();


        String leadDigit = binaryValue.substring(0,1);


        String remDigits = binaryValue.substring(1);


        if (numberDigits == 1) {
            return Integer.parseInt(leadDigit);
        }

        else
        {

            return Integer.parseInt(leadDigit) * (int)Math.pow(2, numberDigits-1) + bin2Dec(remDigits);
        }
    }

    public static int hex2Dec(String hexValue) {


        int numberDigits = hexValue.length();


        if (numberDigits == 0) {
            return 0;
        }


        String leadDigit = hexValue.substring(0,1);


        String remDigits = hexValue.substring(1);


        try {
            Integer.parseInt(leadDigit);
        } catch (NumberFormatException e) {
            int hexLetterValue = 0;
            switch (leadDigit) {
                case "A": hexLetterValue = 10; break;
                case "B": hexLetterValue = 11; break;
                case "C": hexLetterValue = 12; break;
                case "D": hexLetterValue = 13; break;
                case "E": hexLetterValue = 14; break;
                case "F": hexLetterValue = 15; break;
            }
            return hex2Dec(remDigits) + hexLetterValue * (int)Math.pow(16, numberDigits-1);
        }

        return Integer.parseInt(leadDigit) * (int)Math.pow(16, numberDigits-1) + hex2Dec(remDigits);
    }



    /
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        radioGroup = new ToggleGroup();
        this.dec2Bin.setToggleGroup(radioGroup);
        this.dec2Hex.setToggleGroup(radioGroup);
        this.bin2Dec.setToggleGroup(radioGroup);
        this.hex2Dec.setToggleGroup(radioGroup);



    }


}
