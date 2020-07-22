/* *****************************************************************************
    Implementation of Two Stack Algorithm by E.W Dijkstra..

    Input: A simple add/multiply expression with brackets.
    E.g ( 4 + ( 5 * 8 ) ) * 9

    What to do when you encounter any of these in an expression.
    1. value: push onto value stack
    2. operator: push onto the operator stack
    3. Left parenthesis: ignore
    4. Right parenthesis: pop operator and two values, push the result of applying that operator to those
    values onto the spread stack.
 **************************************************************************** */


public class TwoStackAlgorithm {

    public static void main(String[] args) {
        GenericStack<Integer> values = new GenericStack<Integer>();
        GenericStack<Character> operators = new GenericStack<Character>();

        String expression = args[0];

        // Iterating through the string..
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') ;
            else if (expression.charAt(i) == ')') {
                int firstVal = values.pop();
                int secondVal = values.pop();
                char operator = operators.pop();

                if (operator == '+') {
                    values.push(firstVal + secondVal);
                }
                else if (operator == '*') {
                    values.push(firstVal * secondVal);
                }
            }
            else if (expression.charAt(i) == '+' || expression.charAt(i) == '*') {
                operators.push(expression.charAt(i));
            }
            else {
                values.push(Integer.parseInt(String.valueOf(expression.charAt(i))));
            }
        }
        //Print the answer of the expression enclosed within brackets
        System.out.println(values.pop());
    }
}
