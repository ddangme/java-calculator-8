package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    private int calculate(String input) {
        List<Integer> numbers = getNumbers(input);

        for (Integer number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException();
            }
        }
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> getNumbers(String input) {
        if (input.contains("//")) {
            if (input.contains("\\n")) {
                String delimiter = input.split("\\\\n")[0].substring(2);
                String resultInput = input.split("\\\\n", 2)[1];

                System.out.println(delimiter);
                System.out.println(resultInput);

                return Arrays.stream(resultInput.split("[" + delimiter + "]"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException();
            }
        }

        return Arrays.stream(input.split("[,:]"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
