package Stack;

import java.util.Stack;
import java.util.regex.Pattern;

public class RPNmethod {
    private static Stack<String> charStack = new Stack<>();
    private static Stack<Integer> intStack = new Stack<>();

    public static int RPN(String exp){
        String behindEXP = convert(exp);
        String[] chars = behindEXP.split(" ");
        for (int i = 0; i < chars.length; i++){
            String thisOne = chars[i];
            if (isNum(thisOne)){
                intStack.push(Integer.parseInt(thisOne));
            }else if (isSymbol(thisOne)){
                int a = intStack.pop();
                int b = intStack.pop();
                int result;
                switch (thisOne){
                    case "+": result = b + a;
                        intStack.push(result);
                        break;
                    case "-": result = b - a;
                        intStack.push(result);
                        break;
                    case "*": result = b * a;
                        intStack.push(result);
                        break;
                    case "/": result = b / a;
                        intStack.push(result);
                        break;
                }
            }
        }
        return intStack.pop();
    }

    /**
     * 将中缀表达式转为后缀表达式
     * @param middle
     * @return
     */
    private static String convert(String middle){
        String rpnEXP = "";
        String[] chars = middle.split(" ");
        for(int i = 0; i < chars.length; i++){
            String thisOne = chars[i];
            if (isNum(thisOne)){
                rpnEXP = rpnEXP + thisOne + " ";
            }else if (isSymbol(thisOne)) {
                /*
                三个分支 ： 是左括号，是右括号，不是括号
                 */
                if (isLeftPar(thisOne)) {
                    charStack.push(thisOne);
                } else if (isRightPar(thisOne)) {//Here
                    while (!isLeftPar(thisOne)) {
                        thisOne = charStack.pop();
                        if (!isLeftPar(thisOne)) {
                            rpnEXP = rpnEXP + thisOne + " ";
                        }
                    }
                } else {
                    if (charStack.isEmpty() || !lowerPriority(thisOne, charStack.peek())) {
                        charStack.push(thisOne);
                    } else {
                        do {
                            rpnEXP = rpnEXP + charStack.pop() + " ";
                        }
                        while (!charStack.isEmpty() && lowerPriority(thisOne, charStack.peek()));
                        charStack.push(thisOne);
                    }
                }
            }
        }
        while(!charStack.isEmpty()){
            rpnEXP = rpnEXP + charStack.pop() + " ";
        }
        return rpnEXP;
    }

    private static boolean isLeftPar(String aChar) {
        return aChar.equals("(");
    }

    /**
     * 返回新来的优先级是不是不大于栈顶.aChar > peek 就返回false，peek >= aChar 就返回true
     * @param aChar
     * @param peek
     * @return 返回true就弹栈，返回false就将新来的压栈
     */
    private static boolean lowerPriority(String aChar, String peek) {
        if(peek.equals("(")){
            return false;
        }
        else if (aChar.equals("+") || aChar.equals("-")){
            return true;
        }else {
            if (peek.equals("*") || peek.equals("/")){
              return true;
            }else {
                return false;
            }
        }
    }

    private static boolean isRightPar(String c) {
        return c.equals(")");
    }

    private static boolean isNum(String c){
        Pattern pattern = Pattern.compile("[\\d]*$");
        return pattern.matcher(c).matches();
    }

    private static  boolean isSymbol(String c){
        String pattern = "[-/*+()]";
        return Pattern.matches(pattern,c) ;
    }
}
