
public class Main {
    public static void main(String[] args) {
        System.out.println(calc("9 / 3"));
    }


    //
    static Integer parseToArabic(String s){
        int result;
        try{
            result = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return null;
        }
        if (result>10||result<(-10)) return null;
        return result;
    }

    static Integer parseToRoman(String s) {
        if (s.toCharArray().length > 4) return null;
        switch (s) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: return null;
        }
    } 
    public static String calc(String input){

        class IncorrectNumbers extends Exception{
            static {
                System.out.println("Incorrect amount/type of numbers/operands.");
            }
        }

        try {
            String[] array = input.split(" ");
            if (array.length != 3) {
                throw new IncorrectNumbers();
            }
            boolean firstNumberIsRoman = false;
            boolean secondNumberIsRoman = false;
            Integer firstNumber = parseToRoman(array[0]);
            if(firstNumber==null) firstNumber = parseToArabic(array[0]); else firstNumberIsRoman = true;
            Integer secondNumber = parseToRoman(array[2]);
            if(secondNumber==null) secondNumber = parseToArabic(array[2]); else secondNumberIsRoman = true;
            if(firstNumber==null
                ||secondNumber==null
                ||(firstNumberIsRoman&&!secondNumberIsRoman)
                ||(!firstNumberIsRoman&&secondNumberIsRoman))
                throw new IncorrectNumbers();
            int result;
            char operand = array[1].charAt(0);
            switch (operand){
                case '/': result = firstNumber/secondNumber; break;
                case '*': result = firstNumber*secondNumber; break;
                case '+': result = firstNumber+secondNumber;  break;
                case '-': result = firstNumber-secondNumber; break;
                default : throw new IncorrectNumbers();
            }
            if(firstNumberIsRoman&&secondNumberIsRoman&&result<0) throw new IncorrectNumbers();
            else if (firstNumberIsRoman&&secondNumberIsRoman) {
                if(result==0) return null;
                String[] romanLetters = {"C", "XC", "L",  "X", "IX", "V", "IV", "I" };
                int[] romanValues = {100,90,50,10,9,5,4,1};
                StringBuilder romanResult = new StringBuilder();
                for (int i = 0; i < romanValues.length; i ++) {
                    int currentNum = result/romanValues[i];
                    if (currentNum==0) {
                        continue;
                    }
                    for (int j = 0; j < currentNum; j++) {
                        romanResult.append(romanLetters[i]);
                    }
                    result %= romanValues[i];
                }
                return romanResult.toString();
            }
            return String.valueOf(result);

        }catch (IncorrectNumbers e){
            return null;
        }


    }
    }
