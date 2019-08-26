/**
 *
 * @author moham
 */

import java.util.Random;

public class HugeInteger {
    
    int[] hugeIntArray;//this is the array that will store our huge int
    boolean negativeMarker = false;//this marker will keep track of whether the huge int is negative
    int arrayLength; // this will store the length of the array to ensure that the array is space efficient
    
    public HugeInteger(){
        negativeMarker = false;
    }
    
    public HugeInteger(String val){
        
        //exception thrown if string is empty
        if(val == null || val.isEmpty()){
            throw new RuntimeException("String cannot be empty or null.");
        }
        if(val.charAt(0) == '-'){negativeMarker = true;}//we keep track of whether the number is negative
        else{negativeMarker = false;}
        
        int stringLength = val.length(); //this variable will store the length of the string to define the array
        
        hugeIntArray = new int[negativeMarker?(stringLength-1) : stringLength];//defining length of array
        
        arrayLength = negativeMarker?(stringLength-1) : stringLength;
        
        if(negativeMarker == true){
            for(int i=1; i<(arrayLength+1); i++){
                //This if statement will check if the character that we are looking at is a digit or not. If not, it will throw an exception.
                if(Character.isDigit(val.charAt(i)) == false){
                    throw new IllegalArgumentException("String cannot contain anything but numbers and a negative sign at the beginning.");
                }
                hugeIntArray[i-1] = Character.getNumericValue(val.charAt(i));//adding digits from string to array
            }
        }
        
        else if(negativeMarker == false){
            for(int i=0; i<arrayLength; i++){
                //This if statement will check if the character that we are looking at is a digit or not. If not, it will throw an exception.
                if(Character.isDigit(val.charAt(i)) == false){
                    throw new RuntimeException("String cannot contain anything but numbers and a negative sign at the beginning.");
                }
                hugeIntArray[i] = Character.getNumericValue(val.charAt(i));//adding digits from string to array
            }
        }
    }
    
    public HugeInteger(int n){
        if(n<1){
            throw new IllegalArgumentException("N must be greater than or equal to 1.");
        }
        else{
            Random rand = new Random();
            
            //defining the length of the array to be n
            hugeIntArray = new int[n];
            
            arrayLength = n;
            
            //first number in array has to be from 1-9
            hugeIntArray[0] = rand.nextInt(8) + 1; //generating random int from 1-9
            
            //This for loop will create a random number from 0-9 and add it to the array. By the end we should have an array of n digits.
            for(int i=1; i<n; i++){
                hugeIntArray[i] = rand.nextInt(10); //generating random int from 0-9
            }
        }
    }
    
    
    public HugeInteger add(HugeInteger h){
        int addCarry = 0; //addCarry will store the number we have to carry from each addition
        int tempNum = 0; //will store the value of 2 numbers being added or subtracted
        
        HugeInteger addAnswer = new HugeInteger(); //this is a variable of type HugeInteger that will store our answer
        
        //we will have to check if one, both, or none of the numbers are negative.
        if(this.negativeMarker == false && h.negativeMarker == false){
            addAnswer.hugeIntArray = new int[(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1];//setting length of our answer's array.
            addAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1;
            
            for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                
                if(i>=this.arrayLength && i>=h.arrayLength){
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = 0 + addCarry;
                    addCarry = 0;
                }
                
                else if(i>=this.arrayLength){
                    tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + addCarry;
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    addCarry = tempNum / 10;
                }
                
                else if(i>=h.arrayLength){
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + addCarry;
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    addCarry = tempNum / 10;
                }
                
                else {
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + h.hugeIntArray[(h.hugeIntArray.length)-i-1]; //we add the 2 digits from the 2 numbers
                    tempNum += addCarry; //we add the carry to the number that we calculated
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    addCarry = tempNum / 10;
                }
            }
            addAnswer.negativeMarker = false;
            addAnswer.truncate();
            return addAnswer;
        }
        
        else if(this.negativeMarker == false && h.negativeMarker == true){
            addAnswer.hugeIntArray = new int[this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength];//setting length of our answer's array.
            addAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength);
            
            //we need to flip numbers if h has more digits than the current number.
            if(this.absCompareTo(h)==1 || this.absCompareTo(h)==0){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=h.arrayLength){
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + addCarry;
                        
                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] - h.hugeIntArray[(h.hugeIntArray.length)-i-1];
                        tempNum += addCarry;

                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                addAnswer.negativeMarker = false;
                addAnswer.truncate();
                return addAnswer;

            }
            
