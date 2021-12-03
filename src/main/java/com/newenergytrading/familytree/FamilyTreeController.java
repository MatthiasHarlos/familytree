package com.newenergytrading.familytree;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class FamilyTreeController {

    private static List<Human> humanList = new ArrayList<>();
    private static List<CountryForm> countryList = new ArrayList<>();
    private static List<String> genderColors = List.of("style='background-color:green'", "style='background-color:red'", "style='background-color:blue'");

    @GetMapping("/")
    public String inputForm(Model model) {
        model.addAttribute("humanBeanToSave", new HumanBean());
        model.addAttribute("humanList", humanList);
        model.addAttribute("countries", countryList);
        return "input-template";
    }

    @PostMapping("changing")
    public String changingTree (Human human) {
        humanList.get(human.getListNumber()).setLastName(human.getLastName());

        System.out.println(human);
        return "redirect:familyTree";
    }

    @PostMapping("saveHumanBean")
    public String saveHuman(@Valid @ModelAttribute("humanBeanToSave") HumanBean humanBeanToSave, BindingResult bindingResult, Model model) {
        //validate(humanBeanToSave, bindingResult);
        model.addAttribute("countries", countryList);
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("humanBeanToSave", humanBeanToSave);
            return "input-template";
        }

        model.addAttribute("humanBeanToSave", new HumanBean());

        Human humanToSave = new Human(humanBeanToSave.getAge(), humanBeanToSave.getFirstName(), humanBeanToSave.getLastName());
        if (humanBeanToSave.getMotherIndex() != null) {
            humanToSave.setMother(humanList.get(humanBeanToSave.getMotherIndex()));
        }
        if (humanBeanToSave.getFatherIndex() != null) {
            humanToSave.setFather(humanList.get(humanBeanToSave.getFatherIndex()));
        }
        if (humanBeanToSave.getSiblingsIndex() != null) {
            for (int i = 0; i < humanBeanToSave.getSiblingsIndex().size(); i++) {
                System.out.println(i);
                humanToSave.getSiblings().add(humanList.get(humanBeanToSave.getSiblingsIndex().get(i)));
            }
        }
        if (humanBeanToSave.getCountry() != null) {
            humanToSave.setCountry(countryList.get(humanBeanToSave.getCountry()));
        }
        if (humanBeanToSave.getGender() != null) {
            humanToSave.setGender(genderColors.get(humanBeanToSave.getGender()));
        }

        humanList.add(humanToSave);
        humanToSave.setListNumber(humanList.indexOf(humanToSave));
        System.out.println(humanList);
        model.addAttribute("humanList", humanList);
        return "input-template";
    }

   private void validate(HumanBean humanBean, BindingResult bindingResult) {
        Objects.requireNonNull(bindingResult);
        if (bindingResult.hasFieldErrors("country")) {
            try {
                new HumanBean(humanBean.getCountry());
            } catch (IllegalArgumentException e) {
               bindingResult.rejectValue("country", "invalid.country", "Lege ein Land an!");
            }
        }
    }

    @GetMapping("familyTree")
    public String familyTreeOutput(Model model) {
        if (humanList.size() < 1) {
            return "redirect:/";
        }
        model.addAttribute("familyTree", humanList.get(humanList.size()-1).getFamilyTree());
        model.addAttribute("popUpTree", humanList.get(humanList.size()-1).getInfoPopUp());
        model.addAttribute("scriptPopUp", humanList.get(humanList.size()-1).getScript());
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
