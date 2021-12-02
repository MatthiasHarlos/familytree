package com.newenergytrading.familytree;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FamilyTreeController {

    private static List<Human> humanList = new ArrayList<>();
    private static List<CountryForm> countryList = new ArrayList<>();

    @GetMapping("/")
    public String inputForm(Model model) {
        model.addAttribute("humanBeanToSave", new HumanBean());
        model.addAttribute("humanList", humanList);
        model.addAttribute("countries", countryList);
        return "input-template";
    }

    @PostMapping("saveHumanBean")
    public String saveHuman(Model model, HumanBean humanBean) {
        model.addAttribute("humanBeanToSave", new HumanBean());
        Human humanToSave = new Human(humanBean.getAge(), humanBean.getFirstName(), humanBean.getLastName());
        if (humanBean.getMotherIndex() != null) {
            humanToSave.setMother(humanList.get(humanBean.getMotherIndex()));
        }
        if (humanBean.getFatherIndex() != null) {
            humanToSave.setFather(humanList.get(humanBean.getFatherIndex()));
        }
        if (humanBean.getSiblingsIndex() != null) {
            for (int i = 0; i < humanBean.getSiblingsIndex().size(); i++) {
                System.out.println(i);
                humanToSave.getSiblings().add(humanList.get(humanBean.getSiblingsIndex().get(i)));
            }
        }
        if (humanBean.getCountry() != null) {
            humanToSave.setCountry(countryList.get(humanBean.getCountry()));
        }
        model.addAttribute("countries", countryList);
        humanList.add(humanToSave);
        System.out.println(humanList);
        model.addAttribute("humanList", humanList);
        return "input-template";
    }

    @GetMapping("familyTree")
    public String familyTreeOutput(Model model) {
        model.addAttribute("familyTree", humanList.get(humanList.size()-1).getFamilyTree());
        return "output-template";
    }

    @GetMapping("saveCountry")
    public String getCountrySave(Model model) {
        model.addAttribute("countryToSave", new CountryForm());
        return "countryForm";
    }

    @PostMapping("saveCountry")
    public String saveCountry(Model model, CountryForm countryForm) {
        countryList.add(countryForm);
        System.out.println(countryForm);
        model.addAttribute("countryToSave", new CountryForm());
        return "countryForm";
    }
}
