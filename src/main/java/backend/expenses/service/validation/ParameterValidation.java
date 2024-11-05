package backend.expenses.service.validation;

import java.util.Optional;

public class ParameterValidation {


    public static boolean isValidParameter(Optional<Integer> countParameter) {


        return countParameter.isPresent() && countParameter.get() > 0;

    }


}
