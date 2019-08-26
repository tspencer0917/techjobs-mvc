package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="results")
    public String searchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<HashMap<String, String>> searchResults = new ArrayList<>();
        if(searchType.equals("all")){
            searchResults = JobData.findByValue(searchTerm);
        } else {
            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("resultCountMessage", searchResults.size() + " Result(s)");
        model.addAttribute("jobs", searchResults);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

}
