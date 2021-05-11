package com.example.demo.Controller;

import com.example.demo.Model.PartyModel;
import com.example.demo.Model.ReformaModel;
import com.example.demo.Repository.PartyRepository;
import com.example.demo.Repository.ReformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Optional;

@EnableSwagger2
@Controller
public class MainController {

    @Autowired
    private ReformaRepository reformaRepository;

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping("/")
    public String home(Model model){
        Iterable<PartyModel> reforma = partyRepository.findAll();
        model.addAttribute("reforma",reforma);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        Iterable<ReformaModel> reforma = reformaRepository.findAll();
        model.addAttribute("reforma",reforma);
        return "about";
    }

    @GetMapping("/composition")
    public String composition(Model model){
        Iterable<ReformaModel> reforma = reformaRepository.findAll();
        model.addAttribute("reforma",reforma);
        return "composition";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/reforma-add")
    public String addGET(){
        return "reforma-add";
    }

    @PostMapping("/reforma-add")
    public String addPOST(@RequestParam String name, @RequestParam String info, @RequestParam String job, @RequestParam String url, Model model){
        ReformaModel reforma = new ReformaModel(name,info,job,url);
        reformaRepository.save(reforma);
        return "redirect:/";
    }

    @GetMapping("/reforma-add-party")
    public String addPartyGET(){
        return "reforma-add-party";
    }

    @PostMapping("/reforma-add-party")
    public String addPartyPOST(@RequestParam String title, @RequestParam String info, @RequestParam String url,Model model){
        PartyModel reforma = new PartyModel(title,info,url);
        partyRepository.save(reforma);
        return "redirect:/";
    }

    @GetMapping("/person/{id}")
    public String reformaDetail(@PathVariable(value = "id") long id, Model model){
        if(!reformaRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<ReformaModel> reforma = reformaRepository.findById(id);
        ArrayList<ReformaModel> res = new ArrayList<>();
        reforma.ifPresent(res::add);
        model.addAttribute("reforma",res);
        return "reforma-detail";
    }

    @PostMapping("/person/{id}/delete")
    public String reformaDelete(@PathVariable(value = "id") long id){
        ReformaModel reforma = reformaRepository.findById(id).orElseThrow();
        reformaRepository.delete(reforma);
        return "redirect:/";
    }

    @GetMapping("/person/{id}/edit")
    public String updateGet(@PathVariable(value = "id") long id, Model model) {
        if(!reformaRepository.existsById(id)) {
            return "redirect:/";
        }

        Optional<ReformaModel> reforma = reformaRepository.findById(id);
        ArrayList<ReformaModel> res = new ArrayList<>();
        reforma.ifPresent(res::add);
        model.addAttribute("reforma", res);
        return "reforma-update";
    }

    @PostMapping("/person/{id}/edit")
    public  String updatePost(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String info, @RequestParam String url, Model model){
        ReformaModel person = reformaRepository.findById(id).orElseThrow();
        person.setName(name);
        person.setInformation(info);
        person.setImage(url);
        reformaRepository.save(person);
        return  "redirect:/";

    }
}
