package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;


@Controller
@RequestMapping(value = "/meals")
public class MealController extends AbstractMealRestController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
       model.addAttribute("meals", super.getAll());
       return "meals";
    }

    @RequestMapping(params = "action=delete",method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:meals";
    }

    @RequestMapping(params = "action=create",method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @RequestMapping(params = "action=update",method = RequestMethod.GET)
    public String update(Model model, HttpServletRequest request){
        model.addAttribute("meal", super.get(getId(request)));
        return "mealForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUpdate(HttpServletRequest request) {

        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            super.create(meal);
        } else {
           super.update(meal, getId(request));
        }
        return "redirect:meals";
    }

    @RequestMapping(params = "action=filter", method = RequestMethod.POST)
    public String filter(Model model, HttpServletRequest request){
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
