package com.app.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.vo.ResultVO;


@RestController
public class HomeWebController {
	
	private final Logger logger = LoggerFactory.getLogger(HomeWebController.class);

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView testView() {
		logger.debug("Inside home Controller!");
		System.out.println("In home");
		ModelAndView model = new ModelAndView();
		model.setViewName("Home");
		return model;
	}
	
    @RequestMapping(value = "/lettercombinations/{phNo}/{pageno}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody    
    public ResponseEntity ostrichparticipantList(@PathVariable("phNo") String phNo, @PathVariable("pageno") int pageno)
    {
    	
    	try
    	{
    	List<String> strlist=combinations(phNo);
		ResultVO result=new ResultVO();
		int startInex=(pageno-1)*result.getRecordsPerPage();
		int endIndex=pageno*result.getRecordsPerPage()>strlist.size()?strlist.size():pageno*result.getRecordsPerPage();
		List<String> sublist = strlist.subList(startInex, endIndex );
		result.setList(sublist);
		result.setTotalRecords(strlist.size());
		result.setCurrentPage(pageno);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    	}
    	catch(Exception e) {
        	return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);

    	}
    }

    // This code goes under Service layer.
    public static List<String> combinations(String digits) {
	    HashMap<Character, char[]> dictionary = new HashMap<Character, char[]>();
	    dictionary.put('1',new char[]{'1'});
	    dictionary.put('2',new char[]{'2','a','b','c'});
	    dictionary.put('3',new char[]{'3','d','e','f'});
	    dictionary.put('4',new char[]{'4','g','h','i'});
	    dictionary.put('5',new char[]{'5','j','k','l'});
	    dictionary.put('6',new char[]{'6','m','n','o'});
	    dictionary.put('7',new char[]{'7','p','q','r','s'});
	    dictionary.put('8',new char[]{'8','t','u','v'});
	    dictionary.put('9',new char[]{'9','w','x','y','z'});
	    dictionary.put('0',new char[]{'0'});
	    List<String> result = new ArrayList<String>();
	    if(digits==null||digits.length()==0){
	        return result;
	    }
	    char[] arr = new char[digits.length()];
	    getCombinations(digits, 0, dictionary, result, arr);
	    return result;
	}
	 
	private static void getCombinations(String digits, int index, HashMap<Character, char[]> dictionary, 
	                        List<String> result, char[] arr){
	    if(index==digits.length()){
	        result.add(new String(arr));
	        return;
	    }
	    char number = digits.charAt(index);
	    char[] candidates = dictionary.get(number);
	    for(int i=0; i<candidates.length; i++){
	        arr[index]=candidates[i];
	        getCombinations(digits, index+1, dictionary, result, arr);
	    }
	}
	
}