            else if(this.absCompareTo(h)==-1){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=this.arrayLength){
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + addCarry;
                        
                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] - this.hugeIntArray[(this.hugeIntArray.length)-i-1];
                        tempNum += addCarry;

                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                addAnswer.negativeMarker = true;
                addAnswer.truncate();
                return addAnswer;

            }
        }
        
        else if(this.negativeMarker == true && h.negativeMarker == false){
            addAnswer.hugeIntArray = new int[this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength];//setting length of our answer's array.
            addAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength);
            
            //we need to flip numbers if h has more digits than the current number.
            if(this.absCompareTo(h)==1 || this.absCompareTo(h)==0){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=h.arrayLength){
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + addCarry;
                        
                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] - h.hugeIntArray[(h.hugeIntArray.length)-i-1];
                        tempNum += addCarry;

                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                addAnswer.negativeMarker = true;
                addAnswer.truncate();
                return addAnswer;
            }
            
            else if(this.absCompareTo(h)==-1){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=this.arrayLength){
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + addCarry;
                        
                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] - this.hugeIntArray[(this.hugeIntArray.length)-i-1];
                        tempNum += addCarry;

                        if(tempNum >= 10){
                            addCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            addCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            addCarry = 0;
                        }
                        addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
            }
            addAnswer.negativeMarker = false;
            addAnswer.truncate();
            return addAnswer;
        }
        
        else if(this.negativeMarker == true && h.negativeMarker == true){
            addAnswer.hugeIntArray = new int[(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1];//setting length of our answer's array.
            addAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1;
            
            for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1; i++){
                
                if(i>=this.arrayLength && i>=h.arrayLength){
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = 0 + addCarry;
                    addCarry = 0;
                }
                
                else if(i>=this.arrayLength){
                    tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + addCarry;
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    addCarry = tempNum / 10;
                }
                
                else if(i>=h.arrayLength){
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + addCarry;
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    addCarry = tempNum / 10;
                }
                
                else {
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + h.hugeIntArray[(h.hugeIntArray.length)-i-1]; //we add the 2 digits from the 2 numbers
                    tempNum += addCarry; //we add the carry to the number that we calculated
                    addAnswer.hugeIntArray[(addAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    addCarry = tempNum / 10;
                }
            }
            /*System.out.print("Negative? true; Number: ");
            for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1; i++){
                System.out.print(addAnswer.hugeIntArray[i]);
            }*/
            addAnswer.negativeMarker = true;
            addAnswer.truncate();
            return addAnswer;
        }
        addAnswer.truncate();
        return addAnswer;
    }
    
    public HugeInteger subtract(HugeInteger h){
        int subCarry = 0; //subCarry will store the number we have to carry for each subtraction
        int tempNum = 0;
        
        HugeInteger subAnswer = new HugeInteger(); //this is a variable of type HugeInteger that will store our answer
        
        //we will have to check if one, both, or none of the numbers are negative.
        if(this.negativeMarker == false && h.negativeMarker == false){
            subAnswer.hugeIntArray = new int[this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength];//setting length of our answer's array.
            subAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength);
            
            //we need to flip numbers if h has more digits than the current number.
            if(this.absCompareTo(h)==1 || this.absCompareTo(h)==0){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=h.arrayLength){
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + subCarry;
                        
                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] - h.hugeIntArray[(h.hugeIntArray.length)-i-1];
                        tempNum += subCarry;

                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                subAnswer.negativeMarker = false;
                subAnswer.truncate();
                return subAnswer;
            }
            
            else if(this.absCompareTo(h)==-1){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=this.arrayLength){
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + subCarry;
                        
                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] - this.hugeIntArray[(this.hugeIntArray.length)-i-1];
                        tempNum += subCarry;

                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                subAnswer.negativeMarker = true;
                subAnswer.truncate();
                return subAnswer;
            }
        }
        
        else if(this.negativeMarker == false && h.negativeMarker == true){
            subAnswer.hugeIntArray = new int[(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1];//setting length of our answer's array.
            subAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1;
            
            for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1; i++){
                
                if(i>=this.arrayLength && i>=h.arrayLength){
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = 0 + subCarry;
                    subCarry = 0;
                }
                
                else if(i>=this.arrayLength){
                    tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + subCarry;
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    subCarry = tempNum / 10;
                }
                
                else if(i>=h.arrayLength){
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + subCarry;
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    subCarry = tempNum / 10;
                }
                
                else {
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + h.hugeIntArray[(h.hugeIntArray.length)-i-1]; //we add the 2 digits from the 2 numbers
                    tempNum += subCarry; //we add the carry to the number that we calculated
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    subCarry = tempNum / 10;
                }
            }
            subAnswer.negativeMarker = false;
            subAnswer.truncate();
            return subAnswer;
        }
        
        else if(this.negativeMarker == true && h.negativeMarker == false){
            subAnswer.hugeIntArray = new int[(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1];//setting length of our answer's array.
            subAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1;
            
            for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength)+1; i++){
                
                if(i>=this.arrayLength && i>=h.arrayLength){
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = 0 + subCarry;
                    subCarry = 0;
                }
                
                else if(i>=this.arrayLength){
                    tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + subCarry;
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    subCarry = tempNum / 10;
                }
                
                else if(i>=h.arrayLength){
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + subCarry;
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    subCarry = tempNum / 10;
                }
                
                else {
                    tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + h.hugeIntArray[(h.hugeIntArray.length)-i-1]; //we add the 2 digits from the 2 numbers
                    tempNum += subCarry; //we add the carry to the number that we calculated
                    subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum % 10;
                    subCarry = tempNum / 10;
                }
            }
            subAnswer.negativeMarker = true;
            subAnswer.truncate();
            return subAnswer;
        }
        
        else if(this.negativeMarker == true && h.negativeMarker == true){
            subAnswer.hugeIntArray = new int[this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength];//setting length of our answer's array.
            subAnswer.arrayLength = (this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength);
            
            //we need to flip numbers if h has more digits than the current number.
            if(this.absCompareTo(h)==1 || this.absCompareTo(h)==0){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=h.arrayLength){
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] + subCarry;
                        
                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = this.hugeIntArray[(this.hugeIntArray.length)-i-1] - h.hugeIntArray[(h.hugeIntArray.length)-i-1];
                        tempNum += subCarry;

                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                subAnswer.negativeMarker = true;
                subAnswer.truncate();
                return subAnswer;
            }
            
            else if(this.absCompareTo(h)==-1){
                for(int i=0; i<(this.arrayLength>=h.arrayLength ? this.arrayLength : h.arrayLength); i++){
                    if(i>=this.arrayLength){
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] + subCarry;
                        
                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                    else{
                        tempNum = h.hugeIntArray[(h.hugeIntArray.length)-i-1] - this.hugeIntArray[(this.hugeIntArray.length)-i-1];
                        tempNum += subCarry;

                        if(tempNum >= 10){
                            subCarry = 1;
                            tempNum -= 10;
                        }
                        else if(tempNum < 0){
                            subCarry = -1;
                            tempNum += 10;
                        }
                        else{
                            subCarry = 0;
                        }
                        subAnswer.hugeIntArray[(subAnswer.hugeIntArray.length)-i-1] = tempNum;
                    }
                }
                subAnswer.negativeMarker = false;
                subAnswer.truncate();
                return subAnswer;
            }
        }
        subAnswer.truncate();
        return subAnswer;
    }
    
    public HugeInteger multiply(HugeInteger h){
        int mulCarry = 0;//will store the carry
        int tempNum = 0;//will store the number with operations being done on it
        
        HugeInteger mulAnswer = new HugeInteger(); //this will store our answer
        HugeInteger tempHugeInt = new HugeInteger(); //this will hold part of the answer until we can move it to mulAnswer
        HugeInteger thisFlipped = new HugeInteger();
        HugeInteger hFlipped = new HugeInteger();
        
        mulAnswer.hugeIntArray = new int[this.arrayLength + h.arrayLength]; //defining array for answer
        mulAnswer.arrayLength = this.arrayLength + h.arrayLength; //defining length of array
        tempHugeInt.hugeIntArray = new int[this.arrayLength + h.arrayLength]; //defining temp array
        tempHugeInt.arrayLength = this.arrayLength + h.arrayLength; //defining length of array
        thisFlipped.hugeIntArray = new int[this.arrayLength];
        thisFlipped.arrayLength = this.arrayLength;
        hFlipped.hugeIntArray = new int[h.arrayLength];
        hFlipped.arrayLength = h.arrayLength;
        
        for(int i=1; i<=this.hugeIntArray.length; i++){
            thisFlipped.hugeIntArray[i-1] = this.hugeIntArray[(this.arrayLength)-i];
        }
        
        for(int i=1; i<=h.hugeIntArray.length; i++){
            hFlipped.hugeIntArray[i-1] = h.hugeIntArray[(h.arrayLength)-i];
        }
        
        for(int j=0; j<h.arrayLength; j++){
            mulCarry = 0;
            for(int i=0; i<this.arrayLength; i++){
                tempNum = (thisFlipped.hugeIntArray[i] * hFlipped.hugeIntArray[j]) + tempHugeInt.hugeIntArray[i+j] + mulCarry;
                tempHugeInt.hugeIntArray[i+j] = tempNum % 10;
                mulCarry = tempNum/10;
            }
            tempHugeInt.hugeIntArray[j+this.arrayLength] = mulCarry;
        }
        
        for(int i=1; i<=tempHugeInt.hugeIntArray.length; i++){
            mulAnswer.hugeIntArray[i-1] = tempHugeInt.hugeIntArray[(tempHugeInt.arrayLength)-i];
        }
        
        //setting negative sign for answer
        if((this.negativeMarker == false && h.negativeMarker == false) || (this.negativeMarker == true && h.negativeMarker == true)){
            mulAnswer.negativeMarker = false;
        }
        else if((this.negativeMarker == false && h.negativeMarker == true) || (this.negativeMarker == true && h.negativeMarker == false)){
            mulAnswer.negativeMarker = true;
        }
        
        mulAnswer.truncate();
        return mulAnswer;
    }
    
    public HugeInteger divide(HugeInteger h){
        
        //first we will define a HugeInt that = 0 so we can return it when a smaller number is divided by a bigger number
        HugeInteger zeroAnswer = new HugeInteger("0");
        
        //we want to define a hugeInt that = 1 to return if we have to divide 2 equal numbers.
        HugeInteger oneAnswer = new HugeInteger("1");
        
        //if this is smaller than h and both are positive or negative.
        if((this.negativeMarker == false && h.negativeMarker == false) || (this.negativeMarker == true && h.negativeMarker == true)){
            if((this.absCompareTo(h))==-1){
                return zeroAnswer;
            }
            else if((this.absCompareTo(h))==0){
                oneAnswer.negativeMarker = false;
                return oneAnswer;
            }
        }
        
        else if((this.negativeMarker == false && h.negativeMarker == true) || (this.negativeMarker == true && h.negativeMarker == false)){
            if((this.absCompareTo(h))==-1){
                return zeroAnswer;
            }
            else if((this.absCompareTo(h))==0){
                oneAnswer.negativeMarker = true;
                return oneAnswer;
            }
        }
        
        //division by zero exception
        if((h.compareTo(zeroAnswer))==0){
            throw new ArithmeticException("You cannot divide by zero.");
        }
        
        //for all other cases:
        
        //numHugeInt will store the value of this that will be subtracted from to determine the answer: numerator
        HugeInteger numHugeInt = new HugeInteger(this.noPrintToString());
        numHugeInt.negativeMarker = false; //we just need the absolute value of this
        
        //denHugeInt will store the value of h that will be subtrascted from numHugeInt to determine the answer: denominator
        HugeInteger denHugeInt = new HugeInteger();
        denHugeInt = h;
        denHugeInt.negativeMarker = false; //we just need the absolute value of h
        
        //we will increment divAnswer by using the HugeInteger method add() to add oneAnswer to it so we need to make sure it's positive.
        oneAnswer.negativeMarker = false;
        
        HugeInteger divAnswer = new HugeInteger();
        divAnswer.hugeIntArray = new int[this.hugeIntArray.length];
        
        while(((numHugeInt.compareTo(denHugeInt))==1) || ((numHugeInt.compareTo(denHugeInt))==0)){
            numHugeInt = numHugeInt.subtract(denHugeInt);
            divAnswer = divAnswer.add(oneAnswer);
            numHugeInt.truncate();
        }
        
        //setting negative sign for answer

        if((this.negativeMarker == false && h.negativeMarker == false) || (this.negativeMarker == true && h.negativeMarker == true)){
            divAnswer.negativeMarker = false;
        }
        else if((this.negativeMarker == false && h.negativeMarker == true) || (this.negativeMarker == true && h.negativeMarker == false)){
            divAnswer.negativeMarker = true;
        }
        
        divAnswer.truncate();
        
        return divAnswer;
        
    }
    
    public int compareTo(HugeInteger h){
        
        //compareReturn will store the value of the int to be returned (1, 0, or -1). The initalization is to 404 because we want to know if an error occurs. Otherwise, the value is changed.
        int compareReturn = 404;
        
        if(this.negativeMarker == false && h.negativeMarker == false){
            if(this.hugeIntArray.length > h.hugeIntArray.length){
                compareReturn = 1;
                return compareReturn;
            }
            
            else if(this.hugeIntArray.length < h.hugeIntArray.length){
                compareReturn = -1;
                return compareReturn;
            }
            
            else if(this.hugeIntArray.length == h.hugeIntArray.length){
                for(int i=0; i<this.hugeIntArray.length; i++){
                    if(this.hugeIntArray[i] > h.hugeIntArray[i]){
                        compareReturn = 1;
                        return compareReturn;
                    }
                    else if(this.hugeIntArray[i] < h.hugeIntArray[i]){
                        compareReturn = -1;
                        return compareReturn;
                    }
                    else if(this.hugeIntArray[i] == h.hugeIntArray[i]){
                        compareReturn = 0;
                    }
                }
                return compareReturn;
            }
        }
        
        else if(this.negativeMarker == false && h.negativeMarker == true){
            compareReturn = 1;
            return compareReturn;
        }
        
        else if(this.negativeMarker == true && h.negativeMarker == false){
            compareReturn = -1;
            return compareReturn;
        }
        
        else if(this.negativeMarker == true && h.negativeMarker == true){
            if(this.hugeIntArray.length > h.hugeIntArray.length){
                compareReturn = -1;
                return compareReturn;
            }
            
            else if(this.hugeIntArray.length < h.hugeIntArray.length){
                compareReturn = 1;
                return compareReturn;
            }
            
            else if(this.hugeIntArray.length == h.hugeIntArray.length){
                for(int i=0; i<this.hugeIntArray.length; i++){
                    if(this.hugeIntArray[i] > h.hugeIntArray[i]){
                        compareReturn = -1;
                        return compareReturn;
                    }
                    else if(this.hugeIntArray[i] < h.hugeIntArray[i]){
                        compareReturn = 1;
                        return compareReturn;
                    }
                    else if(this.hugeIntArray[i] == h.hugeIntArray[i]){
                        compareReturn = 0;
                    }
                }
                return compareReturn;
            }
        }
        
        return compareReturn;
        
    }
    
    public int absCompareTo(HugeInteger h){
        
        //this method will compare the absoulte value of the 2 number. We will use this for division.
        
        int compareReturn = 404;//set to 404 so we know when an error occurs
        
        if(this.hugeIntArray.length > h.hugeIntArray.length){
                compareReturn = 1;
                return compareReturn;
            }
            
            else if(this.hugeIntArray.length < h.hugeIntArray.length){
                compareReturn = -1;
                return compareReturn;
            }
            
            else if(this.hugeIntArray.length == h.hugeIntArray.length){
                for(int i=0; i<this.hugeIntArray.length; i++){
                    if(this.hugeIntArray[i] > h.hugeIntArray[i]){
                        compareReturn = 1;
                        return compareReturn;
                    }
                    else if(this.hugeIntArray[i] < h.hugeIntArray[i]){
                        compareReturn = -1;
                        return compareReturn;
                    }
                    else if(this.hugeIntArray[i] == h.hugeIntArray[i]){
                        compareReturn = 0;
                    }
                }
                return compareReturn;
            }
        return compareReturn;
    }
    
    public String toString(){
        String hugeIntString="";
        //if the number is negative, we print a negative sign then loop through the array and print each number
        if (this.negativeMarker == true){
            hugeIntString+="-";
            for(int i=0; i<this.hugeIntArray.length; i++){
                    hugeIntString += Character.forDigit(this.hugeIntArray[i], 10);
                }
        }
        //if the number is positive, we loop through the array and print each number
        else if(this.negativeMarker == false){
            for(int i=0; i<this.hugeIntArray.length; i++){
                    hugeIntString += Character.forDigit(this.hugeIntArray[i], 10);
                }
        }
        System.out.println(hugeIntString);
        return hugeIntString;
    }
    
    public HugeInteger truncate(){
        int offset = 0;
        
        for (int a : this.hugeIntArray) {
                if (a != 0) {
                  break;
                }
                offset++;
            }
            int[] outputArray = new int[this.hugeIntArray.length - offset];
            for (int i = 0; i < outputArray.length; i++) {
                outputArray[i] = this.hugeIntArray[i + offset];
            }
            this.hugeIntArray = outputArray;
            
            this.arrayLength = this.hugeIntArray.length;
            
            return this;
    }
    
    public String noPrintToString(){
        String hugeIntString="";
        //if the number is negative, we print a negative sign then loop through the array and print each number
        if (this.negativeMarker == true){
            hugeIntString+="-";
            for(int i=0; i<this.hugeIntArray.length; i++){
                    hugeIntString += Character.forDigit(this.hugeIntArray[i], 10);
                }
        }
        //if the number is positive, we loop through the array and print each number
        else if(this.negativeMarker == false){
            for(int i=0; i<this.hugeIntArray.length; i++){
                    hugeIntString += Character.forDigit(this.hugeIntArray[i], 10);
                }
        }
        return hugeIntString;
    }
    
}
