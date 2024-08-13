package Stack.BasicCalculator.java;
// lintcode 849
// Step 1: convert String s into expression                
// "1 + 12 * (1 + 3) / 4 - 10" ->  [1, +,  12, *, (, 1, +, 3, ), /, 4, -, 10]
// Step 2: 中缀表达式, stack, increasing based on priority 
//                                            i 
// [1, +,  12, *, (, 1, +, 3, ), /, 4, -, 10, #]
// stack = [#]
// nums = [1, 12, 1, 3, +, *, 4, /, +, 10, -]

// Step3: 后缀表达式
//                                            i 
// nums = [1, 12, 1, 3, +, *, 4, /, +, 10, -]
// stack = [3]

public class VersionThree {
    Map<String, Integer> PRIORITY = Map.of(
        "#", 0, // ending operator 
        "(", 1,
        "+", 2,
        "-", 2,
        "*", 3,
        "/", 3
    );

    public int calculate(String s) {
        // getExpression converts String of s into a list separated with 
        // numbers and operators, for example, string of "16+12" will be separated 
        // into ["16","+","12"] 
        List<String> expression = getExpression(s);
        // System.out.println(expression);
        return prioritizeExpression(expression);
    }

    private int prioritizeExpression(List<String> expression) {
        Stack<String> stack = new Stack<>(); // use to prioritize ongoing operators 
        List<String> nums = new ArrayList<>(); // used to store numbers and their operators in String
        expression.add("#"); // used to handle when i is at the end of expression and stack is not empty 
        for (int i = 0; i < expression.size(); i++) {
            String c = expression.get(i);
            if (c.equals("(")) {
                stack.push(c);
            } else if (c.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    // add all operators to queue and -1 to nums 
                    nums.add(stack.pop());
                }
                // remove the "(" at the end of stack 
                stack.pop();
            } else if (c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-") || c.equals("#")) {
                // check for priorities of operator 
                while (!stack.isEmpty() && PRIORITY.get(stack.peek()) >= PRIORITY.get(c)) {
                    // operator with priority need to be computed first 
                    nums.add(stack.pop());
                }
                stack.push(c);
            } else {
                nums.add(c);
            }
        }
        
        return computeResult(nums);
    }

    private int computeResult(List<String> nums) {
        Stack<Integer> stack = new Stack<>(); // use for computating result 
        for (String c : nums) {
            if (c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(calc(num1, num2, c));
            } else {
                stack.push(Integer.valueOf(c));
            }
        }
        return stack.peek();
    }

    private int calc(int n1, int n2, String c) {
        if (c.equals("+")) {
            return n1 + n2;
        }
        if (c.equals("-")) {
            return n2 - n1;
        }
        if (c.equals("*")) {
            return n1 * n2;
        }
        return n2 / n1;
    }

    private List<String> getExpression(String s) {
        List<String> expression = new ArrayList<>();
        int val = 0;
        boolean numIsNull = true;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c == '+' || c == '-' || c == '(' || c == ')' || c == '*' || c == '/') {
                // if there is a previous number add it first 
                if (!numIsNull) {
                    expression.add(String.valueOf(val));
                }
                val = 0;
                expression.add("" + c); // convert char to string
                numIsNull = true;
            } else {
                if (numIsNull) {
                    numIsNull = false;
                    val = 0;
                }
                val = val * 10 + (c - '0');
            }
        }
        if (!numIsNull) {
            expression.add(String.valueOf(val));
        }
        return expression;
    }
}
