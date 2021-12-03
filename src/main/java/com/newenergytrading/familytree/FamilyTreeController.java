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

    private static List<String> errors = new ArrayList<>();
    private static Human initialGeneration;
    private static Integer initialNumber;
    private static List<Human> humanList = new ArrayList<>();
    private static List<CountryForm> countryList = new ArrayList<>();
    private static List<String> genderColors = List.of("style='background-color:green'", "style='background-color:red'", "style='background-color:blue'");

    @GetMapping("/")
    public String inputForm(Model model) {
        model.addAttribute("humanBeanToSave", new HumanBean());
        model.addAttribute("humanList", humanList);
        model.addAttribute("countries", countryList);
        model.addAttribute("initialGeneration", initialNumber);
        return "input-template";
    }

    @PostMapping("changing")
    public String changingTree (Model model,@Valid @ModelAttribute("humanBeanToSave") HumanBean humanBean, BindingResult bindingResult) {
        errors = new ArrayList<>();
        getErrorCode(humanBean, bindingResult);
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", countryList);
            model.addAttribute("genderColors", genderColors);
            model.addAttribute("failing", errors);
            return "output-template";
        }
        Human human = humanList.get(humanBean.getListNumber());
        human.setFirstName(humanBean.getFirstName());
        human.setLastName(humanBean.getLastName());

        if (humanBean.getMotherIndex() != null && humanBean.getMotherIndex() != humanBean.getListNumber() && humanList.get(humanBean.getMotherIndex()).isParent() == null) {
                human.setMother(humanList.get(humanBean.getMotherIndex()));
            humanList.get(humanBean.getMotherIndex()).setParent("parent");
        }
        if (humanBean.getFatherIndex() != null && humanBean.getFatherIndex() != humanBean.getListNumber() && humanList.get(humanBean.getFatherIndex()).isParent() == null) {
            human.setFather(humanList.get(humanBean.getFatherIndex()));
            humanList.get(humanBean.getFatherIndex()).setParent("parent");
        }
        if (humanBean.getSiblingsIndex() != null) {
            for (int i = 0; i < humanBean.getSiblingsIndex().size(); i++) {
                System.out.println(i);
                human.getSiblings().add(humanList.get(humanBean.getSiblingsIndex().get(i)));
            }
        }
        human.setGender(genderColors.get(humanBean.getGender()));
        human.setAge(humanBean.getAge());
        humanList.set(humanBean.getListNumber(), human);
        System.out.println(humanBean);
        return "redirect:familyTree";
    }

    private void getErrorCode(HumanBean humanBean, BindingResult bindingResult) {
        String href = "http://localhost:8080/familyTree/";
        if ( humanBean.getMotherIndex() != null && humanList.get(humanBean.getMotherIndex()).isParent() != null && humanBean.getMotherIndex() == humanBean.getListNumber() && humanList.get(humanBean.getMotherIndex()).isParent().equals("parent")  ||
                humanBean.getMotherIndex() != null && humanList.get(humanBean.getMotherIndex()).isParent() != null  && humanList.get(humanBean.getMotherIndex()).isParent().equals("parent") ||
                humanBean.getMotherIndex() != null && humanBean.getMotherIndex() == (humanList.size()-1)) {
            if (!bindingResult.hasFieldErrors("motherIndex")) {
                try {
                    new HumanBean(null);
                } catch (IllegalArgumentException e) {
                    bindingResult.rejectValue("motherIndex", "invalid.motherIndex", "Kann nicht gewählt werden!");
                    errors.add("\"falsche Mutter <a href='" + href +  "'>zurück</a>\"");
                }
            }
        }
        if (humanBean.getFatherIndex() != null && humanList.get(humanBean.getFatherIndex()).isParent() != null && humanBean.getFatherIndex() == humanBean.getListNumber() && humanList.get(humanBean.getFatherIndex()).isParent().equals("parent")||
                humanBean.getFatherIndex() != null && humanList.get(humanBean.getFatherIndex()).isParent() != null && humanList.get(humanBean.getFatherIndex()).isParent().equals("parent") ||
                humanBean.getFatherIndex() != null && humanBean.getFatherIndex() == (humanList.size()-1)) {
            if (!bindingResult.hasFieldErrors("fatherIndex")) {
                try {
                    new HumanBean(null);
                } catch (IllegalArgumentException e) {
                    bindingResult.rejectValue("fatherIndex", "invalid.fatherIndex", "Kann nicht gewählt werden!");
                    errors.add("\"falscher Vater <a href='" + href +  "'>zurück</a>\"");
                }
            }
        }
        if (bindingResult.hasFieldErrors("age")) {
            errors.add("\"Alter darf nicht leer sein <a href='" + href +  "'>zurück</a>\"");
        }
    }

    @PostMapping("saveHumanBean")
    public String saveHuman(@Valid @ModelAttribute("humanBeanToSave") HumanBean humanBeanToSave, BindingResult bindingResult, Model model) {
        //validate(humanBeanToSave, bindingResult);
        model.addAttribute("countries", countryList);
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("humanList", humanList);
            model.addAttribute("humanBeanToSave", humanBeanToSave);
            return "input-template";
        }

        model.addAttribute("humanBeanToSave", new HumanBean());

        Human humanToSave = new Human(humanBeanToSave.getAge(), humanBeanToSave.getFirstName(), humanBeanToSave.getLastName());
        if (humanBeanToSave.getMotherIndex() != null) {
            humanToSave.setMother(humanList.get(humanBeanToSave.getMotherIndex()));
            humanList.get(humanBeanToSave.getMotherIndex()).setParent("parent");
        }
        if (humanBeanToSave.getFatherIndex() != null) {
            humanToSave.setFather(humanList.get(humanBeanToSave.getFatherIndex()));
            humanList.get(humanBeanToSave.getMotherIndex()).setParent("parent");
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

        model.addAttribute("initialGeneration", initialNumber);

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

    @PostMapping("familyTree")
    public String familyTreeOutput(Model model, HumanBean getGenerationStart) {
        if(getGenerationStart.getInitialGeneration() != null) {
            initialGeneration = humanList.get(getGenerationStart.getInitialGeneration());
            initialNumber = getGenerationStart.getInitialGeneration();
            model.addAttribute("initialGeneration", getGenerationStart.getInitialGeneration());
        } else {
            initialGeneration = null;
            initialNumber = null;
        }


        if (humanList.size() < 1) {
            return "redirect:/";
        }
        errors = new ArrayList<>();
        model.addAttribute("genderColors", genderColors);
        model.addAttribute("countries", countryList);
        model.addAttribute("failing", errors);
        model.addAttribute("humanBeanToSave", new HumanBean());
        if (initialGeneration == null) {
            model.addAttribute("familyTree", humanList.get(humanList.size() - 1).getFamilyTree());
            model.addAttribute("scriptPopUp", humanList.get(humanList.size() - 1).getScript());
        } else {
            model.addAttribute("familyTree", initialGeneration.getFamilyTree());
            model.addAttribute("scriptPopUp", initialGeneration.getScript());
        }
        model.addAttribute("humanList", humanList);
        return "output-template";
    }

    @GetMapping("familyTree")
    public String familyTree(Model model) {
        if (humanList.size() < 1) {
            return "redirect:/";
        }
        errors = new ArrayList<>();
        model.addAttribute("genderColors", genderColors);
        model.addAttribute("countries", countryList);
        model.addAttribute("failing", errors);
        model.addAttribute("humanBeanToSave", new HumanBean());
        if (initialGeneration == null) {
            model.addAttribute("familyTree", humanList.get(humanList.size() - 1).getFamilyTree());
            model.addAttribute("scriptPopUp", humanList.get(humanList.size() - 1).getScript());
        } else {
            model.addAttribute("familyTree", initialGeneration.getFamilyTree());
            model.addAttribute("scriptPopUp", initialGeneration.getScript());
        }
        model.addAttribute("humanList", humanList);
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
