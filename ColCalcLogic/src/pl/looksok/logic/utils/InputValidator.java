package pl.looksok.logic.utils;

import java.util.HashMap;
import java.util.List;

import pl.looksok.logic.CalculationType;
import pl.looksok.logic.PersonData;
import pl.looksok.logic.exceptions.BadInputDataException;
import pl.looksok.logic.exceptions.DuplicatePersonNameException;

public class InputValidator{
		
	public static HashMap<String, PersonData> convertAndValidateInput(List<PersonData> inputPaysList, CalculationType calculationType) 
				throws BadInputDataException, DuplicatePersonNameException{
		
		HashMap<String, PersonData> inputPays = new HashMap<String, PersonData>();
		double sumOfAllPays = 0.0;
		double sumOfAllShouldPays = 0.0;
		
		for (PersonData in : inputPaysList) {
			if(inputPays.containsKey(in.getName()))
				throw new DuplicatePersonNameException(in.getName());
			else{
				inputPays.put(in.getName(), in);
				sumOfAllPays += in.getHowMuchPersonPaid();
				sumOfAllShouldPays += in.getHowMuchPersonShouldPay();
			}
		}
		
		if(calculationType.equals(CalculationType.RESTAURANT)){
			if(sumOfAllPays != sumOfAllShouldPays){
				throw new BadInputDataException("Sum of all Pays made by persons ("+sumOfAllPays+") is not equal to sum of amount that they should pay(" +sumOfAllShouldPays+")");
			}
		}
		return inputPays;
	}
}
